package com.perfect.community.api.mapper.post;

import com.perfect.community.api.dto.post.PostType;
import com.perfect.community.api.vo.post.PostVO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("[Mapper] Post registration")
public class RegistrationTest extends PostMapperTest {
    @Test
    @DisplayName("Post registration")
    void registration() {
        PostVO postVO = PostVO.builder()
                .board_no(1)
                .type(PostType.NORMAL.name())
                .writer("admin")
                .title("Post insert test in JUnit")
                .contents("Post insert test in JUnit...")
                .build();
        log.info(postsMapper.insertPost(postVO));
        log.info(selectPostInfoByPno(postVO.getPost_no()));
    }

    @Test
    @DisplayName("Without board no")
    void withoutBoardNo() {
        try {
            PostVO postVO = PostVO.builder()
                    .type(PostType.NORMAL.name())
                    .writer("admin")
                    .title("Post insert test in JUnit")
                    .contents("Post insert test in JUnit...")
                    .build();
            log.info(postsMapper.insertPost(postVO));
            log.info(selectPostInfoByPno(postVO.getPost_no()));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Test
    @DisplayName("With null type")
    void withNullType() {
        try {
            PostVO postVO = PostVO.builder()
                    .board_no(1)
                    .type(null)
                    .writer("admin")
                    .title("Post insert test in JUnit")
                    .contents("Post insert test in JUnit...")
                    .build();
            log.info(postsMapper.insertPost(postVO));
            log.info(selectPostInfoByPno(postVO.getPost_no()));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Test
    @DisplayName("With null writer")
    void withNullWriter() {
        try {
            PostVO postVO = PostVO.builder()
                    .board_no(1)
                    .type(PostType.NORMAL.name())
                    .writer(null)
                    .title("Post insert test in JUnit")
                    .contents("Post insert test in JUnit...")
                    .build();
            log.info(postsMapper.insertPost(postVO));
            log.info(selectPostInfoByPno(postVO.getPost_no()));
        } catch (Exception e) {
            log.error(e.getMessage());
        }

    }

    @Test
    @DisplayName("With null title")
    void withNullTitle() {
        try {
            PostVO postVO = PostVO.builder()
                    .board_no(1)
                    .type(PostType.NORMAL.name())
                    .writer("admin")
                    .title(null)
                    .contents("Post insert test in JUnit...")
                    .build();
            log.info(postsMapper.insertPost(postVO));
            log.info(selectPostInfoByPno(postVO.getPost_no()));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Test
    @DisplayName("With null contents")
    void withNullContents() {
        try {
            PostVO postVO = PostVO.builder()
                    .board_no(1)
                    .type(PostType.NORMAL.name())
                    .writer("admin")
                    .title("Post insert test in JUnit")
                    .contents(null)
                    .build();
            log.info(postsMapper.insertPost(postVO));
            log.info(selectPostInfoByPno(postVO.getPost_no()));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Test
    @DisplayName("With null entity")
    void withNullEntity() {
        try {
            log.info(postsMapper.insertPost(null));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
