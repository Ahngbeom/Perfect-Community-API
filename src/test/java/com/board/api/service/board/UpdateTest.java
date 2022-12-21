package com.board.api.service.board;

import com.board.api.dto.board.BoardDTO;
import org.junit.jupiter.api.Test;

class UpdateTest extends BoardServiceTest {

    @Test
    void updateBoard() {
        try {
           boardService.updateBoard(
                    "admin",
                    6,
                    BoardDTO.builder()
                            .title("Update")
                            .comment("Update test of board service")
                            .build()
            );
            log.info("Success");
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Test
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
            log.info("Success");
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Test
    void updateBoardWithNullTitle() {
        try {
            boardService.updateBoard(
                    "admin",
                    6,
                    BoardDTO.builder()
                            .title(null)
                            .comment("Update test of board service")
                            .build()
            );
            log.info("Success");
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Test
    void updateBoardWithUnauthorizedUser() {
        try {
            boardService.updateBoard(
                    "tester1",
                    6,
                    BoardDTO.builder()
                            .title("Update")
                            .comment("Update test of board service")
                            .build()
            );
            log.info("Success");
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

}