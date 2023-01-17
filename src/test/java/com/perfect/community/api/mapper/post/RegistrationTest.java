package com.perfect.community.api.mapper.post;

import com.perfect.community.api.dto.post.PostType;
import com.perfect.community.api.vo.post.PostVO;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.Rollback;

public class RegistrationTest extends PostMapperTest {
    @Test
    @Rollback(value = false)
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
    void byWithoutBoardNo() {
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
    void byNullType() {
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
    void byNullWriter() {
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
    void byNullTitle() {
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
    void byNullContents() {
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
    void byNullEntity() {
        try {
            log.info(postsMapper.insertPost(null));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
