package com.wm.entity;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class OrderDetailEntity{
	private Long orderId;
	private LocalDateTime orderTime;
	private Boolean isPaid;
	private LocalDateTime paidTime;
	private List<OrderDishEntity> dishList;
}
