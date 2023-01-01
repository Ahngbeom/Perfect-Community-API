package com.perfect.community.api.service.post;

import org.junit.jupiter.api.Test;

public class InfoTest extends PostServiceTest {

    @Test
    void ByValidPno() {
        log.info(getInfoByPno(1));
    }

    @Test
    void ByInvalidPno_negative() {
        try {
            log.info(getInfoByPno(-1));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Test
    void ByInvalidPno_positive() {
        try {
            log.info(getInfoByPno(1000));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
