package org.zerock.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final BoardController boardController;
//
    @GetMapping("/")
    public ModelAndView home(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        return boardController.boardList(request, mv, 1);
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
