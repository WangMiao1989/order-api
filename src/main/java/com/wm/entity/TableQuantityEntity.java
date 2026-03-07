package com.wm.entity;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class TableQuantityEntity{
	private Long orderId;
	private String tableNo;
	private Integer quantity;
	private LocalDateTime orderTime;
}
