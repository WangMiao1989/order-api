package com.wm.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.Errors;

import com.wm.constant.CommonCode;

@Data
//@JsonInclude(JsonInclude.Include.NON_NULL) // 为null的字段不返回
public class GlobalResponse<T> {
    
	// 状态
    private String status;
    private T data;
    private ErrorEntity error;
    
    // 成功响应（有数据）
    public static <T> GlobalResponse<T> success(T data) {
    	GlobalResponse<T> response = new GlobalResponse<>();
    	response.setStatus(CommonCode.RESPONSE_SUCCESS);
        response.setData(data);
        return response;
    }
    
    // global错误响应
    public static <T> GlobalResponse<T> globalError(String status, String code, String message) {
    	
    	ErrorEntity error = new ErrorEntity();
    	error.setGlobalError(GlobalErrorEntity.builder().code(code).message(message).build());
    	error.setFieldsError(new ArrayList<>());
        
        GlobalResponse<T> response = new GlobalResponse<>();
        response.setStatus(status);
        response.setError(error);
        return response;
    }
    
    // field错误响应
    public static <T> GlobalResponse<T> fieldError(String status, Errors errors) {
    	
    	ErrorEntity error = new ErrorEntity();
    	List<FieldErrorEntity> fields = new ArrayList<>();
    	errors.getAllErrors().stream().forEach(f -> {
    		fields.add(FieldErrorEntity.builder().fieldName(f.getObjectName()).message(f.getDefaultMessage()).build());
    	});
    	error.setFieldsError(fields);
        
        GlobalResponse<T> response = new GlobalResponse<>();
        response.setStatus(status);
        response.setError(error);
        return response;
    }
}