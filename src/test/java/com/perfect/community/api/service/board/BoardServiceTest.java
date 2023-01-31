package com.perfect.community.api.service.board;

import dummy.UtilsForTest;
import com.perfect.community.api.dto.board.BoardDTO;
import com.perfect.community.api.service.utils.RelocateService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration({"file:web/WEB-INF/dispatcher-servlet.xml", "file:web/WEB-INF/securityContext.xml"})
@Transactional
class BoardServiceTest {

    protected final static Logger log = LogManager.getLogger(BoardService.class);

    @Autowired
    protected BoardService boardService;

    @Autowired
    protected RelocateService relocateService;

    protected final UtilsForTest utils = new UtilsForTest();

    @BeforeEach
    void setUp() {
        assertNotNull(boardService);
        assertNotNull(relocateService);
        assertNotNull(utils);
    }

    @AfterTransaction
    void relocateBoardNumbers() {
        log.info("auto_increment key value: " + relocateService.relocateBoardNumbers("boards"));
    }

    public BoardDTO getBoardInfo(long bno) throws Exception {
        return boardService.getBoardInfo(bno);
    }

}