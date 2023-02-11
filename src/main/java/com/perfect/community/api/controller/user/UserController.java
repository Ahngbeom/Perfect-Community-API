package com.perfect.community.api.controller.user;

import com.perfect.community.api.dto.user.UserDTO;
import com.perfect.community.api.service.JwtService;
import com.perfect.community.api.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final JwtService jwtService;
    private final UserService userService;

    @GetMapping("/list")
    public ResponseEntity<List<UserDTO>> list() {
        return ResponseEntity.ok(userService.getUserListWithAuthorities());
    }

    @GetMapping({"", "/{userId}"})
    public ResponseEntity<?> info(HttpServletRequest request, @PathVariable(required = false) String userId) {
        try {
            if (userId != null)
                return ResponseEntity.ok(userService.getUserInfoWithAuthoritiesByUserId(userId));
            else {
                String accessToken = jwtService.resolveAccessToken(request);
                return ResponseEntity.ok(userService.getUserInfoWithAuthoritiesByUserId(jwtService.getUsernameByAccessToken(accessToken)));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.unprocessableEntity().body(e.getMessage());
        }
    }

    @PreAuthorize("isAnonymous()")
    @PostMapping
    public ResponseEntity<String> signUpUser(@RequestBody UserDTO user) {
        try {
            userService.createUser(user);
            return ResponseEntity.ok(user.getUserId());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /* Update User Info */
    @PreAuthorize("isAuthenticated() and principal.username == #userId")
    @PatchMapping("/{userId}")
    public ResponseEntity<String> updateUser(@PathVariable String userId, @RequestBody UserDTO userDTO) {
        try {
            userDTO.setUserId(userId);
            userService.updateUserInfo(userDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok(userId);
    }

    @PreAuthorize("isAuthenticated() and principal.username == #userId")
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> withdrawUser(@PathVariable String userId) {
        try {
            userService.removeUser(userId);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok(userId);
    }

    @PreAuthorize("isAuthenticated() and (principal.username == #userId or hasAnyRole('ADMIN', 'MANAGER'))")
    @PatchMapping("/disable/{userId}")
    public ResponseEntity<?> disable(@PathVariable String userId) {
        try {
            userService.disableUser(userId);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok(userId);
    }

    @PreAuthorize("isAuthenticated() and (principal.username == #userId or hasAnyRole('ADMIN', 'MANAGER'))")
    @PatchMapping("/enable/{userId}")
    public ResponseEntity<?> enable(@PathVariable String userId) {
        try {
            userService.enableUser(userId);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok(userId);
    }

    @PreAuthorize("isAuthenticated() and principal.username == #userId")
    @GetMapping("/verify-password/{userId}")
    public ResponseEntity<Boolean> verifyPassword(@PathVariable String userId, String password) {
        try {
            return ResponseEntity.ok(userService.verifyPassword(userId, password));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(false);
        }
    }

    @GetMapping("/id-availability")
    public boolean userIdDuplicatesChecking(String userId) {
        return userService.isValidUserId(userId);
    }

    @GetMapping("/nickname-availability")
    public boolean usernameDuplicatesChecking(String nickname) {
        return userService.isValidNickname(nickname);
    }
}
