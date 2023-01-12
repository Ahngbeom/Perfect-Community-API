package com.perfect.community.api.mapper.post;

import com.perfect.community.api.dto.post.PostDTO;
import com.perfect.community.api.dto.post.PostType;
import org.junit.jupiter.api.Test;

public class ModificationTest extends PostMapperTest {
    @Test
    void modification() {
        PostDTO postDTO = PostDTO.builder()
                .boardNo(1)
                .postNo(1)
                .type(PostType.NORMAL.name())
                .writer("admin")
                .title("Modification")
                .contents("Modified")
                .build();
        log.info(postsMapper.updatePost(postDTO));
        log.info(selectPostInfoByPno(1));
    }

    @Test
    void withoutPno() {
        PostDTO postDTO = PostDTO.builder()
                .boardNo(1)
                .type(PostType.NORMAL.name())
                .writer("admin")
                .title("Modification")
                .contents("Modified")
                .build();
        log.info(postsMapper.updatePost(postDTO));
        log.info(selectPostInfoByPno(1));
    }

}
