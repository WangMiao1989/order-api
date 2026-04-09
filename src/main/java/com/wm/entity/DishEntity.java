package com.wm.entity;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class DishEntity extends BaseEntity{
	private Long dishId;
	private String name;
	private String description;
	private Long categoryId;
	private BigDecimal price;
	private Long fileId;
	private Long tag;
	private String displayOrder;
}
