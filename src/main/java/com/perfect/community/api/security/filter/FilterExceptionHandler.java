package com.perfect.community.api.security.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FilterExceptionHandler extends Exception{

    private static final Logger log = LogManager.getLogger();

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }

    
}
