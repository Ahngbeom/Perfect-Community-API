package com.perfect.community.api.mapper.authorities;

import com.perfect.community.api.dto.authorities.AuthoritiesDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration({"file:web/WEB-INF/dispatcher-servlet.xml", "file:web/WEB-INF/securityContext.xml"})
@Transactional
class AuthoritiesMapperTest {

    private static final Logger log = LogManager.getLogger(AuthoritiesMapperTest.class);

    @Autowired
    private AuthoritiesMapper mapper;

    @Test
    void selectAllAuthority() {
        log.warn(mapper.selectAllAuthority());
    }

    @Test
    void insertAuthority() {
        log.warn(mapper.insertAuthority(AuthoritiesDTO.builder().authority("ROLE_TESTER").build()));
        log.warn(mapper.selectAllAuthority());
    }

    @Test
    void deleteAuthority() {
        log.warn(mapper.deleteAuthority(AuthoritiesDTO.builder().authority("ROLE_MANAGER").build()));
        log.warn(mapper.selectAllAuthority());
    }

    @Test
    void updateAuthority() {
        log.warn(mapper.updateAuthority("ROLE_MANAGER", "ROLE_MANAGERRRR"));
        log.warn(mapper.selectAllAuthority());
    }
}