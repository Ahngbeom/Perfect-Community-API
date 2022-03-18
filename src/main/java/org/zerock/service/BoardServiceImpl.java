package org.zerock.service;

import lombok.NonNull;
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
    public List<BoardVO> getBoardList() {
        return mapper.selectBoardList();
    }

    @Override
    public BoardVO getBoard(long bno) {
        return mapper.selectBoard(bno);
    }

    @Override
    public BoardVO searchBoardByKeyword(String keyword) {
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
}
