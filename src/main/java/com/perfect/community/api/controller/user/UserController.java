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
import java.security.Principal;
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

    @PreAuthorize(
            "isAuthenticated() and (" +
                    "(#userId == null) or " +
                    "(#userId != null && userService.isAdmin(principal.name)))"
    )
    @DeleteMapping({"", "/{userId}"})
    public ResponseEntity<String> secessionUser(Principal principal, @PathVariable(required = false) String userId) {
        try {
            userService.secessionUser(userId == null ? principal.getName() : userId);
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
    @PostMapping("/verify-password/{userId}")
    public ResponseEntity<Boolean> verifyPassword(@PathVariable String userId, @RequestBody String password) {
        try {
            return ResponseEntity.ok(userService.verifyPassword(userId, password));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(false);
        }
    }

    @PostMapping("/id-availability")
    public ResponseEntity<?> userIdAvailability(@RequestBody(required = false) String userId) {
        try {
            return ResponseEntity.ok(userService.isValidUserId(userId));
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().body(e.getMessage());
        }
    }

    @PostMapping("/password-availability")
    public ResponseEntity<?> passwordAvailability(@RequestBody(required = false) String password) {
        try {
            return ResponseEntity.ok(userService.isValidPassword(password));
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().body(e.getMessage());
        }
    }

    @PostMapping("/nickname-availability")
    public ResponseEntity<?> usernameDuplicatesChecking(@RequestBody(required = false) String nickname) {
        try {
            return ResponseEntity.ok(userService.isValidNickname(nickname));
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().body(e.getMessage());
        }
    }
}
