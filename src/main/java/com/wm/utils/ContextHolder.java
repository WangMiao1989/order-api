package com.wm.utils;

import java.util.Objects;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.wm.entity.TenantEntity;
import com.wm.exception.ForbiddenException;
import com.wm.exception.SystemException;

import jakarta.servlet.http.HttpServletRequest;

public class ContextHolder {
	
	public static String getTenantId() {
		TenantEntity tenantInfo = getContextHolder("tenantInfo", TenantEntity.class);
		if (Objects.isNull(tenantInfo)) {
			throw new ForbiddenException("tenant信息不存在");
        }
		return tenantInfo.getTenantId();
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getContextHolder(String attributeName, Class<T> type) {
		// 从 RequestContextHolder 获取当前Attribute
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (Objects.isNull(attributes)) {
        	return null;
        }
        // 从attribute来获取request
        HttpServletRequest request = attributes.getRequest();
        // 从request中获取参数的attribute
        Object value = request.getAttribute(attributeName);
        
        if (Objects.isNull(value)) {
        	return null;
        }
        
        // 类型转换检查
        if (!type.isAssignableFrom(value.getClass())) {
        	return null;
        }
        
        return (T) value; 
	}
}
