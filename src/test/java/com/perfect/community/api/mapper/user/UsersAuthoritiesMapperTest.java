package com.perfect.community.api.mapper.user;

import com.perfect.community.api.dto.user.UserAuthoritiesDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;

@DisplayName("[Mapper] User's authorities")
class UsersAuthoritiesMapperTest extends UsersMapperTest {

    @Autowired
    private UsersAuthoritiesMapper mapper;

    @Test
    @DisplayName("Get user's all authorities")
    void selectAllUserAuthorities() {
        log.warn(mapper.selectAllUserAuthorities());
    }

    @Test
    @DisplayName("Get user's all authorities by user id")
    void selectAllUserAuthoritiesByUserId() {
        log.warn(mapper.selectAllUserAuthoritiesByUserId("admin"));
    }

    @Test
    @DisplayName("Add user's authorities")
    void insertUserAuthorities() {
        log.warn(mapper.insertUserAuthorities(
                UserAuthoritiesDTO.builder()
                        .userId("tester")
                        .authorities(Collections.singleton("ROLE_MANAGER"))
                        .build()));
        log.warn(mapper.selectAllUserAuthoritiesByUserId("tester"));
    }

    @Test
    @DisplayName("Delete user's authorities")
    void deleteUserAuthorities() {
        log.info(mapper.selectAllUserAuthoritiesByUserId("admin"));
        log.info(mapper.deleteUserAuthorities(
                UserAuthoritiesDTO.builder()
                        .userId("admin")
                        .authorities(Collections.singleton("ROLE_ADMIN"))
                        .build()
        ));
        log.info(mapper.selectAllUserAuthoritiesByUserId("admin"));
    }

    @Test
    @DisplayName("Delete all user's authorities")
    void deleteAllAuthorityToMember() {
        log.warn(mapper.deleteAllUserAuthorities("admin"));
        log.warn(mapper.selectAllUserAuthoritiesByUserId("admin"));
    }

    @Test
    @DisplayName("Has user's authorities")
    void hasAuthority() {
        log.warn(mapper.hasAuthority("admin", "ROLE_ADMIN"));
        log.warn(mapper.hasAuthority("admin", "ROLE_MANAGER"));
    }
}