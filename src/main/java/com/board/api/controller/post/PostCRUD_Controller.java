package com.board.api.controller.post;

import com.board.api.dto.post.PostListOptDTO;
import com.board.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.board.api.dto.post.PostDTO;
import com.board.api.service.post.PostCRUD_Service;

import javax.naming.AuthenticationException;
import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostCRUD_Controller {

    private static final Logger log = LogManager.getLogger();
    private final PostCRUD_Service CRUDService;

    private final UserService userService;

    @GetMapping(value = {"/list"})
    public ResponseEntity<?> getPostList(@RequestBody(required = false) PostListOptDTO postListOptions) {
        try {
            log.info(postListOptions);
            return ResponseEntity.ok(CRUDService.getPostList(postListOptions));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{postNo}")
    public ResponseEntity<?> getPost(@PathVariable long postNo) {
        try {
            return ResponseEntity.ok(CRUDService.getInfoByPno(postNo));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/registration")
    public ResponseEntity<?> register(Principal principal, @RequestBody PostDTO board) {
        try {
            if (principal == null)
                throw new AuthenticationException("Not logged in");
            board.setWriter(userService.getUserInfoById(principal.getName()).getUserName());
            CRUDService.registration(board);
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
    public ResponseEntity<?> modification(Principal principal, @RequestBody PostDTO postDTO) {
        try {
            if (principal == null)
                throw new AuthenticationException("Not logged in");
            CRUDService.modification(postDTO, userService.getUserInfoById(principal.getName()).getUserName());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok(postDTO.getPno());
    }

    @PostMapping("/remove/{postNo}")
    public ResponseEntity<?> remove(Principal principal, @PathVariable int postNo) {
        try {
            if (principal == null)
                throw new AuthenticationException("Not logged in");
            CRUDService.remove(userService.getUserInfoById(principal.getName()).getUserName(), postNo);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok(postNo);
    }

}
