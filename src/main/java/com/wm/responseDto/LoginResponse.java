package com.wm.responseDto;

import com.wm.entity.UserEntity;

import lombok.Data;

@Data
public class LoginResponse {
	private UserEntity userInfo;
	private String token;
	private String tenantName;
}
