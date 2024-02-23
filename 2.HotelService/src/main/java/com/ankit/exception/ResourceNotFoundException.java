package com.ankit.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String s) {
        super(s);
    }

    //we string not passed then we will give manual message
    public ResourceNotFoundException() {
        super("Resource Not Found");
    }
}
