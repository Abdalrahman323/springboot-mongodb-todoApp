package com.springboot.todoApp.springboottodoApp.exceptions;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jdk.jfr.Experimental;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(ApiBaseException.class)
	public ResponseEntity<ErrorDetails> handleApiException(ApiBaseException ex, WebRequest request ){
		ErrorDetails errorDetails = new ErrorDetails( ex.getMessage(),request.getDescription(false));
		return new ResponseEntity<>(errorDetails,ex.getStauts());
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		ValidationError validationError = new ValidationError();
		validationError.setUri(request.getDescription(false));
		List<FieldError> fiedErrors = ex.getBindingResult().getFieldErrors();
		for(FieldError fieldError : fiedErrors) {
			validationError.addError(fieldError.getDefaultMessage());
		}
		
		return new ResponseEntity<>(validationError,HttpStatus.BAD_REQUEST);

	}
}
