package com.wm.requestDto;

import lombok.Data;

@Data
public class OrderDishQuantityRequestForm {
	private Long dishId;
	private Long orderId;
	private Integer quantity;
}
