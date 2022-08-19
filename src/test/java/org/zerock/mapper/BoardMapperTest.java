package org.zerock.mapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.zerock.domain.BoardSearchVO;
import org.zerock.domain.BoardVO;
import org.zerock.domain.MemberVO;

import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration({"file:web/WEB-INF/dispatcher-servlet.xml", "file:web/WEB-INF/securityContext.xml"})
class BoardMapperTest {

    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private BoardMapper boardMapper;

    @Autowired
    private MemberMapper memberMapper;

    @BeforeEach
    void setUp() {
        assertNotNull(boardMapper);
    }

    @Test
    void testSelectBoardList() {
        boardMapper.selectBoardList().forEach(logger::info);
    }

    @Test
    void testSelectBoardListWithPage() {
        boardMapper.selectBoardListWithPage(2).forEach(logger::info);
    }

    @Test
    void testSelectBoard() {
        BoardVO board = boardMapper.selectBoardByBno(1);
        logger.info(board);
        logger.info(board.getRegDate());
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy년 MM월 dd일 hh:mm:ss");
        logger.info(formatDate.format(board.getRegDate()));
    }

    @Test
    void testSearchBoard() {
        BoardSearchVO searchVO = new BoardSearchVO("가나다");
        searchVO.setCheckTitle(true);
        searchVO.setCheckContent(true);
        searchVO.setCheckWriter(true);
        logger.info(boardMapper.selectBoardByKeyword(searchVO));
    }

    @Test
    void testInsertBoard() {
        BoardVO board = new BoardVO("아", "잠깐만", "기둘", null);
        boardMapper.insertBoard(board);
        logger.info(boardMapper.selectBoardByBno(board.getBno()));
    }

    @Test
    void testUpdateBoard() {
        BoardVO board = boardMapper.selectBoardByBno(6);
        board.setTitle("다시다시");
        board.setContent("해볼게게");
        board.setWriter("잠깐만만");
        logger.info(boardMapper.updateBoard(board));
        logger.info(boardMapper.selectBoardByBno(board.getBno()));
    }

    @Test
    void testDeleteBoard() {
        BoardVO board = boardMapper.selectBoardByBno(6);

        logger.info(boardMapper.deleteBoard(board.getBno()) == 1 ? "DELETE SUCCESS" : "DELETE FAILURE");
    }

    @Test
    void initBno() {
        logger.info(boardMapper.initAutoIncrement());
    }

    @Test
    void testAuthenticateForPosts() {
        BoardVO board = boardMapper.selectBoardByBno(1);
        MemberVO member = memberMapper.readMember("tester2");
        logger.info(boardMapper.authenticateForPosts(board, member));
    }

    @Test
    void testPostHasPassword() {
        logger.info(boardMapper.getPostPassword(3));
    }
}