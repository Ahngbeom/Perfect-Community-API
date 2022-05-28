package org.zerock.controller;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.zerock.domain.BoardVO;
import org.zerock.service.BoardService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/board")
public class RESTBoardController {

    private static final Logger log = LogManager.getLogger();

    private final BoardService service;
    private final PasswordEncoder passwordEncoder;

    @GetMapping(value = {"/list", "/"})
    public List<BoardVO> boardList(@RequestParam(defaultValue = "1") int page) {
        long postAmount = service.countBoard();
        //        mv.addObject("pageAmount", postAmount % 10 == 0 ? postAmount / 10 : postAmount / 10 + 1);
        return service.getBoardListWithPage(1);
    }

    @PostMapping("/passwordCheck")
    public boolean passwordCheck(long bno) {
        if (service.getBoardByBno(bno).getBoardPassword() != null) {
            return true;
        }
        return false;
    }

    @PostMapping("/passwordMatches")
    public boolean passwordMatches(long bno, String password) {
        BoardVO board = service.getBoardByBno(bno);
        if (board.getBoardPassword() != null) {
            return passwordEncoder.matches(password, board.getBoardPassword());
        }
        return false;
    }
}
