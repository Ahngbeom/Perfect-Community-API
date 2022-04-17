package org.zerock.controller;

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

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board/**")
public class BoardController {

    private static final Logger logger = LogManager.getLogger();

    private final BoardService service;

    @GetMapping("/list")
    public ModelAndView boardList(ModelAndView mv, @RequestParam(value = "page", defaultValue = "1") int page) {
        long postAmount = service.countBoard();
        mv.addObject("BoardList", service.getBoardListWithPage(page));
        mv.addObject("pageAmount", postAmount % 10 == 0 ? postAmount / 10 : postAmount / 10 + 1);
        mv.setViewName("board/list");
        return mv;
    }

    @GetMapping("/post")
    public ModelAndView getPosts(RedirectAttributes redirectAttributes, ModelAndView mv, long bno) {
        redirectAttributes.addFlashAttribute("type", "Read");
        try {
            if (service.getBoardByBno(bno) == null)
                throw new NullPointerException("Invalid Board Post.");
            redirectAttributes.addFlashAttribute("state", "SUCCESS");
            mv.addObject("Post", service.getBoardByBno(bno));
            mv.setViewName("board/post");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("state", "FAILURE");
            mv.setViewName("redirect:/error");
        }
        return mv;
    }

    @GetMapping("/register")
    public ModelAndView register(ModelAndView mv) {
        mv.setViewName("board/register");
        return mv;
    }

    @PostMapping("/register")
    public ModelAndView register(RedirectAttributes redirectAttributes, ModelAndView mv, BoardVO board) {
        redirectAttributes.addFlashAttribute("type", "Registration");
        try {
            if (service.registerBoard(board) != 1) {
                throw new Exception("Registration Failed");
            }
            redirectAttributes.addFlashAttribute("state", "SUCCESS");
            redirectAttributes.addAttribute("bno", board.getBno());
            mv.setViewName("redirect:/board/post");
        } catch (Exception e) {
            logger.error(e);
            redirectAttributes.addAttribute("state", "FAILURE");
            mv.setViewName("redirect:/board/register");
        }
        return mv;
    }

    @GetMapping("/modify")
    public ModelAndView modifyPost(ModelAndView mv, int bno) {
        mv.addObject("Post", service.getBoardByBno(bno));
        mv.setViewName("board/modify");
        return mv;
    }

    @PostMapping("/modify")
    public ModelAndView modifyPost(RedirectAttributes redirectAttributes, ModelAndView mv, BoardVO board) {
        redirectAttributes.addFlashAttribute("type", "Modify");
        try {
            if (service.modifyBoard(board) == 0)
                throw new Exception("Modify Board Failed.");
            redirectAttributes.addFlashAttribute("state", "SUCCESS");
            redirectAttributes.addAttribute("bno", board.getBno());
            mv.setViewName("redirect:/board/post");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("state", "FAILURE");
            mv.setViewName("redirect:/board/modify");
        }
        return mv;
    }

    @PostMapping("/removeAll") // 관리자 권한
    public ModelAndView removeAllPost(RedirectAttributes redirectAttributes, ModelAndView mv) {
        String  message;
        redirectAttributes.addFlashAttribute("type", "Remove ALL");
        try {
            if (service.countBoard() <= 0) {
                redirectAttributes.addFlashAttribute("state", "WARNING");
                message = "Board is Empty.";
            }
            else {
                if (service.removeAllBoard() == 0)
                    throw new Exception("Board All Remove Failed");
                if (service.initBnoValue() == 0)
                    throw new Exception("Initialize bno value of Board Failed");
                message = "Board Remove All Success.";
                redirectAttributes.addFlashAttribute("state", "SUCCESS");
            }
            logger.info(message);
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("state", "FAILURE");
        }
        mv.setViewName("redirect:/board/list");
        return (mv);
    }

    @PostMapping("/remove")
    public ModelAndView removePost(RedirectAttributes redirectAttributes, ModelAndView mv, int bno) {
        redirectAttributes.addFlashAttribute("type", "Remove");
        try {
            if (service.removeBoard(bno) == 0)
                throw new Exception("Board Remove Failed");
            if (service.countBoard() == 0) { // 삭제된 게시물 이외에 다른 게시물이 존재할 경우 bno를 초기화 하지않는다. 사용자가 스크랩한 게시물 조회 오류 가능성
                if (service.initBnoValue() == 0)
                    throw new Exception("Initialize bno value of Board Failed");
            }
            redirectAttributes.addFlashAttribute("state", "SUCCESS");
            mv.setViewName("redirect:/board/list");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("state", "FAILURE");
            redirectAttributes.addAttribute("bno", bno);
            mv.setViewName("redirect:/board/post");
        }
        return (mv);
    }

    @PostMapping("/createDummy")
    public ModelAndView createDummy(ModelAndView mv, long dummyAmount) {
        BoardVO board = new BoardVO("Test", "Test", "Administrator");
        long    limit = service.countBoard() + dummyAmount;
        long    number = service.countBoard();
        while (number < limit) {
            logger.info("Create Dummy : " + number);
            board.setTitle("Test" + number);
            board.setContent("Test" + number);
            service.registerBoard(board);
            number = service.countBoard();
        }
        mv.setViewName("redirect:/board/list");
        return mv;
    }

    @GetMapping("/search")
    public ModelAndView searchPost(ModelAndView mv, String keyword, @RequestParam(value = "page", defaultValue = "1") int page) {
        List<BoardVO> searchResult = service.searchBoardByKeyword(keyword);
        int size = searchResult.size();
        List<BoardVO> specPagesResult = new ArrayList<>(searchResult.subList(page * 10 - 10, size > page * 10 ? page * 10 : size));
        mv.addObject("BoardList", specPagesResult);
        mv.addObject("pageAmount", size % 10 == 0 ? size / 10 : size / 10 + 1);
        mv.setViewName("board/list");
        return mv;
    }
}
