package com.example.common.config;

import com.example.common.interceptor.AuthorityInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author: pengjia
 * @date: 2020/9/22
 * @description:
 */
@Configuration
@ComponentScan(value = "com.example.common.interceptor")//扫描拦截器所在的包
public class WenAppConfig implements WebMvcConfigurer {
    private AuthorityInterceptor authorityInterceptor;

    @Autowired
    private WenAppConfig(AuthorityInterceptor authorityInterceptor){
        this.authorityInterceptor = authorityInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authorityInterceptor).addPathPatterns("/**")
                .excludePathPatterns("/user/login","/user/logout")
                .excludePathPatterns("/app/**")
                .excludePathPatterns("/static/images/**");
    }
}
