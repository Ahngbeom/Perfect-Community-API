package org.zerock.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/check")
@RequiredArgsConstructor
public class CheckController {

    private final UserDetailsService userDetailsService;

//    @GetMapping("/userid/duplicates/{userId}")
//    @ResponseBody
//    public String userIdDuplicatesChecking(@PathVariable String userId) throws UsernameNotFoundException {
//        try {
//            userDetailsService.loadUserByUsername(userId);
//        } catch (UsernameNotFoundException e) {
//            return Boolean.TRUE.toString();
//        }
//        return Boolean.FALSE.toString();
//    }

    @GetMapping("/userid/duplicates")
    public UserDetails userIdDuplicatesChecking(String userId) throws UsernameNotFoundException {
        UserDetails userDetails;
        try {
            userDetails = userDetailsService.loadUserByUsername(userId);
        } catch (UsernameNotFoundException e) {
            return null;
        }
        return userDetails;
    }
}
