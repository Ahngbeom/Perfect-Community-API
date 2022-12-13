package com.board.api.controller.board;

import com.board.api.dto.board.BoardDTO;
import com.board.api.service.board.BoardCRUD_Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class BoardCRUD_Controller {

    private final BoardCRUD_Service service;

    @GetMapping({"/list", "/"})
    public ResponseEntity<List<BoardDTO>> getBoardList() {
        return ResponseEntity.ok(service.getBoardList());
    }

    @GetMapping("/{boardNo}")
    public ResponseEntity<BoardDTO> getBoardInfo(@PathVariable long boardNo) {
        return ResponseEntity.ok(service.getBoardInfo(boardNo));
    }

    @PostMapping("/create")
    public ResponseEntity<?> createBoard(@RequestBody BoardDTO boardDTO) {
        try {
            service.createBoard(boardDTO);
            return ResponseEntity.ok(boardDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
