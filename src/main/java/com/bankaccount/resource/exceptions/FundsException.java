package com.bankaccount.resource.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor 
@EqualsAndHashCode(callSuper=false)
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FundsException extends RuntimeException {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String code;
	private String message;
	
	
	
	public FundsException(String code, String message) {
		this.code = code;
		this.message = message;
	}
}
