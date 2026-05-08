package com.wm.exception;

import org.springframework.validation.Errors;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
    private String code;
    private String message;
    private Errors error;
    
    public BusinessException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }
    
    public BusinessException(Errors error) {
        this.error = error;
    }
}