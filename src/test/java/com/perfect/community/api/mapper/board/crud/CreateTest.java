package com.perfect.community.api.mapper.board.crud;

import com.perfect.community.api.entity.board.BoardEntity;
import com.perfect.community.api.mapper.board.BoardMapperTest;
import org.junit.jupiter.api.Test;

class CreateTest extends BoardMapperTest {

    @Test
    void createBoardWithNullTitle() {
        try {
            int result = boardMapper.createBoard(
                    BoardEntity.builder()
                            .createUser("admin")
                            .title(null)
                            .comment("Create test from BoardMapperTest")
                            .build()
            );
            log.info(result);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Test
    void createBoard() {
        try {
            int result = boardMapper.createBoard(
                    BoardEntity.builder()
                            .createUser("admin")
                            .title(randomTitle)
                            .comment("Create test from BoardMapperTest")
                            .build()
            );
            log.info(result);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

}