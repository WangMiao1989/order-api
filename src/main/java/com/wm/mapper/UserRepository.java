package com.wm.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.wm.entity.UserEntity;
import com.wm.entity.UserInfoEntity;

@Mapper
public interface UserRepository {
	public UserEntity selectUserInfo(String userId, String password);
	
	public int updateToken(String userId, String token);
	
	public UserInfoEntity selectUserInfoByToken(String token);
	
	public void deleteToken(String userId);
}
