package org.zerock.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.zerock.domain.BoardVO;
import org.zerock.mapper.BoardMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardMapper mapper;

    @Override
    public long countBoard() {
        return mapper.countBoard();
    }

    @Override
    public List<BoardVO> getBoardList() {
        return mapper.selectBoardList();
    }

    @Override
    public BoardVO getBoardByBno(long bno) {
        return mapper.selectBoardByBno(bno);
    }

    @Override
    public List<BoardVO> searchBoardByKeyword(String keyword) {
        return mapper.selectBoardByKeyword(keyword);
    }

    @Override
    public int registerBoard(BoardVO board) {
        return mapper.insertBoard(board);
    }

    @Override
    public int modifyBoard(BoardVO board) {
        return mapper.updateBoard(board);
    }

    @Override
    public int removeBoard(long bno) {
        return mapper.deleteBoard(bno);
    }

    @Override
    public int removeAllBoard() {
        return mapper.deleteAllBoard();
    }

    @Override
    public long initBnoValue() {
        return mapper.initAutoIncrement();
    }
}
