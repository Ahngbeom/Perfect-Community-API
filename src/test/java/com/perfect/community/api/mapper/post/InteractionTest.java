package com.perfect.community.api.mapper.post;

import com.perfect.community.api.vo.post.PostRecommendVO;
import com.perfect.community.api.vo.post.PostViewsVO;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.Rollback;

@Rollback(value = false)
public class InteractionTest extends PostMapperTest {

    @Test
    void increaseViews() {
        PostViewsVO postViewsVO = PostViewsVO.builder()
                .post_no(1)
                .build();
        if (postInteractionMapper.increaseViews(postViewsVO) == 1) {
            log.info(postViewsVO.getViews());
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
