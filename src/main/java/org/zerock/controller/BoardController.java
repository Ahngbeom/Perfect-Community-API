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
import org.zerock.domain.BoardSearchVO;
import org.zerock.domain.BoardVO;
import org.zerock.domain.PageHeaderVO;
import org.zerock.domain.PaginationVO;
import org.zerock.service.BoardService;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private static final Logger log = LogManager.getLogger();

    private final BoardService boardService;

    private static final PageHeaderVO pageHeader = new PageHeaderVO("Board", "/board/list", null);

    private static final PaginationVO pagination = new PaginationVO();


    @GetMapping(value = {"/board/list", "/"})
    public ModelAndView boardList(HttpServletRequest request, ModelAndView mv, @RequestParam(value = "page", defaultValue = "1") int page) {
        //        long postAmount = service.countBoard();

        List<BoardVO> boardListByPage = boardService.getBoardListWithPage(page);
        mv.addObject("pageHeader", pageHeader);
        mv.addObject("BoardList", boardListByPage);
//        mv.addObject("pageAmount", postAmount % 10 == 0 ? postAmount / 10 : postAmount / 10 + 1);
//        if (page <= 10)
//            mv.addObject("pageAmount", 10);
//        else
//            mv.addObject("pageAmount", ((page / 10) + 1) * 10 <= postAmount / 10 ? ((page / 10) + 1) * 10 : postAmount / 10 + 1);
        pagination.setPostsAmount(boardService.countBoard());
        pagination.setPageAmount(boardService.countBoard() / 10 + 1);
        pagination.setPageMin(page % 10 == 0 ? page - 9 : page / 10 * 10 + 1);
        pagination.setPageMax(
                page % 10 > 0
                ? (page + 10) / 10 * 10 < pagination.getPageAmount() ? (page + 10) / 10 * 10 : pagination.getPageAmount()
                : page < pagination.getPageAmount() ? page : pagination.getPageAmount());
//        log.warn(pagination);
        mv.addObject("pagination", pagination);
        mv.setViewName("board/list");
        return mv;
    }


    @GetMapping("/board/posts")
    public ModelAndView getPosts(RedirectAttributes redirectAttributes, ModelAndView mv, long bno) {
        mv.addObject("pageHeader", pageHeader);
        redirectAttributes.addFlashAttribute("boardAlertType", "Board Read");
        try {
            if (boardService.getBoardByBno(bno) == null)
                throw new NullPointerException("Invalid Board Post.");
            redirectAttributes.addFlashAttribute("boardAlertStatus", "SUCCESS");
            log.warn(boardService.getBoardByBno(bno));
            mv.addObject("Posts", boardService.getBoardByBno(bno));
            mv.setViewName("board/posts");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("boardAlertStatus", "FAILURE");
            mv.setViewName("redirect:/error");
        }
        return mv;
    }

    @GetMapping("/board/register")
    public ModelAndView register(ModelAndView mv) {
        mv.addObject("pageHeader", pageHeader);
        mv.setViewName("board/register");
        return mv;
    }

    @PostMapping("/board/register")
    public ModelAndView register(RedirectAttributes redirectAttributes, ModelAndView mv, BoardVO board, Principal principal) {
        redirectAttributes.addFlashAttribute("boardAlertType", "Board Registration");
        try {
            log.warn(board);
            if (boardService.registerBoard(board) != 1) {
                throw new Exception("Registration Failed");
            }
            redirectAttributes.addFlashAttribute("boardAlertStatus", "SUCCESS");
            redirectAttributes.addAttribute("bno", board.getBno());
            mv.setViewName("redirect:/board/posts");
        } catch (Exception e) {
            log.error(e);
            redirectAttributes.addAttribute("boardAlertStatus", "FAILURE");
            mv.setViewName("redirect:/board/register");
        }
        return mv;
    }

    @GetMapping("/board/modify")
    public ModelAndView modifyPost(ModelAndView mv, int bno) {
        mv.addObject("pageHeader", pageHeader);
        mv.addObject("Post", boardService.getBoardByBno(bno));
        mv.setViewName("board/modify");
        return mv;
    }

