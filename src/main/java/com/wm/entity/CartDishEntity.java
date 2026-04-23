package com.wm.entity;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class CartDishEntity {
	private Long dishId;
	private String name;
	private Long categoryId;
	private BigDecimal currentPrice;
	private Long fileId;
	private Integer quantity;
}
