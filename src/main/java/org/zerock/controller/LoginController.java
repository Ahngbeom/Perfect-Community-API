package org.zerock.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class LoginController {

    private static final Logger log = LogManager.getLogger();

    @GetMapping("/login")
    public ModelAndView doLogin(ModelAndView mv, HttpServletRequest request, RedirectAttributes redirectAttributes, Principal principal) {

        if (request.getHeader("Referer") != null && !request.getHeader("Referer").contains("/login")) // 이전 페이지가 로그인 페이지일 경우(로그인 실패, 로그인 페이지 연속 이동 등) prevPage를 설정하지 않음
            request.getSession().setAttribute("prevPage", request.getHeader("Referer"));
        if (principal != null) {
            redirectAttributes.addFlashAttribute("memberAlertType", "Already Signed In");
            redirectAttributes.addFlashAttribute("memberAlertStatus", "WARNING");
            mv.setViewName("redirect:/" + request.getHeader("Referer"));
        } else {
            mv.addObject("title", "Login");
            mv.setViewName("/login/login");
        }
        return mv;
    }

}
