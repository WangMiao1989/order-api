package com.wm.exception;

import lombok.Getter;

@Getter
public class SystemErrorException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

    private final String message;
    
    public SystemErrorException(String message) {
        super(message);
        this.message = message;
    }
}