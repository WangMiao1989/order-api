package com.wm.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.wm.constant.HttpStatusCode;
import com.wm.exception.AuthenticationException;
import com.wm.exception.BusinessException;
import com.wm.exception.SystemErrorException;
import com.wm.response.GlobalResponse;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    
    // 处理业务异常
    @ExceptionHandler(BusinessException.class)
    public GlobalResponse<Void> handleBusinessException(BusinessException e) {
        log.error("业务异常: code={}, message={}", e.getCode(), e.getMessage());
        return GlobalResponse.globalError(HttpStatusCode.BAD_REQUEST, e.getCode(), e.getMessage());
    }
    
    // 认证异常
    @ExceptionHandler(AuthenticationException.class)
    public GlobalResponse<Void> handleSystemErrorException(AuthenticationException e){
        log.error("系统异常: {}", e.getMessage(), e);
        return GlobalResponse.globalError(HttpStatusCode.UNAUTHORIZED, null,"认证失败！");
    }
    
    // 处理所有其他异常
    @ExceptionHandler(SystemErrorException.class)
    public GlobalResponse<Void> handleException(SystemErrorException e){
        log.error("系统异常: {}", e.getMessage(), e);
        return GlobalResponse.globalError(HttpStatusCode.INTERNAL_ERROR, null, "系统异常！");
    }
    
    // 处理所有其他异常
    @ExceptionHandler(Exception.class)
    public GlobalResponse<Void> handleException(Exception e){
        log.error("系统异常: {}", e.getMessage(), e);
        return GlobalResponse.globalError(HttpStatusCode.INTERNAL_ERROR, null, "系统异常！");
    }
}