package com.perfect.community.api.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;
import redis.embedded.RedisServer;

import java.util.Optional;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class EmbeddedRedisConfig implements InitializingBean, DisposableBean {

    private final RedisServer redisServer;

    /**
     * Invoked by the containing {@code BeanFactory} after it has set all bean properties
     * and satisfied {@link BeanFactoryAware}, {@code ApplicationContextAware} etc.
     * <p>This method allows the bean instance to perform validation of its overall
     * configuration and final initialization when all bean properties have been set.
     */
    @Override
    public void afterPropertiesSet() {
//        redisServer = RedisServer.builder()
//                .port(6379)
//                .setting("maxmemory 128M") //maxheap 128M
//                .build();
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
