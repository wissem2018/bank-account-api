package com.bankaccount.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@PropertySource("classpath:bank-account.properties")
@Data
public class BankAccountGlobalProperties {
	
	@Value("${transaction.type.code.default}")
	private String transactionTypeCodeDefault; 
}
