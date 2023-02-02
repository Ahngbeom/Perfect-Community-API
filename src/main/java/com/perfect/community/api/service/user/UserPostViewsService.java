package com.perfect.community.api.service.user;

import com.perfect.community.api.dto.post.PostDTO;
import com.perfect.community.api.mapper.user.UserPostViewsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserPostViewsService {

    private final UserPostViewsMapper mapper;

    public List<PostDTO> getRecentlyViewPosts(String userId, long amount) {
        return mapper.getRecentlyViewedPosts(userId, amount);
    }
}
