package com.board.api.service.board;

import com.board.api.dto.board.BoardDTO;
import com.google.common.base.Preconditions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration({"file:web/WEB-INF/dispatcher-servlet.xml", "file:web/WEB-INF/securityContext.xml"})
class BoardServiceTest {

    private final static Logger log = LogManager.getLogger(BoardService.class);

    @Autowired
    private BoardService boardService;

    @BeforeEach
    void setUp() {
        assertNotNull(boardService);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getBoardList() {
        boardService.getBoardList().forEach(log::info);
    }

    @Test
    void getBoardInfo() {
    }

    @Test
    void createBoard() {
        BoardDTO boardDTO = BoardDTO.builder()
//                .title("CREATE BOARD TEST FROM JUNIT")
                .title(null)
                .comment("FOR TESTING")
                .build();
        Preconditions.checkNotNull(boardDTO.getTitle(), "Board title must not be null");
        log.info(boardDTO);
    }

    @Test
    void updateBoard() {
    }

    @Test
    void deleteBoard() {
    }
}