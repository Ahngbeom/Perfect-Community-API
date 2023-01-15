package com.perfect.community.api.controller.user;

import com.perfect.community.api.dto.user.UserDTO;
import com.perfect.community.api.interceptor.AccessDeniedInterceptor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.perfect.community.api.service.user.UserService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("")
    public ResponseEntity<List<UserDTO>> list() {
        return ResponseEntity.ok(userService.getUserListWithAuthorities());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> info(@PathVariable String userId) {
        try {
            return ResponseEntity.ok(userService.getUserInfoWithAuthoritiesByUserId(userId));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

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

    /**
     * userId를 '@PathVariable'로 분리한 이유
     * UserDeniedAccessInterceptor에서 userId를 확인 하기 위해 '@RequestBody'에 접근할 경우,
     * '@RequestBody' 데이터가 소멸되기 때문에 Controller에서는 제대로 처리를 하지 못하게 된다.
     *
     * @see AccessDeniedInterceptor
     **/
    @PutMapping("/{userId}")
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

//    @PutMapping("/disable/{userId}")
//    public ResponseEntity<?> disable(@PathVariable String userId) {
//        try {
//            userService.disableUser(userId);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//        return ResponseEntity.ok(userId);
//    }
//
//    @PutMapping("/enable/{userId}")
//    public ResponseEntity<?> enable(@PathVariable String userId) {
//        try {
//            userService.enableUser(userId);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//        return ResponseEntity.ok(userId);
//    }

    @PatchMapping("")
    public ResponseEntity<Boolean> verifyPassword(@RequestBody UserDTO userDTO) {
        try {
            return ResponseEntity.ok(userService.verifyPassword(userDTO));
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
