/*
 * Copyright (C) 23. 2. 7. 오후 9:19 Ahngbeom (https://github.com/Ahngbeom)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.perfect.community.api.service.redis;

import com.perfect.community.api.dto.jwt.TokenDTO;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class RedisService {
    private static final String JWT_KEY = "jwt:";
    private static final String ACCESS_TOKEN_FIELD = "access_token";
    private static final String REFRESH_TOKEN_FIELD = "refresh_token";

    public static final String POST_VIEWS_RECORD_BY_ANONYMOUS_KEY = "post_views_record_by_anonymous:";
    public static final String IP_ADDRESS_FIELD = "ip_address";
    public static final String POST_NO_FIELD = "post_no";

    private final RedisTemplate<String, String> redisTemplate;

    public RedisService(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public Long add(String key, String... values) {
        return redisTemplate.opsForSet().add(key, values);
    }

    public Boolean putIfAbsent(String key, String hashKey, String hashValue) {
        return redisTemplate.opsForHash().putIfAbsent(key, hashKey, hashValue);
    }

    public void putJWT(TokenDTO tokenDTO) {
        String key = JWT_KEY + tokenDTO.getUsername();
        try {
            redisTemplate.opsForHash().put(key, ACCESS_TOKEN_FIELD, tokenDTO.getAccessToken());
            redisTemplate.opsForHash().put(key, REFRESH_TOKEN_FIELD, tokenDTO.getRefreshToken());
            redisTemplate.expireAt(key, tokenDTO.getRefreshTokenExpiration());
            log.info("[Save to Redis Successful]\n ACCESS_TOKEN: {}\n REFRESH_TOKEN: {}\n Expire: {}",
                    redisTemplate.opsForHash().get(key, ACCESS_TOKEN_FIELD),
                    redisTemplate.opsForHash().get(key, REFRESH_TOKEN_FIELD),
                    redisTemplate.getExpire(key));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteJWT(String username) {
        String key = JWT_KEY + username;
        redisTemplate.opsForHash().delete(key, ACCESS_TOKEN_FIELD);
        redisTemplate.opsForHash().delete(key, REFRESH_TOKEN_FIELD);
    }

    public void expire(String key, Date date) {
        redisTemplate.expireAt(key, date);
    }

    public void expire(String key, long timeout, TimeUnit timeUnit) {
        redisTemplate.expire(key, timeout, timeUnit);
    }

    public boolean validateAccessTokenByUsernameOnRedis(String username, String accessToken) throws JwtException {
//        log.info("Redis access token: {}", redisTemplate.opsForHash().get(JWT_KEY + username, ACCESS_TOKEN_FIELD));
//        log.info("Requested access token: {}", accessToken);
        return accessToken.equals(redisTemplate.opsForHash().get(JWT_KEY + username, ACCESS_TOKEN_FIELD));
    }

    public boolean validateRefreshTokenByUsernameOnRedis(String username, String refreshToken) throws JwtException {
//        log.info("Redis refresh token: {}", redisTemplate.opsForHash().get(JWT_KEY + username, REFRESH_TOKEN_FIELD));
//        log.info("Requested refresh token: {}", refreshToken);
        return refreshToken.equals(redisTemplate.opsForHash().get(JWT_KEY + username, REFRESH_TOKEN_FIELD));
    }


}
