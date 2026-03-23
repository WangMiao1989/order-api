package com.wm.entity;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class DishRevenueEntity{
	private String dishName;
	private Integer dishQuantity;
	private BigDecimal dishAmount;
}
