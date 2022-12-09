package org.zerock.controller.REST;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zerock.DTO.AuthorityDTO;
import org.zerock.DTO.UserDTO;
import org.zerock.service.AuthService;

import java.util.List;

@RestController
@RequestMapping("/api/user/auth")
@RequiredArgsConstructor
public class AuthAPIController {
    private static final Logger log = LogManager.getLogger();

    //    private final CustomUserDetailService userDetailsService;
    private final AuthService authService;

    @GetMapping("/list")
    public ResponseEntity<List<String>> getAuthorities(String userId) {
        try {
            return ResponseEntity.ok(authService.getAuthList(userId));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/grant")
    public ResponseEntity<String> grantAuthority(@RequestBody AuthorityDTO authorityDTO) {
        try {
            authService.grantAuthority(authorityDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok("SUCCESS");
    }

    @PostMapping("/revoke")
    public ResponseEntity<String> revokeAuthority(@RequestBody AuthorityDTO authorityDTO) {
        try {
            authService.revokeOneAuthority(authorityDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok("SUCCESS");
    }

}
