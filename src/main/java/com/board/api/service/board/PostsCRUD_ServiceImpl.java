package com.board.api.service.board;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.board.api.DTO.PostsDTO;
import com.board.api.DTO.UserDTO;
import com.board.api.mapper.PostsCRUD_Mapper;
import com.board.api.utils.DateUtility;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostsCRUD_ServiceImpl implements PostsCRUD_Service {
    private static final Logger log = LogManager.getLogger(PostsCRUD_ServiceImpl.class);
    private final PostsCRUD_Mapper postsCRUDMapper;

    private final DateUtility dateUtility;

    private final PasswordEncoder passwordEncoder;


    @Override
    public List<PostsDTO> getBoardList() {
        return postsCRUDMapper.selectBoardList();
    }

    @Override
    public List<PostsDTO> getBoardListWithPage(int page) throws RuntimeException {
        if (page < 0)
            throw new RuntimeException("Invalid page");
        List<PostsDTO> boardList = postsCRUDMapper.selectBoardListWithPage(page);
//        boardList.forEach(board -> board.dateToTodayCalculator());
        boardList.forEach(board -> board.setDateToToday(dateUtility.dateToTodayCalculator(board.getRegDate(), board.getUpdateDate())));
        return boardList;
    }

    @Override
    public PostsDTO getBoardByBno(long bno) {
        PostsDTO posts = postsCRUDMapper.selectBoardByBno(bno);
        if (posts == null)
            throw new RuntimeException("There are no posts with that bno.");
        return posts;
    }

    @Override
    public void registerBoard(PostsDTO board) {
        log.warn(board);
        int result;
        if (board.getBoardPassword() != null) {
            result = postsCRUDMapper.insertBoardWithPassword(board.getBno(), passwordEncoder.encode(board.getBoardPassword()));
        } else {
            result = postsCRUDMapper.insertBoard(board);
        }

        if (result == 0)
            throw new RuntimeException("Failed to register post.");

    }

    @Override
    public void modifyPost(PostsDTO board) {
        postsCRUDMapper.updatePost(board);
    }

    @Override
    public int modifyPostWithPassword(PostsDTO board, boolean isAdmin) {
        return postsCRUDMapper.updatePasswordForPost(board.getBno(), passwordEncoder.encode(board.getBoardPassword())) |
                postsCRUDMapper.updatePost(board);
    }

    @Override
    public void removeBoard(long bno) {
        postsCRUDMapper.deleteBoard(bno);
    }

    @Override
    public int removePostWithPassword(PostsDTO board, boolean isAdmin) throws RuntimeException {
        if (!isAdmin) {
            String encodePassword = postsCRUDMapper.getPostPassword(board.getBno());
            if (encodePassword != null) {
                if (!passwordEncoder.matches(board.getBoardPassword(), encodePassword))
                    throw new RuntimeException("Incorrect Password");
            }
        }
        return postsCRUDMapper.deletePasswordForPost(board.getBno()) & postsCRUDMapper.deleteBoard(board.getBno());
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
    public PostsDTO authenticateForPosts(PostsDTO board, UserDTO member) {
        return postsCRUDMapper.authenticateForPosts(board, member);
    }

    @Override
    public boolean postHasPassword(long bno) {
        return postsCRUDMapper.getPostPassword(bno) != null;
    }

    @Override
    public boolean postPasswordMatches(long bno, String password) {
        String encodePassword = postsCRUDMapper.getPostPassword(bno);
        if (encodePassword != null) {
            return passwordEncoder.matches(password, encodePassword);
        }
        return false;
    }
}
