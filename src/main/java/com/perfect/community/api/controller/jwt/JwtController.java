package com.perfect.community.api.controller.jwt;

import com.perfect.community.api.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/jwt")
@RequiredArgsConstructor
public class JwtController {

    private final JwtTokenProvider jwtTokenProvider;

    @GetMapping
    public ResponseEntity<String> Jwt(HttpServletRequest request) {
        return ResponseEntity.ok(request.getHeader("Authorization"));
    }

    @GetMapping("/reissue")
    public ResponseEntity<String> JwtAuthentication(HttpServletRequest request) {
        try {
            List<Cookie> refresh_tokens = Arrays.stream(request.getCookies())
                    .filter(cookie -> cookie.getName().equals("refresh_token")).collect(Collectors.toList());
            for (Cookie refreshToken : refresh_tokens) {
                log.info(refreshToken.getValue());
                log.info(refreshToken.getPath());
                log.info(String.valueOf(refreshToken.getMaxAge()));
                jwtTokenProvider.validateAccessToken(refreshToken.getValue());
            }
        } catch (Exception e) {
        }
        return ResponseEntity.ok(null);
    }
}