//    @PostMapping("/modify")
//    public ModelAndView modifyPost(RedirectAttributes redirectAttributes, ModelAndView mv, BoardVO board) {
//        redirectAttributes.addFlashAttribute("boardAlertType", "Board Modify");
//        try {
//            if (boardService.modifyBoard(board) == 0)
//                throw new Exception("Modify Board Failed.");
//            redirectAttributes.addFlashAttribute("boardAlertStatus", "SUCCESS");
//            redirectAttributes.addAttribute("bno", board.getBno());
//            mv.setViewName("redirect:/board/posts");
//        } catch (Exception e) {
//            e.printStackTrace();
//            redirectAttributes.addFlashAttribute("boardAlertStatus", "FAILURE");
//            mv.setViewName("redirect:/board/modify");
//        }
//        return mv;
//    }

    @PostMapping("/board/removeAll") // 관리자 권한
    public ModelAndView removeAllPost(RedirectAttributes redirectAttributes, ModelAndView mv) {
        String message;
        redirectAttributes.addFlashAttribute("boardAlertType", "Board Remove ALL");
        try {
            if (boardService.countBoard() <= 0) {
                redirectAttributes.addFlashAttribute("boardAlertStatus", "WARNING");
                message = "Board is Empty.";
            } else {
                if (boardService.removeAllBoard() == 0)
                    throw new Exception("Board All Remove Failed");
                if (boardService.initBnoValue() == 0)
                    throw new Exception("Initialize bno value of Board Failed");
                message = "Board Remove All Success.";
                redirectAttributes.addFlashAttribute("boardAlertStatus", "SUCCESS");
            }
            log.info(message);
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("boardAlertStatus", "FAILURE");
        }
        mv.setViewName("redirect:/board/list");
        return (mv);
    }

//    @PostMapping("/remove")
//    public ModelAndView removePost(@AuthenticationPrincipal Principal principal, RedirectAttributes redirectAttributes, ModelAndView mv, int bno) {
//        redirectAttributes.addFlashAttribute("boardAlertType", "Board Remove");
//        try {
//            if (principal != null && (memberService.hasAdminRole(principal.getName()) ||
//                    boardService.authenticateForPosts(boardService.getBoardByBno(bno), memberService.readUser(principal.getName())) != null)) {
//                if (boardService.removeBoard(bno) == 0)
//                    throw new Exception("Board Remove Failed");
//                if (boardService.countBoard() == 0) { // 삭제된 게시물 이외에 다른 게시물이 존재할 경우 bno를 초기화 하지않는다. 사용자가 스크랩한 게시물 조회 오류 가능성
//                    if (boardService.initBnoValue() == 0)
//                        throw new Exception("Initialize bno value of Board Failed");
//                }
//            } else {
//                throw new AuthenticationException("Permission Denied.");
//            }
//            redirectAttributes.addFlashAttribute("boardAlertStatus", "SUCCESS");
//            mv.setViewName("redirect:/board/list");
//        } catch (Exception e) {
//            e.printStackTrace();
//            redirectAttributes.addFlashAttribute("boardAlertStatus", "FAILURE");
//            redirectAttributes.addAttribute("bno", bno);
//            mv.setViewName("board/posts");
//        }
//        return (mv);
//    }

    @PostMapping("/board/createDummy")
    public ModelAndView createDummy(ModelAndView mv, long dummyAmount) {
        BoardVO board = new BoardVO("Test", "Test", "Administrator", null);
        long limit = boardService.countBoard() + dummyAmount;
        long number = boardService.countBoard();
        while (number < limit) {
            log.info("Create Dummy : " + number);
            board.setTitle("Test" + number);
            board.setContent("Test" + number);
            boardService.registerBoard(board);
            number = boardService.countBoard();
        }
        mv.setViewName("redirect:/board/list");
        return mv;
    }

    @GetMapping("/board/search")
    public ModelAndView searchPost(ModelAndView mv, BoardSearchVO searchVO, @RequestParam(value = "page", defaultValue = "1") int page) {
        mv.addObject("pageHeader", pageHeader);
        List<BoardVO> searchResult = boardService.searchBoardByKeyword(searchVO);
        int size = searchResult.size();
        List<BoardVO> specPagesResult = new ArrayList<>(searchResult.subList(page * 10 - 10, Math.min(size, page * 10)));
        mv.addObject("BoardList", specPagesResult);
        mv.addObject("pageAmount", size % 10 == 0 ? size / 10 : size / 10 + 1);
        String checkedType = searchVO.isCheckTitle() ? "제목, " : "";
        checkedType += searchVO.isCheckContent() ? "내용, " : "";
        checkedType += searchVO.isCheckWriter() ? "작성자, " : "";
        checkedType = checkedType.substring(0, checkedType.length() - 2);
//        log.info("Requested Search Type: " + checkedType);
        if (size > 0) {
            mv.addObject("boardAlertType", "Board Search");
            mv.addObject("boardAlertStatus", "SUCCESS");
            mv.addObject("boardAlertMessage", "[" + checkedType + "] \"" + searchVO.getKeyword() + "\"에 대한 " + size + "개의 검색 결과 입니다.");
        } else {
            mv.addObject("boardAlertType", "Board Search");
            mv.addObject("boardAlertStatus", "FAILURE");
            mv.addObject("boardAlertMessage", "조건에 해당하는 게시물이 없습니다.");
        }
        mv.setViewName("board/list");
        return mv;
    }
}
