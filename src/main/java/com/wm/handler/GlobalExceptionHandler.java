package com.wm.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.HttpStatus;

import com.wm.constant.CommonCode;
import com.wm.exception.AuthenticationException;
import com.wm.exception.BusinessException;
import com.wm.exception.IdempotentException;
import com.wm.exception.SystemException;
import com.wm.response.GlobalResponse;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    
    // 处理业务异常
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BusinessException.class)
    public GlobalResponse<Void> handleBusinessException(BusinessException e) {
        log.error("业务异常: code={}, message={}", e.getCode(), e.getMessage());
        return GlobalResponse.globalError(CommonCode.RESPONSE_BUSSINESS_ERROR, e.getCode(), e.getMessage());
    }
    
    // 认证异常
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AuthenticationException.class)
    public GlobalResponse<Void> handleSystemErrorException(AuthenticationException e){
        log.error("认证异常: {}", e.getMessage(), e);
        return GlobalResponse.globalError(CommonCode.RESPONSE_UNAUTHORIZED, null, e.getMessage());
    }
	
	// 幂等性error
	@ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(IdempotentException.class)
    public GlobalResponse<Void> handleSystemErrorException(IdempotentException e){
        log.error("幂等性异常: {}", e.getMessage(), e);
        return GlobalResponse.globalError(CommonCode.RESPONSE_CONFLICT, null, "幂等性异常");
    }
    
     // 处理所有其他异常
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(SystemException.class)
    public GlobalResponse<Void> handleException(SystemException e){
		log.error("系统异常: {}", e.getMessage(), e);
        return GlobalResponse.globalError(CommonCode.RESPONSE_SYSTEM_ERROR, null, "系统异常");
    }
    
    // 处理所有其他异常
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public GlobalResponse<Void> handleException(Exception e){
        log.error("系统异常: {}", e.getMessage(), e);
        return GlobalResponse.globalError(CommonCode.RESPONSE_SYSTEM_ERROR, null, "系统异常");
    }
}