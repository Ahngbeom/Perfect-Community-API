package com.board.api.service.post;

import com.board.api.dto.post.PostListOptDTO;
import com.board.api.entity.post.PostEntity;
import com.board.api.mapper.UserMapper;
import com.board.api.mapper.post.PostTypeMapper;
import com.board.api.mapper.utils.UtilsMapper;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import com.board.api.dto.post.PostDTO;
import com.board.api.mapper.post.PostCRUD_Mapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostCRUD_ServiceImpl implements PostCRUD_Service {
    private static final Logger log = LogManager.getLogger(PostCRUD_ServiceImpl.class);
    private final PostCRUD_Mapper postsCRUDMapper;
    private final PostTypeMapper postTypeMapper;

    private final UserMapper userMapper;
    private final UtilsMapper utilsMapper;

    @Override
    public List<PostDTO> getPostList(PostListOptDTO postListOptions) {
        if (postListOptions.getBoardNo() < 1)
            throw new RuntimeException("Invalid board number.");
        if (postListOptions.getPage() < 0)
            throw new RuntimeException("Invalid page number.");
        if (!verifyPostType(postListOptions.getType()))
            throw new RuntimeException("Invalid post type.");
        return postsCRUDMapper.selectPostList(postListOptions);
    }

    @Override
    public PostDTO getInfoByPno(long pno) {
        PostDTO posts = postsCRUDMapper.selectPostInfoByPno(pno);
        if (posts == null)
            throw new RuntimeException("There are no posts with that bno.");
        return posts;
    }

    @Override
    public void registration(String userId, PostDTO postDTO) throws RuntimeException {
        postDTO.setWrittenUser(userMapper.readMemberByUserId(userId));
        PostEntity postEntity = PostEntity.dtoToEntity(postDTO);
        log.warn(postEntity);
        if (postsCRUDMapper.insertPost(postEntity) == 0)
            throw new RuntimeException("Failed to register post.");
    }

    @Override
    public void modification(PostDTO postDTO, String userName) {
        log.info(postDTO);
        if (postDTO.getPno() < 1)
            throw new RuntimeException("Invalid post number.");
        if (!checkPostVerification(userName, postDTO.getPno()))
            throw new RuntimeException("You do not have permission to modify the post.");
        postsCRUDMapper.updatePost(postDTO);
    }

    @Override
    public void remove(String userName, long pno) {
        if (pno < 1)
            throw new RuntimeException("Invalid post number.");
        if (!checkPostVerification(userName, pno))
            throw new RuntimeException("You do not have permission to remove the post.");
        postsCRUDMapper.deletePost(pno);
    }

    @Override
    public int removeAll() throws RuntimeException {
        int result;
        if (countPosts(1) == 0) {
            throw new RuntimeException("Posts do not exist.");
        } else {
            result = postsCRUDMapper.deleteAllPosts();
            if (result == 0)
                throw new RuntimeException("Failed to delete all posts");
            if (initPnoValue() == 0)
                throw new RuntimeException("Failure to initialize the bno value of the board");
        }
        return result;
    }

    @Override
    public long countPosts(long boardNo) {
        return postsCRUDMapper.countPosts(boardNo);
    }

    @Override
    public long initPnoValue() {
        return utilsMapper.initializeAutoIncrement("posts");
    }

    public boolean checkPostVerification(String userName, long pno) {
        return userName.equals(getInfoByPno(pno).getWrittenUser());
    }

    public boolean verifyPostType(String type) {
        return type == null || postTypeMapper.selectAllPostType().contains(type.toUpperCase());
    }

}
