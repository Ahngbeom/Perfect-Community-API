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
import org.zerock.domain.BoardSearchVO;
import org.zerock.domain.BoardVO;
import org.zerock.domain.PageHeaderVO;
import org.zerock.domain.PaginationVO;
import org.zerock.service.BoardService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board/")
public class BoardController {

    private static final Logger log = LogManager.getLogger();

    private final BoardService boardService;

    private static final PageHeaderVO pageHeader = new PageHeaderVO("Board", "/board/list", null);

    private static final PaginationVO pagination = new PaginationVO();


    @GetMapping(value = {"/list", "/"})
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
        pagination.setPostsAmount(boardService.countPosts());
        pagination.setPageAmount(boardService.countPosts() / 10 + 1);
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

    @GetMapping("/register")
    public ModelAndView register(ModelAndView mv) {
        mv.addObject("pageHeader", pageHeader);
        mv.setViewName("board/register");
        return mv;
    }

    @GetMapping("/modify")
    public ModelAndView modifyPost(ModelAndView mv, int bno) {
        mv.addObject("pageHeader", pageHeader);
        mv.addObject("Post", boardService.getBoardByBno(bno));
        mv.setViewName("board/modify");
        return mv;
    }

    @PostMapping("/createDummy")
    public ModelAndView createDummy(ModelAndView mv, long dummyAmount) {
        BoardVO board = new BoardVO("Test", "Test", "Administrator", null);
        long limit = boardService.countPosts() + dummyAmount;
        long number = boardService.countPosts();
        while (number < limit) {
            log.info("Create Dummy : " + number);
            board.setTitle("Test" + number);
            board.setContents("Test" + number);
            boardService.registerBoard(board);
            number = boardService.countPosts();
        }
        mv.setViewName("redirect:/board/list");
        return mv;
    }

    @GetMapping("/search")
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
