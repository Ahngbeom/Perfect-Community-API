package org.zerock.service;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.zerock.DTO.PostsSearchDTO;
import org.zerock.DTO.PostsDTO;
import org.zerock.DTO.UserDTO;
import org.zerock.mapper.BoardMapper;
import org.zerock.utils.DateUtility;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private static final Logger log = LogManager.getLogger(BoardServiceImpl.class);
    private final BoardMapper boardMapper;

    private final DateUtility dateUtility;

    private final PasswordEncoder passwordEncoder;


    @Override
    public List<PostsDTO> getBoardList() {
        return boardMapper.selectBoardList();
    }

    @Override
    public List<PostsDTO> getBoardListWithPage(int page) throws RuntimeException {
        if (page < 0)
            throw new RuntimeException("Invalid page");
        List<PostsDTO> boardList = boardMapper.selectBoardListWithPage(page);
//        boardList.forEach(board -> board.dateToTodayCalculator());
        boardList.forEach(board -> board.setDateToToday(dateUtility.dateToTodayCalculator(board.getRegDate(), board.getUpdateDate())));
        return boardList;
    }

    @Override
    public PostsDTO getBoardByBno(long bno) {
        PostsDTO posts = boardMapper.selectBoardByBno(bno);
        if (posts == null)
            throw new RuntimeException("There are no posts with that bno.");
        return posts;
    }

    @Override
    public List<PostsDTO> searchBoardByKeyword(PostsSearchDTO searchVO) {
        List<PostsDTO> searchResult = boardMapper.selectBoardByKeyword(searchVO);
        searchResult.forEach(board -> board.setDateToToday(dateUtility.dateToTodayCalculator(board.getRegDate(), board.getUpdateDate())));
        return searchResult;
    }

    @Override
    public int registerBoard(PostsDTO board) {
        log.warn(board);
        int result;
        if (board.getBoardPassword() != null) {
            result = boardMapper.insertBoardWithPassword(board.getBno(), passwordEncoder.encode(board.getBoardPassword()));
        } else {
            result = boardMapper.insertBoard(board);
        }

        if (result == 0)
            throw new RuntimeException("Failed to register post.");

        return result;
    }

    @Override
    public int modifyPost(PostsDTO board) {
        return boardMapper.updatePost(board);
    }

    @Override
    public int modifyPostWithPassword(PostsDTO board, boolean isAdmin) {
        return boardMapper.updatePasswordForPost(board.getBno(), passwordEncoder.encode(board.getBoardPassword())) |
                boardMapper.updatePost(board);
    }

    @Override
    public int removeBoard(long bno) {
        return boardMapper.deleteBoard(bno);
    }

    @Override
    public int removePostWithPassword(PostsDTO board, boolean isAdmin) throws RuntimeException {
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
    public int removeAllBoard() throws RuntimeException {
        int result;
        if (countPosts() == 0) {
            throw new RuntimeException("Posts do not exist.");
        } else {
            result = boardMapper.deleteAllBoard();
            if (result == 0)
                throw new RuntimeException("Failed to delete all posts");
            if (initBnoValue() == 0)
                throw new RuntimeException("Failure to initialize the bno value of the board");
        }
        return result;
    }

    @Override
    public long countPosts() {
        return boardMapper.countBoard();
    }

    @Override
    public long initBnoValue() {
        return boardMapper.initAutoIncrement();
    }

    @Override
    public PostsDTO authenticateForPosts(PostsDTO board, UserDTO member) {
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
