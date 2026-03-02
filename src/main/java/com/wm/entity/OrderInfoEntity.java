package com.wm.entity;

import lombok.Data;

@Data
public class OrderInfoEntity extends BaseEntity{
	private Long orderId;
	private Long tableId;
	private String orderDetail;
}
