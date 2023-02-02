package com.perfect.community.api.mapper.post;

import com.perfect.community.api.dto.post.PostFilterDTO;
import com.perfect.community.api.dto.post.PostType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("[Mapper] Count posts")
public class CountTest extends PostMapperTest {

    @Test
    @DisplayName("Count posts with null options")
    void countPostsNoArguments() {
        log.info(postsMapper.countPosts(null));
    }

    @Test
    @DisplayName("Count posts with board no in options")
    void countPostsByBoardNo() {
        PostFilterDTO options = PostFilterDTO.builder().boardNo(1).build();
        log.info(postsMapper.countPosts(options));
    }

    @Test
    @DisplayName("Count posts with post type in options")
    void countPostsByType() {
        PostFilterDTO options = PostFilterDTO.builder().type(PostType.NOTICE.name()).build();
        log.info(postsMapper.countPosts(options));
    }
}
