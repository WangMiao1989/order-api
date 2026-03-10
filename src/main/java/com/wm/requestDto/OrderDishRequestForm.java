package com.wm.requestDto;

import lombok.Data;

@Data
public class OrderDishRequestForm {
	private Long dishId;
	private Long orderId;
}
