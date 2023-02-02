package com.perfect.community.api.controller.post;

import com.perfect.community.api.dto.post.PostFilterDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("[Integrated Controller] Post's list")
public class ListTest extends PostControllerTest {
    @Test
    @DisplayName("By anonymous user")
    @WithAnonymousUser
    void list() throws Exception {
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/post"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @DisplayName("[Post's List] - By authenticated user")
    void listByAuthenticatedUser() throws Exception {
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/post"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @DisplayName("With options for filtering")
    void listWithOptions() throws Exception {
        String options = objectMapper.valueToTree(
                PostFilterDTO.builder()
                        .boardNo(2)
                        .build()
        ).toString();
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/post")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(options))
                .andExpect(status().isOk())
                .andReturn();
    }
}
