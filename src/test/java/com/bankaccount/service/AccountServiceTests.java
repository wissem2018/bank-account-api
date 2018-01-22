package com.bankaccount.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.bankaccount.persistence.entities.Account;
import com.bankaccount.persistence.entities.Transaction;
import com.bankaccount.persistence.entities.TransactionType;
import com.bankaccount.persistence.repos.AccountRepository;
import com.bankaccount.persistence.repos.TransactionRepository;
import com.bankaccount.persistence.repos.TransactionTypeRepository;
import com.bankaccount.service.exception.InsufficientFundsException;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class AccountServiceTests {
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private TransactionTypeRepository transactionTypeRepository;
	
	@Autowired
	private AccountService accountService;

	
	
	@Test
	@DirtiesContext
	public void executeAccountWithdrawTransaction_thenAcountBalanceUpdated() {
		
		Optional<Account> optionalAccount =  accountRepository.findById(10000L);
		Account account = optionalAccount.get();
		
		// CurrentBalance before transaction
		
		BigDecimal currentBalanceBeforTransaction = account.getCurrentBalance();
		
		log.info("Account Before Transaction : {}, the Current balance : {} ", account, account.getCurrentBalance());
		
		// Withdraw transaction
		Optional<TransactionType> transactionType = transactionTypeRepository.findById(2L);
		
		Transaction transaction = new Transaction(BigDecimal.valueOf(10.00), account.getCurrentBalance(), LocalDateTime.now(), transactionType.get(), "Withdraw 10.00 euros");
		
		log.info("New Transaction : {}, type : {}, amount : {} ", transaction, transaction.getTransactionType().getType(), transaction.getAmount());
		
		
		try {
			accountService.executeAccountTransaction(account, transaction);
		
		} catch (InsufficientFundsException e) {
			log.info("InsufficientFundsException : {} ", e.getMessage());
		}
		
		Optional<Account> optionalAccountAfterTransaction =  accountRepository.findById(10000L);
		Account accountAfterTransaction = optionalAccountAfterTransaction.get();
		
		log.info("Account After Transaction : {}, the Current balance : {} ", accountAfterTransaction, accountAfterTransaction.getCurrentBalance());
		
		assertEquals(currentBalanceBeforTransaction.subtract(transaction.getAmount()), accountAfterTransaction.getCurrentBalance());
	
	}
	
	
	@Test
	@DirtiesContext
	public void executeAccountDepositTransaction_thenAcountBalanceUpdated() {
		
		Optional<Account> optionalAccount =  accountRepository.findById(10000L);
		Account account = optionalAccount.get();
		
		// CurrentBalance before transaction
		
		BigDecimal currentBalanceBeforTransaction = account.getCurrentBalance();
		
		log.info("Account Before Transaction : {}, the Current balance : {} ", account, account.getCurrentBalance());
		
		// Withdraw transaction
		Optional<TransactionType> transactionType = transactionTypeRepository.findById(1L);
		
		Transaction transaction = new Transaction(BigDecimal.valueOf(10.00), account.getCurrentBalance(), LocalDateTime.now(), transactionType.get(), "Deposit 10.00 euros");
		
		log.info("New Transaction : {}, type : {}, amount : {} ", transaction, transaction.getTransactionType().getType(), transaction.getAmount());
		
		
		try {
			accountService.executeAccountTransaction(account, transaction);
		
		} catch (InsufficientFundsException e) {
			log.info("InsufficientFundsException : {} ", e.getMessage());
		}
		
		Optional<Account> optionalAccountAfterTransaction =  accountRepository.findById(10000L);
		Account accountAfterTransaction = optionalAccountAfterTransaction.get();
		
		log.info("Account After Transaction : {}, the Current balance : {} ", accountAfterTransaction, accountAfterTransaction.getCurrentBalance());
		
		assertEquals(currentBalanceBeforTransaction.subtract(transaction.getAmount()), accountAfterTransaction.getCurrentBalance());
	
	}
	
	@Test
	@DirtiesContext
	public void executeAccountWithdrawTransaction_thenInsufficientFundsException() {
		
		Optional<Account> optionalAccount =  accountRepository.findById(10000L);
		Account account = optionalAccount.get();
		
		// CurrentBalance before transaction
		
		BigDecimal currentBalanceBeforTransaction = account.getCurrentBalance();
		
		log.info("Account Before Transaction : {}, the Current balance : {} ", account, account.getCurrentBalance());
		
		// Withdraw transaction
		Optional<TransactionType> transactionType = transactionTypeRepository.findById(2L);
		
		Transaction transaction = new Transaction(BigDecimal.valueOf(110.00), account.getCurrentBalance(), LocalDateTime.now(), transactionType.get(), "Withdraw 10.00 euros");
		
		log.info("New Transaction : {}, type : {}, amount : {} ", transaction, transaction.getTransactionType().getType(), transaction.getAmount());
		
		
		try {
			accountService.executeAccountTransaction(account, transaction);
		
		} catch (InsufficientFundsException e) {
			log.info("InsufficientFundsException : {} ", e.getMessage());
		}
		
		Optional<Account> optionalAccountAfterTransaction =  accountRepository.findById(10000L);
		Account accountAfterTransaction = optionalAccountAfterTransaction.get();
		
		log.info("Account After Transaction : {}, the Current balance : {} ", accountAfterTransaction, accountAfterTransaction.getCurrentBalance());
		// When the InsufficientFundsException is thrown the transaction is aborted no change in Account Balance  
		assertTrue(currentBalanceBeforTransaction.compareTo(accountAfterTransaction.getCurrentBalance()) == 0);
	
	}
}
