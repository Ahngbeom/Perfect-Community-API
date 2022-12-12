package com.board.api.service.board;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.board.api.DTO.PostDTO;
import com.board.api.DTO.UserDTO;
import com.board.api.mapper.PostCRUD_Mapper;
import com.board.api.utils.DateUtility;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostCRUD_ServiceImpl implements PostCRUD_Service {
    private static final Logger log = LogManager.getLogger(PostCRUD_ServiceImpl.class);
    private final PostCRUD_Mapper postsCRUDMapper;

    private final DateUtility dateUtility;

    private final PasswordEncoder passwordEncoder;


    @Override
    public List<PostDTO> getBoardList() {
        return postsCRUDMapper.selectBoardList();
    }

    @Override
    public List<PostDTO> getBoardListWithPage(int page) throws RuntimeException {
        if (page < 0)
            throw new RuntimeException("Invalid page");
        List<PostDTO> boardList = postsCRUDMapper.selectBoardListWithPage(page);
//        boardList.forEach(board -> board.dateToTodayCalculator());
        boardList.forEach(board -> board.setDateToToday(dateUtility.dateToTodayCalculator(board.getRegDate(), board.getUpdateDate())));
        return boardList;
    }

    @Override
    public PostDTO getBoardByBno(long bno) {
        PostDTO posts = postsCRUDMapper.selectBoardByBno(bno);
        if (posts == null)
            throw new RuntimeException("There are no posts with that bno.");
        return posts;
    }

    @Override
    public void registerBoard(PostDTO board) {
        log.warn(board);
        int result;
        result = postsCRUDMapper.insertBoard(board);
        if (result == 0)
            throw new RuntimeException("Failed to register post.");

    }

    @Override
    public void modifyPost(PostDTO board) {
        postsCRUDMapper.updatePost(board);
    }

    @Override
    public void removeBoard(long bno) {
        postsCRUDMapper.deleteBoard(bno);
    }

    @Override
    public int removeAllBoard() throws RuntimeException {
        int result;
        if (countPosts() == 0) {
            throw new RuntimeException("Posts do not exist.");
        } else {
            result = postsCRUDMapper.deleteAllBoard();
            if (result == 0)
                throw new RuntimeException("Failed to delete all posts");
            if (initBnoValue() == 0)
                throw new RuntimeException("Failure to initialize the bno value of the board");
        }
        return result;
    }

    @Override
    public long countPosts() {
        return postsCRUDMapper.countBoard();
    }

    @Override
    public long initBnoValue() {
        return postsCRUDMapper.initAutoIncrement();
    }

    @Override
    public PostDTO authenticateForPosts(PostDTO board, UserDTO member) {
        return postsCRUDMapper.authenticateForPosts(board, member);
    }

}
