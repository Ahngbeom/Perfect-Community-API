package com.perfect.community.api.mapper.user;

import com.perfect.community.api.mapper.MapperTest;
import com.perfect.community.api.vo.user.UserVO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

@DisplayName("[Mapper] User")
class UsersMapperTest extends MapperTest {

    @Test
    @Transactional(readOnly = true)
    @DisplayName("User's details")
    void selectUserByUserId() {
        log.warn(usersMapper.selectUserByUserId("admin"));
    }

    @Test
    @Transactional(readOnly = true)
    @DisplayName("User's details with authorities")
    void selectUserWithAuthoritiesByUserId() {
        log.warn(usersMapper.selectUserWithAuthoritiesByUserId("admin"));
    }

    @Test
    @Transactional(readOnly = true)
    @DisplayName("User list")
    void selectAllUsers() {
        log.warn(usersMapper.selectAllUsers());
    }

    @Test
    @Transactional(readOnly = true)
    @DisplayName("User list with authorities")
    void selectAllUsersWithAuthorities() {
        log.warn(usersMapper.selectAllUsersWithAuthorities());
    }

    @Test
    @DisplayName("Insert user")
    void insertUser() {
        try {
            log.warn(usersMapper.insertUser(UserVO.builder().user_id("junit_tester").password(passwordEncoder.encode("junit")).nickname("JUNIT_TESTER").build()));
            log.warn(usersMapper.selectUserByUserId("junit_tester"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Update user")
    void updateUser() {
        log.warn(usersMapper.updateUser(UserVO.builder().user_id("admin").nickname("Administrator").build()));
        log.warn(usersMapper.selectUserByUserId("admin"));
    }

    @Test
    @DisplayName("Update password")
    void updatePassword() {
        log.warn(usersMapper.updatePassword(UserVO.builder().user_id("admin").password(passwordEncoder.encode("admin")).build()));
        log.warn(usersMapper.selectUserByUserId("admin"));
    }

    @Test
    @DisplayName("Delete user")
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
    @DisplayName("Enable user")
    void enableUser() {
        log.warn(usersMapper.enableUser("admin"));
        log.warn(usersMapper.selectUserByUserId("admin"));
    }

    @Test
    @DisplayName("Disable user")
    void disableUser() {
        log.warn(usersMapper.disableUser("admin"));
        log.warn(usersMapper.selectUserByUserId("admin"));
        log.warn(usersMapper.enableUser("admin"));
    }

    @Test
    @Transactional(readOnly = true)
    @DisplayName("User id availability")
    void userIdAvailability() {
        log.warn(usersMapper.userIdAvailability("admin"));
        log.warn(usersMapper.userIdAvailability("ahngbeom"));
    }

    @Test
    @Transactional(readOnly = true)
    @DisplayName("Nickname availability")
    void nicknameAvailability() {
        log.warn(usersMapper.nicknameAvailability("Administrator"));
        log.warn(usersMapper.nicknameAvailability("AHNG"));
    }
}