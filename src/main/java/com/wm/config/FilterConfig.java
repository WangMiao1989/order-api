package com.wm.config;

import com.wm.filter.CorsFilter;
import com.wm.filter.ExceptionFilter;
import com.wm.filter.LogFilter;
import com.wm.filter.TenantFilter;
import com.wm.mapper.TenantRepository;
import com.wm.mapper.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wm.filter.AuthFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private TenantRepository tenantRepository;
	@Autowired
	private WhitelistProperties whitelistProperties;
	@Autowired
    private ObjectMapper objectMapper;

    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilter() {
        FilterRegistrationBean<CorsFilter> registrationBean = new FilterRegistrationBean<>();
        
        registrationBean.setFilter(new CorsFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setName("CorsFilter");
        registrationBean.setOrder(1); // 跨域过滤器最先执行
        
        return registrationBean;
    }
    
    @Bean
    public FilterRegistrationBean<ExceptionFilter> exceptionFilter() {
        FilterRegistrationBean<ExceptionFilter> registrationBean = new FilterRegistrationBean<>();
        
        registrationBean.setFilter(new ExceptionFilter(objectMapper));
        registrationBean.addUrlPatterns("/*");
        registrationBean.setName("ExceptionFilter");
        registrationBean.setOrder(2); 
        
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<LogFilter> logFilter() {
        FilterRegistrationBean<LogFilter> registrationBean = new FilterRegistrationBean<>();
        
        registrationBean.setFilter(new LogFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setName("LogFilter");
        registrationBean.setOrder(3);
        
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<TenantFilter> tenantFilter() {
        FilterRegistrationBean<TenantFilter> registrationBean = new FilterRegistrationBean<>();
        
        registrationBean.setFilter(new TenantFilter(tenantRepository, whitelistProperties));
        registrationBean.addUrlPatterns("/*"); 
        registrationBean.setName("TenantFilter");
        registrationBean.setOrder(4); 
        
        return registrationBean;
    }
    
    @Bean
    public FilterRegistrationBean<AuthFilter> authFilter() {
        FilterRegistrationBean<AuthFilter> registrationBean = new FilterRegistrationBean<>();
        
        registrationBean.setFilter(new AuthFilter(userRepository, whitelistProperties));
        registrationBean.addUrlPatterns("/*"); 
        registrationBean.setName("AuthFilter");
        registrationBean.setOrder(5); 
        
        return registrationBean;
    }
}