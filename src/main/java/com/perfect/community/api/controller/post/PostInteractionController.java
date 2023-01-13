package com.perfect.community.api.controller.post;

import com.perfect.community.api.service.post.PostInteractionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostInteractionController {

    private final PostInteractionService postInteractionService;

    @PatchMapping("/views/{postNo}")
    public ResponseEntity<?> increaseViews(@PathVariable long postNo) {
        try {
            return ResponseEntity.ok(postInteractionService.increaseViews(postNo));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/recommend/{postNo}")
    public ResponseEntity<?> recommendation(@PathVariable long postNo) {
        try {
            return ResponseEntity.ok(postInteractionService.increaseRecommend(postNo));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/not-recommend/{postNo}")
    public ResponseEntity<?> notRecommendation(@PathVariable long postNo) {
        try {
            return ResponseEntity.ok(postInteractionService.increaseNotRecommend(postNo));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
