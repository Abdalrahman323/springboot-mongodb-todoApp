package com.springboot.todoApp.springboottodoApp.exceptions;

import org.springframework.http.HttpStatus;

public class NotFoundException extends ApiBaseException {

	public NotFoundException(String message) {
		super(message);
	}
	

	@Override
	public HttpStatus getStauts() {
		return HttpStatus.NOT_FOUND;
	}
 }
