package com.perfect.community.api.service.post;

import com.perfect.community.api.dto.post.PostFilterDTO;
import com.perfect.community.api.dto.post.PostType;
import org.junit.jupiter.api.Test;

public class ListTest extends PostServiceTest {

    @Test
    void list() {
        postService.getPostList(PostFilterDTO.builder().build()).forEach(log::info);
    }

    @Test
    void optionsIsNull() {
        postService.getPostList(null).forEach(log::info);
    }

    @Test
    void byBoardNo() {
        PostFilterDTO options = PostFilterDTO.builder()
                .boardNo(1)
                .build();
        postService.getPostList(options).forEach(log::info);
    }

    @Test
    void byType() {
        PostFilterDTO options = PostFilterDTO.builder()
                .type(PostType.NOTICE.name())
                .build();
        postService.getPostList(options).forEach(log::info);
    }

    @Test
    void byPage() {
        PostFilterDTO options = PostFilterDTO.builder()
                .page(2)
                .build();
        postService.getPostList(options).forEach(log::info);
    }

    @Test
    void byBoardNoAndPageAndType() {
        PostFilterDTO options = PostFilterDTO.builder()
                .boardNo(2)
                .page(1)
                .type(PostType.NORMAL.name())
                .build();
        postService.getPostList(options).forEach(log::info);
    }
}
