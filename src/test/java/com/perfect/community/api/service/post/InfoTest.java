package com.perfect.community.api.service.post;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("[Service] Read post")
public class InfoTest extends PostServiceTest {

    @Test
    @DisplayName("With valid post no")
    void ByValidPno() {
        log.info(getInfoByPno(1));
    }

    @Test
    @DisplayName("With invalid negative post no")
    void ByInvalidPno_negative() {
        try {
            log.info(getInfoByPno(-1));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Test
    @DisplayName("With invalid post no")
    void ByInvalidPno_positive() {
        try {
            log.info(getInfoByPno(1000));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
