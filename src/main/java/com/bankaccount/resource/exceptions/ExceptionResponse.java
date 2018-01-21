package com.bankaccount.resource.exceptions;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class ExceptionResponse {
	
	private Instant timestamp;
	private String message;
	private String code;
	private String details;
	public ExceptionResponse(Instant timestamp, String message, String details) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}
	
	

}
