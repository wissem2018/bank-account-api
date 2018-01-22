package com.bankaccount.persistence;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bankaccount.DataFactory;
import com.bankaccount.persistence.entities.Account;
import com.bankaccount.persistence.entities.Transaction;
import com.bankaccount.persistence.repos.AccountRepository;
import com.bankaccount.persistence.repos.TransactionRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionRepositoryTests {
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private DataFactory dataFactory;
	
	@Test
	public void executeNewTransactionWithDrawToAccount() {
		// 1. Arrange 
		Optional<Account> optionalAccount =  accountRepository.findById(10000L);
		Account account = optionalAccount.get();
		// CurrentBalance before transaction
		
		BigDecimal currentBalanceBeforTransaction = account.getCurrentBalance();
		
		Transaction transaction = dataFactory.getWithDrawTransaction();
		
		// 2. Act
		account.setCurrentBalance(transaction.getBalance().subtract(transaction.getAmount()));
		
		transaction.setAccount(account);

		transactionRepository.save(transaction);
		
		accountRepository.save(account);
		
		Optional<Account> optionalAccountAfterTransaction =  accountRepository.findById(10000L);
		Account accountAfterTransaction = optionalAccountAfterTransaction.get();
		
		// 3. Assert
		assertEquals(currentBalanceBeforTransaction.subtract(transaction.getAmount()), accountAfterTransaction.getCurrentBalance());
	
	}
	
}
