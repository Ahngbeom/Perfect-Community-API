package com.board.api.mapper;

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

    @Autowired
    private UserMapper userMapper;

    @BeforeEach
    void setUp() {
        assertNotNull(postsCRUDMapper);
    }

    @Test
    void testSelectBoardList() {
        postsCRUDMapper.selectBoardList().forEach(logger::info);
    }

    @Test
    void testSelectBoardListWithPage() {
//        postsCRUDMapper.selectBoardListWithPage(new PostListOptDTO(1, "normal")).forEach(logger::info);
    }

    @Test
    void testSelectBoard() {
        PostDTO board = postsCRUDMapper.selectBoardByPno(1);
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
        PostDTO board = postsCRUDMapper.selectBoardByPno(6);
        board.setTitle("다시다시");
        board.setContents("해볼게게");
        board.setWriter("잠깐만만");
        logger.info(postsCRUDMapper.updatePost(board));
        logger.info(postsCRUDMapper.selectBoardByPno(board.getPno()));
    }

    @Test
    void testDeleteBoard() {
        PostDTO board = postsCRUDMapper.selectBoardByPno(6);

        logger.info(postsCRUDMapper.deleteBoard(board.getPno()) == 1 ? "DELETE SUCCESS" : "DELETE FAILURE");
    }

}