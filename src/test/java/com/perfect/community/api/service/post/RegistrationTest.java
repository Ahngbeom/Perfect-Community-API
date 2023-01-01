package com.perfect.community.api.service.post;

import com.perfect.community.api.dto.post.PostDTO;
import com.perfect.community.api.dto.post.PostType;
import org.junit.jupiter.api.Test;

public class RegistrationTest extends PostServiceTest {
    @Test
    void registration() {
        PostDTO postDTO = PostDTO.builder()
                .boardNo(1)
                .type(PostType.NORMAL.name())
                .title("JUNIT TEST")
                .contents("THIS POST FOR JUNIT TEST")
                .build();
        long createdPostNo = postService.registration("admin", postDTO);
        log.info(getInfoByPno(createdPostNo));
    }

    @Test
    void byNullUserId() {
        try {
            PostDTO postDTO = PostDTO.builder()
                    .boardNo(1)
                    .type(PostType.NORMAL.name())
                    .title("JUNIT TEST")
                    .contents("THIS POST FOR JUNIT TEST")
                    .build();
            long createdPostNo = postService.registration(null, postDTO);
            log.info(getInfoByPno(createdPostNo));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Test
    void byInvalidBoardNo() {
        try {
            PostDTO postDTO = PostDTO.builder()
                    .boardNo(-1)
                    .type(PostType.NORMAL.name())
                    .title("JUNIT TEST")
                    .contents("THIS POST FOR JUNIT TEST")
                    .build();
            long createdPostNo = postService.registration("admin", postDTO);
            log.info(getInfoByPno(createdPostNo));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Test
    void withoutType() {
        try {
            PostDTO postDTO = PostDTO.builder()
                    .boardNo(1)
                    .type(PostType.NORMAL.name())
                    .title("JUNIT TEST")
                    .contents("THIS POST FOR JUNIT TEST")
                    .build();
            long createdPostNo = postService.registration("admin", postDTO);
            log.info(getInfoByPno(createdPostNo));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Test
    void byNullTitle() {
        try {
            PostDTO postDTO = PostDTO.builder()
                    .boardNo(1)
                    .type(PostType.NORMAL.name())
                    .title(null)
                    .contents("THIS POST FOR JUNIT TEST")
                    .build();
            long createdPostNo = postService.registration("admin", postDTO);
            log.info(getInfoByPno(createdPostNo));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Test
    void byNullContents() {
        try {
            PostDTO postDTO = PostDTO.builder()
                    .boardNo(1)
                    .type(PostType.NORMAL.name())
                    .title("JUNIT TEST")
                    .contents(null)
                    .build();
            long createdPostNo = postService.registration("admin", postDTO);
            log.info(getInfoByPno(createdPostNo));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Test
    void byNullPostDTO() {
        try {
            long createdPostNo = postService.registration("admin", null);
            log.info(getInfoByPno(createdPostNo));
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }
}
