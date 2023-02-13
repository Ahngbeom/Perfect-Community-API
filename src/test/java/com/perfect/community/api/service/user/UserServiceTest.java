package com.perfect.community.api.service.user;

import com.perfect.community.api.dto.user.UserDTO;
import com.perfect.community.api.service.ServiceTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.Rollback;

import java.util.Collections;

@DisplayName("[Service] User")
class UserServiceTest extends ServiceTest {

    @Test
    @DisplayName("User list")
    void getUserList() {
        log.warn(service.getUserList());
    }

    @Test
    @DisplayName("User list with authorities")
    void getUserListWithAuthorities() {
        log.warn(service.getUserListWithAuthorities());
    }

    @Test
    @DisplayName("User's details")
    void getUserInfoByUserId() {
        log.warn(service.getUserInfoByUserId("admin"));
    }

    @Test
    @DisplayName("User's details with authorities")
    void getUserInfoWithAuthoritiesByUserId() {
        log.warn(service.getUserInfoWithAuthoritiesByUserId("admin"));
    }

    @Test
    @DisplayName("Create user")
    void createUser() {
        UserDTO user = UserDTO.builder()
                .userId("aaaaa")
                .password("aaaa")
                .nickname("AAA")
                .authorities(Collections.singleton("ROLE_USER"))
                .build();
        service.createUser(user);
        log.warn(service.getUserListWithAuthorities());
    }

    @Test
//    @Rollback(value = false)
    @DisplayName("Update user")
    void updateUserInfo() {
        service.updateUserInfo(
                UserDTO.builder()
                        .userId("admin")
                        .nickname("Administrator")
                        .build()
        );
        log.warn(service.getUserInfoByUserId("admin"));
    }

    @Test
    @Rollback(value = false)
    @DisplayName("Change user's password")
    void changeUserPassword() {
        service.changeUserPassword(
                UserDTO.builder()
                        .userId("admin")
                        .password("admin")
                        .build()
        );
        UserDTO user = service.getUserInfoByUserId("admin");
        log.warn(user);
        log.warn(passwordEncoder.matches("admin", user.getPassword()));
        log.warn(passwordEncoder.matches("abcde", user.getPassword()));
        log.warn(passwordEncoder.matches("1234", user.getPassword()));
    }

    @Test
    @DisplayName("Remove user")
    void removeUser() {
        service.secessionUser("tester");
        log.warn(service.getUserInfoByUserId("tester"));
    }

    @Test
    @DisplayName("Disable user")
    void disableUser() {
        service.disableUser("admin");
        log.warn(service.getUserInfoByUserId("admin"));
    }

    @Test
//    @Rollback(value = false)
    @DisplayName("enable user")
    void enableUser() {
        service.disableUser("admin");
        log.warn(service.getUserInfoByUserId("admin"));
        service.enableUser("admin");
        log.warn(service.getUserInfoByUserId("admin"));
    }

    @Test
    @DisplayName("User's password verify")
    void verifyPassword() {
        log.warn(service.verifyPassword("admin", "admin"));
    }

    @Test
    @DisplayName("User id availability")
    void userIdAvailability() {
        log.warn(service.isValidUserId("admin"));
        log.warn(service.isValidUserId("ahngbeom"));
    }

    @Test
    @DisplayName("Nickname availability")
    void nicknameAvailability() {
        log.warn(service.isValidNickname("Administrator"));
        log.warn(service.isValidNickname("AHNGBEOM"));
    }
}