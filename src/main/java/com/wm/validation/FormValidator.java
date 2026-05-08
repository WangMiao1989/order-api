package com.wm.validation;

import org.springframework.validation.Errors;

public interface FormValidator<T> {
	void validate(T target, Errors errors);
}
