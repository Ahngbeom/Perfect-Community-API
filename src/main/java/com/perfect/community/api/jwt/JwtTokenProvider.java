package com.perfect.community.api.jwt;

import com.perfect.community.api.dto.jwt.JwtTokenDTO;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Slf4j
@Component
public class JwtTokenProvider implements InitializingBean {

    enum TOKEN_TYPE {
        ACCESS,
        REFRESH
    }

    private static final String AUTHORITIES_KEY = "auth";

    private final String accessSecretCode;
    private final String refreshSecretCode;
    private final long accessTokenValidityInMilliseconds;
    private final long refreshTokenValidityInMilliseconds;

    private Key accessSecretkey;
    private Key refreshSecretkey;

    public JwtTokenProvider(@Value("${jwt.access-key-secret}") String accessSecretCode, @Value("${jwt.refresh-key-secret}") String refreshSecretCode, @Value("${jwt.access-token-validity-in-seconds}") long accessTokenValidityInMilliseconds, @Value("${jwt.refresh-token-validity-in-seconds}") long refreshTokenValidityInMilliseconds) {
        this.accessSecretCode = accessSecretCode;
        this.refreshSecretCode = refreshSecretCode;
        this.accessTokenValidityInMilliseconds = accessTokenValidityInMilliseconds * 1000;
        this.refreshTokenValidityInMilliseconds = refreshTokenValidityInMilliseconds * 1000;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.accessSecretkey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(accessSecretCode));
        this.refreshSecretkey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(refreshSecretCode));
    }

    public String createAccessToken(Authentication authentication) {
        String authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));

        long now = new Date().getTime();
        Date validity = new Date(now + this.accessTokenValidityInMilliseconds);
        log.warn("Access token expiration = {}", validity);

        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim(AUTHORITIES_KEY, authorities)
//                .claim("username", authentication.getName())
                .signWith(accessSecretkey, SignatureAlgorithm.HS512)
                .setExpiration(validity)
                .compact();
    }

    public String createRefreshToken(Authentication authentication) {
        String authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));

        long now = new Date().getTime();
        Date validity = new Date(now + this.refreshTokenValidityInMilliseconds);
        log.warn("Refresh token expiration = {}", validity);

        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim(AUTHORITIES_KEY, authorities)
                .signWith(refreshSecretkey, SignatureAlgorithm.HS512)
                .setExpiration(validity)
                .compact();
    }

    public Authentication getAuthenticationByAccessToken(TOKEN_TYPE type, String token) {
        Claims claims;
        if (type.equals(TOKEN_TYPE.ACCESS)) {
            claims = Jwts.parserBuilder()
                    .setSigningKey(accessSecretkey)
                    .build()
                    .parseClaimsJws(token) // JWS (Json Web Signature): 인증 정보를 서버가 보유한 private key로 서명한 것을 토큰화한 객체
                    .getBody();
        } else {
            claims = Jwts.parserBuilder()
                    .setSigningKey(refreshSecretkey)
                    .build()
                    .parseClaimsJws(token) // JWS (Json Web Signature): 인증 정보를 서버가 보유한 private key로 서명한 것을 토큰화한 객체
                    .getBody();
        }

        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        User principal = new User(claims.getSubject(), "", authorities);

        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

    /**
     * @throws UnsupportedJwtException  if the {@code claimsJws} argument does not represent an Claims JWS
     * @throws MalformedJwtException    if the {@code claimsJws} string is not a valid JWS
     * @throws SignatureException       if the {@code claimsJws} JWS signature validation fails
     * @throws ExpiredJwtException      if the specified JWT is a Claims JWT and the Claims has an expiration time
     *                                  before the time this method is invoked.
     * @throws IllegalArgumentException if the {@code claimsJws} string is {@code null} or empty or only whitespace
     */
    public void validateAccessToken(String token) throws JwtException {
        Jws<Claims> jwsClaims = Jwts.parserBuilder().setSigningKey(accessSecretkey).build().parseClaimsJws(token);
        log.info("[Access Token] JWS Claims = {}", jwsClaims);
        log.info("[Access Token] Expiration = {}", jwsClaims.getBody().getExpiration());
    }

    public boolean validateRefreshToken(String token) {
        try {
            Jws<Claims> jwsClaims = Jwts.parserBuilder().setSigningKey(refreshSecretkey).build().parseClaimsJws(token);
            log.info("[Refresh Token] JWS Claims = {}", jwsClaims);
            log.info("[Refresh Token] Expiration = {}", jwsClaims.getBody().getExpiration());
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
        return true;
    }

    public void JwtToResponseHeaderAndCookie(HttpServletResponse response, JwtTokenDTO tokenDTO) {
//        String accessToken = createAccessToken(authentication);
//        String refreshToken = createRefreshToken(authentication);

        response.setHeader(JwtAuthenticationFilter.AUTHORIZATION_HEADER, "Bearer " + tokenDTO.getAccessToken());
        Cookie cookieForRefreshToken = new Cookie("refresh-token", tokenDTO.getRefreshToken());
        cookieForRefreshToken.setPath("/");
        cookieForRefreshToken.setHttpOnly(true); // not accessible from JavaScript
        response.addCookie(cookieForRefreshToken);
    }
}
