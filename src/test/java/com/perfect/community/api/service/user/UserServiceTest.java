package com.perfect.community.api.service.user;

import com.perfect.community.api.dto.user.UserDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration({"file:web/WEB-INF/dispatcher-servlet.xml", "file:web/WEB-INF/securityContext.xml"})
@Transactional
class UserServiceTest {

    private static final Logger log = LogManager.getLogger(UserServiceTest.class);

    @Autowired
    private UserServiceImpl service;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        assertNotNull(service);
    }

    @Test
    void getUserList() {
        log.warn(service.getUserList());
    }

    @Test
    void getUserListWithAuthorities() {
        log.warn(service.getUserListWithAuthorities());
    }

    @Test
    void getUserInfoByUserId() {
        log.warn(service.getUserInfoByUserId("admin"));
    }

    @Test
    void getUserInfoWithAuthoritiesByUserId() {
        log.warn(service.getUserInfoWithAuthoritiesByUserId("admin"));
    }

    @Test
    void createUser() {
        UserDTO user = UserDTO.builder()
                .userId("aaa")
                .password("aaaa")
                .nickname("AAA")
                .authorities(Collections.singletonList("ROLE_USER"))
                .build();
        service.createUser(user);
        log.warn(service.getUserListWithAuthorities());
    }

    @Test
    void updateUserInfo() {
        service.updateUserInfo(
                UserDTO.builder()
                        .userId("admin")
                        .nickname("관리자")
                        .build()
        );
        log.warn(service.getUserInfoByUserId("admin"));
    }

    @Test
    void changeUserPassword() {
        service.changeUserPassword(
                UserDTO.builder()
                        .userId("admin")
                        .password("abcde")
                        .build()
        );
        UserDTO user = service.getUserInfoByUserId("admin");
        log.warn(user);
        log.warn(passwordEncoder.matches("abcde", user.getPassword()));
        log.warn(passwordEncoder.matches("12345", user.getPassword()));
    }

    @Test
    void removeUser() {
        service.removeUser("tester1");
        log.warn(service.getUserInfoByUserId("tester1"));
    }

    @Test
    void disableUser() {
        service.disableUser("admin");
        log.warn(service.getUserInfoByUserId("admin"));
    }

    @Test
    void enableUser() {
        service.disableUser("admin");
        log.warn(service.getUserInfoByUserId("admin"));
        service.enableUser("admin");
        log.warn(service.getUserInfoByUserId("admin"));
    }

    @Test
    void verifyPassword() {
        log.warn(service.verifyPassword("admin", "1234"));
        log.warn(service.verifyPassword("admin", "admin"));
    }

    @Test
    void userIdAvailability() {
        log.warn(service.userIdAvailability("admin"));
        log.warn(service.userIdAvailability("ahngbeom"));
    }

    @Test
    void nicknameAvailability() {
        log.warn(service.nicknameAvailability("Administrator"));
        log.warn(service.nicknameAvailability("AHNGBEOM"));
    }
}