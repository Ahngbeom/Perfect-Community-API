package org.zerock.controller.REST;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.zerock.domain.BoardVO;
import org.zerock.service.BoardService;
import org.zerock.service.MemberService;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/board")
public class RESTBoardController {

    private static final Logger log = LogManager.getLogger();

    private final BoardService boardService;
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping(value = {"/list", "/"})
    public List<BoardVO> boardList(@RequestParam(defaultValue = "1") int page) {
        long postAmount = boardService.countBoard();
        //        mv.addObject("pageAmount", postAmount % 10 == 0 ? postAmount / 10 : postAmount / 10 + 1);
        return boardService.getBoardListWithPage(1);
    }

    @GetMapping("/hasPassword")
    public ResponseEntity<?> hasPassword(@AuthenticationPrincipal Principal principal, @RequestParam("bno") long bno) {
        try {
            if (principal == null)
                return ResponseEntity.ok(boardService.postHasPassword(bno));
            else if (memberService.hasAdminRole(principal.getName())) {
                return ResponseEntity.ok("isAdmin");
            }
        } catch (Exception e) {
//            e.printStackTrace();
            log.error(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(false);
    }

    @PostMapping("/remove")
    public ResponseEntity<?> removePost(@AuthenticationPrincipal Principal principal, @RequestBody BoardVO board) {
        try {
            log.warn(board);
            if (principal == null && boardService.postHasPassword(board.getBno())) {
                if (boardService.removePostWithPassword(board, false) == 0)
                    throw new RuntimeException("Board Remove Failed");
            } else if (boardService.authenticateForPosts(boardService.getBoardByBno(board.getBno()), memberService.readUser(principal.getName())) != null) {
                if (boardService.removeBoard(board.getBno()) == 0)
                    throw new RuntimeException("Board Remove Failed");
            } else if (memberService.hasAdminRole(principal.getName())) {
                if (boardService.removePostWithPassword(board, true) == 0)
                    throw new RuntimeException("Board Remove Failed");
            } else {
                throw new AuthenticationException("Permission Denied.");
            }
            if (boardService.countBoard() == 0) { // 삭제된 게시물 이외에 다른 게시물이 존재할 경우 bno를 초기화 하지않는다. (사용자가 게시물을 스크랩한 기록이 있을 경우, 추후 조회 오류 가능성)
                if (boardService.initBnoValue() == 0)
                    throw new RuntimeException("Initialize bno value of Board Failed");
            }
        } catch (AuthenticationException e) {
//            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());

        } catch (Exception e) {
//            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
        }
        return ResponseEntity.ok(true);
    }

    @PostMapping("/passwordCheck")
    public boolean passwordCheck(long bno) {
        log.warn(boardService.getBoardByBno(bno).getBoardPassword());
        if (boardService.getBoardByBno(bno).getBoardPassword() != null) {
            return true;
        }
        return false;
    }

    @PostMapping("/passwordMatches")
    public boolean passwordMatches(long bno, String password) {
        BoardVO board = boardService.getBoardByBno(bno);
        if (board.getBoardPassword() != null) {
            return passwordEncoder.matches(password, board.getBoardPassword());
        }
        return false;
    }
}
