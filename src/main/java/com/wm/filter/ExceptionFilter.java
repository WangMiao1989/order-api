package com.wm.filter;

import java.io.IOException;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wm.constant.CommonCode;
import com.wm.exception.AuthenticationException;
import com.wm.exception.BusinessException;
import com.wm.exception.ForbiddenException;
import com.wm.response.GlobalResponse;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;

public class ExceptionFilter implements Filter {
	
	private final ObjectMapper objectMapper;
	
	public ExceptionFilter(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
		try {
			chain.doFilter(request, response);
		} catch(Exception e) {
			handleException((HttpServletResponse)response, e);
		}
	}
	
	private void handleException(HttpServletResponse response, Exception e)  throws IOException{
		int status = 0;
		String responseStatus = "";
		String message = e.getMessage();
		
		if (e instanceof BusinessException) {
			status = HttpStatus.BAD_REQUEST.value();
			responseStatus = CommonCode.RESPONSE_BUSSINESS_ERROR;
		} else if (e instanceof AuthenticationException) {
			status = HttpStatus.UNAUTHORIZED.value();
			responseStatus = CommonCode.RESPONSE_UNAUTHORIZED;
		} else if (e instanceof ForbiddenException) {
			status = HttpStatus.FORBIDDEN.value();
			responseStatus = CommonCode.RESPONSE_FORBIDDEN;
		} else {
			status = HttpStatus.INTERNAL_SERVER_ERROR.value();
			responseStatus = CommonCode.RESPONSE_SYSTEM_ERROR;
		}

		GlobalResponse<Object> globalResponse = GlobalResponse.globalError(responseStatus, null, message);
		response.setStatus(status);
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().write(objectMapper.writeValueAsString(globalResponse));
	}
}
