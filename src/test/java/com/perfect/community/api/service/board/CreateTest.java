package com.perfect.community.api.service.board;

import com.perfect.community.api.dto.board.BoardDTO;
import org.junit.jupiter.api.Test;

class CreateTest extends BoardServiceTest {

    @Test
    void createBoardWithNullTitle() {
        try {
            BoardDTO boardDTO = boardService.createBoard(
                    "admin",
                    BoardDTO.builder()
                            .title(null)
                            .comment("Creation test of board service")
                            .build()
            );
            log.info(boardDTO);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Test
    void createBoard() {
        try {
            BoardDTO boardDTO = boardService.createBoard(
                    "admin",
                    BoardDTO.builder()
                            .title(randomTitle)
                            .comment("Creation test of board service")
                            .build()
            );
            log.info(boardDTO);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

}