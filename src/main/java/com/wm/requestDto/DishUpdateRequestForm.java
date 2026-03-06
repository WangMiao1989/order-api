package com.wm.requestDto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;

@Data
public class DishUpdateRequestForm {
	private Long dishId;
	private String name;
	private Long categoryId;
	private String description;
	private BigDecimal price;
	private String imgUrl;
	private Boolean hasDiscount;
	private BigDecimal rate;
	private LocalDate startDate;
	private LocalDate endDate;
	
}