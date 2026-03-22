package com.wm.aspect;

import java.time.LocalDateTime;
import java.util.Objects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.wm.entity.BaseEntity;
import com.wm.entity.UserInfoEntity;

import jakarta.servlet.http.HttpServletRequest;

@Aspect
@Component
public class MapperAspect {
	// *=任意返回值类型, com.wm.mapper..*=mapper下面包及子包所有类, .*=任意方法名, (..)=任意参数
	@Pointcut("execution(* com.wm.mapper..*.*(..))")
	public void mapperMethod() {}
	
	@Before("mapperMethod()")
	public void beforeMapper(JoinPoint joinPoint) {
		// use信息取得
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		
		if(Objects.isNull(attributes)) {
			return;
		}
		
		HttpServletRequest request = attributes.getRequest();
		UserInfoEntity currentUser = (UserInfoEntity)request.getAttribute("userInfo");
		
		if(Objects.isNull(currentUser)) {
			return;
		}
		
		Object[] args = joinPoint.getArgs();
		for(Object arg: args) {
			if(arg instanceof BaseEntity) {
				setUserInfo((BaseEntity)arg, currentUser);
			}
		}
	}
	
	private void setUserInfo(BaseEntity arg, UserInfoEntity userInfo) {
		// 更新者信息设定
		arg.setUpdateUser(userInfo.getUserId());
		arg.setUpdateTime(LocalDateTime.now());
		// 创建者信息没有被设定的情况下，设定
		if(Objects.isNull(arg.getCreateUser())) {
			arg.setCreateUser(userInfo.getUserId());
			arg.setCreateTime(LocalDateTime.now());
		}
	}
}
