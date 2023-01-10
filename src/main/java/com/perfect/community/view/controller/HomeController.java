package com.perfect.community.view.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @GetMapping({"/"})
    public String home() {
//        return "default_layout";
        return "pages/home";
    }

    @GetMapping({"/docs"})
    public String docs() {
//        return "default_layout";
        return "docs/intro";
    }

    @GetMapping({"/docs/*"})
    public ModelAndView docsWithCategory(ModelAndView mv) {
        return mv;
    }
}
