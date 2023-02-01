package com.perfect.community.api.mapper.user;

import com.perfect.community.api.mapper.MapperTest;
import com.perfect.community.api.vo.user.UserVO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("[Mapper] User")
class UsersMapperTest extends MapperTest {

    protected static final Logger log = LogManager.getLogger();

    @Autowired
    protected UsersMapper usersMapper;

    @Autowired
    protected UserScrapPostMapper userScrapPostMapper;

    @BeforeEach
    void setUp() {
        assertNotNull(usersMapper);
        assertNotNull(userScrapPostMapper);
    }

    @Test
    @Transactional(readOnly = true)
    void selectUserByUserId() {
        log.warn(usersMapper.selectUserByUserId("admin"));
    }

    @Test
    @Transactional(readOnly = true)
    void selectUserWithAuthoritiesByUserId() {
        log.warn(usersMapper.selectUserWithAuthoritiesByUserId("admin"));
    }

    @Test
    @Transactional(readOnly = true)
    void selectAllUsers() {
        log.warn(usersMapper.selectAllUsers());
    }

    @Test
    @Transactional(readOnly = true)
    void selectAllUsersWithAuthorities() {
        log.warn(usersMapper.selectAllUsersWithAuthorities());
    }

    @Test
    @Transactional
    void insertUser() {
        log.warn(usersMapper.insertUser(UserVO.builder().user_id("junit_tester").password("junit").nickname("JUNIT_TESTER").build()));
        log.warn(usersMapper.selectUserByUserId("junit_tester"));
    }

    @Test
    @Transactional
    void updateUser() {
        log.warn(usersMapper.updateUser(UserVO.builder().user_id("admin").nickname("Administrator").build()));
        log.warn(usersMapper.selectUserByUserId("admin"));
    }

    @Test
    @Transactional
    void updatePassword() {
        log.warn(usersMapper.updatePassword(UserVO.builder().user_id("admin").password("admin").build()));
        log.warn(usersMapper.selectUserByUserId("admin"));
    }

    @Test
    @Transactional
    void deleteUser() {

        // admin 유저가 관리하고 있는 게시판이 존재하기 때문에 삭제 불가능
        // admin 유저에게 게시판의 관리자를 다른 유저로 인가하도록 유도
        try {
            log.warn(usersMapper.deleteUser("admin"));
            log.warn(usersMapper.selectUserByUserId("admin"));
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        // tester1 유저의 권한까지 모두 삭제
        log.warn(usersMapper.deleteUser("tester1"));
        log.warn(usersMapper.selectUserWithAuthoritiesByUserId("tester1") == null ? "SUCCESS" : "FAILURE");

    }

    @Test
    @Transactional
    void enableMember() {
        log.warn(usersMapper.enableMember("admin"));
        log.warn(usersMapper.selectUserByUserId("admin"));
    }

    @Test
    @Transactional
    void disableUser() {
        log.warn(usersMapper.disableUser("admin"));
        log.warn(usersMapper.selectUserByUserId("admin"));
        log.warn(usersMapper.enableMember("admin"));
    }

    @Test
    @Transactional(readOnly = true)
    void userIdAvailability() {
        log.warn(usersMapper.userIdAvailability("admin"));
        log.warn(usersMapper.userIdAvailability("ahngbeom"));
    }

    @Test
    @Transactional(readOnly = true)
    void nicknameAvailability() {
        log.warn(usersMapper.nicknameAvailability("Administrator"));
        log.warn(usersMapper.nicknameAvailability("AHNG"));
    }
}