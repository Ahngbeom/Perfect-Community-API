/*
 * Copyright (C) 23. 2. 4. 오후 8:53 Ahngbeom (https://github.com/Ahngbeom)
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

package com.perfect.community.api.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;
import redis.embedded.RedisServer;

import java.util.Optional;

@Slf4j
@Configuration
public class EmbeddedRedisConfig implements InitializingBean, DisposableBean {

    private final RedisServer redisServer;

    public EmbeddedRedisConfig() {
        this.redisServer = RedisServer.builder()
                .port(6379)
                .setting("maxmemory 128M") //maxheap 128M
                .build();
    }

    /**
     * Invoked by the containing {@code BeanFactory} after it has set all bean properties
     * and satisfied {@link BeanFactoryAware}, {@code ApplicationContextAware} etc.
     * <p>This method allows the bean instance to perform validation of its overall
     * configuration and final initialization when all bean properties have been set.
     */
    @Override
    public void afterPropertiesSet() {
        if (!redisServer.isActive()) {
            try {
                redisServer.start();
                log.info("Redis is active = {}", redisServer.isActive());
            } catch (Exception e) {
                log.warn("{}", e.getMessage());
            }
        } else {
            log.info("Redis is active = {}", redisServer.isActive());
        }
    }

    /**
     * Invoked by the containing {@code BeanFactory} on destruction of a bean.
     *
     */
    @Override
    public void destroy() {
        Optional.ofNullable(redisServer).ifPresent(RedisServer::stop);
        assert redisServer != null;
        log.info("Redis is stopped = {}", !redisServer.isActive());
    }
}
