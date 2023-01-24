package com.perfect.community.api.service;

import com.perfect.community.api.jwt.JwtTokenProvider;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
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

    public String getUsernameByJwt(HttpServletRequest request) {
        String token = resolveAccessToken(request);
        if (token != null) {
            return tokenProvider.getAuthentication(JwtTokenProvider.TOKEN_TYPE.ACCESS, token).getName();
        } else {
            token = resolveRefreshToken(request);
            return tokenProvider.getAuthentication(JwtTokenProvider.TOKEN_TYPE.REFRESH, token).getName();
        }
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
