package com.perfect.community.api.controller.post;

import com.perfect.community.api.dto.post.PostFilterDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.perfect.community.api.dto.post.PostDTO;
import com.perfect.community.api.service.post.PostService;

import javax.naming.AuthenticationException;
import java.security.Principal;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostController {

    private final PostService postService;

    @GetMapping
    public ResponseEntity<?> getPostList(@RequestParam(required = false) Map<String, String> filterParam) {
        try {
            PostFilterDTO postFilterDTO = PostFilterDTO.builder()
                    .boardNo(Long.parseLong(filterParam.getOrDefault("boardNo", "0")))
                    .page(Integer.parseInt(filterParam.getOrDefault("page", "0")))
                    .type(filterParam.getOrDefault("type", null))
                    .keyword(filterParam.getOrDefault("keyword", null))
                    .build();
            log.info("{}", postFilterDTO);
            return ResponseEntity.ok(postService.getPostList(postFilterDTO));
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

    @GetMapping("/count")
    public ResponseEntity<?> getPostCount(@RequestParam(required = false) Map<String, String> filterParam) {
        try {
            PostFilterDTO postFilterDTO = PostFilterDTO.builder()
                    .boardNo(Long.parseLong(filterParam.getOrDefault("boardNo", "0")))
                    .page(Integer.parseInt(filterParam.getOrDefault("page", "0")))
                    .type(filterParam.getOrDefault("type", null))
                    .keyword(filterParam.getOrDefault("keyword", null))
                    .build();
            return ResponseEntity.ok(postService.getPostCount(postFilterDTO));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> register(Principal principal, @RequestBody PostDTO postDTO) {
        try {
            return ResponseEntity.ok(postService.registration(principal.getName(), postDTO));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/{postNo}")
    public ResponseEntity<?> modification(@PathVariable long postNo, @RequestBody PostDTO postDTO) {
        try {
            return ResponseEntity.ok(postService.modification(postNo, postDTO));
        } catch (Exception e) {
//            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{postNo}")
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
