package com.board.api.mapper.board;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration({"file:web/WEB-INF/dispatcher-servlet.xml", "file:web/WEB-INF/securityContext.xml"})
class BoardMapperTest {
    private static final Logger log = LogManager.getLogger();

    @Autowired
    private BoardMapper boardMapper;

    @BeforeEach
    void setUp() {
    }

    @Test
    void getBoardList() {
        boardMapper.getBoardList().forEach(log::info);
    }

    @Test
    void getBoardInfo() {
    }

    @Test
    void createBoard() {
    }

    @Test
    void updateBoard() {
    }

    @Test
    void deleteBoard() {
    }
}