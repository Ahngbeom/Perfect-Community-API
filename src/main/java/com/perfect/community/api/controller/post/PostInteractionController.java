package com.perfect.community.api.controller.post;

import com.perfect.community.api.service.post.PostInteractionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostInteractionController {

    private final PostInteractionService postInteractionService;

    @PatchMapping("/views/{postNo}")
    public ResponseEntity<?> increaseViews(Authentication authentication, @PathVariable long postNo) {
        try {
            return ResponseEntity.ok(postInteractionService.increaseViews(authentication.getName(), postNo));
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
