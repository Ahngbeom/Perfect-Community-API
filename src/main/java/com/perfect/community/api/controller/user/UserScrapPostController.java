package com.perfect.community.api.controller.user;

import com.perfect.community.api.service.post.PostScrapService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserScrapPostController {

    private final PostScrapService service;

    @GetMapping("/scraped-posts")
    public ResponseEntity<?> getScrapedPosts(Authentication authentication) {
        return ResponseEntity.ok(service.getAllScrapedPosts(authentication.getName()));
    }

    @PostMapping("/scrap-post/{postNo}")
    public ResponseEntity<?> scrapPost(Authentication authentication, @PathVariable long postNo) {
        try {
            service.scrapePost(authentication.getName(), postNo);
            return ResponseEntity.ok(postNo);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e.getMessage());
        }
    }

    @DeleteMapping("/release-scraped-post/{postNo}")
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
