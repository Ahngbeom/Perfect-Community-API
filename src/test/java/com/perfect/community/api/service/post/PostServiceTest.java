package com.perfect.community.api.service.post;

import com.perfect.community.api.dto.board.BoardDTO;
import com.perfect.community.api.dto.post.PostDTO;
import com.perfect.community.api.dto.post.PostExtractionDTO;
import com.perfect.community.api.dto.post.PostListOptDTO;
import com.perfect.community.api.service.utils.RelocateService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration({
        "file:web/WEB-INF/dispatcher-servlet.xml",
        "file:web/WEB-INF/securityContext.xml",
        "file:web/WEB-INF/interceptor-servlet.xml",
})
@Transactional
class PostServiceTest {

    protected static final Logger log = LogManager.getLogger();

    @Autowired
    protected PostService postService;

    @Autowired
    protected PostJoinUserService postJoinUserService;

    @Autowired
    protected PostUtils postUtils;

    @Autowired
    private RelocateService relocateService;

    @BeforeEach
    void setUp() {
        assertNotNull(log);
        assertNotNull(postService);
        assertNotNull(postJoinUserService);
        assertNotNull(postUtils);
    }

    @AfterTransaction
    void relocatePno() {
        log.info("auto_increment key: " + relocateService.relocateBoardNumbers("posts"));
    }

    public PostDTO getInfoByPno(long pno) {
        return postService.getInfoByPno(pno);
    }

    public long countPosts(PostExtractionDTO.List options) {
        return postUtils.countPosts(options);
    }

    public boolean checkPostVerification(String userId, long pno) {
        return postUtils.checkPostVerification(userId, pno);
    }

}