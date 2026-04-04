package com.wm.requestDto;

import lombok.Data;

@Data
public class DishDisplayUpdateRequestForm {
	private Long dishId;
	private Boolean displayFlag;
}