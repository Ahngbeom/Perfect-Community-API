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

    long increaseViews(long pno) {
        PostViewsVO postViewsVO = PostViewsVO.builder()
                .post_no(pno)
                .build();
        mapper.increaseViews(postViewsVO);
        return postViewsVO.getViews();
    }

    long increaseRecommend(long pno) {
        PostRecommendVO postRecommendVO = PostRecommendVO.builder()
                .post_no(pno)
                .build();
        mapper.recommendation(postRecommendVO);
        return postRecommendVO.getRecommend();
    }

    long increaseNotRecommend(long pno) {
        PostRecommendVO postRecommendVO = PostRecommendVO.builder()
                .post_no(pno)
                .build();
        mapper.notRecommendation(postRecommendVO);
        return postRecommendVO.getNot_recommend();
    }
}
