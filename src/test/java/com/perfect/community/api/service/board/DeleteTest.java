package com.perfect.community.api.service.board;

import org.junit.jupiter.api.Test;

class DeleteTest extends BoardServiceTest {

    @Test
    void deleteBoard() {
        try {
            boardService.deleteBoard("admin", 6);
            log.info("Success");
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Test
    void deleteBoardInvalidBoardNo() {
        try {
            boardService.deleteBoard("admin", -1);
            log.info("Success");
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Test
    void deleteBoardWithUnauthorizedUser() {
        try {
            boardService.deleteBoard("tester1", 6);
            log.info("Success");
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

}