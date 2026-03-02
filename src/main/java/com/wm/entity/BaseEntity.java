package com.wm.entity;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class BaseEntity {
	private String createUser;
	private LocalDateTime createTime;
	private String updateUser;
	private LocalDateTime updateTime;
}
