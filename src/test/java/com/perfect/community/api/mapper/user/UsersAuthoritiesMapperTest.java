package com.perfect.community.api.mapper.user;

import com.perfect.community.api.dto.user.UserAuthoritiesDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;

@ExtendWith(SpringExtension.class)
@ContextConfiguration({"file:web/WEB-INF/dispatcher-servlet.xml", "file:web/WEB-INF/securityContext.xml"})
@Transactional
class UsersAuthoritiesMapperTest {

    private static final Logger log = LogManager.getLogger();

    @Autowired
    private UsersAuthoritiesMapper mapper;

    @Test
    void selectAllUserAuthorities() {
        log.warn(mapper.selectAllUserAuthorities());
    }

    @Test
    void selectAllUserAuthoritiesByUserId() {
        log.warn(mapper.selectAllUserAuthoritiesByUserId("admin"));
    }

    @Test
    void insertUserAuthorities() {
        log.warn(mapper.insertUserAuthorities(
                UserAuthoritiesDTO.builder()
                        .userId("admin")
                        .authority("ROLE_MANAGER")
                        .build()
        ));
        log.warn(mapper.selectAllUserAuthoritiesByUserId("admin"));
    }

    @Test
    void deleteUserAuthorities() {
        log.warn(mapper.deleteUserAuthorities(
                UserAuthoritiesDTO.builder()
                        .userId("admin")
                        .authority("ROLE_ADMIN")
                        .build()
        ));
        log.warn(mapper.selectAllUserAuthoritiesByUserId("admin"));
    }

    @Test
    void deleteAllAuthorityToMember() {
        log.warn(mapper.deleteAllUserAuthorities("admin"));
        log.warn(mapper.selectAllUserAuthoritiesByUserId("admin"));
    }

    @Test
    void hasAuthority() {
        log.warn(mapper.hasAuthority("admin", "ROLE_ADMIN"));
        log.warn(mapper.hasAuthority("admin", "ROLE_MANAGER"));
    }
}