package com.board.api.service.post;

import com.board.api.dto.post.PostDTO;
import com.board.api.dto.post.PostListOptDTO;

import java.util.List;

public interface PostJoinUserService {
    List<PostDTO> getPostListWithUserDetails(PostListOptDTO postListOptions);
}
