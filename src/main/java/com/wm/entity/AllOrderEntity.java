package com.wm.entity;

import java.util.List;

import lombok.Data;

@Data
public class AllOrderEntity {
	private Long dishId;
	private String name;
	private Integer totalQuantity;
	private List<TableQuantityEntity> tableQuantityList;
}
