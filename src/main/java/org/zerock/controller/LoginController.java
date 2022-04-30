package org.zerock.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    private static final Logger log = LogManager.getLogger();

    @GetMapping("/login")
    public ModelAndView doLogin(ModelAndView mv, String error, String logout) {
        log.info("Login");
        log.info("Error: " + error);
        log.info("Logout: " + logout);
        if (error != null) {
            mv.addObject("serverMessage", "Invalid Account");
        }
        if (logout != null) {
            mv.addObject("serverMessage", "Logout.");
        }
        mv.addObject("title", "Login");

        mv.setViewName("/login/login");
        return mv;
    }

    @PostMapping("/logout")
    public void doLogout() {
        log.info("Logout");
    }

    @GetMapping("/all")
    public ModelAndView doAll(ModelAndView mv) {
        log.info("All");
        mv.setViewName("login/all");
        return mv;
    }

    @GetMapping("/member")
    public ModelAndView doMember(ModelAndView mv) {
        log.info("Member");
        mv.setViewName("login/member");
        return mv;
    }

    @GetMapping("/admin")
    public ModelAndView doAdmin(ModelAndView mv) {
        log.info("Admin");
        mv.setViewName("login/admin");
        return mv;
    }
}
