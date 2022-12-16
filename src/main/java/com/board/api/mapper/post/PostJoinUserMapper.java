package com.board.api.mapper.post;

import com.board.api.dto.post.PostDTO;
import com.board.api.dto.post.PostListOptDTO;

import java.util.List;

public interface PostJoinUserMapper {
    List<PostDTO> selectPostListJoinUsers(PostListOptDTO postListOptions);

}
