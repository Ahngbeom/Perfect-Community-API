package com.board.api.service.board;

import com.board.api.dto.PostListOptDTO;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.board.api.dto.PostDTO;
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
    public List<PostDTO> getBoardListWithPage(PostListOptDTO postListOptions) {
        if (postListOptions.getPage() < 0)
            throw new RuntimeException("Invalid page");
        return postsCRUDMapper.selectBoardListWithPage(postListOptions);
    }

    @Override
    public PostDTO getPostByBno(long bno) {
        PostDTO posts = postsCRUDMapper.selectBoardByPno(bno);
        if (posts == null)
            throw new RuntimeException("There are no posts with that bno.");
        return posts;
    }

    @Override
    public void registerPost(PostDTO board) throws RuntimeException {
        log.warn(board);
        if (postsCRUDMapper.insertBoard(board) == 0)
            throw new RuntimeException("Failed to register post.");
    }

    @Override
    public void modifyPost(PostDTO board) {
        postsCRUDMapper.updatePost(board);
    }

    @Override
    public void removePost(long bno) {
        postsCRUDMapper.deleteBoard(bno);
    }

    @Override
    public int removeAllPost() throws RuntimeException {
        int result;
        if (countPosts() == 0) {
            throw new RuntimeException("Posts do not exist.");
        } else {
            result = postsCRUDMapper.deleteAllBoard();
            if (result == 0)
                throw new RuntimeException("Failed to delete all posts");
            if (initPnoValue() == 0)
                throw new RuntimeException("Failure to initialize the bno value of the board");
        }
        return result;
    }

    @Override
    public long countPosts() {
        return postsCRUDMapper.countBoard();
    }

    @Override
    public long initPnoValue() {
        return postsCRUDMapper.initAutoIncrement();
    }

}
