package com.perfect.community.api.mapper;

import com.perfect.community.api.mapper.board.BoardMapper;
import com.perfect.community.api.mapper.post.PostScrapMapper;
import com.perfect.community.api.mapper.user.UsersMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration({
        "file:web/WEB-INF/applicationContext.xml", "file:web/WEB-INF/dispatcher-context.xml", "file:web/WEB-INF/security-context.xml"
})
@Transactional
public class MapperTest {

    protected static final Logger log = LogManager.getLogger();

    @Autowired
    protected BoardMapper boardMapper;

    @Autowired
    protected UsersMapper usersMapper;

    @Autowired
    protected PostScrapMapper postScrapMapper;

    @Autowired
    protected PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        assertNotNull(log);
        assertNotNull(boardMapper);
        assertNotNull(usersMapper);
        assertNotNull(postScrapMapper);
        assertNotNull(passwordEncoder);
    }

    public String generateRandomTitle() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}