package com.board.api.service.post;

import com.board.api.dto.post.PostListOptDTO;
import com.board.api.mapper.utils.UtilsMapper;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import com.board.api.dto.post.PostDTO;
import com.board.api.mapper.PostCRUD_Mapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostCRUD_ServiceImpl implements PostCRUD_Service {
    private static final Logger log = LogManager.getLogger(PostCRUD_ServiceImpl.class);
    private final PostCRUD_Mapper postsCRUDMapper;
    private final UtilsMapper utilsMapper;

    @Override
    public List<PostDTO> getPostList(PostListOptDTO postListOptions) {
        if (postListOptions.getBoardNo() < 1)
            throw new RuntimeException("Invalid board number.");
        if (postListOptions.getPage() < 0)
            throw new RuntimeException("Invalid page number.");
        return postsCRUDMapper.selectBoardListWithPage(postListOptions);
    }

    @Override
    public PostDTO getInfoByBno(long bno) {
        PostDTO posts = postsCRUDMapper.selectBoardByPno(bno);
        if (posts == null)
            throw new RuntimeException("There are no posts with that bno.");
        return posts;
    }

    @Override
    public void registration(PostDTO board) throws RuntimeException {
        log.warn(board);
        if (postsCRUDMapper.insertPost(board) == 0)
            throw new RuntimeException("Failed to register post.");
    }

    @Override
    public void modification(PostDTO board) {
        postsCRUDMapper.updatePost(board);
    }

    @Override
    public void remove(long bno) {
        postsCRUDMapper.deleteBoard(bno);
    }

    @Override
    public int removeAll() throws RuntimeException {
        int result;
        if (countPosts(1) == 0) {
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
    public long countPosts(long boardNo) {
        return postsCRUDMapper.countBoard(boardNo);
    }

    @Override
    public long initPnoValue() {
        return utilsMapper.initializeAutoIncrement("posts");
    }

}
