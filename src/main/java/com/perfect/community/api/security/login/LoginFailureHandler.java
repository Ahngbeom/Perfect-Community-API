package com.perfect.community.api.security.login;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.security.auth.login.LoginException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFailureHandler implements AuthenticationFailureHandler {

    private static final Logger log = LogManager.getLogger();

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        log.error("Login Failed: " + exception.getMessage());
        if (request.getHeader("x-requested-with").equals("XMLHttpRequest")) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } else {
            response.sendRedirect("/login?error");
        }
    }
}
