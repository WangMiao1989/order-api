package com.wm.validation;

import java.util.Objects;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import com.wm.requestDto.DishUpdateRequestForm;

public class DishUpdateRequestFormValidator implements FormValidator<DishUpdateRequestForm>{
	
	@Override
	public void validate(DishUpdateRequestForm form, Errors errors) {
		if(Boolean.TRUE.equals(form.getHasDiscount())) {
			if(Objects.isNull(form.getRate())){
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "rate", "", "折扣不能为空");
			}
			
			if(Objects.isNull(form.getStartDate())){
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "startDate", "", "折扣开始时间不能为空");
			}
			
			if(Objects.isNull(form.getEndDate())){
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "endDate", "", "折扣结束时间不能为空");
			}
		}
	}
}
