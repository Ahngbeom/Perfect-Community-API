package org.zerock.controller.REST;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.zerock.DTO.PostsDTO;
import org.zerock.service.AuthService;
import org.zerock.service.BoardService;
import org.zerock.service.UserService;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class BoardAPIController {

    private static final Logger log = LogManager.getLogger();
    private final BoardService boardService;
    private final UserService userService;
    private final AuthService authService;

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

}
