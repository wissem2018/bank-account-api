package com.bankaccount.service.account;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankaccount.persistence.entities.Account;
import com.bankaccount.persistence.entities.Transaction;
import com.bankaccount.persistence.entities.TransactionType;
import com.bankaccount.persistence.repos.AccountRepository;
import com.bankaccount.persistence.repos.TransactionRepository;
import com.bankaccount.persistence.repos.TransactionTypeRepository;
import com.bankaccount.service.exception.InsufficientFundsException;
import com.bankaccount.utils.BankAccountGlobalProperties;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class AccountService {
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private TransactionTypeRepository transactionTypeRepository;
	
	@Autowired
	private BankAccountGlobalProperties bankAccountGlobalProperties;
	
	public Account executeAccountTransaction (Account account, Transaction transaction ) throws InsufficientFundsException {
		
		TransactionType withdrawTransactionType = transactionTypeRepository.findByCode(bankAccountGlobalProperties.getTransactionTypeCodeDefault());
				
		
		if(withdrawTransactionType.getCode().equals(transaction.getTransactionType().getCode())) {
			log.info("Withdraw transaction : {} with balance = {}  ", account, account.getCurrentBalance());
			if (account.getCurrentBalance().compareTo(transaction.getAmount()) < 0 ){
				log.info("Withdraw with InsufficientFunds" );
				throw new InsufficientFundsException("Not enough funds");
			}
			account.setCurrentBalance(account.getCurrentBalance().subtract(transaction.getAmount()));
			
			
			
		} else {
		// Deposit type transaction
			log.info("Deposit transaction : {} with balance = {}  ", account, account.getCurrentBalance());
			account.setCurrentBalance(account.getCurrentBalance().add(transaction.getAmount()));
		}
		
		
		transaction.setAccount(account);

		transactionRepository.save(transaction);
		
		accountRepository.save(account);
		
		return account;
		
	}

}
