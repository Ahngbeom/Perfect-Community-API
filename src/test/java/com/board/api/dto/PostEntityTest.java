package com.board.api.dto;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.board.api.service.post.PostService;
import com.board.api.utils.DateUtility;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:web/WEB-INF/dispatcher-servlet.xml")
class PostEntityTest {

    private static final Logger log = LogManager.getLogger();

    @Autowired
    private PostService service;

    @Autowired
    private DateUtility dateUtility;

}