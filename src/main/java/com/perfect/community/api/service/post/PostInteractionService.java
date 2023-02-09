/*
 * Copyright (C) 23. 2. 1. 오후 10:03 Ahngbeom (https://github.com/Ahngbeom)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.perfect.community.api.service.post;

import com.perfect.community.api.mapper.post.PostInteractionMapper;
import com.perfect.community.api.mapper.user.UserPostViewsMapper;
import com.perfect.community.api.service.redis.RedisService;
import com.perfect.community.api.vo.post.PostRecommendVO;
import com.perfect.community.api.vo.post.PostViewsVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sun.net.util.IPAddressUtil;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostInteractionService {

    private final PostInteractionMapper postInteractionMapper;
    private final UserPostViewsMapper userPostViewsMapper;
    private final RedisService redisService;

    public long increaseViews(String username, long postNo) {
        /* Add a record of user scrap posts */
        if (IPAddressUtil.isIPv4LiteralAddress(username) || IPAddressUtil.isIPv6LiteralAddress(username)) {
            String key = RedisService.POST_VIEWS_RECORD_BY_ANONYMOUS_KEY + username;
            if (redisService.add(key, String.valueOf(postNo)) == 0) {
                return postInteractionMapper.getViews(postNo);
            }
            redisService.expire(key, 1, TimeUnit.DAYS);
        } else {
            LocalDateTime postViewedDate = userPostViewsMapper.getPostViewedDate(username, postNo);
            if (postViewedDate == null) {
                if (userPostViewsMapper.addViewedPost(username, postNo) != 1)
                    throw new RuntimeException("Failed to save user's view post.");
            } else if (ChronoUnit.DAYS.between(LocalDateTime.now(), postViewedDate) >= 1) {
                /* 1 Day limit */
                if (userPostViewsMapper.updateViewedPost(username, postNo) != 1)
                    throw new RuntimeException("Failed to save user's view post.");
            } else {
                return 0;
            }
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
