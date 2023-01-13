package com.perfect.community.api.controller.post;

import com.fasterxml.jackson.core.type.TypeReference;
import com.perfect.community.api.controller.ControllerIntegrationTest;
import com.perfect.community.api.dto.post.PostDTO;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PostControllerTest extends ControllerIntegrationTest {

    @Autowired
    protected PostController controller;

    @BeforeEach
    void setUp() {
        setUpWithController(controller);
    }

    @AfterTransaction
    void relocateBoardNumbers() {
        log.info("auto_increment key value: " + relocateService.relocateBoardNumbers("posts"));
    }

    public PostDTO getPostInfo(long pno) throws Exception {
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/post/" + pno))
                .andExpect(status().isOk())
                .andReturn();
        if (mvcResult.getResponse().getContentAsString().isEmpty())
            return null;
        return objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<PostDTO>(){});
    }

}