package org.zerock.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.MemberVO;

import java.security.Principal;

@Controller
public class LoginController {

    private static final Logger log = LogManager.getLogger();

    @GetMapping("/login")
    public ModelAndView doLogin(ModelAndView mv, RedirectAttributes redirectAttributes, String error, String logout) {
        log.info("Login");
        log.info("Error: " + error);
        log.info("Logout: " + logout);
        if (error != null) {
            mv.addObject("serverMessage", "Invalid Account");
        }
        if (logout != null) {
//            mv.addObject("serverMessage", "Logout.");
            redirectAttributes.addFlashAttribute("type", "Logout");
            redirectAttributes.addFlashAttribute("state", "SUCCESS");
            mv.setViewName("redirect:/");
            return mv;
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

    @GetMapping("/login/admin")
    public void doAdmin() {
        log.info("Admin");
    }

//    @GetMapping("/admin")
//    public ModelAndView doAdmin(ModelAndView mv, @AuthenticationPrincipal MemberVO member) {
//        log.info("Admin");
//        if (member == null) {
//            log.error("Invalid Member");
//        }
//        else {
//            log.info(member);
//        }
//        mv.setViewName("login/admin");
//        return mv;
//    }
}
