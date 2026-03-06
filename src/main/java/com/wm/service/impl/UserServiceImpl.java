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
	UserRepository userRepository;
	
	public LoginResponse login(LoginRequestForm request) {
		LoginResponse response = new LoginResponse();
		
		UserEntity user = userRepository.selectUserInfo(request.getUserId(), request.getPassword());
		
		// 认证失败的场合
		if(Objects.isNull(user)) {
			throw new AuthenticationException("");
		}
		
		// 发行token
		String token = TokenGenerator.generateToken(32);
		
		// 保存token
		userRepository.inserToken(request.getUserId(), token);
		
		response.setUserInfo(user);
		response.setToken(token);
		
		return response;
	}
}
