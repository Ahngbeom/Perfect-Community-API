package org.zerock.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.zerock.domain.BoardVO;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:web/WEB-INF/dispatcher-servlet.xml")
class BoardServiceTest {

    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private BoardService service;


    @BeforeEach
    void setUp() {
        assertNotNull(logger);
        assertNotNull(service);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testCountBoard() {
        logger.info(service.countBoard());
    }

    @Test
    void getBoardList() {
        service.getBoardList().forEach(board -> logger.info(board));
    }

    @Test
    void getBoard() {
        logger.info(service.getBoardByBno(5));
    }

    @Test
    void searchBoardByKeyword() {
        service.searchBoardByKeyword("잠깐만").forEach(board -> logger.info(board));
    }

    @Test
    void registerBoard() {
        BoardVO board = new BoardVO("짜잔", "내가", "돌아왔다");
        service.registerBoard(board);
        logger.info(service.getBoardByBno(board.getBno()));
    }

    @Test
    void modifyBoard() {
        BoardVO board = service.getBoardByBno(7);
        board.setContent("내가 돌아왔다");
        board.setWriter("잭스");
        service.modifyBoard(board);
        logger.info(service.getBoardByBno(board.getBno()));
    }

    @Test
    void removeBoard() {
        logger.info(service.removeBoard(5));
        service.getBoardList().forEach(board -> logger.info(board));
    }
}