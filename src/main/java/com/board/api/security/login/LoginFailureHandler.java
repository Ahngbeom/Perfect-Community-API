package com.board.api.security.login;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import com.board.api.security.JSONConverterForAJAX;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFailureHandler extends JSONConverterForAJAX implements AuthenticationFailureHandler {

    private static final Logger log = LogManager.getLogger();

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        try {
//            request.getSession().setAttribute("signAlertType", "Login");
//            if (exception instanceof InternalAuthenticationServiceException || exception instanceof BadCredentialsException)
//                request.getSession().setAttribute("signAlertStatus", "Invalid Accounts | Bad Credentials");
//            else if (exception instanceof DisabledException)
//                request.getSession().setAttribute("signAlertStatus", "Account is Disabled");
//            else
//                request.getSession().setAttribute("signAlertStatus", "FAILURE");
            log.error("Login Failed: " + exception.getLocalizedMessage());
//            response.sendRedirect("/login");
            response.sendRedirect("/error?" + exception.getMessage());
        } catch (DisabledException e) {
            log.warn(e.getLocalizedMessage());
        }
    }
}
