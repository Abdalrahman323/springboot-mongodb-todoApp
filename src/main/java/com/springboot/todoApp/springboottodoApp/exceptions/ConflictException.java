package com.springboot.todoApp.springboottodoApp.exceptions;

import org.springframework.http.HttpStatus;

public class ConflictException extends ApiBaseException {

	public ConflictException(String message) {
		super(message);
	}

	@Override
	public HttpStatus getStauts() {
		return HttpStatus.CONFLICT;
	}

}
