package com.perfect.community.api.jwt;

import com.perfect.community.api.dto.jwt.TokenDTO;
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

    public enum TOKEN_TYPE {
        ACCESS,
        REFRESH
    }

    private static final String AUTHORITIES_KEY = "auth";
    private static final String TOKEN_TYPE_KEY = "type";
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

    public TokenDTO generateToken(Authentication authentication) {
        String authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));

        long now = new Date().getTime();
        Date accessTokenValidity = new Date(now + this.accessTokenValidityInMilliseconds);
        Date refreshTokenValidity = new Date(now + this.refreshTokenValidityInMilliseconds);

//        log.warn("Access token expiration = {}", validity);

        String accessToken = Jwts.builder()
                .setSubject(authentication.getName())
                .claim(TOKEN_TYPE_KEY, TOKEN_TYPE.ACCESS)
                .claim(AUTHORITIES_KEY, authorities)
                .signWith(accessSecretkey, SignatureAlgorithm.HS512)
                .setExpiration(accessTokenValidity)
                .compact();

        String refreshToken = Jwts.builder()
                .setSubject(authentication.getName())
                .claim(TOKEN_TYPE_KEY, TOKEN_TYPE.REFRESH)
                .claim(AUTHORITIES_KEY, authorities)
                .signWith(accessSecretkey, SignatureAlgorithm.HS512)
                .setExpiration(refreshTokenValidity)
                .compact();

        return TokenDTO.builder()
                .username(authentication.getName())
                .accessToken(accessToken)
                .accessTokenExpiration(accessTokenValidity)
                .refreshToken(refreshToken)
                .refreshTokenExpiration(refreshTokenValidity)
                .build();
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

    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(accessSecretkey)
                .build()
                .parseClaimsJws(token) // JWS(Json Web Signature): 인증 정보를 서버가 보유한 private key로 서명한 것을 토큰화한 객체
                .getBody();

        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        User principal = new User(claims.getSubject(), "[PROTECTED]", authorities);

        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

//    public Authentication getAuthentication(TOKEN_TYPE type, String token) {
//        if (token == null)
//            return null;
//        Claims claims;
//        if (type.equals(TOKEN_TYPE.ACCESS)) {
//            claims = Jwts.parserBuilder()
//                    .setSigningKey(accessSecretkey)
//                    .build()
//                    .parseClaimsJws(token) // JWS(Json Web Signature): 인증 정보를 서버가 보유한 private key로 서명한 것을 토큰화한 객체
//                    .getBody();
//        } else {
//            claims = Jwts.parserBuilder()
//                    .setSigningKey(refreshSecretkey)
//                    .build()
//                    .parseClaimsJws(token) // JWS(Json Web Signature): 인증 정보를 서버가 보유한 private key로 서명한 것을 토큰화한 객체
//                    .getBody();
//        }
//        Collection<? extends GrantedAuthority> authorities =
//                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
//                        .map(SimpleGrantedAuthority::new)
//                        .collect(Collectors.toList());
//
//        User principal = new User(claims.getSubject(), "[PROTECTED]", authorities);
//
//        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
//    }

    public Date getExpiration(TOKEN_TYPE type, String token) {
        if (type.equals(JwtTokenProvider.TOKEN_TYPE.ACCESS))
            return Jwts.parserBuilder().setSigningKey(accessSecretkey).build().parseClaimsJws(token).getBody().getExpiration();
        else
            return Jwts.parserBuilder().setSigningKey(refreshSecretkey).build().parseClaimsJws(token).getBody().getExpiration();
    }

    /**
     * @throws UnsupportedJwtException  if the {@code claimsJws} argument does not represent an Claims JWS
     * @throws MalformedJwtException    if the {@code claimsJws} string is not a valid JWS
     * @throws SignatureException       if the {@code claimsJws} JWS signature validation fails
     * @throws ExpiredJwtException      if the specified JWT is a Claims JWT and the Claims has an expiration time
     *                                  before the time this method is invoked.
     * @throws IllegalArgumentException if the {@code claimsJws} string is {@code null} or empty or only whitespace
     */
    public boolean validateToken(String token) throws JwtException {
        Jws<Claims> jwsClaims = Jwts.parserBuilder().setSigningKey(accessSecretkey).build().parseClaimsJws(token);
        log.info("JWS Claims = {}\n {}", jwsClaims, jwsClaims.getBody().getExpiration());
        return true;
    }

    public void validateRefreshToken(String token) {
        Jws<Claims> jwsClaims = Jwts.parserBuilder().setSigningKey(refreshSecretkey).build().parseClaimsJws(token);
        log.info("[Refresh Token]\n JWS Claims = {}\n {}", jwsClaims, jwsClaims.getBody().getExpiration());
    }

    public void JwtToResponseHeaderAndCookie(HttpServletResponse response, TokenDTO tokenDTO) {
        response.setHeader(JwtAuthenticationFilter.AUTHORIZATION_HEADER, "Bearer " + tokenDTO.getAccessToken());
        Cookie cookieForRefreshToken = new Cookie("refresh-token", tokenDTO.getRefreshToken());
        cookieForRefreshToken.setPath("/");
        cookieForRefreshToken.setHttpOnly(true); // not accessible from JavaScript
        response.addCookie(cookieForRefreshToken);
    }

    public void JwtToResponseHeaderAndCookie(HttpServletResponse response, String accessToken, String refreshToken) {
        response.setHeader(JwtAuthenticationFilter.AUTHORIZATION_HEADER, "Bearer " + accessToken);
        Cookie cookieForRefreshToken = new Cookie("refresh-token", refreshToken);
        cookieForRefreshToken.setPath("/");
        cookieForRefreshToken.setHttpOnly(true); // not accessible from JavaScript
        response.addCookie(cookieForRefreshToken);
    }
}
