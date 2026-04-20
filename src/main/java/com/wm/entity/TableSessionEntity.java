package com.wm.entity;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TableSessionEntity extends BaseEntity{
	private String tableSessionId;
	private String tableNo;
	private Integer customerCnt;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
}
