package com.perfect.community.api.service;

import com.perfect.community.api.service.user.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration({
        "file:web/WEB-INF/applicationContext.xml", "file:web/WEB-INF/dispatcher-context.xml", "file:web/WEB-INF/security-context.xml"
})
@Transactional
public class ServiceTest {

    protected static final Logger log = LogManager.getLogger(ServiceTest.class);

    @Autowired
    protected UserService service;

    @Autowired
    protected PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        assertNotNull(log);
        assertNotNull(service);
        assertNotNull(passwordEncoder);
    }
}