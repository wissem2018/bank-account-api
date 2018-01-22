package com.bankaccount.persistence.entities;

import lombok.Getter;

@Getter
public enum TransactionTypeCode {
	
	WITHDRAW("W") ,
	DEPOSIT("D");
	
	private final String code;
	
	private TransactionTypeCode(String code){
		this.code = code;
	}
	
}
