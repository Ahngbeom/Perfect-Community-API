package com.perfect.community.api.mapper.post;

import com.perfect.community.api.dto.post.PostDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface PostInteractionMapper {

    int increaseViews(PostDTO postDTO);
}
