package com.perfect.community.api.mapper.user;

import com.perfect.community.api.dto.user.UserDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
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
class UsersMapperTest {

    private static final Logger log = LogManager.getLogger();

    @Autowired
    private UsersMapper mapper;

    @BeforeEach
    void setUp() {
        assertNotNull(log);
        assertNotNull(mapper);
    }

    @Test
    void selectUserByUserId() {
        log.warn(mapper.selectUserByUserId("admin"));
    }

    @Test
    void selectUserWithAuthoritiesByUserId() {
        log.warn(mapper.selectUserWithAuthoritiesByUserId("admin"));
    }

    @Test
    void selectAllUsers() {
        log.warn(mapper.selectAllUsers());
    }

    @Test
    void selectAllUsersWithAuthorities() {
        log.warn(mapper.selectAllUsersWithAuthorities());
    }

    @Test
    void insertUser() {
        log.warn(mapper.insertUser(UserDTO.builder().userId("junit_tester").password("junit").userName("JUNIT_TESTER").build()));
        log.warn(mapper.selectUserByUserId("junit_tester"));
    }

    @Test
    void updateUser() {
        log.warn(mapper.updateUser(UserDTO.builder().userId("admin").userName("ADMIN").build()));
        log.warn(mapper.selectUserByUserId("admin"));
    }

    @Test
    void updatePassword() {
        log.warn(mapper.updatePassword(UserDTO.builder().userId("admin").password("1234").build()));
        log.warn(mapper.selectUserByUserId("admin"));
    }

    @Test
    void deleteUser() {

        // admin 유저가 관리하고 있는 게시판이 존재하기 때문에 삭제 불가능
        // admin 유저에게 게시판의 관리자를 다른 유저로 인가하도록 유도
        try {
            log.warn(mapper.deleteUser("admin"));
            log.warn(mapper.selectUserByUserId("admin"));
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        // tester1 유저의 권한까지 모두 삭제
        log.warn(mapper.deleteUser("tester1"));
        log.warn(mapper.selectUserWithAuthoritiesByUserId("tester1") == null ? "SUCCESS" : "FAILURE");

    }

    @Test
    void enableMember() {
        log.warn(mapper.enableMember("admin"));
        log.warn(mapper.selectUserByUserId("admin"));
    }

    @Test
    void disableUser() {
        log.warn(mapper.disableUser("admin"));
        log.warn(mapper.selectUserByUserId("admin"));
    }
}