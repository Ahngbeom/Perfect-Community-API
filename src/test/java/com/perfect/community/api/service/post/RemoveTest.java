package com.perfect.community.api.service.post;

import org.junit.jupiter.api.Test;

public class RemoveTest extends PostServiceTest {

    @Test
    void remove() {
        postService.remove("admin", 1);
        log.info(getInfoByPno(1));
    }

    @Test
    void byInvalidPno() {
        try {
            postService.remove("admin", -1);
            log.info(getInfoByPno(-1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void byOtherUser() {
        try {
            postService.remove("adminnnnnn", 1);
            log.info(getInfoByPno(1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
