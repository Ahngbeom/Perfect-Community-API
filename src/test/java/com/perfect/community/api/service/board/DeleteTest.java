package com.perfect.community.api.service.board;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("[Service] Delete board")
class DeleteTest extends BoardServiceTest {

    @Test
    void deleteBoard() {
        try {
            boardService.deleteBoard("admin", 1);
            log.info(getBoardInfo(1));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Test
    void deleteBoardInvalidBoardNo() {
        try {
            boardService.deleteBoard("admin", -1);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Test
    void deleteBoardWithUnauthorizedUser() {
        try {
            boardService.deleteBoard("tester1", 1);
            log.info(getBoardInfo(1));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

}