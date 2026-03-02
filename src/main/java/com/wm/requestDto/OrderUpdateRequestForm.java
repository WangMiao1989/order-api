package com.wm.requestDto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class OrderUpdateRequestForm {
	private Long tableId;
	private String tableNo;
	private Integer customerCnt;
	private LocalDateTime startTime;
	private String orderDetail; 
}
