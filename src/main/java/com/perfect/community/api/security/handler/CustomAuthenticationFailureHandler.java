package com.perfect.community.api.security.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException{
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
