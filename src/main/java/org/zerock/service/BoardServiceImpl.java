package org.zerock.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.zerock.domain.BoardSearchVO;
import org.zerock.domain.BoardVO;
import org.zerock.domain.MemberVO;
import org.zerock.mapper.BoardMapper;
import org.zerock.utils.DateUtility;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardMapper boardMapper;

    private final DateUtility dateUtility;

    private final PasswordEncoder passwordEncoder;

    @Override
    public long countBoard() {
        return boardMapper.countBoard();
    }

    @Override
    public List<BoardVO> getBoardList() {
        return boardMapper.selectBoardList();
    }

    @Override
    public List<BoardVO> getBoardListWithPage(int page) {
        List<BoardVO> boardList = boardMapper.selectBoardListWithPage(page);
//        boardList.forEach(board -> board.dateToTodayCalculator());
        boardList.forEach(board -> board.setDateToToday(dateUtility.dateToTodayCalculator(board.getRegDate(), board.getUpdateDate())));
        return boardList;
    }

    @Override
    public BoardVO getBoardByBno(long bno) {
        return boardMapper.selectBoardByBno(bno);
    }

    @Override
    public List<BoardVO> searchBoardByKeyword(BoardSearchVO searchVO) {
        List<BoardVO> searchResult = boardMapper.selectBoardByKeyword(searchVO);
        searchResult.forEach(board -> board.setDateToToday(dateUtility.dateToTodayCalculator(board.getRegDate(), board.getUpdateDate())));
        return searchResult;
    }

    @Override
    public int registerBoard(BoardVO board) {
        if (boardMapper.insertBoard(board) == 1) {
            if (board.getBoardPassword() != null) {
                return boardMapper.insertBoardWithPassword(board.getBno(), passwordEncoder.encode(board.getBoardPassword()));
            }
        } else {
            return 0;
        }
        return 1;
    }

    @Override
    public int modifyPost(BoardVO board) {
        return boardMapper.updatePost(board);
    }

    @Override
    public int modifyPostWithPassword(BoardVO board, boolean isAdmin) {
        return boardMapper.updatePasswordForPost(board.getBno(), passwordEncoder.encode(board.getBoardPassword()));
    }

    @Override
    public int removeBoard(long bno) {
        return boardMapper.deleteBoard(bno);
    }

    @Override
    public int removePostWithPassword(BoardVO board, boolean isAdmin) throws RuntimeException {
        if (!isAdmin) {
            String encodePassword = boardMapper.getPostPassword(board.getBno());
            if (encodePassword != null) {
                if (!passwordEncoder.matches(board.getBoardPassword(), encodePassword))
                    throw new RuntimeException("Incorrect Password");
            }
        }
        return boardMapper.deletePasswordForPost(board.getBno()) & boardMapper.deleteBoard(board.getBno());
    }

    @Override
    public int removeAllBoard() {
        return boardMapper.deleteAllBoard();
    }

    @Override
    public long initBnoValue() {
        return boardMapper.initAutoIncrement();
    }

    @Override
    public BoardVO authenticateForPosts(BoardVO board, MemberVO member) {
        return boardMapper.authenticateForPosts(board, member);
    }

    @Override
    public boolean postHasPassword(long bno) {
        return boardMapper.getPostPassword(bno) != null;
    }

    @Override
    public boolean postPasswordMatches(long bno, String password) {
        String encodePassword = boardMapper.getPostPassword(bno);
        if (encodePassword != null) {
            return passwordEncoder.matches(password, encodePassword);
        }
        return false;
    }
}
