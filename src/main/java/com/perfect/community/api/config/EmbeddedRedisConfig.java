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

    private RedisServer redisServer;

    /**
     * Invoked by the containing {@code BeanFactory} after it has set all bean properties
     * and satisfied {@link BeanFactoryAware}, {@code ApplicationContextAware} etc.
     * <p>This method allows the bean instance to perform validation of its overall
     * configuration and final initialization when all bean properties have been set.
     *
     * @throws Exception in the event of misconfiguration (such as failure to set an
     *                   essential property) or if initialization fails for any other reason
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        redisServer = new RedisServer(6379);
//        redisServer = RedisServer.builder()
//                .port(6379)
//                .setting("maxmemory 128M") //maxheap 128M
//                .build();
        try {
            redisServer.start();
            log.info("Redis is active = {}", redisServer.isActive());
        } catch (Exception e) {
            log.warn("Redis is active = {}", redisServer.isActive());
        }
    }

    /**
     * Invoked by the containing {@code BeanFactory} on destruction of a bean.
     *
     * @throws Exception in case of shutdown errors. Exceptions will get logged
     *                   but not rethrown to allow other beans to release their resources as well.
     */
    @Override
    public void destroy() throws Exception {
        Optional.ofNullable(redisServer).ifPresent(RedisServer::stop);
        assert redisServer != null;
        log.info("Redis is stopped = {}", redisServer.isActive());
    }
}
