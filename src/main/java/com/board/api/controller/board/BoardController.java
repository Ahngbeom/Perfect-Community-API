package com.board.api.controller.board;

import com.board.api.dto.board.BoardDTO;
import com.board.api.service.auth.AuthService;
import com.board.api.service.board.BoardService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
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
                throw new AuthenticationException("Not logged in.");
            if (!authService.hasRole(principal.getName(), "admin"))
                throw new AuthenticationException("Unauthorized.");
            log.info(principal);
            boardDTO.setCreatedUser(principal.getName());
            return ResponseEntity.ok(service.createBoard(boardDTO));
        } catch (AuthenticationException authenticationException) {
            authenticationException.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(authenticationException.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/update/{boardNo}")
    public ResponseEntity<?> updateBoard(Principal principal, @PathVariable long boardNo, @RequestBody BoardDTO boardDTO) {
        try {
            if (principal == null)
                throw new AuthenticationException("Not logged in.");
            service.updateBoard(boardNo, principal.getName(), boardDTO);
            return ResponseEntity.ok(boardNo);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{boardNo}")
    public ResponseEntity<?> deleteBoard(Principal principal, @PathVariable long boardNo) {
        try {
            if (principal == null)
                throw new AuthenticationException("Not logged in.");
            service.deleteBoard(principal.getName(), boardNo);
            return ResponseEntity.ok(boardNo);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
