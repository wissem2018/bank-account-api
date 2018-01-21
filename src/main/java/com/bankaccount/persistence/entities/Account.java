package com.bankaccount.persistence.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Data @NoArgsConstructor
public class Account {
	@Setter(value=AccessLevel.NONE)	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@NotNull
	@Size(max=100)
	private String accountNumber;
	private LocalDateTime openedDate;
	private LocalDateTime closedDateTime;
	private BigDecimal currentBalance;
	private Boolean allowOverdraft;
	private BigDecimal overdraftAmount;
	private String details;
	
	
	@JsonIgnore
	@ManyToOne (fetch=FetchType.EAGER)
	private Client client;
	
	@JsonIgnore
	@Setter(value=AccessLevel.NONE)	
	@OneToMany (mappedBy="account", fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
	private List<Transaction> transactions = new ArrayList<Transaction>(); 
	@JsonIgnore
	@UpdateTimestamp
	private LocalDateTime lastUpdatedDate;
	@JsonIgnore
	@CreationTimestamp
	private LocalDateTime createdDate;
	
	public void addTransaction(Transaction transaction) {
		this.transactions.add(transaction);
	}
	
	public void removeTransaction(Transaction transaction) {
		this.transactions.remove(transaction);
	}
	
	public Account(String accountNumber, LocalDateTime openedDate,
			LocalDateTime closedDateTime, BigDecimal currentBalance, Boolean allowOverdraft, BigDecimal overdraftAmount,
			String details) {
		super();
		this.accountNumber = accountNumber;
		this.openedDate = openedDate;
		this.closedDateTime = closedDateTime;
		this.currentBalance = currentBalance;
		this.allowOverdraft = allowOverdraft;
		this.overdraftAmount = overdraftAmount;
		this.details = details;
	}
	

	@Override
	public String toString() {
		return "Account [accountNumber=" + accountNumber + ", openedDate=" + openedDate + ", closedDateTime="
				+ closedDateTime + ", currentBalance=" + currentBalance + ", allowOverdraft=" + allowOverdraft
				+ ", overdraftAmount=" + overdraftAmount + ", details=" + details + ", lastUpdatedDate="
				+ lastUpdatedDate + ", createdDate=" + createdDate 
				
				 + "]";
	}

	
}
