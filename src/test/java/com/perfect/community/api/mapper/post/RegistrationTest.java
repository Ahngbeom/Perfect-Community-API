package com.perfect.community.api.mapper.post;

import com.perfect.community.api.dto.post.PostType;
import com.perfect.community.api.entity.post.PostEntity;
import org.junit.jupiter.api.Test;

public class RegistrationTest extends PostMapperTest {
    @Test
    void registration() {
        PostEntity postEntity = PostEntity.builder()
                .boardNo(1)
                .type(PostType.NORMAL.name())
                .writer("admin")
                .title("Post insert test in JUnit")
                .contents("Post insert test in JUnit...")
                .build();
        log.info(postsMapper.insertPost(postEntity));
        log.info(selectPostInfoByPno(postEntity.getPno()));
    }

    @Test
    void byWithoutBoardNo() {
        try {
            PostEntity postEntity = PostEntity.builder()
                    .type(PostType.NORMAL.name())
                    .writer("admin")
                    .title("Post insert test in JUnit")
                    .contents("Post insert test in JUnit...")
                    .build();
            log.info(postsMapper.insertPost(postEntity));
            log.info(selectPostInfoByPno(postEntity.getPno()));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Test
    void byNullType() {
        try {
            PostEntity postEntity = PostEntity.builder()
                    .boardNo(1)
                    .type(null)
                    .writer("admin")
                    .title("Post insert test in JUnit")
                    .contents("Post insert test in JUnit...")
                    .build();
            log.info(postsMapper.insertPost(postEntity));
            log.info(selectPostInfoByPno(postEntity.getPno()));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Test
    void byNullWriter() {
        try {
            PostEntity postEntity = PostEntity.builder()
                    .boardNo(1)
                    .type(PostType.NORMAL.name())
                    .writer(null)
                    .title("Post insert test in JUnit")
                    .contents("Post insert test in JUnit...")
                    .build();
            log.info(postsMapper.insertPost(postEntity));
            log.info(selectPostInfoByPno(postEntity.getPno()));
        } catch (Exception e) {
            log.error(e.getMessage());
        }

    }

    @Test
    void byNullTitle() {
        try {
            PostEntity postEntity = PostEntity.builder()
                    .boardNo(1)
                    .type(PostType.NORMAL.name())
                    .writer("admin")
                    .title(null)
                    .contents("Post insert test in JUnit...")
                    .build();
            log.info(postsMapper.insertPost(postEntity));
            log.info(selectPostInfoByPno(postEntity.getPno()));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Test
    void byNullContents() {
        try {
            PostEntity postEntity = PostEntity.builder()
                    .boardNo(1)
                    .type(PostType.NORMAL.name())
                    .writer("admin")
                    .title("Post insert test in JUnit")
                    .contents(null)
                    .build();
            log.info(postsMapper.insertPost(postEntity));
            log.info(selectPostInfoByPno(postEntity.getPno()));
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
