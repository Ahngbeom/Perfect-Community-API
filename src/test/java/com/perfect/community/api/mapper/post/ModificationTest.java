package com.perfect.community.api.mapper.post;

import com.perfect.community.api.dto.post.PostType;
import com.perfect.community.api.entity.post.PostEntity;
import org.junit.jupiter.api.Test;

public class ModificationTest extends PostMapperTest {
    @Test
    void modification() {
        PostEntity postEntity = PostEntity.builder()
                .boardNo(1)
                .pno(1)
                .type(PostType.NORMAL.name())
                .writer("admin")
                .title("Modification")
                .contents("Modified")
                .build();
        log.info(postsMapper.updatePost(postEntity));
        log.info(selectPostInfoByPno(1));
    }

    @Test
    void withoutPno() {
        PostEntity postEntity = PostEntity.builder()
                .boardNo(1)
                .type(PostType.NORMAL.name())
                .writer("admin")
                .title("Modification")
                .contents("Modified")
                .build();
        log.info(postsMapper.updatePost(postEntity));
        log.info(selectPostInfoByPno(1));
    }

}
