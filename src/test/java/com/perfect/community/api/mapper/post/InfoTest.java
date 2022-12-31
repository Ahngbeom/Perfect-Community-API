package com.perfect.community.api.mapper.post;

import org.junit.jupiter.api.Test;

public class InfoTest extends PostMapperTest {

    @Test
    void ByValidPno() {
        log.info(selectPostInfoByPno(1));
    }

    @Test
    void ByInvalidPno() {
        log.info(selectPostInfoByPno(-1));
    }
}
