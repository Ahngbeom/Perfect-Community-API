package com.perfect.community.api.service.board;

import com.perfect.community.api.dto.board.BoardDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("BoardService Test For Update")
class UpdateTest extends BoardServiceTest {

    @Test
    @DisplayName("Update board")
    void updateBoard() {
        try {
           boardService.updateBoard(
                    "admin",
                    1,
                    BoardDTO.builder()
                            .title("Update")
                            .comment("Update test of board service")
                            .build()
            );
            log.info(getBoardInfo(1));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Test
    @DisplayName("Update board with invalid board no")
    void updateBoardWithInvalidBoardNo() {
        try {
            boardService.updateBoard(
                    "admin",
                    -1,
                    BoardDTO.builder()
                            .title("Update")
                            .comment("Update test of board service")
                            .build()
            );
            log.info(getBoardInfo(-1));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Test
    @DisplayName("Update board with null title")
    void updateBoardWithNullTitle() {
        try {
            boardService.updateBoard(
                    "admin",
                    1,
                    BoardDTO.builder()
                            .title(null)
                            .comment("Update test of board service")
                            .build()
            );
            log.info(getBoardInfo(1));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Test
    @DisplayName("Update board by other user")
    void updateBoardWithUnauthorizedUser() {
        try {
            boardService.updateBoard(
                    "tester1",
                    1,
                    BoardDTO.builder()
                            .title("Update")
                            .comment("Update test of board service")
                            .build()
            );
            log.info(getBoardInfo(1));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

}