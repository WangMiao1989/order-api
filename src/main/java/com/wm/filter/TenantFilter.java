package com.wm.filter;

import java.io.IOException;
import java.util.Objects;

import com.wm.config.WhitelistProperties;
import com.wm.entity.TenantEntity;
import com.wm.exception.ForbiddenException;
import com.wm.mapper.TenantRepository;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

public class TenantFilter implements Filter {

    private final  TenantRepository tenantRepository;
    private final  WhitelistProperties whitelistProperties;
    
    public TenantFilter(TenantRepository tenantRepository, WhitelistProperties whitelistProperties ) {
    	this.tenantRepository = tenantRepository;
    	this.whitelistProperties = whitelistProperties;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
    	HttpServletRequest req = (HttpServletRequest) request;
    	// 白名单判定
        boolean isWhitelisted = whitelistProperties.getWhitelists().get("tenant").stream().anyMatch(url -> url.equals(req.getRequestURI()));
        if(isWhitelisted) {
        	chain.doFilter(request, response);
        	return;
        }
        
        String tenantId = req.getHeader("X-Tenant");
        // tenant未设定的场合，403
        if(Objects.isNull(tenantId)) {
        	// header里没设定的场合，request里如有，用request里的tenantId
        	tenantId = req.getParameter("x-tenant");
        	// 如果request也没有的话403异常
        	if(Objects.isNull(tenantId)) {
        		throw new ForbiddenException("tenant信息无效");
        	}
        }
        // tenant判定
        TenantEntity tenantInfo = tenantRepository.selectTenantInfo(tenantId);
        // tenant无效的场合，403
        if(Objects.isNull(tenantInfo)) {
        	throw new ForbiddenException("tenant信息无效");
        }

        // 把tenantInfo设定到requestContextHolder里
        req.setAttribute("tenantInfo", tenantInfo);
        
        // 继续执行
        chain.doFilter(request, response);
    }
}