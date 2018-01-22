package com.bankaccount.service;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.bankaccount.DataFactory;
import com.bankaccount.persistence.entities.Account;
import com.bankaccount.persistence.entities.Transaction;
import com.bankaccount.persistence.repos.AccountRepository;
import com.bankaccount.service.account.AccountService;
import com.bankaccount.service.exception.InsufficientFundsException;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class AccountServiceTests {
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private AccountService accountService;

	@Autowired
	private DataFactory dataFactory;
	
	@Test
	@DirtiesContext
	public void executeAccountWithdrawTransaction_thenAcountBalanceUpdated() throws InsufficientFundsException {
		// 1. Arrange 
		Optional<Account> optionalAccount =  accountRepository.findById(10000L);
		Account account = optionalAccount.get();
		
		BigDecimal currentBalanceBeforTransaction = account.getCurrentBalance();
		// Withdraw transaction
		Transaction transaction = dataFactory.getWithDrawTransaction();
		// Expected expectedBalance : 100.00 - 10.00 = 90.00
		BigDecimal expectedBalance = currentBalanceBeforTransaction.subtract(transaction.getAmount());
		
		

		// 2. Act
		accountService.executeAccountTransaction(account, transaction);
		
		// 3. Assert
		Optional<Account> optionalAccountAfterTransaction =  accountRepository.findById(10000L);
		Account accountAfterTransaction = optionalAccountAfterTransaction.get();
		
		assertEquals(expectedBalance, accountAfterTransaction.getCurrentBalance());
	
	}
	
	
	@Test
	@DirtiesContext
	public void executeAccountDepositTransaction_thenAcountBalanceUpdated() throws InsufficientFundsException {
		// 1. Arrange
		Optional<Account> optionalAccount =  accountRepository.findById(10000L);
		Account account = optionalAccount.get();
		// CurrentBalance before transaction
		BigDecimal currentBalanceBeforTransaction = account.getCurrentBalance();
		// Deposit transaction
		Transaction transaction = dataFactory.getDepositTransaction();
		// Expected expectedBalance : 100.00 + 10.00 = 110.00
		BigDecimal expectedBalance = currentBalanceBeforTransaction.add(transaction.getAmount());
		
		// 2. Act
		accountService.executeAccountTransaction(account, transaction);
		
		// 3. Assert
		Optional<Account> optionalAccountAfterTransaction =  accountRepository.findById(10000L);
		Account accountAfterTransaction = optionalAccountAfterTransaction.get();
		
		assertEquals(expectedBalance, accountAfterTransaction.getCurrentBalance());
	
	}
	
	
}
