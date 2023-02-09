package com.perfect.community.api.mapper.post;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("[Mapper] Post's delete")
public class RemoveTest extends PostMapperTest {

    @Test
    @DisplayName("Delete")
    void delete() {
        log.info(postsMapper.deletePost(1));
        log.info(selectPostInfoByPno(1));
    }

    @Test
    @DisplayName("Invalid post no")
    void invalidPno() {
        log.info(postsMapper.deletePost(-1));
    }

}
