package com.perfect.community.api.controller.user;

import com.perfect.community.api.service.user.UserScrapPostService;
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

    private final UserScrapPostService service;

    @GetMapping("/scraped-posts")
    public ResponseEntity<?> getScrapedPosts(Authentication authentication) {
        return ResponseEntity.ok(service.getAllScrapedPosts(authentication.getName()));
    }

    @PostMapping("/scrap-post/{postNo}")
    public ResponseEntity<?> scrapPost(Principal principal, @PathVariable long postNo) {
        try {
            service.scrapePost(principal.getName(), postNo);
            return ResponseEntity.ok(postNo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e.getMessage());
        }
    }

    @DeleteMapping("/release-scraped-post/{postNo}")
    public ResponseEntity<?> releaseScrapedPost(Principal principal, @PathVariable long postNo) {
        try {
            service.releaseScrapedPost(principal.getName(), postNo);
            return ResponseEntity.ok(postNo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e.getMessage());
        }
    }
}
