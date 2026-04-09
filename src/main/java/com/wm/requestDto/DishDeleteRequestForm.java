package com.wm.requestDto;

import lombok.Data;

@Data
public class DishDeleteRequestForm {
	private Long dishId;
	private Long fileId;
}