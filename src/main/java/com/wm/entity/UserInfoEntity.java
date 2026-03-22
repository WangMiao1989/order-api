package com.wm.entity;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class UserInfoEntity{
	private String userId;
	private String userName;
	private String userType;
	private LocalDateTime expiresTime;
}
