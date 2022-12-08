package org.zerock.controller.REST;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.zerock.DTO.AuthorityDTO;
import org.zerock.DTO.UserDTO;
import org.zerock.security.detail.CustomUserDetailService;
import org.zerock.service.MemberService;

import java.util.List;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberAPIController {

    private static final Logger log = LogManager.getLogger();

    private final CustomUserDetailService userDetailsService;
    private final MemberService memberService;

    @GetMapping("/info")
    public ResponseEntity<?> info(String userId, String userName) {
        try {
            if (userId != null) {
                return ResponseEntity.ok(memberService.readUserById(userId));
            } else if (userName != null) {
                return ResponseEntity.ok(memberService.readUserByName(userName));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        return ResponseEntity.badRequest().body("Invalid params");
    }

    @GetMapping("/list")
    public ResponseEntity<List<UserDTO>> list() {
        return ResponseEntity.ok(memberService.getUserList());
    }

    @PostMapping("/create")
    public ResponseEntity<String> createMember(@RequestBody UserDTO user) {
        log.warn(user);
        try {
            memberService.createUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok("SUCCESS");
    }

    @PostMapping("/remove")
    public ResponseEntity<String> removeMember(String userId) {
        try {
            memberService.deleteUser(userId);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok("SUCCESS");
    }

    @PostMapping("/userId-duplicatesCheck")
    public boolean userIdDuplicatesChecking(String userId) {
        UserDetails user = userDetailsService.loadUserByUsername(userId);
        return user != null ? true : false;
    }

//    @PostMapping("/username-duplicatesCheck")
//    public boolean usernameDuplicatesChecking(String username) {
//        UserDetails user = userDetailsService.loadUserByUsername(username);
//        return user != null ? true : false;
//    }
}
