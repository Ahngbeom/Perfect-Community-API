package com.perfect.community.api.mapper.board;

import com.perfect.community.api.dto.board.BoardDTO;
import com.perfect.community.api.mapper.MapperTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("[Mapper] Create board")
class CreateTest extends MapperTest {

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