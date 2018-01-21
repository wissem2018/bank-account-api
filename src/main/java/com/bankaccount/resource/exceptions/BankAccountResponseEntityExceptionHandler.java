package com.bankaccount.resource.exceptions;

import java.time.Instant;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
@RestController
public class BankAccountResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions
				(Exception ex, WebRequest request) {
		ExceptionResponse exceptionResponse =  new ExceptionResponse(Instant.now(), ex.getMessage(), request.getDescription(false) );
		
		return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	

	@ExceptionHandler(AccountNotFoundException.class)
	public final ResponseEntity<Object> handelAccountNotFoundExceptions(AccountNotFoundException ex, WebRequest request) {
		ExceptionResponse exceptionResponse =  new ExceptionResponse(Instant.now(), ex.getMessage(), request.getDescription(false) );
		return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ClientNotFoundException.class)
	public final ResponseEntity<Object> handelAccountNotFoundExceptions(ClientNotFoundException ex, WebRequest request) {
		ExceptionResponse exceptionResponse =  new ExceptionResponse(Instant.now(), ex.getMessage(), request.getDescription(false) );
		return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(FundsException.class)
	public final ResponseEntity<Object> handelFundsExceptions(FundsException ex, WebRequest request) {
		ExceptionResponse exceptionResponse =  new ExceptionResponse(Instant.now(), ex.getMessage(), ex.getCode(), request.getDescription(false) );
		return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
			
		ExceptionResponse exceptionResponse =  new ExceptionResponse(Instant.now(), "Validation failed", ex.getBindingResult().getAllErrors().toString() );
		return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
	}



	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		ExceptionResponse exceptionResponse =  new ExceptionResponse(Instant.now(), "type mismatch", ex.getMessage() );
		return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	
}
