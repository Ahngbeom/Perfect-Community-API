package com.board.api.controller.board;

import com.board.api.dto.board.BoardDTO;
import com.board.api.service.board.BoardCRUD_Service;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class BoardCRUD_Controller {

    private final Logger log = LogManager.getLogger();
    private final BoardCRUD_Service service;

    @GetMapping({"/list", "/"})
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
            boardDTO.setCreatedUser(principal.getName());
            service.createBoard(boardDTO);
            return ResponseEntity.ok(boardDTO.getBno());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateBoard(Principal principal, @RequestBody BoardDTO boardDTO) {
        try {
            if (principal == null)
                throw new AuthenticationException("Not logged in.");
            service.updateBoard(principal.getName(), boardDTO);
            return ResponseEntity.ok(boardDTO.getBno());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/delete/{boardNo}")
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
