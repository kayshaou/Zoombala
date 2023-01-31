package com.jakesmommy.zblrestapi.decorator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class IsAuthorizedInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        if (!(handler instanceof HandlerMethod)) {
            log.info("Preflight request");
            return true;
        }
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.getWriter().append("{\"error\": \"UNAUTHORIZED\"}").println();
        return false;
    }
}
