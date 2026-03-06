package com.wm.entity;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class TableEntity extends BaseEntity{
	private Long tableId;
	private String tableNo;
	private Integer customerCnt;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
}
