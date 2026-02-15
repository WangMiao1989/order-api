package com.wm.entity;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DishEntity {
	private Long id;
	private String name;
	private Long categoryId;
	private String description;
	private BigDecimal originalPrice;
	private BigDecimal price;
	private byte[] image;
	private List<String> tags;
}
