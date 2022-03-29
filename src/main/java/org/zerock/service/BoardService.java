package org.zerock.service;

import org.zerock.domain.BoardVO;

import java.util.List;

public interface BoardService {

    long    countBoard();

    List<BoardVO> getBoardList();

    BoardVO getBoardByBno(long bno);

    List<BoardVO> searchBoardByKeyword(String keyword);

    int registerBoard(BoardVO board);

    int modifyBoard(BoardVO board);

    int removeBoard(long bno);

    int removeAllBoard();

    long initBnoValue();
}
