package com.perfect.community.api.mapper.authorities;

import com.perfect.community.api.dto.authorities.AuthoritiesDTO;
import com.perfect.community.api.mapper.MapperTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@DisplayName("[Mapper] Authorities")
class AuthoritiesMapperTest extends MapperTest {

    @Autowired
    private AuthoritiesMapper mapper;

    @Test
    @DisplayName("select all authorities")
    void selectAllAuthority() {
        log.warn(mapper.selectAllAuthority());
    }

    @Test
    @DisplayName("Insert authority")
    void insertAuthority() {
        log.warn(mapper.insertAuthority(AuthoritiesDTO.builder().authority("ROLE_TESTER").build()));
        log.warn(mapper.selectAllAuthority());
    }

    @Test
    @DisplayName("Delete authority")
    void deleteAuthority() {
        log.warn(mapper.deleteAuthority(AuthoritiesDTO.builder().authority("ROLE_MANAGER").build()));
        log.warn(mapper.selectAllAuthority());
    }

    @Test
    @DisplayName("Update authority")
    void updateAuthority() {
        log.warn(mapper.updateAuthority("ROLE_MANAGER", "ROLE_MANAGERRRR"));
        log.warn(mapper.selectAllAuthority());
    }
}