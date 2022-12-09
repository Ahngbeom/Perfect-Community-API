package org.zerock.controller.REST;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.zerock.DTO.PostsDTO;
import org.zerock.DTO.PostsSearchDTO;
import org.zerock.service.BoardService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board/search")
public class BoardSearchAPIController {

    private final BoardService boardService;

    @GetMapping("/")
    public ModelAndView searchPost(ModelAndView mv, PostsSearchDTO searchVO, @RequestParam(value = "page", defaultValue = "1") int page) {
        List<PostsDTO> searchResult = boardService.searchBoardByKeyword(searchVO);
        int size = searchResult.size();
        List<PostsDTO> specPagesResult = new ArrayList<>(searchResult.subList(page * 10 - 10, Math.min(size, page * 10)));
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
