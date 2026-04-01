package com.wm.entity;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class PeriodRevenueInfoEntity{
	private String businessDate;
	private Long dishId;
	private String name;
	private BigDecimal price;
	private Integer quantity;
}
