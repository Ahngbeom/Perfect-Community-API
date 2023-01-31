package com.perfect.community.api.service.post;

import com.perfect.community.api.mapper.post.PostInteractionMapper;
import com.perfect.community.api.mapper.user.UserPostViewsMapper;
import com.perfect.community.api.vo.post.PostRecommendVO;
import com.perfect.community.api.vo.post.PostViewsVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostInteractionService {

    private final PostInteractionMapper postInteractionMapper;
    private final UserPostViewsMapper userPostViewsMapper;

    public long increaseViews(String username, long postNo) {
        LocalDateTime postViewedDate = userPostViewsMapper.getPostViewedDate(username, postNo);
        if (postViewedDate == null) {
            if (userPostViewsMapper.addViewedPost(username, postNo) != 1)
                throw new RuntimeException("Failed to save user's view post.");
        } else if (ChronoUnit.DAYS.between(LocalDateTime.now(), postViewedDate) >= 1) {
            if (userPostViewsMapper.updateViewedPost(username, postNo) != 1)
                throw new RuntimeException("Failed to save user's view post.");
        } else {
            return 0;
        }

        PostViewsVO postViewsVO = PostViewsVO.builder()
                .post_no(postNo)
                .build();
        if (postInteractionMapper.increaseViews(postViewsVO) != 1)
            throw new RuntimeException("Failed to increase views of post.");
        return postViewsVO.getViews();
    }

    public long increaseRecommend(long pno) {
        PostRecommendVO postRecommendVO = PostRecommendVO.builder()
                .post_no(pno)
                .build();
        if (postInteractionMapper.recommendation(postRecommendVO) != 1)
            throw new RuntimeException("Failed to post recommendation.");
        return postRecommendVO.getRecommend();
    }

    public long increaseNotRecommend(long pno) {
        PostRecommendVO postRecommendVO = PostRecommendVO.builder()
                .post_no(pno)
                .build();
        if (postInteractionMapper.notRecommendation(postRecommendVO) != 1)
            throw new RuntimeException("Failed to post not recommendation.");
        return postRecommendVO.getNot_recommend();
    }
}
