package com.board.api.service.board;

import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
class ReadTest extends BoardServiceTest {

    @Test
    void getBoardList() {
        log.info(boardService.getBoardList());
    }

    @Test
    void getBoardInfo() {
        log.info(boardService.getBoardInfo(6));
    }

    @Test
    void getBoardInfoWithInvalidBoardNo() {
        try {
            log.info(boardService.getBoardInfo(-1));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}