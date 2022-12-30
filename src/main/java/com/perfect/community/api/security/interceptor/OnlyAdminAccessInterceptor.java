package com.perfect.community.api.security.interceptor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OnlyAdminAccessInterceptor implements HandlerInterceptor {

    private static final Logger log = LogManager.getLogger(OnlyAdminAccessInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!request.isUserInRole("ROLE_ADMIN")) {
            throw new AccessDeniedException("Only administrator can access.");
        }
        return true;
    }

}
