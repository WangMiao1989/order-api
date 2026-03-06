package com.wm.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;

@Data
public class DishInfoEntity {
	private Long dishId;
	private String name;
	private Long categoryId;
	private String description;
	private BigDecimal originalPrice;
	private BigDecimal price;
	private byte[] image;
	private Boolean hasDiscount;
	private BigDecimal rate;
	private LocalDate startDate;
	private LocalDate endDate;
}
