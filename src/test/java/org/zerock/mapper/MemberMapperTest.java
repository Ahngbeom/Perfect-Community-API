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
import org.zerock.DTO.AuthorityDTO;
import org.zerock.DTO.UserDTO;

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
        log.info(mapper.readMemberByUserId("aa"));
    }

    @Test
    void readAllMember() {
        mapper.readAllMember().forEach(log::info);
    }

    @Test
    void deleteMember() {
        UserDTO member = mapper.readMemberByUserId("new2");
        log.info(member);

        if (bCryptEncoder.matches("1234", member.getPassword())) {
            log.info("Passwords match.");
            log.warn(mapper.deleteAuthOfSpecificMember(member.getUserId()));
            log.warn(mapper.deleteMember(member.getUserId()));
        }
    }

    @Test
    void insertMember() {
        UserDTO member = UserDTO.builder().userId("new2").password(encoder.encode("1234")).userName("New Member").build();
//        log.info(member);
        if (mapper.insertMember(member) == 1) {
            log.info("success");
            if (mapper.insertAuthorityToMember(new AuthorityDTO(member.getUserId(), "ROLE_USER")) == 1) {
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
        UserDTO member = UserDTO.builder().userId("admin").password(encoder.encode("admin")).userName("Administrator").build();
//        log.info(member);
        if (mapper.insertMember(member) == 1) {
            log.info("success");
            if (mapper.insertAuthorityToMember(new AuthorityDTO(member.getUserId(), "ROLE_ADMIN")) == 1) {
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
        AuthorityDTO auth = new AuthorityDTO("new", "ROLE_USER");
        if (mapper.insertAuthorityToMember(auth) == 1) {
            log.info("success");
        } else {
            log.error("failed");
        }
    }

    @Test
    void deleteAuthTests() {
        log.warn(mapper.deleteOneAuthorityToMember(new AuthorityDTO("aa", "ROLE_ADMIN")));
    }
}