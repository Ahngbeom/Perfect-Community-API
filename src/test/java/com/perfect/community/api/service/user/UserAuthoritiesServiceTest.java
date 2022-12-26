package com.perfect.community.api.service.user;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration({"file:web/WEB-INF/dispatcher-servlet.xml", "file:web/WEB-INF/securityContext.xml"})
class UserAuthoritiesServiceTest {

    private static final Logger log = LogManager.getLogger();

    @Autowired
    private UserAuthoritiesService service;

    @BeforeEach
    void setUp() {
        assertNotNull(log);
        assertNotNull(service);
    }

    @Test
    void getAllUserAuthorities() {
        log.warn(service.getAllUserAuthorities());
    }

    @Test
    void getAllUserAuthoritiesByUserId() {
    }

    @Test
    void grantUserAuthorities() {
    }

    @Test
    void revokeUserAuthorities() {
    }

    @Test
    void revokeAllUserAuthoritiesByUserId() {
    }
}