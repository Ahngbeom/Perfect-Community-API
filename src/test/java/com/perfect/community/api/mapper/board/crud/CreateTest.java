package com.perfect.community.api.mapper.board.crud;

import com.perfect.community.api.dto.board.BoardDTO;
import com.perfect.community.api.vo.board.BoardVO;
import com.perfect.community.api.mapper.board.BoardMapperTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("BoardMapper Test for Create")
class CreateTest extends BoardMapperTest {

    @Test
    @DisplayName("Create with null title")
    void createBoardWithNullTitle() {
        try {
            int result = boardMapper.createBoard(
                    BoardDTO.builder()
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
    @DisplayName("Create with not null title")
    void createBoard() {
        try {
            int result = boardMapper.createBoard(
                    BoardDTO.builder()
                            .createUser("admin")
                            .title(generateRandomTitle())
                            .comment("Create test from BoardMapperTest")
                            .build()
            );
            log.info(result);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

}