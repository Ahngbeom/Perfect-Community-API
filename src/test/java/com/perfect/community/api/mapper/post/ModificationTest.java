package com.perfect.community.api.mapper.post;

import com.perfect.community.api.dto.post.PostType;
import com.perfect.community.api.vo.post.PostVO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ModificationTest extends PostMapperTest {
    @Test
    @DisplayName("Modification")
    void modification() {
        PostVO postVO = PostVO.builder()
                .board_no(1)
                .post_no(1)
                .type(PostType.NORMAL.name())
                .writer("admin")
                .title("Modification")
                .contents("Modified")
                .build();
        log.info(postsMapper.updatePost(postVO));
        log.info(selectPostInfoByPno(1));
    }

    @Test
    @DisplayName("Modification without post no")
    void withoutPno() {
        PostVO postVO = PostVO.builder()
                .board_no(1)
                .type(PostType.NORMAL.name())
                .writer("admin")
                .title("Modification")
                .contents("Modified")
                .build();
        log.info(postsMapper.updatePost(postVO));
        log.info(selectPostInfoByPno(1));
    }

}
