package com.perfect.community.view.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @GetMapping({"/", "/api/docs", "/api/help"})
    public ModelAndView home(ModelAndView mv) {
        mv.setViewName("api/docs");
        return mv;
    }
}
