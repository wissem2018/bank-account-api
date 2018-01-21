package com.bankaccount.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankaccount.persistence.entities.Account;
import com.bankaccount.persistence.entities.Transaction;
import com.bankaccount.persistence.repos.AccountRepository;
import com.bankaccount.persistence.repos.TransactionRepository;
import com.bankaccount.service.exception.InsufficientFundsException;

@Service
@Transactional
public class AccountService {
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	public Account executeAccountTransaction (Account account, Transaction transaction ) throws InsufficientFundsException {
		
		if("W".equals(transaction.getTransactionType().getCode())) {
			
			if (account.getCurrentBalance().compareTo(transaction.getAmount()) < 0 ){
				throw new InsufficientFundsException("Not enough funds");
			}
			account.setCurrentBalance(account.getCurrentBalance().subtract(transaction.getAmount()));
		
		}
		
		if("D".equals(transaction.getTransactionType().getCode())) {
			account.setCurrentBalance(account.getCurrentBalance().add(transaction.getAmount()));
		}
		
		
		transaction.setAccount(account);

		transactionRepository.save(transaction);
		
		accountRepository.save(account);
		
		return account;
		
	}

}
