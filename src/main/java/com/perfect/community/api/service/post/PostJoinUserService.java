package com.perfect.community.api.service.post;

import com.perfect.community.api.dto.post.PostDTO;
import com.perfect.community.api.dto.post.PostListOptDTO;

import java.util.List;

public interface PostJoinUserService {
    List<PostDTO> getPostListWithUserDetails(PostListOptDTO postListOptions);
}
