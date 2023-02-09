package com.perfect.community.api.mapper.post;

import com.perfect.community.api.dto.post.PostDTO;
import com.perfect.community.api.mapper.MapperTest;
import com.perfect.community.api.mapper.utils.UtilsMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.transaction.AfterTransaction;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class PostMapperTest extends MapperTest {

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

    public PostDTO selectPostInfoByPno(long pno) {
        return postsMapper.selectPostInfoByPno(pno);
    }

}