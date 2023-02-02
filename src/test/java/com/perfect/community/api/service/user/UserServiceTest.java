package com.perfect.community.api.service.user;

import com.perfect.community.api.dto.user.UserDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration({"file:web/WEB-INF/dispatcher-servlet.xml", "file:web/WEB-INF/securityContext.xml"})
@Transactional
class UserServiceTest {

    protected static final Logger log = LogManager.getLogger(UserServiceTest.class);

    @Autowired
    private UserService service;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        assertNotNull(log);
        assertNotNull(service);
        assertNotNull(passwordEncoder);
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
                .userId("aaaaa")
                .password("aaaa")
                .nickname("AAA")
                .authority("ROLE_USER")
                .build();
        service.createUser(user);
        log.warn(service.getUserListWithAuthorities());
    }

    @Test
    @Rollback(value = false)
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
    void changeUserPassword() {
        service.changeUserPassword(
                UserDTO.builder()
                        .userId("admin")
                        .password("admin")
                        .build()
        );
        UserDTO user = service.getUserInfoByUserId("admin");
        log.warn(user);
        log.warn(passwordEncoder.matches("abcde", user.getPassword()));
        log.warn(passwordEncoder.matches("1234", user.getPassword()));
    }

    @Test
    void removeUser() {
        service.removeUser("tester");
        log.warn(service.getUserInfoByUserId("tester"));
    }

    @Test
    void disableUser() {
        service.disableUser("admin");
        log.warn(service.getUserInfoByUserId("admin"));
    }

    @Test
    @Rollback(value = false)
    void enableUser() {
        service.disableUser("admin");
        log.warn(service.getUserInfoByUserId("admin"));
        service.enableUser("admin");
        log.warn(service.getUserInfoByUserId("admin"));
    }

    @Test
    void verifyPassword() {
        log.warn(service.verifyPassword("admin", "1234"));
    }

    @Test
    void userIdAvailability() {
        log.warn(service.isValidUserId("admin"));
        log.warn(service.isValidUserId("ahngbeom"));
    }

    @Test
    void nicknameAvailability() {
        log.warn(service.isValidNickname("Administrator"));
        log.warn(service.isValidNickname("AHNGBEOM"));
    }
}