package com.perfect.community.api.controller.post;

import com.perfect.community.api.service.post.PostScrapService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostScrapController {

    private final PostScrapService service;

    @GetMapping("/scraped")
    public ResponseEntity<?> getScrapedPosts(Authentication authentication) {
        try {
            return ResponseEntity.ok(service.getAllScrapedPosts(authentication.getName()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.unprocessableEntity().body(null);
        }
    }

    @PostMapping("/scrap/{postNo}")
    public ResponseEntity<?> scrapPost(Authentication authentication, @PathVariable long postNo) {
        try {
            service.scrapePost(authentication.getName(), postNo);
            return ResponseEntity.ok(postNo);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e.getMessage());
        }
    }

    @DeleteMapping("/release-scrap/{postNo}")
    public ResponseEntity<?> releaseScrapedPost(Authentication authentication, @PathVariable long postNo) {
        try {
            service.releaseScrapedPost(authentication.getName(), postNo);
            return ResponseEntity.ok(postNo);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e.getMessage());
        }
    }
}
