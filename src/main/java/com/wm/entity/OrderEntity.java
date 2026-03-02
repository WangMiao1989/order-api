package com.wm.entity;

import lombok.Data;

@Data
public class OrderEntity {
	private String tableNo;
	private String customerCnt;
	private String startTime;
	private Object orderDetail; 
	private String operator;
}
