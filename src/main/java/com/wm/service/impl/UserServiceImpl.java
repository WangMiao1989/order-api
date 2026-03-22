package com.wm.service.impl;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wm.entity.UserEntity;
import com.wm.exception.AuthenticationException;
import com.wm.mapper.UserRepository;
import com.wm.requestDto.LoginRequestForm;
import com.wm.responseDto.LoginResponse;
import com.wm.service.UserService;
import com.wm.utils.TokenGenerator;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	public LoginResponse login(LoginRequestForm request) {
		LoginResponse response = new LoginResponse();
		
		UserEntity user = userRepository.selectUserInfo(request.getUserId(), request.getPassword());
		
		// 认证失败的场合
		if(Objects.isNull(user)) {
			throw new AuthenticationException("用户名或密码有误。");
		}
		
		// 发行token
		String token = TokenGenerator.generateToken(32);
		
		// 保存token
		userRepository.updateToken(request.getUserId(), token);
		
		response.setUserInfo(user);
		response.setToken(token);
		
		return response;
	}
	
	public void logout(String userId) {
		userRepository.deleteToken(userId);
	}
}
