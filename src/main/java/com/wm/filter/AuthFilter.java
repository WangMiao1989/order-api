package com.wm.filter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;

import com.wm.config.WhitelistProperties;
import com.wm.entity.UserInfoEntity;
import com.wm.exception.AuthenticationException;
import com.wm.mapper.UserRepository;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

public class AuthFilter implements Filter {

	private final  UserRepository userRepository;
    private final  WhitelistProperties whitelistProperties;
    
    public AuthFilter(UserRepository userRepository, WhitelistProperties whitelistProperties ) {
    	this.userRepository = userRepository;
    	this.whitelistProperties = whitelistProperties;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest req = (HttpServletRequest) request;
        String token = req.getHeader("Authorization");
        UserInfoEntity userInfo = new UserInfoEntity();
        
        // 白名单判定
        boolean isWhitelisted = whitelistProperties.getWhitelists().get("auth").stream().anyMatch(url -> url.equals(req.getRequestURI()));
        // 如果url不在白名单 或 token不为空的场合，需要认证
        // token不为空的：管理端调用的共同api，因为token被设定，所以验证token有效性
        if (!isWhitelisted || !Objects.isNull(token)) {
        	// 真正token取得
            if (!Objects.isNull(token) && token.startsWith("Bearer ")) {
                token = token.substring(7);
            }
            
            // token存在性check
            if (Objects.isNull(token) || token.isEmpty()) {
                throw new AuthenticationException("登录信息无效，请重新登录。");
            }
            
            // 通过token取得user信息
            userInfo = userRepository.selectUserInfoByToken(token);
            
            // token有效性check
            if (Objects.isNull(userInfo)) {
            	throw new AuthenticationException("登录信息无效，请重新登录。");
            }
            
            // token时效性check
            if (LocalDateTime.now().isAfter(userInfo.getExpiresTime())) {
            	throw new AuthenticationException("登录信息过期，请重新登录。");
            }
        	
        } else {
        	// 在白名单并且token为空的场合 ===> 顾客端调用
        	userInfo.setUserId("customer");
        	userInfo.setUserName("顾客");
        }
        
        // 把userInfo设定到requestContextHolder里
        req.setAttribute("userInfo", userInfo);
        
        // 继续执行
        chain.doFilter(request, response);
    }
}