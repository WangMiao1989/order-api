package com.wm.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;

@Data
public class DiscountEntity extends BaseEntity{
	private Long discountId;
	private Long dishId;
	private BigDecimal rate;
	private LocalDate startDate;
	private LocalDate endDate;
}