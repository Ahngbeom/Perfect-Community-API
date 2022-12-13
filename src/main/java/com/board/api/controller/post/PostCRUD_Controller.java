package com.board.api.controller.post;

import com.board.api.dto.PostListOptDTO;
import com.board.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.board.api.dto.PostDTO;
import com.board.api.service.board.PostCRUD_Service;

import javax.naming.AuthenticationException;
import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class PostCRUD_Controller {

    private static final Logger log = LogManager.getLogger();
    private final PostCRUD_Service CRUDService;

    private final UserService userService;

    @GetMapping(value = {"/{boardNo}/list"})
    public ResponseEntity<?> getPostList(@PathVariable long boardNo, @RequestBody(required = false) PostListOptDTO postListOptions) {
        try {
            log.info(postListOptions);
            return ResponseEntity.ok(CRUDService.getBoardListWithPage(postListOptions));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/info")
    public ResponseEntity<?> getPost(long pno) {
        try {
            return ResponseEntity.ok(CRUDService.getPostByBno(pno));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/registration")
    public ResponseEntity<?> register(Principal principal, PostDTO board) {
        try {
            if (principal == null)
                throw new AuthenticationException("Not logged in");
            board.setWriter(userService.getUserInfoById(principal.getName()).getUserName());
            CRUDService.registerPost(board);
        } catch (AuthenticationException authenticationException) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(authenticationException.getMessage());
        } catch (DataIntegrityViolationException dataIntegrityViolationException) {
            return ResponseEntity.badRequest().body(dataIntegrityViolationException.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok(board.getPno());
    }

    @PostMapping("/modify")
    public ResponseEntity<?> modification(PostDTO postDTO) {
        try {
            CRUDService.modifyPost(postDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok(postDTO.getPno());
    }

    @PostMapping("/remove")
    public ResponseEntity<?> remove(int pno) {
        try {
            CRUDService.removePost(pno);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok(pno);
    }

}
