package com.perfect.community.api.controller.board;

import com.perfect.community.api.dto.board.BoardDTO;
import com.perfect.community.api.service.auth.AuthService;
import com.perfect.community.api.service.board.BoardService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;
import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class BoardController {

    private final Logger log = LogManager.getLogger();
    private final BoardService service;

    private final AuthService authService;

    @GetMapping({"/list", ""})
    public ResponseEntity<List<BoardDTO>> getBoardList() {
        return ResponseEntity.ok(service.getBoardList());
    }

    @GetMapping({"/info/{boardNo}"})
    public ResponseEntity<?> getBoardInfo(@PathVariable long boardNo) {
        try {
            return ResponseEntity.ok(service.getBoardInfo(boardNo));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createBoard(Principal principal, @RequestBody BoardDTO boardDTO) {
        try {
            if (principal == null)
                throw new AccountNotFoundException("Not logged in.");
            if (!authService.hasRole(principal.getName(), "admin"))
                throw new AccessDeniedException("Unauthorized.");
            return ResponseEntity.ok(service.createBoard(principal.getName(), boardDTO));
        } catch (AccountNotFoundException | AccessDeniedException unauthorizedException) {
            unauthorizedException.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(unauthorizedException.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/update/{boardNo}")
    public ResponseEntity<?> updateBoard(Principal principal, @PathVariable long boardNo, @RequestBody BoardDTO boardDTO) {
        try {
            if (principal == null)
                throw new AccountNotFoundException("Not logged in.");
            service.updateBoard(principal.getName(), boardNo, boardDTO);
            return ResponseEntity.ok(boardNo);
        } catch (AccountNotFoundException | AccessDeniedException unauthorizedException) {
            unauthorizedException.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(unauthorizedException.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{boardNo}")
    public ResponseEntity<?> deleteBoard(Principal principal, @PathVariable long boardNo) {
        try {
            if (principal == null)
                throw new AccountNotFoundException("Not logged in.");
            service.deleteBoard(principal.getName(), boardNo);
            return ResponseEntity.ok(boardNo);
        } catch (AccountNotFoundException | AccessDeniedException unauthorizedException) {
            unauthorizedException.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(unauthorizedException.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
