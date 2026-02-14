package com.wm.config;

import com.wm.filter.CorsFilter;
import com.wm.filter.LogFilter;
import com.wm.filter.AuthFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

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
    public FilterRegistrationBean<LogFilter> logFilter() {
        FilterRegistrationBean<LogFilter> registrationBean = new FilterRegistrationBean<>();
        
        registrationBean.setFilter(new LogFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setName("LogFilter");
        registrationBean.setOrder(2); // 日志过滤器第二个执行
        
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<AuthFilter> authFilter() {
        FilterRegistrationBean<AuthFilter> registrationBean = new FilterRegistrationBean<>();
        
        registrationBean.setFilter(new AuthFilter());
        registrationBean.addUrlPatterns("/api/*"); // 只过滤 /api 路径
        registrationBean.setName("AuthFilter");
        registrationBean.setOrder(3); // 认证过滤器最后执行
        
        return registrationBean;
    }
}