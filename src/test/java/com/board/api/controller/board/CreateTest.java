package com.board.api.controller.board;

import com.board.api.dto.board.BoardDTO;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Objects;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CreateTest extends BoardControllerTest {

    @Test
    @WithUserDetails("admin")
    @Order(1)
    void createBoardWithNullTitle() throws Exception {
        String requestBody;
        try {
            requestBody = objectMapper.valueToTree(
                    BoardDTO.builder()
                            .title(null)
                            .build()
            ).toString();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/board/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();
        log.error(Objects.requireNonNull(mvcResult.getResolvedException()).getMessage());
    }

    @Test
    @WithUserDetails("tester1")
    @Order(2)
    void createBoardWithNotAdmin() throws Exception {
        String requestBody = objectMapper.valueToTree(
                BoardDTO.builder()
                        .title("Creating a board for testing in JUNIT")
                        .build()
        ).toString();
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/board/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andDo(print())
                .andExpect(status().isUnauthorized())
                .andReturn();
        log.info(mvcResult.getResponse().getContentAsString());
    }

    @Test
    @WithUserDetails("admin")
    @Order(3)
    void createBoard() throws Exception {
        String requestBody = objectMapper.valueToTree(
                BoardDTO.builder()
                        .title("Creating a board for testing in JUNIT 3")
                        .build()
        ).toString();
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/board/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        log.info(mvcResult.getResponse().getContentAsString());
    }
}