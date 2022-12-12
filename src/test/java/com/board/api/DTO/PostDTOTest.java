package com.board.api.DTO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.board.api.service.board.PostCRUD_Service;
import com.board.api.utils.DateUtility;

import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:web/WEB-INF/dispatcher-servlet.xml")
class PostDTOTest {

    private static final Logger log = LogManager.getLogger();

    @Autowired
    private PostCRUD_Service service;

    @Autowired
    private DateUtility dateUtility;

    @Test
    void dateToTodayCalculator() {
        List<PostDTO> boardList = service.getBoardList();
        boardList.forEach(board -> {
            log.info(board.getRegDate() + ", " + board.getUpdateDate());
            log.info(dateUtility.dateToTodayCalculator(board.getRegDate(), board.getUpdateDate()));
        });
    }
}