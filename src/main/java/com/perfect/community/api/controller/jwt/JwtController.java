package com.perfect.community.api.controller.jwt;

import com.perfect.community.api.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/jwt")
@RequiredArgsConstructor
public class JwtController {

    private final JwtTokenProvider jwtTokenProvider;

    @GetMapping
    public ResponseEntity<String> Jwt(HttpServletRequest request) {
        return ResponseEntity.ok(request.getHeader("Authorization"));
    }

    @GetMapping("/authentication")
    public ResponseEntity<String> JwtAuthentication() {
        try {
            return null;
        } catch (Exception e) {
            return null;
        }
    }
}
