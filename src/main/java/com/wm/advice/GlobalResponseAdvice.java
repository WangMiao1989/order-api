package com.wm.advice;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.wm.response.GlobalResponse;

@RestControllerAdvice
public class GlobalResponseAdvice implements ResponseBodyAdvice<Object> {
	
	@Override
	public boolean supports(MethodParameter returnType, Class converterType) {
		// 不为GlobalResponse的场合，进行封装
		return !returnType.getParameterType().equals(GlobalResponse.class);
	}
    
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType,
                                 MediaType selectedContentType,
                                 Class selectedConverterType,
                                 ServerHttpRequest request,
                                 ServerHttpResponse response) {
        
        // 如果是 void 方法
        if (body == null) {
            return GlobalResponse.success();
        }
        
        // 包装成 ApiResponse
        return GlobalResponse.success(body);
    }
}