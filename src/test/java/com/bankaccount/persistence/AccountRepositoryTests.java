package com.bankaccount.persistence;

import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bankaccount.persistence.entities.Account;
import com.bankaccount.persistence.repos.AccountRepository;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class AccountRepositoryTests {
	
	@Autowired
	private AccountRepository accountRepository;
	
	
	@Test
	public void findAccountById() {
		Optional<Account> optionalAccount =  accountRepository.findById(10000L);
		log.info("Account with id=10000L is {} ", optionalAccount.get());
		assertNotNull(optionalAccount.get());
	}
	
	@Test
	public void saveNewAccount() {
		Account account = new Account("11547854798", LocalDateTime.of(2000,10 , 10, 14, 30), null, BigDecimal.valueOf(100.00), false, BigDecimal.valueOf(0.00), "No comment");
		
		accountRepository.save(account);
	}
	
	@Test
	public void updateAccount() {
		Optional<Account> optionalAccount =  accountRepository.findById(10000L);
		if (optionalAccount.isPresent()) {
			Account account = optionalAccount.get();
			account.setAccountNumber("6669888774");
			accountRepository.save(account);
			log.info("Update Account Number with : id={}, result = {} ",10000L,  account);
		
		}
		
	}
	
}
