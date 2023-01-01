package com.perfect.community.api.service.post;

import com.google.common.base.Preconditions;
import com.perfect.community.api.dto.post.PostExtractionDTO;
import com.perfect.community.api.entity.post.PostEntity;
import com.perfect.community.api.mapper.user.UsersMapper;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import com.perfect.community.api.dto.post.PostDTO;
import com.perfect.community.api.mapper.post.PostMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private static final Logger log = LogManager.getLogger(PostServiceImpl.class);
    private final PostMapper postMapper;
    private final UsersMapper usersMapper;
    private final PostUtils postUtils;

    @Override
    public List<PostDTO> getPostList(PostExtractionDTO.List postListOptions) {
//        if (postListOptions.getBoardNo() < 1)
//            throw new RuntimeException("Invalid board number.");
//        if (postListOptions.getPage() < 0)
//            throw new RuntimeException("Invalid page number.");
//        if (!postUtils.verifyPostType(postListOptions.getType()))
//            throw new RuntimeException("Invalid post type.");
        List<PostEntity> postEntities = postMapper.selectPostList(postListOptions);
        return postEntities.stream().map(this::entityToDTO).collect(Collectors.toList());
    }

    @Override
    public PostDTO getInfoByPno(long pno) {
        Preconditions.checkState(pno > 0, "Invalid post no.");
        PostEntity postEntity = postMapper.selectPostInfoByPno(pno);
        return postEntity != null ? entityToDTO(postEntity) : null;
    }

    @Override
    public long registration(String userId, PostDTO postDTO) throws RuntimeException {
        Preconditions.checkNotNull(postDTO, "PostDTO must not be null.");
        PostEntity postEntity = PostEntity.builder()
                .boardNo(postDTO.getBoardNo())
                .type(postDTO.getType())
                .title(postDTO.getTitle())
                .contents(postDTO.getContents())
                .writer(userId)
                .build();
        if (postMapper.insertPost(postEntity) == 0)
            throw new RuntimeException("Failed to register post.");
        return postEntity.getPno();
    }

    @Override
    public void modification(long postNo, String userId, PostDTO postDTO) {
        Preconditions.checkState(postNo > 0, "Post number must be greater than zero.");
        Preconditions.checkState(postUtils.checkPostVerification(userId, postNo), "You do not have permission to modify the post.");
        postDTO.setPno(postNo);
        postMapper.updatePost(PostEntity.dtoToEntity(postDTO));
    }

    @Override
    public void remove(String userId, long pno) {
        Preconditions.checkState(pno > 0, "Invalid post no.");
        Preconditions.checkState(postUtils.checkPostVerification(userId, pno), "You do not have permission to remove the post.");
        postMapper.deletePost(pno);
    }

    public PostDTO entityToDTO(PostEntity entity) {
        return PostDTO.builder()
                .pno(entity.getPno())
                .boardNo(entity.getBoardNo())
                .type(entity.getType())
                .writer(entity.getWriter())
                .title(entity.getTitle())
                .contents(entity.getContents())
                .regDate(entity.getRegDate())
                .updateDate(entity.getUpdateDate())
                .build();
    }

}
