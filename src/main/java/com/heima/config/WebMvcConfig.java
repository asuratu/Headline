package com.heima.config;

import com.heima.interceptor.LoginProtectInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author asura
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private final LoginProtectInterceptor loginProtectInterceptor;

    public WebMvcConfig(LoginProtectInterceptor loginProtectInterceptor) {
        this.loginProtectInterceptor = loginProtectInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginProtectInterceptor).addPathPatterns("/news/**");
    }
}
