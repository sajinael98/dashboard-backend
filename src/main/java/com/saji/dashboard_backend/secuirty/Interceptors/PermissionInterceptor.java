package com.saji.dashboard_backend.secuirty.Interceptors;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.saji.dashboard_backend.secuirty.services.JwtService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PermissionInterceptor implements HandlerInterceptor {
    private JwtService jwtService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String requestURI = request.getRequestURI();
        String method = request.getMethod();
        String token = request.getHeader("Authorization");

        System.out.println(requestURI);
        System.out.println(method);
        System.out.println(token);
        return true; // Allow the request to proceed
    }
}
