package org.zerock.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.zerock.domain.BoardSearchVO;
import org.zerock.domain.BoardVO;
import org.zerock.mapper.BoardMapper;
import org.zerock.utils.DateUtility;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardMapper mapper;

    private final DateUtility dateUtility;

    private final PasswordEncoder passwordEncoder;


    @Override
    public long countBoard() {
        return mapper.countBoard();
    }

    @Override
    public List<BoardVO> getBoardList() {
        return mapper.selectBoardList();
    }

    @Override
    public List<BoardVO> getBoardListWithPage(int page) {
        List<BoardVO> boardList = mapper.selectBoardListWithPage(page);
//        boardList.forEach(board -> board.dateToTodayCalculator());
        boardList.forEach(board -> board.setDateToToday(dateUtility.dateToTodayCalculator(board.getRegDate(), board.getUpdateDate())));
        return boardList;
    }

    @Override
    public BoardVO getBoardByBno(long bno) {
        return mapper.selectBoardByBno(bno);
    }

    @Override
    public List<BoardVO> searchBoardByKeyword(BoardSearchVO searchVO) {
        List<BoardVO> searchResult = mapper.selectBoardByKeyword(searchVO);
        searchResult.forEach(board -> {
            board.setDateToToday(dateUtility.dateToTodayCalculator(board.getRegDate(), board.getUpdateDate()));
        });
        return searchResult;
    }

    @Override
    public int registerBoard(BoardVO board) {
        if (board.getBoardPassword() != null)
            board.setBoardPassword(passwordEncoder.encode(board.getBoardPassword()));
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
