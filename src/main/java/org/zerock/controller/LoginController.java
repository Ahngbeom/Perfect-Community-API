package org.zerock.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    private static final Logger log = LogManager.getLogger();

    @GetMapping("/login")
    public ModelAndView doLogin(ModelAndView mv, HttpServletRequest request, RedirectAttributes redirectAttributes, String error, String logout) {
//        log.info("Login");
//        log.info("Error: " + error);
//        log.info("Logout: " + logout);
        if (error != null) {
            redirectAttributes.addFlashAttribute("serverMessage", "Invalid Account");
            mv.setViewName("redirect:/login");
            return mv;
        }
        if (logout != null) {
//            mv.addObject("serverMessage", "Logout.");
            redirectAttributes.addFlashAttribute("type", "Logout");
            redirectAttributes.addFlashAttribute("state", "SUCCESS");
            mv.setViewName("redirect:/board/list");
            return mv;
        }
        if (request.getHeader("Referer") != null && !request.getHeader("Referer").contains("/login")) // 이전 페이지가 로그인 페이지일 경우(로그인 실패, 로그인 페이지 연속 이동 등) prevPage를 설정하지 않음
            request.getSession().setAttribute("prevPage", request.getHeader("Referer"));
        mv.addObject("title", "Login");
        mv.setViewName("/login/login");
        return mv;
    }

}
