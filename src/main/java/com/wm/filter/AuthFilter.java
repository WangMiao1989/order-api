package com.wm.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        
        // 登录接口不需要认证
        String path = req.getRequestURI();
        if (path.contains("/login")) {
            chain.doFilter(request, response);
            return;
        }
        
        // 检查token
        String token = req.getHeader("Authorization");
        if (token == null || token.isEmpty()) {
            resp.setStatus(401);
            resp.getWriter().write("{\"error\":\"未授权\"}");
            return;
        }
        
        // token验证通过，继续执行
        chain.doFilter(request, response);
    }
}