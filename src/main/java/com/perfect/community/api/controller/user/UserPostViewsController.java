package com.perfect.community.api.controller.user;

import com.perfect.community.api.service.user.UserPostViewsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user/post")
public class UserPostViewsController {

    private final UserPostViewsService service;

    @GetMapping("/recently-viewed")
    public ResponseEntity<?> getRecentlyViewedPosts(Authentication authentication) {
        try {
            log.info("{},{}", authentication, 10);
            return ResponseEntity.ok(service.getRecentlyViewPosts(authentication.getName(), 10));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.unprocessableEntity().body(null);
        }
    }
}
