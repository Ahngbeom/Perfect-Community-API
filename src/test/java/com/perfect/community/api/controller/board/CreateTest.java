package com.perfect.community.api.controller.board;

import com.perfect.community.api.dto.board.BoardDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("[Integrated Controller] Create board")
class CreateTest extends BoardControllerTest {

    String requestBody;

    @Test
    @DisplayName("By administrator")
    @WithUserDetails("admin")
    void createBoard() throws Exception {
        requestBody = objectMapper.valueToTree(
                BoardDTO.builder()
                        .title(utilsForTest.generateRandomTitle())
                        .comment("Creating a board for testing in JUNIT")
                        .build()
        ).toString();
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/board")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @DisplayName("By anonymous user")
    @WithAnonymousUser
    void createBoardWithAnonymous() throws Exception {
        requestBody = objectMapper.valueToTree(
                BoardDTO.builder()
                        .title(utilsForTest.generateRandomTitle())
                        .comment("Creating a board for testing in JUNIT")
                        .build()
        ).toString();
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/board")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isUnauthorized())
                .andReturn();
    }

    @Test
    @DisplayName("If title is null")
    @WithUserDetails("admin")
    void createBoardWithNullTitle() throws Exception {
        requestBody = objectMapper.valueToTree(
                BoardDTO.builder()
                        .title(null)
                        .comment("Creating a board for testing in JUNIT")
                        .build()
        ).toString();
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/board")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    @DisplayName("By unauthorized user")
    @WithUserDetails("tester")
    void createBoardWithNotAdmin() throws Exception {
        requestBody = objectMapper.valueToTree(
                BoardDTO.builder()
                        .title(utilsForTest.generateRandomTitle())
                        .comment("Creating a board for testing in JUNIT")
                        .build()
        ).toString();
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/board")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isUnauthorized())
                .andReturn();
        log.error(mvcResult.getResponse().getErrorMessage());
    }

}