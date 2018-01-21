package com.bankaccount;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bankaccount.persistence.entities.Account;
import com.bankaccount.persistence.entities.Address;
import com.bankaccount.persistence.entities.Bank;
import com.bankaccount.persistence.entities.Client;
import com.bankaccount.persistence.entities.GenderType;
import com.bankaccount.persistence.entities.Transaction;
import com.bankaccount.persistence.entities.TransactionType;
import com.bankaccount.persistence.repos.AccountRepository;
import com.bankaccount.persistence.repos.BankRepository;
import com.bankaccount.persistence.repos.ClientRepository;
import com.bankaccount.persistence.repos.GenderTypeRepository;
import com.bankaccount.persistence.repos.TransactionRepository;
import com.bankaccount.persistence.repos.TransactionTypeRepository;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class BankAccountApiApplication implements CommandLineRunner {

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private GenderTypeRepository genderTypeRepository;

	@Autowired
	private BankRepository bankRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private TransactionTypeRepository transactionTypeRepository;
	
	
	public static void main(String[] args) {
		SpringApplication.run(BankAccountApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		/*
			// Get the gender type from the referential
			Optional<GenderType> optionalGenderType = genderTypeRepository.findById(1L);
			// Get the bank
			Optional<Bank> optionalBank = bankRepository.findById(1L);
	
			Client client = new Client("3304849857", "Marcel", "Martin", "0766654758", LocalDate.of(1980, 10, 27),
					"marcel.martin@yahoo.com", optionalGenderType.get(), "No more informations");
			client.setBank(optionalBank.get());
			// Adding the client address
			Address address = new Address("17 avenue Rome", "75009", "Paris", "France", "No comment");
			address.setClient(client);
			
			client.setAddress(address);
	
			Client savedClient = clientRepository.save(client);
			log.info("The new Client is : {} ", savedClient);
		*/
		
		/*
		Optional<Account> optionalAccount =  accountRepository.findById(10000L);
		
		Account account = optionalAccount.get();
		
		Optional<TransactionType> transactionType = transactionTypeRepository.findById(2L);
		
		Transaction transaction = new Transaction(BigDecimal.valueOf(10.00), account.getCurrentBalance(), LocalDateTime.now(), transactionType.get(), "Withdraw 10.00 euros");
		
		if("W".equals(transaction.getTransactionType().getCode())) {
			account.setCurrentBalance(transaction.getBalance().subtract(transaction.getAmount()));
		}
		
		if("D".equals(transaction.getTransactionType().getCode())) {
			account.setCurrentBalance(transaction.getBalance().add(transaction.getAmount()));
		}
		
		transaction.setAccount(account);

		transactionRepository.save(transaction);
		
		accountRepository.save(account);
		
	*/
	}
}
