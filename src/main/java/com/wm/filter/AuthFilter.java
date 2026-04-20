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
        // 白名单判定
        boolean isWhitelisted = whitelistProperties.getWhitelists().get("auth").stream().anyMatch(url -> url.equals(req.getRequestURI()));
        if( isWhitelisted) {
        	// 白名单的场合，继续执行
        	chain.doFilter(request, response);
        	return;
        }
        
        boolean authRequired = Boolean.parseBoolean(req.getHeader("X-Auth-Required"));
        UserInfoEntity userInfo = new UserInfoEntity();
        if(authRequired) {
        	// 管理端调用
        	String token = req.getHeader("Authorization");
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
        	// 顾客端调用
        	userInfo.setUserId("customer");
        	userInfo.setUserName("顾客");
        }
        
        // 把userInfo设定到requestContextHolder里
        req.setAttribute("userInfo", userInfo);
        
        // 继续执行
        chain.doFilter(request, response);
    }
}