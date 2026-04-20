package com.wm.aspect;

import java.lang.reflect.Method;
import java.util.Objects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.wm.annotation.Idempotent;
import com.wm.exception.IdempotentException;

@Aspect
@Component
public class IdempotentAspect { 
	
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	@Around("@annotation(com.wm.annotation.Idempotent)")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable{
		MethodSignature signature = (MethodSignature)joinPoint.getSignature();
		Method method = signature.getMethod();
		Idempotent idempotent = method.getAnnotation(Idempotent.class);
		
		// 幂等性key没有设定的场合
		if(Objects.isNull(idempotent.key())){
			return joinPoint.proceed();
		}
		
		// 幂等key存到redis里
		Boolean success = redisTemplate.opsForValue().setIfAbsent(idempotent.key(), "1", idempotent.expire(), idempotent.timeUnit());
		
		if( Boolean.FALSE.equals(success)) {
			// 键已存在，说明重复提交
			throw new IdempotentException();
		}
		
		try {
			return joinPoint.proceed();
		} finally {
			redisTemplate.delete(idempotent.key());
		}
	}
}
