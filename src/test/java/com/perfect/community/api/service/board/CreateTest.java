package com.perfect.community.api.service.board;

import com.perfect.community.api.dto.board.BoardDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("[Service] Create board")
class CreateTest extends BoardServiceTest {

    @Test
    @DisplayName("Create board with null create user")
    void createBoardWithNullCreateUser() {
        try {
            long createdBoardNo = boardService.createBoard(
                    null,
                    BoardDTO.builder()
                            .title(utils.generateRandomTitle())
                            .comment("Creation test of board service...")
                            .build()
            );
            log.info(createdBoardNo);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Test
    @DisplayName("Create board with null title")
    void createBoardWithNullTitle() {
        try {
            long createdBoardNo = boardService.createBoard(
                    "admin",
                    BoardDTO.builder()
                            .title(null)
                            .comment("Creation test of board service")
                            .build()
            );
            log.info(createdBoardNo);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Test
    @DisplayName("Create board")
    void createBoard() {
        try {
            long createdBoardNo = boardService.createBoard(
                    "admin",
                    BoardDTO.builder()
                            .title(utils.generateRandomTitle())
                            .comment("Creation test of board service")
                            .build()
            );
            log.info(createdBoardNo);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }

}