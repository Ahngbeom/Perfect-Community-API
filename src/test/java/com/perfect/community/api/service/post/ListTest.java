package com.perfect.community.api.service.post;

import com.perfect.community.api.dto.post.PostFilterDTO;
import com.perfect.community.api.dto.post.PostType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("[Service] Post's list")
public class ListTest extends PostServiceTest {

    @Test
    @DisplayName("No options")
    void list() {
        postService.getPostList(PostFilterDTO.builder().build()).forEach(log::info);
    }

    @Test
    @DisplayName("Null options")
    void optionsIsNull() {
        postService.getPostList(null).forEach(log::info);
    }

    @Test
    @DisplayName("Board no in options")
    void byBoardNo() {
        PostFilterDTO options = PostFilterDTO.builder()
                .boardNo(1)
                .build();
        postService.getPostList(options).forEach(log::info);
    }

    @Test
    @DisplayName("Post type in options")
    void byType() {
        PostFilterDTO options = PostFilterDTO.builder()
                .type(PostType.NOTICE.name())
                .build();
        postService.getPostList(options).forEach(log::info);
    }

    @Test
    @DisplayName("Page number in options")
    void byPage() {
        PostFilterDTO options = PostFilterDTO.builder()
                .page(2)
                .build();
        postService.getPostList(options).forEach(log::info);
    }

    @Test
    @DisplayName("Board no and page number in options")
    void byBoardNoAndPageAndType() {
        PostFilterDTO options = PostFilterDTO.builder()
                .boardNo(2)
                .page(1)
                .type(PostType.NORMAL.name())
                .build();
        postService.getPostList(options).forEach(log::info);
    }
}
