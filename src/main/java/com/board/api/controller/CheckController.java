package com.board.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import com.board.api.security.detail.CustomUserDetailService;

import java.security.Principal;

@RestController
@RequestMapping("/check")
@RequiredArgsConstructor
public class CheckController {

    private final CustomUserDetailService userDetailsService;

    @GetMapping("/userid/duplicates")
    public Boolean userIdDuplicatesChecking(String userId) {
        return userDetailsService.loadUserByUsername(userId) != null ? true : false;
    }

    @GetMapping("/logged-in")
    public UserDetails loggedIn(@AuthenticationPrincipal Principal principal) {
        if (principal != null)
            return userDetailsService.loadUserByUsername(principal.getName());
        else
            return null;
    }

}
