package com.perfect.community.api.mapper.post;

import com.perfect.community.api.dto.post.PostDTO;
import com.perfect.community.api.dto.post.PostType;
import com.perfect.community.api.entity.post.PostEntity;
import org.junit.jupiter.api.Test;

public class RegistrationTest extends PostMapperTest {
    @Test
    void registration() {
        PostDTO postDTO = PostDTO.builder()
                .boardNo(1)
                .type(PostType.NORMAL.name())
                .writer("admin")
                .title("Post insert test in JUnit")
                .contents("Post insert test in JUnit...")
                .build();
        log.info(postsMapper.insertPost(postDTO));
        log.info(selectPostInfoByPno(postDTO.getPno()));
    }

    @Test
    void byWithoutBoardNo() {
        try {
            PostDTO postDTO = PostDTO.builder()
                    .type(PostType.NORMAL.name())
                    .writer("admin")
                    .title("Post insert test in JUnit")
                    .contents("Post insert test in JUnit...")
                    .build();
            log.info(postsMapper.insertPost(postDTO));
            log.info(selectPostInfoByPno(postDTO.getPno()));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Test
    void byNullType() {
        try {
            PostDTO postDTO = PostDTO.builder()
                    .boardNo(1)
                    .type(null)
                    .writer("admin")
                    .title("Post insert test in JUnit")
                    .contents("Post insert test in JUnit...")
                    .build();
            log.info(postsMapper.insertPost(postDTO));
            log.info(selectPostInfoByPno(postDTO.getPno()));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Test
    void byNullWriter() {
        try {
            PostDTO postDTO = PostDTO.builder()
                    .boardNo(1)
                    .type(PostType.NORMAL.name())
                    .writer(null)
                    .title("Post insert test in JUnit")
                    .contents("Post insert test in JUnit...")
                    .build();
            log.info(postsMapper.insertPost(postDTO));
            log.info(selectPostInfoByPno(postDTO.getPno()));
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
                    .writer("admin")
                    .title(null)
                    .contents("Post insert test in JUnit...")
                    .build();
            log.info(postsMapper.insertPost(postDTO));
            log.info(selectPostInfoByPno(postDTO.getPno()));
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
                    .writer("admin")
                    .title("Post insert test in JUnit")
                    .contents(null)
                    .build();
            log.info(postsMapper.insertPost(postDTO));
            log.info(selectPostInfoByPno(postDTO.getPno()));
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