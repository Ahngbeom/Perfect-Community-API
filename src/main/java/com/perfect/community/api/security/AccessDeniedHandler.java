package com.perfect.community.api.security;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.access.AccessDeniedException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AccessDeniedHandler implements org.springframework.security.web.access.AccessDeniedHandler {
    private static final Logger log = LogManager.getLogger();

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
//        response.sendRedirect("/");
//        log.error(accessDeniedException.getMessage());
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, accessDeniedException.getMessage());
    }
}
