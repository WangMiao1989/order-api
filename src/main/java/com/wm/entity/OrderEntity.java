package com.wm.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class OrderEntity extends BaseEntity{
	private Long orderId;
	private String tableSessionId;
	private String orderDetail;
}
