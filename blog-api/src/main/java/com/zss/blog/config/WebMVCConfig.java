package com.zss.blog.config;

import com.zss.blog.handler.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 周书胜
 * @date 2023年04月20 16:52
 */
@Configuration
public class WebMVCConfig implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor loginInterceptor;

    /**
     * 前后端分离
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 跨域配置
        // 前端是8080端口，后端是8888端口
        // 前后端分离
        registry.addMapping("/**").allowedOrigins("http://localhost:8080");
    }

    /**
     * 配置拦截器, 使拦截器生效
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/test").addPathPatterns("/comments/create/change")
                .addPathPatterns("/articles/publish");
    }
}
