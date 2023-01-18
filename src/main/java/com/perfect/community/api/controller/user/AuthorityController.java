package com.perfect.community.api.controller.user;

import com.perfect.community.api.dto.authorities.AuthoritiesDTO;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.perfect.community.api.service.auth.AuthService;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthorityController {
    private static final Logger log = LogManager.getLogger();

    //    private final CustomUserDetailService userDetailsService;
    private final AuthService authService;

    @GetMapping("/list")
    public ResponseEntity<List<AuthoritiesDTO>> getAuthorities() {
        try {
            return ResponseEntity.ok(authService.getAuthorities());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/grant")
    public ResponseEntity<String> grantAuthority(@RequestBody AuthoritiesDTO authoritiesDTO) {
        try {
            authService.addAuthority(authoritiesDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok("SUCCESS");
    }

    @PostMapping("/revoke")
    public ResponseEntity<String> revokeAuthority(@RequestBody AuthoritiesDTO authoritiesDTO) {
        try {
            authService.removeAuthority(authoritiesDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok("SUCCESS");
    }

}
