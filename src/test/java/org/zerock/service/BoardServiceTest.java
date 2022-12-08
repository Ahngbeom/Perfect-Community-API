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
import org.zerock.DTO.PostsSearchDTO;
import org.zerock.DTO.PostsDTO;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration({"file:web/WEB-INF/dispatcher-servlet.xml", "file:web/WEB-INF/securityContext.xml"})
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
        logger.info(service.countPosts());
    }

    @Test
    void getBoardList() {
        service.getBoardList().forEach(logger::info);
    }

    @Test
    void getBoardListWithPage() {
        service.getBoardListWithPage(2).forEach(logger::info);
    }

    @Test
    void getBoard() {
        logger.info(service.getBoardByBno(5));
    }

    @Test
    void searchBoardByKeyword() {
        PostsSearchDTO searchVO = new PostsSearchDTO("가나다");
        searchVO.setCheckTitle(true);
        searchVO.setCheckContent(false);
        searchVO.setCheckWriter(false);
        service.searchBoardByKeyword(searchVO).forEach(logger::info);
    }

    @Test
    void registerBoard() {
        PostsDTO board = new PostsDTO("짜잔", "내가", "돌아왔다", null);
        service.registerBoard(board);
        logger.info(service.getBoardByBno(board.getBno()));
    }

    @Test
    void modifyBoard() {
        PostsDTO board = service.getBoardByBno(7);
        board.setContents("내가 돌아왔다");
        board.setWriter("잭스");
        service.modifyPost(board);
        logger.info(service.getBoardByBno(board.getBno()));
    }

    @Test
    void removeBoard() {
//        logger.info(service.removeBoard(5));
        service.getBoardList().forEach(logger::info);
    }
}