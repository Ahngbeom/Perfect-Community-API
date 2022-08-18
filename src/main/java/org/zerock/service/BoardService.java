package org.zerock.service;

import org.zerock.domain.BoardSearchVO;
import org.zerock.domain.BoardVO;
import org.zerock.domain.MemberVO;

import java.util.List;

public interface BoardService {

    long    countBoard();

    List<BoardVO> getBoardList();

    List<BoardVO> getBoardListWithPage(int page);

    BoardVO getBoardByBno(long bno);

    List<BoardVO> searchBoardByKeyword(BoardSearchVO searchVO);

    int registerBoard(BoardVO board);

    int modifyBoard(BoardVO board);

    int removeBoard(long bno);

    int removePostWithPassword(BoardVO board);

    int removeAllBoard();

    long initBnoValue();

    BoardVO authenticateForPosts(BoardVO board, MemberVO member);

    boolean postHasPassword(long bno);
}
