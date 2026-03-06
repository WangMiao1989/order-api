package com.wm.entity;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class UserEntity{
	private String userName;
	private String userType;
	private LocalDateTime lastLoginTime;
}
