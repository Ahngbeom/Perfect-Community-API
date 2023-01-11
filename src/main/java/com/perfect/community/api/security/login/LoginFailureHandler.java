package com.perfect.community.api.security.login;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFailureHandler implements AuthenticationFailureHandler {

    private static final Logger log = LogManager.getLogger();

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        log.error("Login Failed: " + exception.getMessage());
        String xRequestedWithValue = request.getHeader("x-requested-with");
        String contentType = request.getContentType();
        if ((contentType != null && contentType.equals("application/json")) || (xRequestedWithValue != null && xRequestedWithValue.equals("XMLHttpRequest"))) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write(exception.getMessage());
        } else {
            response.sendRedirect("/login?error");
        }
    }
}
