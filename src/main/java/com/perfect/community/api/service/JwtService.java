package com.perfect.community.api.service;

import com.perfect.community.api.dto.jwt.TokenDTO;
import com.perfect.community.api.security.jwt.JwtTokenProvider;
import com.perfect.community.api.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtService {

    public static final String AUTHORIZATION_HEADER = "Authorization";
    private final JwtTokenProvider tokenProvider;

    private final UserService userService;

    public String getUsernameByAccessToken(String accessToken) {
        return tokenProvider.getAuthentication(accessToken).getName();
    }

    public TokenDTO reissue(String refreshToken) {
        Authentication authentication = tokenProvider.getAuthentication(refreshToken);
        return tokenProvider.generateToken(authentication);
    }

    public TokenDTO reissueByPassword(String refreshToken, String password) {
        Authentication authentication = tokenProvider.getAuthentication(refreshToken);
        if (userService.verifyPassword(authentication.getName(), password)) {
            log.info("[SUCCESS] Reissued JWT");
            return tokenProvider.generateToken(authentication);
        }
        return null;
    }


    public String resolveAccessToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring("Bearer ".length());
        }
        return null;
    }

    public String resolveRefreshToken(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("refresh-token")) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
