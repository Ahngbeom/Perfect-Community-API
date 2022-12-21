package com.board.api.mapper.board;

import org.junit.jupiter.api.Test;

class DeleteTest extends BoardMapperTest {

    @Test
    void deleteBoard() {
        try {
            log.info(boardMapper.deleteBoard(1));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Test
    void deleteBoardInvalidBoardNo() {
        try {
            log.info(boardMapper.deleteBoard(-1));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

}