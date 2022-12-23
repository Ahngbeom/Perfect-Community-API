package com.perfect.community.api.controller.post;

import com.perfect.community.api.dto.post.PostListOptDTO;
import com.perfect.community.api.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.perfect.community.api.dto.post.PostDTO;
import com.perfect.community.api.service.post.PostService;

import javax.naming.AuthenticationException;
import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostController {

    private static final Logger log = LogManager.getLogger();
    private final PostService postService;
    private final UserService userService;

    @GetMapping(value = {"/list"})
    public ResponseEntity<?> getPostList(@RequestBody(required = false) PostListOptDTO postListOptions) {
        try {
            log.info(postListOptions);
            return ResponseEntity.ok(postService.getPostList(postListOptions));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{postNo}")
    public ResponseEntity<?> getPost(@PathVariable long postNo) {
        try {
            return ResponseEntity.ok(postService.getInfoByPno(postNo));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/registration")
    public ResponseEntity<?> register(Principal principal, @RequestBody PostDTO postDTO) {
        try {
            if (principal == null)
                throw new AuthenticationException("Not logged in");
            log.info(postDTO);
            return ResponseEntity.ok(postService.registration(principal.getName(), postDTO));
        } catch (AuthenticationException authenticationException) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(authenticationException.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/modify/{postNo}")
    public ResponseEntity<?> modification(Principal principal, @PathVariable long postNo, @RequestBody PostDTO postDTO) {
        try {
            if (principal == null)
                throw new AuthenticationException("Not logged in");
            postService.modification(postNo, principal.getName(), postDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok(postDTO.getPno());
    }

    @DeleteMapping("/remove/{postNo}")
    public ResponseEntity<?> remove(Principal principal, @PathVariable int postNo) {
        try {
            if (principal == null)
                throw new AuthenticationException("Not logged in");
            postService.remove(principal.getName(), postNo);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok(postNo);
    }

}
