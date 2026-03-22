package com.wm.requestDto;

import lombok.Data;

@Data
public class OrderDishCancelRequestForm {
	private Long dishId;
	private Long orderId;
	private Integer quantity;
	private String dishName;
	private Boolean isServed;
	private Integer cancelQuantity;
}
