package com.wm.requestDto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class OrderUpdateRequestForm {
	private String tableNo;
	private Integer customerCnt;
	private LocalDateTime startTime;
	private String orderDetail; 
}
