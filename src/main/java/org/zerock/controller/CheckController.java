package org.zerock.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.zerock.security.detail.CustomUserDetailService;

@RestController
@RequestMapping("/check")
@RequiredArgsConstructor
public class CheckController {

    private final CustomUserDetailService userDetailsService;

    @GetMapping("/userid/duplicates")
    public Boolean userIdDuplicatesChecking(String userId) {
        return userDetailsService.loadUserByUsername(userId) != null ? true : false;
    }

}
