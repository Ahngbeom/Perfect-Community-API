package com.perfect.community.api.service.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("[Service] User authorities")
class UserAuthoritiesServiceTest extends UserServiceTest {

    @Autowired
    protected UserAuthoritiesService userAuthoritiesService;

    @BeforeEach
    void setUp() {
        assertNotNull(log);
        assertNotNull(userAuthoritiesService);
    }

    @Test
    @DisplayName("Get all user's authorities")
    void getAllUserAuthorities() {
        log.warn(userAuthoritiesService.getAllUserAuthorities());
    }

    @Test
    @DisplayName("Get all user's authorities by user id")
    void getAllUserAuthoritiesByUserId() {
    }

    @Test
    @DisplayName("Grant user's authorities")
    void grantUserAuthorities() {
    }

    @Test
    @DisplayName("Revoke user's authorities")
    void revokeUserAuthorities() {
    }

    @Test
    @DisplayName("Revoke user's all authorities")
    void revokeAllUserAuthoritiesByUserId() {
    }
}