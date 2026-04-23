package com.wm.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class LogAspect {

    /**
     * 拦截 Controller、Service、Mapper 包下的所有 public 方法
     */
    @Around("execution(* com.wm.controller..*.*(..)) || " +
            "execution(* com.wm.service..*.*(..)) || " +
            "execution(* com.wm.mapper..*.*(..))")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        // 只记录入参，不记录返回值
        log.info("【START】{}.{} parameter: {}", className, methodName, args);

        try {
            Object result = joinPoint.proceed();
            long cost = System.currentTimeMillis() - start;
            // 只记录耗时，不记录返回值内容
            log.info("【END】{}.{} cost: {}ms", className, methodName, cost);
            return result;
        } catch (Exception e) {
            long cost = System.currentTimeMillis() - start;
            log.error("【ERROR】{}.{} cost: {}ms, exception: {}", className, methodName, cost, e.getMessage(), e);
            throw e;
        }
    }
}