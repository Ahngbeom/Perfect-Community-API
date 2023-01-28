package com.perfect.community.api.controller.jwt;

import com.google.common.base.Preconditions;
import com.perfect.community.api.dto.jwt.TokenDTO;
import com.perfect.community.api.jwt.JwtTokenProvider;
import com.perfect.community.api.service.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/jwt")
@RequiredArgsConstructor
public class JwtController {

    private final JwtTokenProvider jwtTokenProvider;
    private final JwtService jwtService;

//    @GetMapping
//    public ResponseEntity<String> Jwt(HttpServletRequest request) {
//        return ResponseEntity.ok(request.getHeader("Authorization"));
//    }

    @PostMapping("/reissue")
    public ResponseEntity<?> JwtAuthentication(HttpServletRequest request/*, @RequestBody Map<String, String> requestBody*/) {
        try {
            String refreshToken = jwtService.resolveRefreshToken(request);
            Preconditions.checkState(refreshToken != null && !refreshToken.isEmpty(), "Invalid JWT.");
            TokenDTO tokenDTO = jwtService.reissue(refreshToken);
            if (tokenDTO != null) {
                log.info("[SUCCESS] Reissued JWT");
                return ResponseEntity.ok(tokenDTO);
            }
            else
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Failed");
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getClass().getSimpleName() + " - {}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}
