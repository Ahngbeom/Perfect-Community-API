package com.perfect.community.api.service.board;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@DisplayName("BoardService Test For Read")
class ReadTest extends BoardServiceTest {

    @Test
    void getBoardList() {
        log.info(boardService.getBoardList());
    }

    @Test
    void getInfo() throws Exception {
        log.info(getBoardInfo(1));
    }

    @Test
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