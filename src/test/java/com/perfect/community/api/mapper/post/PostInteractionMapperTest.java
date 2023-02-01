package com.perfect.community.api.mapper.post;

import com.perfect.community.api.vo.post.PostRecommendVO;
import com.perfect.community.api.vo.post.PostViewsVO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("[Mapper] Post interaction")
class PostInteractionMapperTest extends PostMapperTest {

    @Test
    @DisplayName("Initialize views")
    void initializeViews() {
        log.info(postInteractionMapper.initializeViews(1));
    }

    @Test
    @DisplayName("Initialize recommend and not recommend")
    void initializeRecommend() {
        log.info(postInteractionMapper.initializeRecommend(1));
    }

    @Test
    @DisplayName("Get views")
    void getViews() {
        log.info(postInteractionMapper.getViews(1));
    }

    @Test
    @DisplayName("Get recommended")
    void getRecommend() {
        log.info(postInteractionMapper.getRecommend(1));
    }

    @Test
    @DisplayName("Get not recommended")
    void getNotRecommend() {
        log.info(postInteractionMapper.getNotRecommend(1));
    }

    @Test
    void increaseViews() {
        PostViewsVO postViewsVO = PostViewsVO.builder()
                .post_no(1)
                .build();
        if (postInteractionMapper.increaseViews(postViewsVO) == 1) {
            log.info(postViewsVO);
        }
    }

    @Test
    void recommendation() {
        PostRecommendVO postRecommendVO = PostRecommendVO.builder()
                .post_no(1)
                .build();
        if (postInteractionMapper.recommendation(postRecommendVO) == 1) {
            log.info(postRecommendVO.getRecommend());
        }
    }

    @Test
    void notRecommendation() {
        PostRecommendVO postRecommendVO = PostRecommendVO.builder()
                .post_no(1)
                .build();
        if (postInteractionMapper.notRecommendation(postRecommendVO) == 1) {
            log.info(postRecommendVO.getNot_recommend());
        }
    }
}