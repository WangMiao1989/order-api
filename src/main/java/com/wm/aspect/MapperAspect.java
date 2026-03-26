package com.wm.aspect;

import java.time.LocalDateTime;
import java.util.Objects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.wm.entity.BaseEntity;
import com.wm.entity.UserInfoEntity;
import com.wm.utils.ContextHolder;

@Aspect
@Component
public class MapperAspect {
	// *=任意返回值类型, com.wm.mapper..*=mapper下面包及子包所有类, .*=任意方法名, (..)=任意参数
	@Pointcut("execution(* com.wm.mapper..*.*(..))")
	public void mapperMethod() {}
	
	@Before("mapperMethod()")
	public void beforeMapper(JoinPoint joinPoint) {
		// userInfo取得
		UserInfoEntity userInfo = ContextHolder.getContextHolder("userInfo", UserInfoEntity.class);
		if(Objects.isNull(userInfo)) {
			return;
		}
		Object[] args = joinPoint.getArgs();
		for(Object arg: args) {
			if(arg instanceof BaseEntity) {
				setUserInfo((BaseEntity)arg, userInfo);
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
