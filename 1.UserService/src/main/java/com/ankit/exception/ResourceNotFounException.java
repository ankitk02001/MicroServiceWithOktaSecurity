package com.ankit.exception;

public class ResourceNotFounException extends RuntimeException {

	public ResourceNotFounException() {
		super("Resource not found on server !!");
	}

	public ResourceNotFounException(String msg) {
		super(msg);
	}

	
}
