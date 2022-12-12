package com.board.api.controller.board;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.board.api.DTO.PostDTO;
import com.board.api.service.board.PostCRUD_Service;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class PostCRUD_Controller {

    private static final Logger log = LogManager.getLogger();
    private final PostCRUD_Service CRUDService;

    @GetMapping(value = {"/list", "/"})
    public ResponseEntity<?> boardList(@RequestParam(defaultValue = "1") int page) {
        try {
            return ResponseEntity.ok(CRUDService.getBoardListWithPage(page));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/info")
    public ResponseEntity<?> getPosts(long bno) {
        try {
            return ResponseEntity.ok(CRUDService.getBoardByBno(bno));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/registration")
    public ResponseEntity<?> register(PostDTO board) {
        try {
            CRUDService.registerBoard(board);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok(board.getBno());
    }

    @PostMapping("/modify")
    public ResponseEntity<?> modification(PostDTO postDTO) {
        try {
            CRUDService.modifyPost(postDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok(postDTO.getBno());
    }

    @PostMapping("/remove")
    public ResponseEntity<?> remove(int bno) {
        try {
            CRUDService.removeBoard(bno);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok(bno);
    }

}
