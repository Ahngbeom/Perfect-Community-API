package com.perfect.community.api.controller.post;

import com.perfect.community.api.dto.post.PostRecommendDTO;
import com.perfect.community.api.dto.post.PostViewsDTO;
import com.perfect.community.api.service.post.PostInteractionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostInteractionController {

    private final PostInteractionService postInteractionService;

    @PatchMapping("/views")
    public ResponseEntity<?> increaseViews(@RequestBody PostViewsDTO postViewsDTO) {
        try {
            return ResponseEntity.ok(postInteractionService.increaseViews(postViewsDTO.getPostNo()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/recommend")
    public ResponseEntity<?> recommendation(@RequestBody PostRecommendDTO postRecommendDTO) {
        try {
            return ResponseEntity.ok(postInteractionService.increaseRecommend(postRecommendDTO.getPostNo()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/not-recommend")
    public ResponseEntity<?> notRecommendation(@RequestBody PostRecommendDTO postRecommendDTO) {
        try {
            return ResponseEntity.ok(postInteractionService.increaseNotRecommend(postRecommendDTO.getPostNo()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
