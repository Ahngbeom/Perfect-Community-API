package com.perfect.community.api.controller.board;

import com.perfect.community.api.dto.board.BoardDTO;
import com.perfect.community.api.service.board.BoardService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class BoardController {

    private final Logger log = LogManager.getLogger();
    private final BoardService service;

    @GetMapping({""})
    public ResponseEntity<List<BoardDTO>> getBoardList() {
        return ResponseEntity.ok(service.getBoardList());
    }

    @GetMapping({"/{boardNo}"})
    public ResponseEntity<?> getBoardInfo(@PathVariable long boardNo) {
        try {
            return ResponseEntity.ok(service.getBoardInfo(boardNo));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("")
    public ResponseEntity<?> createBoard(Principal principal, @RequestBody BoardDTO boardDTO) {
        try {
            return ResponseEntity.ok(service.createBoard(principal.getName(), boardDTO));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{boardNo}")
    public ResponseEntity<?> updateBoard(Principal principal, @PathVariable long boardNo, @RequestBody BoardDTO boardDTO) {
        try {
            service.updateBoard(principal.getName(), boardNo, boardDTO);
            return ResponseEntity.ok(boardNo);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{boardNo}")
    public ResponseEntity<?> deleteBoard(Principal principal, @PathVariable long boardNo) {
        try {
            service.deleteBoard(principal.getName(), boardNo);
            return ResponseEntity.ok(boardNo);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
