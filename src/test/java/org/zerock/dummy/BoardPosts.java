package org.zerock.dummy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.zerock.DTO.PostsDTO;
import org.zerock.service.BoardService;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration({"file:web/WEB-INF/dispatcher-servlet.xml", "file:web/WEB-INF/securityContext.xml"})
class BoardPosts {

    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private BoardService service;


    @BeforeEach
    void setUp() {
        assertNotNull(logger);
        assertNotNull(service);
    }

    @Test
    void registerPostsDummy() {
        for (int i = 1; i <= 100; i++) {
            PostsDTO board = new PostsDTO("TEST " + i, "TEST " + i, "TESTER");
            service.registerBoard(board);
//            logger.info(service.getBoardByBno(board.getBno()));
            logger.info(board);
        }
    }

    @Test
    void removeAllPosts() {
        logger.info(service.removeAllBoard());
    }
}