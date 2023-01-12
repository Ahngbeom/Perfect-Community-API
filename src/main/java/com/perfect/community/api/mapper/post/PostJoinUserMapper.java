package com.perfect.community.api.mapper.post;

import com.perfect.community.api.dto.post.PostDTO;
import com.perfect.community.api.dto.post.PostFilterDTO;
import com.perfect.community.api.dto.post.PostViewsDTO;

import java.util.List;

public interface PostJoinUserMapper {
    List<PostDTO> selectPostListJoinUsers(PostFilterDTO postFilters);

}
