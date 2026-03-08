package com.wm.response;

import lombok.Data;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.wm.constant.HttpStatusCode;

@Data
//@JsonInclude(JsonInclude.Include.NON_NULL) // 为null的字段不返回
public class GlobalResponse<T> {
    
	// 状态
    private int status;
    private T data;
    private ErrorEntity error;
    
    // 成功响应（有数据）
    public static <T> GlobalResponse<T> success(T data) {
    	GlobalResponse<T> response = new GlobalResponse<>();
    	response.setStatus(HttpStatusCode.SUCCESS);
        response.setData(data);
        return response;
    }
    
    // 错误响应
    public static <T> GlobalResponse<T> globalError(int status, String code, String message) {
    	
    	ErrorEntity error = new ErrorEntity();
    	error.setGlobalError(CommonErrorEntity.builder().code(code).message(message).build());
    	error.setFieldsError(new ArrayList<>());
        
        GlobalResponse<T> response = new GlobalResponse<>();
        response.setStatus(status);
        response.setError(error);
        return response;
    }
}