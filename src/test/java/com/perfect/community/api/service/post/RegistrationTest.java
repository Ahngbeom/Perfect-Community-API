package com.perfect.community.api.service.post;

import com.perfect.community.api.dto.post.PostDTO;
import com.perfect.community.api.dto.post.PostType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("[Service] Post's registration")
public class RegistrationTest extends PostServiceTest {

    @Test
    @DisplayName("Registration")
    void registration() {
        PostDTO postDTO = PostDTO.builder()
                .boardNo(1)
                .type(PostType.NORMAL.name())
                .title("JUNIT TEST")
                .contents("THIS POST FOR JUNIT TEST")
                .build();
        long createdPno = postService.registration("admin", postDTO);
        log.info(getInfoByPno(createdPno));
    }

    @Test
    @DisplayName("userId is null")
    void byNullUserId() {
        try {
            PostDTO postDTO = PostDTO.builder()
                    .boardNo(1)
                    .type(PostType.NORMAL.name())
                    .title("JUNIT TEST")
                    .contents("THIS POST FOR JUNIT TEST")
                    .build();
            postService.registration(null, postDTO);
            log.info(getInfoByPno(postDTO.getPostNo()));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Test
    @DisplayName("Invalid board no")
    void byInvalidBoardNo() {
        try {
            PostDTO postDTO = PostDTO.builder()
                    .boardNo(-1)
                    .type(PostType.NORMAL.name())
                    .title("JUNIT TEST")
                    .contents("THIS POST FOR JUNIT TEST")
                    .build();
            postService.registration("admin", postDTO);
            log.info(getInfoByPno(postDTO.getPostNo()));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Test
    @DisplayName("Without post type")
    void withoutType() {
        try {
            PostDTO postDTO = PostDTO.builder()
                    .boardNo(1)
                    .type(PostType.NORMAL.name())
                    .title("JUNIT TEST")
                    .contents("THIS POST FOR JUNIT TEST")
                    .build();
            postService.registration("admin", postDTO);
            log.info(getInfoByPno(postDTO.getPostNo()));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Test
    @DisplayName("title is null")
    void byNullTitle() {
        try {
            PostDTO postDTO = PostDTO.builder()
                    .boardNo(1)
                    .type(PostType.NORMAL.name())
                    .title(null)
                    .contents("THIS POST FOR JUNIT TEST")
                    .build();
            postService.registration("admin", postDTO);
            log.info(getInfoByPno(postDTO.getPostNo()));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Test
    @DisplayName("contents is null")
    void byNullContents() {
        try {
            PostDTO postDTO = PostDTO.builder()
                    .boardNo(1)
                    .type(PostType.NORMAL.name())
                    .title("JUNIT TEST")
                    .contents(null)
                    .build();
            postService.registration("admin", postDTO);
            log.info(getInfoByPno(postDTO.getPostNo()));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Test
    @DisplayName("PostDTO is null")
    void byNullPostDTO() {
        try {
            postService.registration("admin", null);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }
}
