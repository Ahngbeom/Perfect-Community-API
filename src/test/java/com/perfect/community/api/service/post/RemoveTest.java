package com.perfect.community.api.service.post;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("[Service] Post's Remove")
public class RemoveTest extends PostServiceTest {

    @Test
    @DisplayName("Remove")
    void remove() {
        postService.remove(1);
        log.info(getInfoByPno(1));
    }

    @Test
    @DisplayName("Invalid post no")
    void byInvalidPno() {
        try {
            postService.remove(-1);
            log.info(getInfoByPno(-1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
