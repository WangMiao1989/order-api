package com.wm.filter;

import java.io.IOException;
import java.util.Objects;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebFilter("/*") // 过滤所有请求
public class CorsFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 初始化方法，可以留空
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String origin = req.getHeader("Origin");
        if(isAllowOrigin(origin)) {
        	resp.setHeader("Access-Control-Allow-Origin", origin);
        	resp.setHeader("Access-Control-Allow-Credentials", "true");
        }
        // 设置 CORS 响应头
        resp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        resp.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization, X-Tenant, X-Auth-Required, X-Idempotent-Key");
        resp.setHeader("Access-Control-Max-Age", "3600"); // 预检请求缓存时间，单位秒

        // 处理 OPTIONS 预检请求
        if ("OPTIONS".equalsIgnoreCase(req.getMethod())) {
            resp.setStatus(HttpServletResponse.SC_OK);
            return; // 直接返回，不继续执行过滤器链
        }

        // 继续执行其他过滤器或目标资源
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // 销毁方法，可以留空
    }
    
    // 验证origin
    private boolean isAllowOrigin(String origin) {
    	// 未设定返回false
    	if(Objects.isNull(origin)) return false;
    	
    	// 生产环境的域名和ip访问的时候，ok
        if (origin.equals("https://www.online-ordering.site") ||
            origin.equals("http://49.232.151.8")) {
            return true;
        }
        
        // 匹配 localhost (任意端口)
        if (origin.matches("^https?://localhost(:[0-9]+)?$")) {
            return true;
        }
        
        // 匹配 192.168.x.x (任意端口)
        if (origin.matches("^https?://192\\.168\\.[0-9]{1,3}\\.[0-9]{1,3}(:[0-9]+)?$")) {
            return true;
        }
        return false;
    }
}