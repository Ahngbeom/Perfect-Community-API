package com.perfect.community.api.mapper.post;

import com.perfect.community.api.dto.post.PostFilterDTO;
import com.perfect.community.api.dto.post.PostType;
import org.junit.jupiter.api.Test;

public class CountTest extends PostMapperTest {

    @Test
    void countPostsNoArguments() {
        log.info(postsMapper.countPosts());
    }
    @Test
    void countPostsByBoardNo() {
        PostFilterDTO options = PostFilterDTO.builder().boardNo(1).build();
        log.info(postsMapper.countPosts(options));
    }
    @Test
    void countPostsByType() {
        PostFilterDTO options = PostFilterDTO.builder().type(PostType.NOTICE.name()).build();
        log.info(postsMapper.countPosts(options));
    }
}
