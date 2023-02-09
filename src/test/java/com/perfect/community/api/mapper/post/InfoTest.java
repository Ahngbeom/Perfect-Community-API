package com.perfect.community.api.mapper.post;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("[Mapper] Post's details")
public class InfoTest extends PostMapperTest {

    @Test
    @DisplayName("Post's details")
    void ByValidPno() {
        log.info(selectPostInfoByPno(1));
    }

    @Test
    @DisplayName("By Invalid post no")
    void ByInvalidPno() {
        log.info(selectPostInfoByPno(-1));
    }
}
