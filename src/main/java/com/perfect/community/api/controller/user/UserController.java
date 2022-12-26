package com.perfect.community.api.controller.user;

import com.perfect.community.api.dto.user.UserDTO;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import com.perfect.community.api.security.detail.CustomUserDetailService;
import com.perfect.community.api.service.auth.AuthService;
import com.perfect.community.api.service.user.UserService;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private static final Logger log = LogManager.getLogger();

    private final CustomUserDetailService userDetailsService;
    private final UserService userService;
    private final AuthService authService;


    @GetMapping("/info")
    public ResponseEntity<?> info(String userId) {
        try {
            if (userId != null)
                return ResponseEntity.ok(userService.getUserInfoByUserId(userId));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        return ResponseEntity.badRequest().body("Invalid params");
    }

    @GetMapping("/list")
    public ResponseEntity<List<UserDTO>> list() {
        return ResponseEntity.ok(userService.getUserList());
    }

    @PostMapping("/create")
    public ResponseEntity<String> createMember(@RequestBody UserDTO user) {
        log.warn(user);
        try {
            userService.createUser(user);
//            for (AuthoritiesDTO auth : user.getAuthorities()) {
//                auth.setUserId(user.getUserId());
//                authService.grantAuthority(UserAuthoritiesDTO.builder().userId(user).authorities(user.getAuthorities()).build());
//            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok("SUCCESS");
    }

    @PostMapping("/update")
    public ResponseEntity<String> updateUser(@RequestBody UserDTO user) {
        try {
            userService.updateUserInfo(user);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok("SUCCESS");
    }

    @PostMapping("/remove")
    public ResponseEntity<String> removeUser(@RequestBody UserDTO user) {
        try {
            if (Objects.equals(verifyPassword(user).getBody(), true)) {
//                authService.revokeAllAuthority(user.getUserId());
                userService.removeUser(user.getUserId());
            }
            else
                throw new RuntimeException("Passwords do not match.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok("SUCCESS");
    }

    @PostMapping("/disable")
    public ResponseEntity<?> disable(String userId) {
        // 장기 미접속 계정 휴면 상태 전환 기능 필요
        try {
            userService.disableUser(userId);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok("SUCCESS");
    }

    @PostMapping("/enable")
    public ResponseEntity<?> enable(String userId) {
        try {
            userService.enableUser(userId);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok("SUCCESS");
    }

    @PostMapping("/verify-password")
    public ResponseEntity<Boolean> verifyPassword(@RequestBody UserDTO user) {
        try {
            return ResponseEntity.ok(userService.verifyPassword(user.getUserId(), user.getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(false);
        }
    }

    @PostMapping("/userId-duplicatesCheck")
    public boolean userIdDuplicatesChecking(String userId) {
        UserDetails user = userDetailsService.loadUserByUsername(userId);
        return user != null;
    }

//    @PostMapping("/username-duplicatesCheck")
//    public boolean usernameDuplicatesChecking(String username) {
//        UserDetails user = userDetailsService.loadUserByUsername(username);
//        return user != null ? true : false;
//    }
}
