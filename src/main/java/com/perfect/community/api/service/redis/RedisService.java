package com.perfect.community.api.service.redis;

import com.perfect.community.api.dto.jwt.TokenDTO;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Date;

@Slf4j
@Service
public class RedisService {
    private static final String JWT_KEY = "jwt:";
    private static final String ACCESS_TOKEN_FIELD = "access_token";
    private static final String REFRESH_TOKEN_FIELD = "refresh_token";

    private final RedisTemplate<String, String> redisTemplate;
    private final HashOperations<String, String, String> hashOperations;

    private final JedisPool jedisPool = new JedisPool("localhost", 6379);

    public RedisService(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.hashOperations = this.redisTemplate.opsForHash();
    }

    public void put(String key, String hashKey, String hashValue) {
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.hset(key, hashKey, hashValue);
        }
//        hashOperations.put();
    }

    public Long putJWT(TokenDTO tokenDTO) {
        try (Jedis jedis = jedisPool.getResource()) {
            String key = JWT_KEY + tokenDTO.getUsername();
            return jedis.hset(key, ACCESS_TOKEN_FIELD, tokenDTO.getAccessToken())
                    | jedis.hset(key, REFRESH_TOKEN_FIELD, tokenDTO.getRefreshToken())
                    | expire(key, tokenDTO.getRefreshTokenExpiration());
        }
    }

    public Long deleteJWT(String username) {
        try (Jedis jedis = jedisPool.getResource()) {
            String key = JWT_KEY + username;
            return jedis.hdel(key, ACCESS_TOKEN_FIELD) | jedis.hdel(key, REFRESH_TOKEN_FIELD);
        }
    }

    public Long expire(String key, Date date) {
        try (Jedis jedis = jedisPool.getResource()) {
//            redisTemplate.expireAt(key, date);
            return jedis.pexpireAt(key, date.getTime());
        }
    }

    public void validateAccessTokenByUsername(String username, String accessToken) throws JwtException {
        try (Jedis jedis = jedisPool.getResource()) {
            if (!accessToken.equals(jedis.hget(JWT_KEY + username, ACCESS_TOKEN_FIELD)))
                throw new JwtException("Invalid JWT.");
        }
    }

    public void validateRefreshTokenByUsername(String username, String refreshToken) throws JwtException {
        try (Jedis jedis = jedisPool.getResource()) {
            if (!refreshToken.equals(jedis.hget(JWT_KEY + username, REFRESH_TOKEN_FIELD)))
                throw new JwtException("Invalid JWT.");
        }
    }


}
