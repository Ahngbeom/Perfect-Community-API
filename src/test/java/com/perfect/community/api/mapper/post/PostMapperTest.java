package com.perfect.community.api.mapper.post;

import com.perfect.community.api.vo.post.PostVO;
import com.perfect.community.api.mapper.utils.UtilsMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration({
        "file:web/WEB-INF/dispatcher-servlet.xml",
        "file:web/WEB-INF/securityContext.xml",
        "file:web/WEB-INF/interceptor-servlet.xml"
})
@Transactional
class PostMapperTest {

    protected static final Logger log = LogManager.getLogger(PostMapperTest.class);

    @Autowired
    protected PostMapper postsMapper;

    @Autowired
    protected PostInteractionMapper postInteractionMapper;

    @Autowired
    private UtilsMapper utilsMapper;

    @BeforeEach
    void setUp() {
        assertNotNull(log);
        assertNotNull(postsMapper);
    }

    @AfterTransaction
    void initializeAutoIncrement() {
        log.info("auto_increment key: " + utilsMapper.initializeAutoIncrement("posts"));
    }

    public PostVO selectPostInfoByPno(long pno) {
        return postsMapper.selectPostInfoByPno(pno);
    }

}