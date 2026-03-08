package com.wm.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class OrderDishEntity extends BaseEntity{
	private Long dishId;
	private String name;
	private BigDecimal price;
	private Integer quantity;
	private Boolean isServed;
	private LocalDateTime servedTime;
}
