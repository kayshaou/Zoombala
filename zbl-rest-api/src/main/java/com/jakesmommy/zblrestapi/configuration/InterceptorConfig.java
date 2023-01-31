package com.jakesmommy.zblrestapi.configuration;

import com.jakesmommy.zblrestapi.decorator.IsAuthorizedInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class InterceptorConfig implements WebMvcConfigurer {

    @Autowired
    IsAuthorizedInterceptor isAuthorizedInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(isAuthorizedInterceptor).order(Ordered.HIGHEST_PRECEDENCE);
    }

}
