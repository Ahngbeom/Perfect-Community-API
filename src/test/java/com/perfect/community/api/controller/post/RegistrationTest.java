package com.perfect.community.api.controller.post;

import com.perfect.community.api.controller.ControllerIntegrationTest;
import com.perfect.community.api.dto.post.PostDTO;
import com.perfect.community.api.dto.post.PostType;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RegistrationTest extends ControllerIntegrationTest {

    @Test
    public void registration() throws Exception {
        PostDTO postDTO = PostDTO.builder()
                .boardNo(1)
                .type(PostType.NORMAL.name())
                .title("JUNIT INTEGRATION TEST FOR POST REGISTRATION")
                .contents("JUST FOR POST REGISTRATION")
                .build();
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/post/registration")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.valueToTree(postDTO).toString()))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @WithAnonymousUser
    void ByAnonymous() throws Exception {
        PostDTO postDTO = PostDTO.builder()
                .boardNo(1)
                .type(PostType.NORMAL.name())
                .title("JUNIT INTEGRATION TEST FOR POST REGISTRATION")
                .contents("JUST FOR POST REGISTRATION")
                .build();
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/post/registration")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.valueToTree(postDTO).toString()))
                .andExpect(status().isUnauthorized())
                .andReturn();
    }

    @Test
    @WithUserDetails("admin")
    void withoutBoardNo() {
        try {
            PostDTO postDTO = PostDTO.builder()
                    .type(PostType.NORMAL.name())
                    .title("JUNIT INTEGRATION TEST FOR POST REGISTRATION")
                    .contents("JUST FOR POST REGISTRATION")
                    .build();
            mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/post/registration")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.valueToTree(postDTO).toString()))
                    .andExpect(status().isBadRequest())
                    .andReturn();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Test
    @WithUserDetails("admin")
    void invalidBoardNo() {
        try {
            PostDTO postDTO = PostDTO.builder()
                    .boardNo(-1)
                    .type(PostType.NORMAL.name())
                    .title("JUNIT INTEGRATION TEST FOR POST REGISTRATION")
                    .contents("JUST FOR POST REGISTRATION")
                    .build();
            mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/post/registration")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.valueToTree(postDTO).toString()))
                    .andExpect(status().isBadRequest())
                    .andReturn();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Test
    @WithUserDetails("admin")
    void ByNullType() {
        try {
            PostDTO postDTO = PostDTO.builder()
                    .boardNo(1)
                    .type(null)
                    .title("JUNIT INTEGRATION TEST FOR POST REGISTRATION")
                    .contents("JUST FOR POST REGISTRATION")
                    .build();
            mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/post/registration")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.valueToTree(postDTO).toString()))
                    .andExpect(status().isBadRequest())
                    .andReturn();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Test
    @WithUserDetails("admin")
    void ByNullTitle() {
        try {
            PostDTO postDTO = PostDTO.builder()
                    .boardNo(1)
                    .type(PostType.NORMAL.name())
                    .title(null)
                    .contents("JUST FOR POST REGISTRATION")
                    .build();
            mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/post/registration")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.valueToTree(postDTO).toString()))
                    .andExpect(status().isBadRequest())
                    .andReturn();
        } catch (Exception e) {
            log.error(e.getMessage());
        }

    }

    @Test
    @WithUserDetails("admin")
    void ByNullContents() {
        try {
            PostDTO postDTO = PostDTO.builder()
                    .boardNo(1)
                    .type(PostType.NORMAL.name())
                    .title("JUNIT INTEGRATION TEST FOR POST REGISTRATION")
                    .contents(null)
                    .build();
            mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/post/registration")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.valueToTree(postDTO).toString()))
                    .andExpect(status().isBadRequest())
                    .andReturn();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
