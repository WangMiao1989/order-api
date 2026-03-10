package com.wm.entity;

import lombok.Data;

@Data
public class CategoryEntity {
	private Long categoryId;
	private String type;
	private String name;
	private String description;
	private String icon;
}
