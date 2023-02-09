package com.perfect.community.api.service.post;

import com.perfect.community.api.dto.post.PostDTO;
import com.perfect.community.api.mapper.post.PostScrapMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostScrapService {

    private final PostScrapMapper mapper;

    public void scrapePost(String userId, long postNo) {
        if (mapper.insertScrapPost(userId, postNo) != 1)
            throw new RuntimeException("Failed to scrape post");
    }

    public List<PostDTO> getAllScrapedPosts(String userId) {
        return mapper.getAllByUserId(userId);
    }

    public void releaseScrapedPost(String userId, long postNo) {
        if (mapper.deleteByUserIdAndPostNo(userId, postNo) != 1)
            throw new RuntimeException("Failed to release scraped post");
    }
}
