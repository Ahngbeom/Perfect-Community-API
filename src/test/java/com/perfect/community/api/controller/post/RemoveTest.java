package com.perfect.community.api.controller.post;

import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

public class RemoveTest extends PostControllerTest {
    @Test
    @WithUserDetails("admin")
    void remove() throws Exception {
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/api/post/remove/1")).andReturn();
        log.info("The post requested to be deleted is now " + getPostInfo(Long.parseLong(mvcResult.getResponse().getContentAsString())));
    }

    @Test
    @WithAnonymousUser
    void byAnonymous() throws Exception {
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/api/post/remove/1")).andReturn();
    }

    @Test
    @WithUserDetails("tester1")
    void byOtherUser() throws Exception {
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/api/post/remove/1")).andReturn();
    }

    @Test
    @WithUserDetails("admin")
    void byInvalidPno() throws Exception {
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/api/post/remove/-1")).andReturn();
    }
}
