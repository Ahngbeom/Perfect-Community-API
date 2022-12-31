package com.perfect.community.api.mapper.post;

import com.perfect.community.api.dto.post.PostExtractionDTO;
import com.perfect.community.api.dto.post.PostType;
import org.junit.jupiter.api.Test;

public class ListTest extends PostMapperTest {

    private PostExtractionDTO.List listOptions;

    @Test
    void all() {
        listOptions = PostExtractionDTO.List.builder().build();
        postsMapper.selectPostList(listOptions).forEach(log::info);
    }

    @Test
    void listOptionsIsNull() {
        listOptions = PostExtractionDTO.List.builder().build();
        postsMapper.selectPostList(null).forEach(log::info);
        // It is the same as requesting PostExtractionDTO initialized without arguments.
    }

    @Test
    void byInvalidBoardNo() {
        try {
            listOptions = PostExtractionDTO.List.builder().boardNo(-1).build();
            postsMapper.selectPostList(listOptions).forEach(log::info);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Test
    void byBoardNo() {
        listOptions = PostExtractionDTO.List.builder().boardNo(1).build();
        postsMapper.selectPostList(listOptions).forEach(log::info);
    }

    @Test
    void byType() {
        listOptions = PostExtractionDTO.List.builder().type(PostType.NOTICE.name()).build();
        postsMapper.selectPostList(listOptions).forEach(log::info);
    }

    @Test
    void byPage() {
        listOptions = PostExtractionDTO.List.builder().page(2).build();
        postsMapper.selectPostList(listOptions).forEach(log::info);
    }

    @Test
    void byBoardNoAndPage() {
        listOptions = PostExtractionDTO.List.builder().boardNo(1).page(2).build();
        postsMapper.selectPostList(listOptions).forEach(log::info);
    }

    @Test
    void byBoardNoAndType() {
        listOptions = PostExtractionDTO.List.builder().boardNo(1).type(PostType.NOTICE.name()).build();
        postsMapper.selectPostList(listOptions).forEach(log::info);
    }

    @Test
    void byPageAndType() {
        listOptions = PostExtractionDTO.List.builder().boardNo(1).page(2).build();
        postsMapper.selectPostList(listOptions).forEach(log::info);
    }

    @Test
    void byBoardNoAndPageAndType() {
        listOptions = PostExtractionDTO.List.builder().boardNo(1).page(2).type(PostType.NORMAL.name()).build();
        postsMapper.selectPostList(listOptions).forEach(log::info);
    }
}
