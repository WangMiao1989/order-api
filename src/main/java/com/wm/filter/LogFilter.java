package com.wm.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

public class LogFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest req = (HttpServletRequest) request;
        
        // 记录请求日志
        System.out.println("请求URL: " + req.getRequestURL());
        System.out.println("请求方法: " + req.getMethod());
        
        long start = System.currentTimeMillis();
        
        // 继续执行
        chain.doFilter(request, response);
        
        long end = System.currentTimeMillis();
        System.out.println("请求耗时: " + (end - start) + "ms");
    }
}