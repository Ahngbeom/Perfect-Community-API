package org.zerock.domain;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.zerock.service.BoardService;
import org.zerock.utils.DateUtility;

import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:web/WEB-INF/dispatcher-servlet.xml")
class BoardVOTest {

    private static final Logger log = LogManager.getLogger();

    @Autowired
    private BoardService service;

    @Autowired
    private DateUtility dateUtility;

    @Test
    void dateToTodayCalculator() {
        List<BoardVO> boardList = service.getBoardList();
        boardList.forEach(board -> {
            log.info(board.getRegDate() + ", " + board.getUpdateDate());
            log.info(dateUtility.dateToTodayCalculator(board.getRegDate(), board.getUpdateDate()));
        });
    }
}