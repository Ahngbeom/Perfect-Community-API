package com.perfect.community.api.service.board;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@DisplayName("[Service] Read the board")
class ReadTest extends BoardServiceTest {

    @Test
    @DisplayName("[Service] Get the board's list")
    void getBoardList() {
        log.info(boardService.getBoardList());
    }

    @Test
    @DisplayName("[Service] Read the board's details")
    void getInfo() throws Exception {
        log.info(getBoardInfo(1));
    }

    @Test
    @DisplayName("[Service] Read the board's details with invalid board no")
    void getBoardInfoWithInvalidBoardNo() {
        try {
            log.info(getBoardInfo(-1));
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        try {
            log.info(getBoardInfo(99));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}