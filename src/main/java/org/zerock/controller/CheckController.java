package org.zerock.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.zerock.security.CustomUserDetailService;

@RestController
@RequestMapping("/check")
@RequiredArgsConstructor
public class CheckController {

    private final CustomUserDetailService userDetailsService;

    @GetMapping("/userid/duplicates")
    public Boolean userIdDuplicatesChecking(String userId) {
        return userDetailsService.loadUserByUsername(userId) != null ? true : false;
    }

//    @GetMapping("/userid/duplicates")
//    public UserDetails userIdDuplicatesChecking(String userId) throws UsernameNotFoundException {
//        UserDetails userDetails;
//        try {
//            userDetails = userDetailsService.loadUserByUsername(userId);
//        } catch (UsernameNotFoundException e) {
//            return null;
//        }
//        return userDetails;
//    }
}
