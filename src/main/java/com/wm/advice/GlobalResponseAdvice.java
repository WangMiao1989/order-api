package com.wm.advice;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.wm.response.GlobalResponse;

@RestControllerAdvice
public class GlobalResponseAdvice implements ResponseBodyAdvice<Object> {
	
	@Override
	public boolean supports(MethodParameter returnType, Class converterType) {
		Class<?> paramType = returnType.getParameterType();
	    // 不包装 byte[]、ResponseEntity 以及已经包装过的 GlobalResponse
		boolean isSpecial = byte[].class.isAssignableFrom(paramType) ||
                ResponseEntity.class.isAssignableFrom(paramType) ||
                GlobalResponse.class.equals(paramType);
		return !isSpecial;	
	}
    
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType,
                                 MediaType selectedContentType,
                                 Class selectedConverterType,
                                 ServerHttpRequest request,
                                 ServerHttpResponse response) {
        return GlobalResponse.success(body);
    }
}