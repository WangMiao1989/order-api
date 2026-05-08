package com.wm.requestDto;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DishUpdateRequestForm {
	private Long dishId;
	@NotBlank
	private String name;
	@NotNull
	private Long categoryId;
	private String description;
	@NotNull
	private BigDecimal price;
	private Long fileId;
	private String image;
	private Long tag;
	@NotNull
	private Boolean hasDiscount;
	private BigDecimal rate;
	private LocalDate startDate;
	private LocalDate endDate;
}