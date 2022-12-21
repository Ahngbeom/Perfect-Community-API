package com.board.api.mapper;

import com.board.api.dto.post.PostListOptDTO;
import com.board.api.mapper.post.PostMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration({"file:web/WEB-INF/dispatcher-servlet.xml", "file:web/WEB-INF/securityContext.xml"})
class PostsCRUDMapperTest {

    private static final Logger log = LogManager.getLogger();

    @Autowired
    private PostMapper postsCRUDMapper;

    @BeforeEach
    void setUp() {
        assertNotNull(postsCRUDMapper);
    }

    @Test
    void testSelectBoardListWithPage() {
        postsCRUDMapper.selectPostList(new PostListOptDTO());
    }

    @Test
    void testSelectBoard() {

    }


    @Test
    void testInsertBoard() {

    }

    @Test
    void testUpdateBoard() {

    }

    @Test
    void testDeleteBoard() {
    }

}