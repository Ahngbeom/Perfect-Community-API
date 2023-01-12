package com.perfect.community.api.service.post;

import com.perfect.community.api.mapper.post.PostInteractionMapper;
import com.perfect.community.api.vo.post.PostRecommendVO;
import com.perfect.community.api.vo.post.PostViewsVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostInteractionService {

    private final PostInteractionMapper mapper;

    public long increaseViews(long pno) {
        PostViewsVO postViewsVO = PostViewsVO.builder()
                .post_no(pno)
                .build();
        if (mapper.increaseViews(postViewsVO) != 1)
            throw new RuntimeException("Failed to increase views of post.");
        return postViewsVO.getViews();
    }

    public long increaseRecommend(long pno) {
        PostRecommendVO postRecommendVO = PostRecommendVO.builder()
                .post_no(pno)
                .build();
        if (mapper.recommendation(postRecommendVO) != 1)
            throw new RuntimeException("Failed to post recommendation.");
        return postRecommendVO.getRecommend();
    }

    public long increaseNotRecommend(long pno) {
        PostRecommendVO postRecommendVO = PostRecommendVO.builder()
                .post_no(pno)
                .build();
        if (mapper.notRecommendation(postRecommendVO) != 1)
            throw new RuntimeException("Failed to post not recommendation.");
        return postRecommendVO.getNot_recommend();
    }
}
