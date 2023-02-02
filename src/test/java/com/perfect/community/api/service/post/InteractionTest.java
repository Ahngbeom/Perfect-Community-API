package com.perfect.community.api.service.post;

import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.Rollback;

@Rollback(value = false)
public class InteractionTest extends PostServiceTest {

    @Test
    void increaseViews() {
        log.info(postInteractionService.increaseViews("admin", 1));
    }

    @Test
    void increaseRecommend() {
        log.info(postInteractionService.increaseRecommend(1));
    }

    @Test
    void increaseNotRecommend() {
        log.info(postInteractionService.increaseNotRecommend(1));
    }
}
