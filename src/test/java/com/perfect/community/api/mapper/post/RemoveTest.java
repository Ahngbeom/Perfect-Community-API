package com.perfect.community.api.mapper.post;

import org.junit.jupiter.api.Test;

public class RemoveTest extends PostMapperTest {

    @Test
    void remove() {
        log.info(postsMapper.deletePost(1));
        log.info(selectPostInfoByPno(1));
    }

    @Test
    void invalidPno() {
        log.info(postsMapper.deletePost(-1));
        log.info(selectPostInfoByPno(-1));
    }

}
