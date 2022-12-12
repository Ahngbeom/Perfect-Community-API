package com.board.api.mapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.board.api.DTO.PostsDTO;
import com.board.api.DTO.UserDTO;

import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration({"file:web/WEB-INF/dispatcher-servlet.xml", "file:web/WEB-INF/securityContext.xml"})
class PostsCRUDMapperTest {

    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private PostsCRUD_Mapper postsCRUDMapper;

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
        postsCRUDMapper.selectBoardListWithPage(2).forEach(logger::info);
    }

    @Test
    void testSelectBoard() {
        PostsDTO board = postsCRUDMapper.selectBoardByBno(1);
        logger.info(board);
        logger.info(board.getRegDate());
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy년 MM월 dd일 hh:mm:ss");
        logger.info(formatDate.format(board.getRegDate()));
    }


    @Test
    void testInsertBoard() {
        PostsDTO board = new PostsDTO("아", "잠깐만", "기둘", null);
        postsCRUDMapper.insertBoard(board);
        logger.info(postsCRUDMapper.selectBoardByBno(board.getBno()));
    }

    @Test
    void testUpdateBoard() {
        PostsDTO board = postsCRUDMapper.selectBoardByBno(6);
        board.setTitle("다시다시");
        board.setContents("해볼게게");
        board.setWriter("잠깐만만");
        logger.info(postsCRUDMapper.updatePost(board));
        logger.info(postsCRUDMapper.selectBoardByBno(board.getBno()));
    }

    @Test
    void testDeleteBoard() {
        PostsDTO board = postsCRUDMapper.selectBoardByBno(6);

        logger.info(postsCRUDMapper.deleteBoard(board.getBno()) == 1 ? "DELETE SUCCESS" : "DELETE FAILURE");
    }

    @Test
    void initBno() {
        logger.info(postsCRUDMapper.initAutoIncrement());
    }

    @Test
    void testAuthenticateForPosts() {
        PostsDTO board = postsCRUDMapper.selectBoardByBno(1);
        UserDTO member = userMapper.readMemberByUserId("tester2");
        logger.info(postsCRUDMapper.authenticateForPosts(board, member));
    }

    @Test
    void testPostHasPassword() {
        logger.info(postsCRUDMapper.getPostPassword(3));
    }
}