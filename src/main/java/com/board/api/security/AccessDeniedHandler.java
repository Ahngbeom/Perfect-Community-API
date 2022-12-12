package com.board.api.security;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.access.AccessDeniedException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AccessDeniedHandler implements org.springframework.security.web.access.AccessDeniedHandler {
    private static final Logger log = LogManager.getLogger();

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        log.error("Access Denied Handler");
        log.error("Redirect...");
        response.sendRedirect("/accessError");
    }
}
