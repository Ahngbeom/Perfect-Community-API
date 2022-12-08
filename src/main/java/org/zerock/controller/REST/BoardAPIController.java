package org.zerock.controller.REST;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.zerock.DTO.PostsDTO;
import org.zerock.service.BoardService;
import org.zerock.service.MemberService;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class BoardAPIController {

    private static final Logger log = LogManager.getLogger();
    private final BoardService boardService;
    private final MemberService memberService;

    @GetMapping(value = {"/list", "/"})
    public ResponseEntity<?> boardList(@RequestParam(defaultValue = "1") int page) {
        try {
            return ResponseEntity.ok(boardService.getBoardListWithPage(page));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/info")
    public ResponseEntity<?> getPosts(long bno) {
        try {
            return ResponseEntity.ok(boardService.getBoardByBno(bno));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/registration")
    public ResponseEntity<?> register(PostsDTO board) {
        try {
            boardService.registerBoard(board);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok(board.getBno());
    }

    @PostMapping("/modify")
    public ResponseEntity<?> modification(PostsDTO postsDTO) {
        try {
            boardService.modifyPost(postsDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok(postsDTO.getBno());
    }

    @PostMapping("/remove")
    public ResponseEntity<?> remove(int bno) {
        try {
            boardService.removeBoard(bno);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok(bno);
    }

//    @PostMapping("/modify")
//    public ResponseEntity<?> modification(@AuthenticationPrincipal Principal principal, @RequestBody BoardVO board) throws RuntimeException {
//        try {
//            log.warn(board);
//            if (principal == null && boardService.postHasPassword(board.getBno())) {
//                if (boardService.modifyPostWithPassword(board, false) == 0)
//                    throw new RuntimeException("Modify Board Failed");
//            } else {
//                assert principal != null;
//                if (boardService.authenticateForPosts(boardService.getBoardByBno(board.getBno()), memberService.readUser(principal.getName())) != null) {
//                    if (boardService.modifyPost(board) == 0)
//                        throw new RuntimeException("Modify Board Failed");
//                } else {
//                    throw new RuntimeException("Permission Denied");
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
//        }
//        return ResponseEntity.ok(true);
//    }
//
//    @PostMapping("/remove")
//    public ResponseEntity<?> remove(@AuthenticationPrincipal Principal principal, @RequestBody BoardVO board) {
//        try {
//            log.warn(board);
//            if (principal == null && boardService.postHasPassword(board.getBno())) {
//                if (boardService.removePostWithPassword(board, false) == 0)
//                    throw new RuntimeException("Board Remove Failed");
//            } else {
//                assert principal != null;
//                if (boardService.authenticateForPosts(boardService.getBoardByBno(board.getBno()), memberService.readUser(principal.getName())) != null) {
//                    if (boardService.removeBoard(board.getBno()) == 0)
//                        throw new RuntimeException("Board Remove Failed");
//                } else if (memberService.hasAdminRole(principal.getName())) {
//                    if (boardService.removePostWithPassword(board, true) == 0)
//                        throw new RuntimeException("Board Remove Failed");
//                } else {
//                    throw new AuthenticationException("Permission Denied.");
//                }
//            }
//            if (boardService.countPosts() == 0) { // 삭제된 게시물 이외에 다른 게시물이 존재할 경우 bno를 초기화 하지않는다. (사용자가 게시물을 스크랩한 기록이 있을 경우, 추후 조회 오류 가능성)
//                if (boardService.initBnoValue() == 0)
//                    throw new RuntimeException("Initialize bno value of Board Failed");
//            }
//        } catch (AuthenticationException e) {
////            e.printStackTrace();
//            log.error(e.getMessage());
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
//
//        } catch (Exception e) {
////            e.printStackTrace();
//            log.error(e.getMessage());
//            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
//        }
//        return ResponseEntity.ok(true);
//    }

    @PostMapping("/removeAll") // 관리자 권한
    public ResponseEntity<String> removeAllPost() throws RuntimeException {
        try {
            boardService.removeAllBoard();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.ok("SUCCESS");
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

    @PostMapping("/passwordCheck")
    public boolean passwordCheck(long bno) {
        log.warn(boardService.getBoardByBno(bno).getBoardPassword());
        return boardService.getBoardByBno(bno).getBoardPassword() != null;
    }

    @PostMapping("/passwordMatches")
    public ResponseEntity<?> passwordMatches(@RequestBody PostsDTO board) {
//        BoardVO board = boardService.getBoardByBno(bno);
//        if (board.getBoardPassword() != null) {
//            return passwordEncoder.matches(password, board.getBoardPassword());
//        }
        log.warn(board);
        return ResponseEntity.ok(boardService.postPasswordMatches(board.getBno(), board.getBoardPassword()));
    }


}
