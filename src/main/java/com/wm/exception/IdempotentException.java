package com.wm.exception;

import lombok.Getter;

@Getter
public class IdempotentException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
    public IdempotentException() {
        super("");
    }
}