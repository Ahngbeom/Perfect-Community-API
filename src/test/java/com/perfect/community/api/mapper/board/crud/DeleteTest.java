package com.perfect.community.api.mapper.board.crud;

import com.perfect.community.api.mapper.board.BoardMapperTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("BoardMapper Test for Delete")
class DeleteTest extends BoardMapperTest {

    @Test
    @DisplayName("Delete with valid board no.")
    void deleteBoard() {
        try {
            log.info(boardMapper.deleteBoard(1));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Test
    @DisplayName("Delete with invalid board no.")
    void deleteBoardInvalidBoardNo() {
        try {
            log.info(boardMapper.deleteBoard(-1));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

}