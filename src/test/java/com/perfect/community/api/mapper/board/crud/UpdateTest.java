package com.perfect.community.api.mapper.board.crud;

import com.perfect.community.api.entity.board.BoardEntity;
import com.perfect.community.api.mapper.board.BoardMapperTest;
import org.junit.jupiter.api.Test;

class UpdateTest extends BoardMapperTest {

    @Test
    void updateBoard() {
        try {
           int result = boardMapper.updateBoard(
                    BoardEntity.builder()
                            .createUser("admin")
                            .bno(1)
                            .title("Update")
                            .comment("Update test of board service")
                            .build()
            );
            log.info(result);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Test
    void updateBoardWithInvalidBoardNo() {
        try {
            int result = boardMapper.updateBoard(
                    BoardEntity.builder()
                            .createUser("admin")
                            .bno(-1)
                            .title("Update")
                            .comment("Update test of board service")
                            .build()
            );
            log.info(result);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Test
    void updateBoardWithNullTitle() {
        try {
            int result = boardMapper.updateBoard(
                    BoardEntity.builder()
                            .createUser("admin")
                            .bno(1)
                            .title(null)
                            .comment("Update test of board service")
                            .build()
            );
            log.info(result);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

}