package com.board.api.mapper.board.crud;

import com.board.api.mapper.board.BoardMapperTest;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
class ReadTest extends BoardMapperTest {

    @Test
    void getBoardList() {
        log.info(boardMapper.getBoardList());
    }

    @Test
    void getBoardInfo() {
        log.info(boardMapper.getBoardInfo(1));
    }

    @Test
    void getBoardInfoWithInvalidBoardNo() {
        try {
            log.info(boardMapper.getBoardInfo(-1));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}