package com.perfect.community.api.service.post;

import com.perfect.community.api.dto.post.PostDTO;
import com.perfect.community.api.dto.post.PostFilterDTO;
import com.perfect.community.api.service.ServiceTest;
import com.perfect.community.api.service.utils.RelocateService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.transaction.AfterTransaction;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class PostServiceTest extends ServiceTest {

    protected static final Logger log = LogManager.getLogger();

    @Autowired
    protected PostService postService;

    @Autowired
    protected PostInteractionService postInteractionService;

    @Autowired
    protected PostUtils postUtils;

    @Autowired
    private RelocateService relocateService;

    @BeforeEach
    void setUp() {
        assertNotNull(log);
        assertNotNull(postService);
        assertNotNull(postUtils);
    }

    @AfterTransaction
    void relocatePno() {
        log.info("auto_increment key: " + relocateService.relocateBoardNumbers("posts"));
    }

    public PostDTO getInfoByPno(long pno) {
        return postService.getInfoByPno(pno);
    }

    public long countPosts(PostFilterDTO options) {
        return postUtils.countPosts(options);
    }

    public boolean checkPostVerification(String userId, long pno) {
        return postUtils.checkPostVerification(userId, pno);
    }

}