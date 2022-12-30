package com.perfect.community.api.mapper.board.crud;

import com.perfect.community.api.mapper.board.BoardMapperTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@DisplayName("BoardMapper Test for Read")
class ReadTest extends BoardMapperTest {

    @Test
    @DisplayName("Get board list.")
    void getBoardList() {
        log.info(boardMapper.getBoardList());
    }

    @Test
    @DisplayName("Get board info with valid board no.")
    void getBoardInfo() {
        log.info(boardMapper.getBoardInfo(1));
    }

    @Test
    @DisplayName("Get board info with invalid board no.")
    void getBoardInfoWithInvalidBoardNo() {
        try {
            log.info(boardMapper.getBoardInfo(-1));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}