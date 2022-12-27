package com.perfect.community.api.controller.user;

import com.perfect.community.api.dto.user.UserDTO;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;
import com.perfect.community.api.service.user.UserService;

import javax.security.auth.login.AccountNotFoundException;
import java.security.Principal;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private static final Logger log = LogManager.getLogger();

    private final UserService userService;


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

    @PostMapping("/sign-up")
    public ResponseEntity<String> signUpUser(@RequestBody UserDTO user) {
        log.warn(user);
        try {
            userService.createUser(user);
            return ResponseEntity.ok(user.getUserId());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateUser(Principal principal, @RequestBody UserDTO user) {
        try {
            if (principal == null)
                throw new AccountNotFoundException("Not logged in.");
            if (!principal.getName().equals(user.getUserId()))
                throw new AccessDeniedException("Access denied.");
            userService.updateUserInfo(user);
        } catch (AccountNotFoundException | AccessDeniedException unauthorizedException) {
             unauthorizedException.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(unauthorizedException.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok(user.getUserId());
    }

    @DeleteMapping("/withdraw")
    public ResponseEntity<String> withdrawUser(Principal principal, @RequestBody UserDTO user) {
        try {
            if (userService.verifyPassword(user.getUserId(), user.getPassword())) {
//                authService.revokeAllAuthority(user.getUserId());
                userService.removeUser(user.getUserId());
            } else
                throw new BadCredentialsException("Passwords do not match.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok(user.getUserId());
    }

    @PutMapping("/disable")
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

    @PutMapping("/enable")
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

    @GetMapping("/id-availability")
    public boolean userIdDuplicatesChecking(String userId) {
        return userService.userIdAvailability(userId);
    }

    @PostMapping("/nickname-availability")
    public boolean usernameDuplicatesChecking(String nickname) {
        return userService.nicknameAvailability(nickname);
    }
}
