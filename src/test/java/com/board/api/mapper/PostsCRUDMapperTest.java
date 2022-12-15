package com.board.api.mapper;

import com.board.api.dto.post.PostListOptDTO;
import com.board.api.mapper.post.PostCRUD_Mapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.board.api.dto.post.PostDTO;

import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration({"file:web/WEB-INF/dispatcher-servlet.xml", "file:web/WEB-INF/securityContext.xml"})
class PostsCRUDMapperTest {

    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private PostCRUD_Mapper postsCRUDMapper;

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
        PostDTO board = postsCRUDMapper.selectPostInfoByPno(1);
        logger.info(board);
        logger.info(board.getRegDate());
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy년 MM월 dd일 hh:mm:ss");
        logger.info(formatDate.format(board.getRegDate()));
    }


    @Test
    void testInsertBoard() {
//        PostDTO board = new PostDTO("normal", "아", "잠깐만");
//        postsCRUDMapper.insertPost(board);
//        logger.info(postsCRUDMapper.selectBoardByPno(board.getPno()));
    }

    @Test
    void testUpdateBoard() {

    }

    @Test
    void testDeleteBoard() {
        PostDTO board = postsCRUDMapper.selectPostInfoByPno(6);

        logger.info(postsCRUDMapper.deletePost(board.getPno()) == 1 ? "DELETE SUCCESS" : "DELETE FAILURE");
    }

}