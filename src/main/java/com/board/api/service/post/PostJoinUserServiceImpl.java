package com.board.api.service.post;

import com.board.api.dto.post.PostDTO;
import com.board.api.dto.post.PostListOptDTO;
import com.board.api.mapper.post.PostJoinUserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostJoinUserServiceImpl implements PostJoinUserService {

    private final PostUtils postUtils;
    private final PostJoinUserMapper postJoinUserMapper;

    @Override
    public List<PostDTO> getPostListWithUserDetails(PostListOptDTO postListOptions) {
        if (postListOptions.getBoardNo() < 1)
            throw new RuntimeException("Invalid board number.");
        if (postListOptions.getPage() < 0)
            throw new RuntimeException("Invalid page number.");
        if (!postUtils.verifyPostType(postListOptions.getType()))
            throw new RuntimeException("Invalid post type.");
        return postJoinUserMapper.selectPostListJoinUsers(postListOptions);
    }
}
