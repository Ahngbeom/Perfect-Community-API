package com.perfect.community.api.service.post;

import com.perfect.community.api.dto.post.PostExtractionDTO;
import com.perfect.community.api.dto.post.PostType;
import org.junit.jupiter.api.Test;

public class ListTest extends PostServiceTest {

    @Test
    void list() {
        postService.getPostList(PostExtractionDTO.List.builder().build()).forEach(log::info);
    }

    @Test
    void optionsIsNull() {
        postService.getPostList(null).forEach(log::info);
    }

    @Test
    void byBoardNo() {
        PostExtractionDTO.List options = PostExtractionDTO.List.builder()
                .boardNo(1)
                .build();
        postService.getPostList(options).forEach(log::info);
    }

    @Test
    void byType() {
        PostExtractionDTO.List options = PostExtractionDTO.List.builder()
                .type(PostType.NOTICE.name())
                .build();
        postService.getPostList(options).forEach(log::info);
    }

    @Test
    void byPage() {
        PostExtractionDTO.List options = PostExtractionDTO.List.builder()
                .page(2)
                .build();
        postService.getPostList(options).forEach(log::info);
    }

    @Test
    void byBoardNoAndPageAndType() {
        PostExtractionDTO.List options = PostExtractionDTO.List.builder()
                .boardNo(2)
                .page(1)
                .type(PostType.NORMAL.name())
                .build();
        postService.getPostList(options).forEach(log::info);
    }
}
