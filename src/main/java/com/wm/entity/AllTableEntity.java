package com.wm.entity;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class AllTableEntity {
	private String tableSessionId;
	private String tableNo;
	private String floor;
	private String type;
	private Integer capacity;
	private Integer customerCnt;
	private LocalDateTime startTime;
}
