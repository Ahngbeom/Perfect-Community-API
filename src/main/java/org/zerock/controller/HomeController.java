package org.zerock.controller;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.Principal;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

//@Controller
@RestController
@RequiredArgsConstructor
public class HomeController {

    private static final Logger log = LogManager.getLogger();

    @GetMapping("/")
    public ResponseEntity<Map<String, String>> home(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        Map<String, String> pageInfo = putRequestData(request);
        return ResponseEntity.ok(pageInfo);
    }

    @GetMapping("/error")
    public ResponseEntity<Map<String, String>> error(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        Map<String, String> pageInfo = putRequestData(request);
        return ResponseEntity.ok(pageInfo);
    }

    public static Map<String, String> putRequestData(HttpServletRequest request) throws UnsupportedEncodingException {
        Map<String, String> info = new LinkedHashMap<>();
        info.put("server_name", request.getServerName());
        info.put("server_port", String.valueOf(request.getServerPort()));
        info.put("locale", request.getLocale().toString());
        info.put("content_type", request.getContentType());
        info.put("character_encoding", request.getCharacterEncoding());
        info.put("method", request.getMethod());
        info.put("request_uri", request.getRequestURI());
        info.put("query_string", request.getQueryString() != null ? URLDecoder.decode(request.getQueryString(), "UTF-8") : null);
        info.put("auth_type", request.getAuthType());
        Principal principal = request.getUserPrincipal();
        info.put("principal", principal != null ? principal.toString() : null);

        Enumeration<String> attrNames = request.getSession().getAttributeNames();
        while (attrNames.hasMoreElements()) {
            String attrName = attrNames.nextElement();
            log.info(attrName + ": " + request.getSession().getAttribute(attrName));
        }

        return info;
    }

}
