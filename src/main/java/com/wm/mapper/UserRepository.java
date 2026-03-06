package com.wm.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.wm.entity.UserEntity;

@Mapper
public interface UserRepository {
	public UserEntity selectUserInfo(String userId, String password);
	
	public int inserToken(String userId, String token);
}
