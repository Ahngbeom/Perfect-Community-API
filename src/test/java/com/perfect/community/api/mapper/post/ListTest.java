package com.perfect.community.api.mapper.post;

import com.perfect.community.api.dto.post.PostFilterDTO;
import com.perfect.community.api.dto.post.PostType;
import org.junit.jupiter.api.Test;

public class ListTest extends PostMapperTest {

    private PostFilterDTO listOptions;

    @Test
    void all() {
        listOptions = PostFilterDTO.builder().build();
        postsMapper.selectPostList(listOptions).forEach(log::info);
    }

    @Test
    void listOptionsIsNull() {
        listOptions = PostFilterDTO.builder().build();
        postsMapper.selectPostList(null).forEach(log::info);
        // It is the same as requesting PostExtractionDTO initialized without arguments.
    }

    @Test
    void byInvalidBoardNo() {
        try {
            listOptions = PostFilterDTO.builder().boardNo(-1).build();
            postsMapper.selectPostList(listOptions).forEach(log::info);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Test
    void byBoardNo() {
        listOptions = PostFilterDTO.builder().boardNo(1).build();
        postsMapper.selectPostList(listOptions).forEach(log::info);
    }

    @Test
    void byType() {
        listOptions = PostFilterDTO.builder().type(PostType.NOTICE.name()).build();
        postsMapper.selectPostList(listOptions).forEach(log::info);
    }

    @Test
    void byPage() {
        listOptions = PostFilterDTO.builder().page(2).build();
        postsMapper.selectPostList(listOptions).forEach(log::info);
    }

    @Test
    void byBoardNoAndPage() {
        listOptions = PostFilterDTO.builder().boardNo(1).page(2).build();
        postsMapper.selectPostList(listOptions).forEach(log::info);
    }

    @Test
    void byBoardNoAndType() {
        listOptions = PostFilterDTO.builder().boardNo(1).type(PostType.NOTICE.name()).build();
        postsMapper.selectPostList(listOptions).forEach(log::info);
    }

    @Test
    void byPageAndType() {
        listOptions = PostFilterDTO.builder().boardNo(1).page(2).build();
        postsMapper.selectPostList(listOptions).forEach(log::info);
    }

    @Test
    void byBoardNoAndPageAndType() {
        listOptions = PostFilterDTO.builder().boardNo(1).page(2).type(PostType.NORMAL.name()).build();
        postsMapper.selectPostList(listOptions).forEach(log::info);
    }
}
