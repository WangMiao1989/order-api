package com.wm.entity;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class TableSessionEntity extends BaseEntity{
	private String tableSessionId;
	private String tableNo;
	private Integer customerCnt;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
}
