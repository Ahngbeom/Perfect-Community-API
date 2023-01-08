package com.perfect.community.view.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    // https://hungseong.tistory.com/60
    @GetMapping("/login")
    public String login(HttpServletRequest request) {
        request.getSession().setAttribute("prevPage", request.getHeader("Referer"));
        return "/login/login";
    }
}
