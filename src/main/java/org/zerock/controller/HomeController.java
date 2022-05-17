package org.zerock.controller;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.MemberVO;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private static final Logger log = LogManager.getLogger();
    private final BoardController boardController;
//
    @GetMapping("/")
    public ModelAndView home(RedirectAttributes redirectAttributes, ModelAndView mv) {
        mv.setViewName("/index");
        return mv;
    }

    @GetMapping("/error")
    public ModelAndView error(ModelAndView mv) {
        mv.addObject("serverMessage", "ERROR");
        mv.setViewName("error");
        return mv;
    }

    @GetMapping("/accessError")
    public ModelAndView accessError(ModelAndView mv) {
        mv.addObject("serverMessage", "Access Denied");
        mv.setViewName("error");
        return mv;
    }
}
