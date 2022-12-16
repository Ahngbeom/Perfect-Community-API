package com.board.api.service.post;

import com.board.api.dto.post.PostListOptDTO;
import com.board.api.entity.post.PostEntity;
import com.board.api.mapper.post.PostJoinUserMapper;
import com.board.api.mapper.user.UserMapper;
import com.board.api.mapper.post.PostTypeMapper;
import com.board.api.mapper.utils.UtilsMapper;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import com.board.api.dto.post.PostDTO;
import com.board.api.mapper.post.PostMapper;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private static final Logger log = LogManager.getLogger(PostServiceImpl.class);
    private final PostMapper postMapper;
    private final PostTypeMapper postTypeMapper;

    private final UserMapper userMapper;
    private final PostUtils postUtils;

    @Override
    public List<PostDTO> getPostList(PostListOptDTO postListOptions) {
//        if (postListOptions.getBoardNo() < 1)
//            throw new RuntimeException("Invalid board number.");
        if (postListOptions.getPage() < 0)
            throw new RuntimeException("Invalid page number.");
        if (!postUtils.verifyPostType(postListOptions.getType()))
            throw new RuntimeException("Invalid post type.");
        List<PostEntity> postEntities = postMapper.selectPostList(postListOptions);
        return postEntities.stream().map(this::entityToDTO).collect(Collectors.toList());
    }

    @Override
    public PostDTO getInfoByPno(long pno) {
        PostEntity postEntity = postMapper.selectPostInfoByPno(pno);
        if (postEntity == null)
            throw new RuntimeException("There are no posts with that bno.");
        return entityToDTO(postEntity);
    }

    @Override
    public PostDTO registration(String userId, PostDTO postDTO) throws RuntimeException {
        PostEntity postEntity = PostEntity.builder()
                .boardNo(postDTO.getBoardNo())
                .type(postDTO.getType())
                .title(postDTO.getTitle())
                .contents(postDTO.getContents())
                .writer(userId)
                .build();
        if (postMapper.insertPost(postEntity) == 0)
            throw new RuntimeException("Failed to register post.");
        log.warn(postEntity);
        return entityToDTO(postEntity);
    }

    @Override
    public void modification(String userId, PostDTO postDTO) {
        log.info(postDTO);
        if (postDTO.getPno() < 1)
            throw new RuntimeException("Invalid post number.");
        if (!postUtils.checkPostVerification(userId, postDTO.getPno()))
            throw new RuntimeException("You do not have permission to modify the post.");
        postMapper.updatePost(postDTO);
    }

    @Override
    public void remove(String userId, long pno) {
        if (pno < 1)
            throw new RuntimeException("Invalid post number.");
        if (!postUtils.checkPostVerification(userId, pno))
            throw new RuntimeException("You do not have permission to remove the post.");
        postMapper.deletePost(pno);
    }

    public PostDTO entityToDTO(PostEntity entity) {
        return PostDTO.builder()
                .pno(entity.getPno())
                .boardNo(entity.getBoardNo())
                .type(entity.getType())
                .writtenUser(userMapper.readMemberByUserId(entity.getWriter()))
                .title(entity.getTitle())
                .contents(entity.getContents())
                .regDate(entity.getRegDate())
                .updateDate(entity.getUpdateDate())
                .build();
    }

}
