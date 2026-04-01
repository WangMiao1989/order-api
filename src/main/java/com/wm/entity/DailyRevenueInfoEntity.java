package com.wm.entity;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class DailyRevenueInfoEntity{
	private String tableSessionId;
	private String tableNo;
	private Integer customerCnt;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private List<OrderDetailEntity> orderList;
}
