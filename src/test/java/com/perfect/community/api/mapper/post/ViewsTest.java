package com.perfect.community.api.mapper.post;

import com.perfect.community.api.dto.post.PostDTO;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.Rollback;

public class ViewsTest extends PostMapperTest {

    @Test
    @Rollback(value = false)
    void increaseViews() {
        PostDTO postDTO = PostDTO.builder()
                .pno(1)
                .build();
        if (postInteractionMapper.increaseViews(postDTO) == 1) {
            log.info(postDTO.getViews());
        }
    }
}
