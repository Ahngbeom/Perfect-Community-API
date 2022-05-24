package org.zerock.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoginFailureHandler extends JSONConverterForAJAX implements AuthenticationFailureHandler {

    private static final Logger log = LogManager.getLogger();

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
//            log.warn(request.getParameter("username"));
//            log.warn(request.getParameter("password"));
        try {
            log.error("Login Failed: " + exception.getLocalizedMessage());
            request.getSession().setAttribute("signAlertType", "Login");
            if (exception instanceof InternalAuthenticationServiceException || exception instanceof BadCredentialsException)
                request.getSession().setAttribute("signAlertStatus", "Invalid Accounts | Bad Credentials");
            else if (exception instanceof DisabledException)
                request.getSession().setAttribute("signAlertStatus", "Account is Disabled");
            else
                request.getSession().setAttribute("signAlertStatus", "FAILURE");
            response.sendRedirect("/login");

            Map<String, Object> map = new HashMap<String, Object>();
    //        map.put("status", "FAILURE");
    //        map.put("redirectURL", "/login");

    //        JSONConverter(response, null, map);
        } catch (DisabledException e) {
            log.warn(e.getLocalizedMessage());
        }


//
    }

}
