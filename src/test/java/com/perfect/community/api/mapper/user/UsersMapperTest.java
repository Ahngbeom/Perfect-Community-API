package com.perfect.community.api.mapper.user;

import com.perfect.community.api.mapper.MapperTest;
import com.perfect.community.api.vo.user.UserVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("[Mapper] User")
@Transactional
class UsersMapperTest extends MapperTest {

    @Autowired
    protected UsersMapper usersMapper;

    @Autowired
    protected UserScrapPostMapper userScrapPostMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

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
    void insertUser() {
        try {
            log.warn(usersMapper.insertUser(UserVO.builder().user_id("junit_tester").password(passwordEncoder.encode("junit")).nickname("JUNIT_TESTER").build()));
            log.warn(usersMapper.selectUserByUserId("junit_tester"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void updateUser() {
        log.warn(usersMapper.updateUser(UserVO.builder().user_id("admin").nickname("Administrator").build()));
        log.warn(usersMapper.selectUserByUserId("admin"));
    }

    @Test
    void updatePassword() {
        log.warn(usersMapper.updatePassword(UserVO.builder().user_id("admin").password(passwordEncoder.encode("admin")).build()));
        log.warn(usersMapper.selectUserByUserId("admin"));
    }

    @Test
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
        log.warn(usersMapper.deleteUser("junit_tester"));
        log.warn(usersMapper.selectUserWithAuthoritiesByUserId("junit_tester") == null ? "SUCCESS" : "FAILURE");

    }

    @Test
    void enableMember() {
        log.warn(usersMapper.enableMember("admin"));
        log.warn(usersMapper.selectUserByUserId("admin"));
    }

    @Test
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