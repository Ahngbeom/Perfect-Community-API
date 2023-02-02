package com.perfect.community.api.service.redis;

import com.perfect.community.api.dto.jwt.TokenDTO;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
public class RedisService {
    private static final String JWT_KEY = "jwt:";
    private static final String ACCESS_TOKEN_FIELD = "access_token";
    private static final String REFRESH_TOKEN_FIELD = "refresh_token";

    private final RedisTemplate<String, String> redisTemplate;

    public RedisService(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void put(String key, String hashKey, String hashValue) {
        redisTemplate.opsForHash().put(key, hashKey, hashValue);
    }

    public void putJWT(TokenDTO tokenDTO) {
        String key = JWT_KEY + tokenDTO.getUsername();
        redisTemplate.opsForHash().put(key, ACCESS_TOKEN_FIELD, tokenDTO.getAccessToken());
        redisTemplate.opsForHash().put(key, REFRESH_TOKEN_FIELD, tokenDTO.getRefreshToken());
        redisTemplate.expireAt(key, tokenDTO.getRefreshTokenExpiration());
    }

    public void deleteJWT(String username) {
        String key = JWT_KEY + username;
        redisTemplate.opsForHash().delete(key, ACCESS_TOKEN_FIELD);
        redisTemplate.opsForHash().delete(key, REFRESH_TOKEN_FIELD);
    }

    public void expire(String key, Date date) {
        redisTemplate.expireAt(key, date);
    }

    public boolean validateAccessTokenByUsername(String username, String accessToken) throws JwtException {
        return accessToken.equals(redisTemplate.opsForHash().get(JWT_KEY + username, ACCESS_TOKEN_FIELD));
    }

    public boolean validateRefreshTokenByUsername(String username, String refreshToken) throws JwtException {
        return refreshToken.equals(redisTemplate.opsForHash().get(JWT_KEY + username, REFRESH_TOKEN_FIELD));
    }


}
