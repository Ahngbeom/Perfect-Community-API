package org.zerock.service;

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
import org.zerock.domain.AuthVO;
import org.zerock.domain.MemberVO;

import java.security.Principal;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration({"file:web/WEB-INF/dispatcher-servlet.xml", "file:web/WEB-INF/securityContext.xml"})
class MemberServiceTest {

    private static final Logger log = LogManager.getLogger();

    @InjectMocks
    @Autowired
    private MemberService service;

    @Autowired
    private PasswordEncoder encoder;

    @Mock
    private Principal principal;

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
        MemberVO member = new MemberVO("new", "1234", "new");
        service.createUser(member, new AuthVO(member.getUserId(), "ROLE_USER"));
//        service.authorizationToUser(new AuthVO(member.getUserId(), "ROLE_USER"));
    }

    @Test
    void adminDeleteUser() {
        service.deleteUser("new2");
    }

    @Test
    void adminSingleAuthorityDelete() {

    }
}