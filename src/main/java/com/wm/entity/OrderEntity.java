package com.wm.entity;

import lombok.Data;

@Data
public class OrderEntity extends BaseEntity{
	private Long orderId;
	private Long tableId;
	private String orderDetail;
}
