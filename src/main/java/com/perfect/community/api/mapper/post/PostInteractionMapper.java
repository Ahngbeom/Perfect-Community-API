package com.perfect.community.api.mapper.post;

import com.perfect.community.api.vo.post.PostRecommendVO;
import com.perfect.community.api.vo.post.PostViewsVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface PostInteractionMapper {

    int initializeViews(long postNo);
    int initializeRecommend(long postNo);
    int increaseViews(PostViewsVO postViewsVO);
    int recommendation(PostRecommendVO postRecommendVO);
    int notRecommendation(PostRecommendVO postRecommendVO);
}
