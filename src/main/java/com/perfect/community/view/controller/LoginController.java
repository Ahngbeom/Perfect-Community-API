package com.perfect.community.view.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Controller
public class LoginController {

    private static final Logger log = LogManager.getLogger(LoginController.class);

    // https://hungseong.tistory.com/60
    @GetMapping("/login")
    public String login(HttpServletRequest request) throws IOException {
        request.getSession().setAttribute("prevPage", request.getHeader("Referer"));
        return "/login/login";
    }
}
