package com.perfect.community.api.utils;

import org.springframework.security.web.savedrequest.DefaultSavedRequest;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

public class HttpServletCheck {

    public void headerPrint(HttpServletRequest request) {
        System.out.println("=== Request Header ===");
        Enumeration<String> reqHeadersEnum = request.getHeaderNames();
        while (reqHeadersEnum.hasMoreElements()) {
            String headerName = reqHeadersEnum.nextElement();
            System.out.println(headerName + ": " + request.getHeader(headerName));
        }
    }

    public void sessionPrint(HttpServletRequest request) {
        Enumeration<String> reqSessionEnum = request.getSession().getAttributeNames();
        while (reqSessionEnum.hasMoreElements()) {
            String sessionName = reqSessionEnum.nextElement();
            if (request.getSession().getAttribute(sessionName) instanceof DefaultSavedRequest) {
                System.out.println(sessionName + ": "
                        + "\nContextPath: " + ((DefaultSavedRequest) request.getSession().getAttribute(sessionName)).getContextPath()
                        + "\nRequestURI: " + ((DefaultSavedRequest) request.getSession().getAttribute(sessionName)).getRequestURI()
                        + "\nMethod: " + ((DefaultSavedRequest) request.getSession().getAttribute(sessionName)).getMethod()
                        + "\nRedirectUrl: " + ((DefaultSavedRequest) request.getSession().getAttribute(sessionName)).getRedirectUrl()
                        + "\nPathInfo: " + ((DefaultSavedRequest) request.getSession().getAttribute(sessionName)).getPathInfo()
                        + "\nQueryString: " + ((DefaultSavedRequest) request.getSession().getAttribute(sessionName)).getQueryString()
                        + "\nParameterMap: " + ((DefaultSavedRequest) request.getSession().getAttribute(sessionName)).getParameterMap()
                );
            } else {
                System.out.println(sessionName + ": " + request.getSession().getAttribute(sessionName));
            }
        }
    }

    public void responsePrint(HttpServletResponse response) {
        System.out.println("=== Response Header ===");
        response.getHeaderNames().forEach(name -> System.out.println(name + ": " + response.getHeader(name)));
    }

}
