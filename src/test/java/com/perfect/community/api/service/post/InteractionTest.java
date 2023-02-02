package com.perfect.community.api.service.post;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.Rollback;

@Rollback(value = false)
@DisplayName("[Service] Post interaction")
public class InteractionTest extends PostServiceTest {

    @Test
    @DisplayName("Increase views")
    void increaseViews() {
        log.info(postInteractionService.increaseViews("admin", 1));
    }

    @Test
    @DisplayName("Increase recommend")
    void increaseRecommend() {
        log.info(postInteractionService.increaseRecommend(1));
    }

    @Test
    @DisplayName("Increase not recommend")
    void increaseNotRecommend() {
        log.info(postInteractionService.increaseNotRecommend(1));
    }
}
