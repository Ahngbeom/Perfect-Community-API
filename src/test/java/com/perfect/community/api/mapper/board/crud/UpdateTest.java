package com.perfect.community.api.mapper.board.crud;

import com.perfect.community.api.vo.board.BoardEntity;
import com.perfect.community.api.mapper.board.BoardMapperTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("BoardMapper Test for Update")
class UpdateTest extends BoardMapperTest {

    @Test
    @DisplayName("Correct update board")
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
    @DisplayName("Update board with invalid board no.")
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
    @DisplayName("Update board with null title.")
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