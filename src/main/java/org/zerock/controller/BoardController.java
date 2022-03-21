package org.zerock.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardVO;
import org.zerock.service.BoardService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private static final Logger logger = LogManager.getLogger();

    private final BoardService service;

    @GetMapping("/list")
    public ModelAndView boardList(ModelAndView mv) {
        mv.addObject("list", service.getBoardList());
        mv.setViewName("board/list");
        return mv;
    }

    @GetMapping("/posts")
    public ModelAndView getPosts(ModelAndView mv, @RequestParam long bno) {
        mv.addObject("posts", service.getBoardByBno(bno));
        mv.setViewName("board/posts");
        return mv;
    }

    @GetMapping("/register")
    public ModelAndView registerGet(ModelAndView mv) {
        mv.setViewName("board/register");
        return mv;
    }

    @PostMapping("/register")
    public ModelAndView registerPost(RedirectAttributes redirectAttributes, ModelAndView mv, BoardVO board) throws Exception {
        try {
            if (service.registerBoard(board) != 1) {
                throw new Exception("Registration Failed");
            }
            redirectAttributes.addAttribute("result", board.getBno());
            mv.setViewName("redirect:/board/list");
        } catch (Exception e) {
            logger.error(e);
            redirectAttributes.addAttribute("result", e);
            mv.setViewName("redirect:/board/register");
        }
        return mv;
    }

}
