package com.perfect.community.view.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

//    @GetMapping({"/", "/docs"})
//    public ModelAndView docs(ModelAndView mv) {
////        mv.setViewName("docs/intro");
////        mv.setViewName("pages/docs/intro");
//        return mv;
//    }

    @GetMapping({"/", "/docs"})
    public String docs(ModelAndView mv) {
//        return "default_layout";
        return "docs/intro";
    }

    @GetMapping({"/docs/*"})
    public ModelAndView docsWithCategory(ModelAndView mv) {
        return mv;
    }
}
