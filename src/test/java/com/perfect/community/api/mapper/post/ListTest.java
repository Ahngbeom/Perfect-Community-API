package com.perfect.community.api.mapper.post;

import com.perfect.community.api.dto.post.PostFilterDTO;
import com.perfect.community.api.dto.post.PostType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("[Mapper] Search posts")
public class ListTest extends PostMapperTest {

    private PostFilterDTO listOptions;

    @Test
    @DisplayName("No options")
    void all() {
        listOptions = PostFilterDTO.builder().build();
        postsMapper.selectPostList(listOptions).forEach(log::info);
    }

    @Test
    @DisplayName("null options")
    void listOptionsIsNull() {
        listOptions = PostFilterDTO.builder().build();
        postsMapper.selectPostList(null).forEach(log::info);
        // It is the same as requesting PostExtractionDTO initialized without arguments.
    }

    @Test
    @DisplayName("Invalid board no")
    void byInvalidBoardNo() {
        try {
            listOptions = PostFilterDTO.builder().boardNo(-1).build();
            postsMapper.selectPostList(listOptions).forEach(log::info);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Test
    @DisplayName("Board no in options")
    void byBoardNo() {
        listOptions = PostFilterDTO.builder().boardNo(1).build();
        postsMapper.selectPostList(listOptions).forEach(log::info);
    }

    @Test
    @DisplayName("post type in options")
    void byType() {
        listOptions = PostFilterDTO.builder().type(PostType.NOTICE.name()).build();
        postsMapper.selectPostList(listOptions).forEach(log::info);
    }

    @Test
    @DisplayName("page number in options")
    void byPage() {
        listOptions = PostFilterDTO.builder().page(2).build();
        postsMapper.selectPostList(listOptions).forEach(log::info);
    }

    @Test
    @DisplayName("board no and page number in options")
    void byBoardNoAndPage() {
        listOptions = PostFilterDTO.builder().boardNo(1).page(2).build();
        postsMapper.selectPostList(listOptions).forEach(log::info);
    }

    @Test
    @DisplayName("board no and type in options")
    void byBoardNoAndType() {
        listOptions = PostFilterDTO.builder().boardNo(1).type(PostType.NOTICE.name()).build();
        postsMapper.selectPostList(listOptions).forEach(log::info);
    }

    @Test
    @DisplayName("page number and type in options")
    void byPageAndType() {
        listOptions = PostFilterDTO.builder().boardNo(1).page(2).build();
        postsMapper.selectPostList(listOptions).forEach(log::info);
    }

    @Test
    @DisplayName("board no and page number and type in options")
    void byBoardNoAndPageAndType() {
        listOptions = PostFilterDTO.builder().boardNo(1).page(2).type(PostType.NORMAL.name()).build();
        postsMapper.selectPostList(listOptions).forEach(log::info);
    }

    @Test
    @DisplayName("keyword in options")
    void byKeyword() {
        listOptions = PostFilterDTO.builder().keyword("97").build();
        postsMapper.selectPostList(listOptions).forEach(log::info);
    }
}
