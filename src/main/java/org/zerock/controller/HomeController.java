package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    @GetMapping("/")
    public ModelAndView home() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("Title", "Basic Spring MVC");
        mv.addObject("Message", "Welcome!");
        mv.setViewName("index");
        return mv;
    }

    @GetMapping("/error")
    public ModelAndView error(ModelAndView mv) {
        mv.setViewName("error");
        return mv;
    }
}
