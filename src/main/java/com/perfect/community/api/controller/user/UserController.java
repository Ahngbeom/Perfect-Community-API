package com.perfect.community.api.controller.user;

import com.perfect.community.api.dto.user.UserDTO;
import com.perfect.community.api.security.interceptor.AccessDeniedInterceptor;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.perfect.community.api.service.user.UserService;

import java.security.Principal;
import java.util.List;

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
                return ResponseEntity.ok(userService.getUserInfoWithAuthoritiesByUserId(userId));
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
        try {
            userService.createUser(user);
            return ResponseEntity.ok(user.getUserId());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * userId를 '@PathVariable'로 분리한 이유
     * UserDeniedAccessInterceptor에서 userId를 확인 하기 위해 '@RequestBody'에 접근할 경우,
     * '@RequestBody' 데이터가 소멸되기 때문에 Controller에서는 제대로 처리를 하지 못하게 된다.
     * @see AccessDeniedInterceptor
     **/
    @PutMapping("/update/{userId}")
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

    @DeleteMapping("/withdraw/{userId}")
    public ResponseEntity<String> withdrawUser(@PathVariable String userId) {
        try {
            userService.removeUser(userId);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok(userId);
    }

    @PutMapping("/disable/{userId}")
    public ResponseEntity<?> disable(@PathVariable String userId) {
        try {
            userService.disableUser(userId);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok(userId);
    }

    @PutMapping("/enable/{userId}")
    public ResponseEntity<?> enable(@PathVariable String userId) {
        try {
            userService.enableUser(userId);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok(userId);
    }

    @PostMapping("/verify-password")
    public ResponseEntity<Boolean> verifyPassword(Principal principal, String password) {
        try {
            return ResponseEntity.ok(userService.verifyPassword(principal.getName(), password));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(false);
        }
    }

    @GetMapping("/id-availability")
    public boolean userIdDuplicatesChecking(String userId) {
        return userService.userIdAvailability(userId);
    }

    @GetMapping("/nickname-availability")
    public boolean usernameDuplicatesChecking(String nickname) {
        return userService.nicknameAvailability(nickname);
    }
}
