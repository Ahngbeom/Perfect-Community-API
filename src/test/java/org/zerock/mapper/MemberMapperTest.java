package org.zerock.mapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.zerock.domain.AuthVO;
import org.zerock.domain.MemberVO;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration({"file:web/WEB-INF/dispatcher-servlet.xml", "file:web/WEB-INF/securityContext.xml"})
class MemberMapperTest {

    private static final Logger log = LogManager.getLogger();

    @Autowired
    private MemberMapper mapper;

    @Autowired
    private BCryptPasswordEncoder bCryptEncoder;

    @Autowired
    private PasswordEncoder encoder;

    @BeforeEach
    void setUp() {
        assertNotNull(mapper);
    }

    @Test
    void readMember() {
        log.info(mapper.readMember("aa"));
    }

    @Test
    void readAllMember() {
        mapper.readAllMember().forEach(log::info);
    }

    @Test
    void deleteMember() {
        MemberVO member = mapper.readMember("new2");
        log.info(member);

        if (bCryptEncoder.matches("1234", member.getPassword())) {
            log.info("Passwords match.");
            log.warn(mapper.deleteAuthOfSpecificMember(member.getUserId()));
            log.warn(mapper.deleteMember(member.getUserId()));
        }
    }

    @Test
    void insertMember() {
        MemberVO member = new MemberVO("new2", encoder.encode("1234"), "New Member");
//        log.info(member);
        if (mapper.insertMember(member) == 1) {
            log.info("success");
            if (mapper.insertAuthorityToMember(new AuthVO(member.getUserId(), "ROLE_USER")) == 1) {
                log.info("success");
            } else {
                log.error("failed");
                mapper.deleteMember(member.getUserId());
            }
        } else {
            log.error("failed");
        }
    }

    @Test
    void insertAdmin() {
        MemberVO member = new MemberVO("admin", encoder.encode("admin"), "Administrator");
//        log.info(member);
        if (mapper.insertMember(member) == 1) {
            log.info("success");
            if (mapper.insertAuthorityToMember(new AuthVO(member.getUserId(), "ROLE_ADMIN")) == 1) {
                log.info("success");
            } else {
                log.error("failed");
                mapper.deleteMember(member.getUserId());
            }
        } else {
            log.error("failed");
        }
    }

    @Test
    void InsertAuthOfSpecificMember() {
        AuthVO auth = new AuthVO("new", "ROLE_USER");
        if (mapper.insertAuthorityToMember(auth) == 1) {
            log.info("success");
        } else {
            log.error("failed");
        }
    }

    @Test
    void deleteAuthTests() {
        log.warn(mapper.deleteOneAuthorityToMember(new AuthVO("aa", "ROLE_ADMIN")));
    }
}