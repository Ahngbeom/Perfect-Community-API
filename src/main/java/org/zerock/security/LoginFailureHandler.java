package org.zerock.security;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
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
        try {
//            log.warn(request.getParameter("username"));
//            log.warn(request.getParameter("password"));

            log.error("Login Failed: " + exception.getLocalizedMessage());
            request.getSession().setAttribute("alertType", "Login");
            if (exception instanceof InternalAuthenticationServiceException || exception instanceof BadCredentialsException)
                request.getSession().setAttribute("alertStatus", "Invalid Accounts | Bad Credentials");
            else
                request.getSession().setAttribute("alertStatus", "FAILURE");
            response.sendRedirect("/login");
        } catch (Exception e) {
            log.error(e.getMessage());
        }

    }


}
