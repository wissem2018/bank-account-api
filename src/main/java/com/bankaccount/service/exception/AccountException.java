package com.bankaccount.service.exception;



import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class AccountException extends ServiceException {

	private String code;
	private String details;
	
	private static final long serialVersionUID = 1L;

	public AccountException(String code, String message, String details) {
		super(message);
		this.code = code;
		this.details = details;
	}

	
	
}
