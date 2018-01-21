package com.bankaccount.persistence.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Data @NoArgsConstructor
public class Transaction {
	
	@Setter(value=AccessLevel.NONE)
	@Id
	@GeneratedValue (strategy=GenerationType.AUTO)
	private Long id;
	private BigDecimal amount;
	private BigDecimal balance;
	
	private LocalDateTime transactionDate;
	
	@JsonIgnore
	@ManyToOne
	private Account account;
	
	@OneToOne (fetch=FetchType.EAGER)
	private TransactionType transactionType;
	
	private String details;

	public Transaction(BigDecimal amount, BigDecimal balance, LocalDateTime transactionDate, 
			 TransactionType transactionType, String details) {
		super();
		this.amount = amount;
		this.balance = balance;
		this.transactionDate = transactionDate;
		this.transactionType = transactionType;
		this.details = details;
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", amount=" + amount + ", balance=" + balance + ", transactionDate="
				+ transactionDate + ", account=" + account + ", transactionType=" + transactionType + ", details="
				+ details + "]";
	}
	

}
