package com.wm.entity;

import java.util.List;

import lombok.Data;

@Data
public class UnservedOrderEntity {
	private Long dishId;
	private String name;
	private Integer totalQuantity;
	private List<TableQuantityEntity> tableQuantityList;
}
