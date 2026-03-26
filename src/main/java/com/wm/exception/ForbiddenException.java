package com.wm.exception;

import lombok.Getter;

@Getter
public class ForbiddenException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

    private final String message;
    
    public ForbiddenException(String message) {
        super(message);
        this.message = message;
    }
}