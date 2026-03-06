package com.wm.exception;

import lombok.Getter;

@Getter
public class AuthenticationException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

    private final String message;
    
    public AuthenticationException(String message) {
        super(message);
        this.message = message;
    }
}