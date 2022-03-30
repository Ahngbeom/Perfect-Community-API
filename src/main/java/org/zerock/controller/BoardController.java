package org.zerock.controller;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardVO;
import org.zerock.service.BoardService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board/**")
public class BoardController {

    private static final Logger logger = LogManager.getLogger();

    private final BoardService service;

    @GetMapping("/list")
    public ModelAndView boardList(ModelAndView mv) {
        mv.addObject("BoardList", service.getBoardList());
        mv.setViewName("board/list");
        return mv;
    }

    @GetMapping("/post")
    public ModelAndView getPosts(ModelAndView mv, long bno) {
        mv.addObject("post", service.getBoardByBno(bno));
        mv.setViewName("board/post");
        return mv;
    }

    @GetMapping("/register")
    public ModelAndView register(ModelAndView mv) {
        mv.setViewName("board/register");
        return mv;
    }

    @PostMapping("/register")
    public ModelAndView register(RedirectAttributes redirectAttributes, ModelAndView mv, BoardVO board) throws Exception {
        try {
            if (service.registerBoard(board) != 1) {
                throw new Exception("Registration Failed");
            }
            redirectAttributes.addFlashAttribute("result", board.getBno());
            redirectAttributes.addAttribute("bno", board.getBno());
            mv.setViewName("redirect:/board/post");
        } catch (Exception e) {
            logger.error(e);
            redirectAttributes.addAttribute("result", e);
            mv.setViewName("redirect:/board/register");
        }
        return mv;
    }

    @GetMapping("/modify")
    public ModelAndView modifyPost(ModelAndView mv, int bno) {
        mv.setViewName("board/modify");
        return mv;
    }

    @PostMapping("/modify")
    public ModelAndView modifyPost(RedirectAttributes redirectAttributes, ModelAndView mv, BoardVO board) throws Exception {
        try {
            if (service.modifyBoard(board) == 0)
                throw new Exception("Modify Board Failed.");
            redirectAttributes.addAttribute("result", "SUCCESS");
            mv.setViewName("redirect:/board/post");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addAttribute("result", "FAILURE");
            mv.setViewName("redirect:/board/modify");
        }
        return mv;
    }

    @PostMapping("/removeAll") // 관리자 권한
    public ModelAndView removeAllPost(RedirectAttributes redirectAttributes, ModelAndView mv) throws Exception{
        String  message;
        try {
            if (service.countBoard() <= 0) {
                message = "Board is Empty.";
                redirectAttributes.addAttribute("result", "WARNING");
            }
            else {
                if (service.removeAllBoard() == 0)
                    throw new Exception("Board All Remove Failed");
                if (service.initBnoValue() == 0)
                    throw new Exception("Initialize bno value of Board Failed");
                message = "Board Remove All Success.";
                redirectAttributes.addAttribute("result", "SUCCESS");
            }
            logger.info(message);
            redirectAttributes.addAttribute("message", message);
        } catch (Exception e) {
            redirectAttributes.addAttribute("result", "FAILURE");
            redirectAttributes.addAttribute("message", e.getMessage());
            e.printStackTrace();
        }
        mv.setViewName("redirect:/board/list");
        return (mv);
    }

    @PostMapping("/remove")
    public ModelAndView removePost(RedirectAttributes redirectAttributes, ModelAndView mv, int bno) throws Exception {
        try {
            if (service.removeBoard(bno) == 0)
                throw new Exception("Board Remove Failed");
            if (service.countBoard() == 0) { // 삭제된 게시물 이외에 다른 게시물이 존재할 경우 bno를 초기화 하지않는다. 사용자가 스크랩한 게시물 조회 오류 가능성
                if (service.initBnoValue() == 0)
                    throw new Exception("Initialize bno value of Board Failed");
            }
            redirectAttributes.addFlashAttribute("result", "SUCCESS");
            mv.setViewName("redirect:/board/list");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addAttribute("result", "FAILURE");
            redirectAttributes.addAttribute("bno", bno);
            mv.setViewName("board/post");
        }
        return (mv);
    }

}
