package com.bankaccount;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bankaccount.persistence.entities.Account;
import com.bankaccount.persistence.entities.Transaction;
import com.bankaccount.persistence.entities.TransactionType;
import com.bankaccount.persistence.repos.TransactionTypeRepository;

@Component
public class DataFactory {
	
	
	@Autowired
	private TransactionTypeRepository transactionTypeRepository; 
	
		
	public Transaction getWithDrawTransaction() {
			TransactionType transactionTypeWithdraw = transactionTypeRepository.findByCode("W");  
			return new Transaction(BigDecimal.valueOf(10.00), BigDecimal.valueOf(100.00) , LocalDateTime.now(),transactionTypeWithdraw , "Withdraw 10.00 euros"); 
	}
	
	public Transaction getDepositTransaction() {
		TransactionType transactionTypeWithdraw = transactionTypeRepository.findByCode("D");  
		return new Transaction(BigDecimal.valueOf(10.00), BigDecimal.valueOf(100.00) , LocalDateTime.now(),transactionTypeWithdraw , "Withdraw 10.00 euros"); 
	}
	
		
	public Account getAccount() {
		return new Account("11547854798", LocalDateTime.of(2000,10 , 10, 14, 30), null, BigDecimal.valueOf(100.00), false, BigDecimal.valueOf(0.00), "No comment");
	}
	
	

}
