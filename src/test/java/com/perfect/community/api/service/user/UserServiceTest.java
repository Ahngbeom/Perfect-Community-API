package com.perfect.community.api.service.user;

import com.perfect.community.api.mapper.user.UsersMapper;
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

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration({"file:web/WEB-INF/dispatcher-servlet.xml", "file:web/WEB-INF/securityContext.xml"})
class UserServiceTest {

    private static final Logger log = LogManager.getLogger();

    @InjectMocks
    @Autowired
    private UserServiceImpl service;

    @Mock
    private UsersMapper mapper;

    @Mock
    private PasswordEncoder encoder;

    @BeforeEach
    void setUp() {
        assertNotNull(service);
    }

    @Test
    void getUserList() {
        service.getUserList().forEach(log::info);
    }

    @Test
    void createUser() {
//        UserDTO member = UserDTO.builder()
//                .userId("aaa")
//                .password("aaaa")
//                .userName("AAA")
//                .authList(Collections.singletonList(AuthorityDTO.builder().authority("ROLE_USER").build()))
//                .build();
//        service.createUser(member);
//        log.info(member);
    }

    @Test
    void adminDeleteUser() {
        service.removeUser("aaa");
    }

    @Test
    void adminSingleAuthorityDelete() {

    }

}