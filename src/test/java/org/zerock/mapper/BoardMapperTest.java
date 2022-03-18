package org.zerock.mapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.zerock.domain.BoardVO;

import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:web/WEB-INF/dispatcher-servlet.xml")
class BoardMapperTest {

    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private BoardMapper mapper;

    @BeforeEach
    void setUp() {
        assertNotNull(mapper);
    }

    @Test
    void testSelectBoardList() {
        mapper.selectBoardList().forEach(board -> logger.info(board));
    }

    @Test
    void testSelectBoard() {
        BoardVO board = mapper.selectBoard(1);
        logger.info(board);
        logger.info(board.getRegDate());
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy년 MM월 dd일 hh:mm:ss");
        logger.info(formatDate.format(board.getRegDate()));
    }

    @Test
    void testSearchBoard() {
        BoardVO board = mapper.selectBoardByKeyword("Tester");
        logger.info(board);
        logger.info(board.getRegDate());
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy년 MM월 dd일 hh:mm:ss");
        logger.info(formatDate.format(board.getRegDate()));
    }

    @Test
    void testInsertBoard() {
        BoardVO board = new BoardVO("아", "잠깐만", "기둘");
        mapper.insertBoard(board);
        logger.info(mapper.selectBoard(board.getBno()));
    }

    @Test
    void testUpdateBoard() {
        BoardVO board = mapper.selectBoard(6);
        board.setTitle("다시다시");
        board.setContent("해볼게게");
        board.setWriter("잠깐만만");
        logger.info(mapper.updateBoard(board));
        logger.info(mapper.selectBoard(board.getBno()));
    }

    @Test
    void testDeleteBoard() {
        BoardVO board = mapper.selectBoard(6);

        logger.info(mapper.deleteBoard(board.getBno()) == 1 ? "DELETE SUCCESS" : "DELETE FAILURE");
    }
}