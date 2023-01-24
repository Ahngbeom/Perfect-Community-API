package com.perfect.community.api.service;

import com.perfect.community.api.dto.jwt.TokenDTO;
import com.perfect.community.api.jwt.JwtTokenProvider;
import com.perfect.community.api.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class JwtService {

    public static final String AUTHORIZATION_HEADER = "Authorization";
    private final JwtTokenProvider tokenProvider;

    private final UserService userService;

    public String getUsernameByAccessToken(String accessToken) {
        return tokenProvider.getAuthentication(accessToken).getName();
    }

    public TokenDTO reissueByPassword(String refreshToken, String password) {
        Authentication authentication = tokenProvider.getAuthentication(refreshToken);
        if (userService.verifyPassword(authentication.getName(), password))
            return tokenProvider.generateToken(authentication);
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
