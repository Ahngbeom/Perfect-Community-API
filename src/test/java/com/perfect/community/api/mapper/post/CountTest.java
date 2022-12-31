package com.perfect.community.api.mapper.post;

import com.perfect.community.api.dto.post.PostExtractionDTO;
import com.perfect.community.api.dto.post.PostType;
import org.junit.jupiter.api.Test;

public class CountTest extends PostMapperTest {

    @Test
    void countPostsNoArguments() {
        log.info(postsMapper.countPosts());
    }
    @Test
    void countPostsByBoardNo() {
        PostExtractionDTO.List options = PostExtractionDTO.List.builder().boardNo(1).build();
        log.info(postsMapper.countPosts(options));
    }
    @Test
    void countPostsByType() {
        PostExtractionDTO.List options = PostExtractionDTO.List.builder().type(PostType.NOTICE.name()).build();
        log.info(postsMapper.countPosts(options));
    }
}
