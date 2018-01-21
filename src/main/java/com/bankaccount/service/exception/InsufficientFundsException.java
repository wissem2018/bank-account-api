package com.bankaccount.service.exception;

public class InsufficientFundsException extends ServiceException {

	
	private static final long serialVersionUID = 1L;

	public InsufficientFundsException(String message) {
		super(message);
	}
	
}
