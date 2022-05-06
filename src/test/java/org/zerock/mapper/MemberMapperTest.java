package org.zerock.mapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.zerock.domain.AuthVO;
import org.zerock.domain.MemberVO;
import org.zerock.security.CustomPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration({"file:web/WEB-INF/dispatcher-servlet.xml", "file:web/WEB-INF/securityContext.xml"})
class MemberMapperTest {

    private static final Logger log = LogManager.getLogger();

    @Autowired
    private MemberMapper mapper;

    @Autowired
    private BCryptPasswordEncoder bCryptEncoder;

    @BeforeEach
    void setUp() {
        assertNotNull(mapper);
    }

    @Test
    void readMember() {
        log.info(mapper.readMember("admin"));
    }

    @Test
    void readAllMember() {
        mapper.readAllMember().forEach(log::info);
    }

    @Test
    void deleteMember() {
        MemberVO member = mapper.readMember("user8");
        log.info(member);

        log.warn(bCryptEncoder.matches("user8", member.getPassword()));

        log.warn(mapper.deleteAuthOfSpecificMember(member.getUserId()));
        log.warn(mapper.deleteMember(member.getUserId()));
    }

    @Test
    void insertMember() {
        MemberVO member = new MemberVO("new1", "1234", "new_1");
        List<AuthVO> authList = new ArrayList<>();
        authList.add(new AuthVO("new1", "ROLE_USER"));
        member.setAuthList(authList);
        log.warn(mapper.insertMember(member));
    }
}