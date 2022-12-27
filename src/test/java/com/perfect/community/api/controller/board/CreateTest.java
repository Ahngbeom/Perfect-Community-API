package com.perfect.community.api.controller.board;

import com.perfect.community.api.dto.board.BoardDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Objects;
import java.util.Random;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CreateTest extends BoardControllerTest {

    String randomTitle;
    @BeforeEach
    void generateRandomTitle() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        randomTitle = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

    }

    @Test
    @WithUserDetails("admin")
    void createBoardWithNullTitle() throws Exception {
        String requestBody;
        try {
            requestBody = objectMapper.valueToTree(
                    BoardDTO.builder()
                            .title(null)
                            .comment("Creating a board for testing in JUNIT")
                            .build()
            ).toString();
        } catch (Exception e) {
            log.error(e.getMessage());
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
    void createBoardWithNotAdmin() throws Exception {
        String requestBody = objectMapper.valueToTree(
                BoardDTO.builder()
                        .title(randomTitle)
                        .comment("Creating a board for testing in JUNIT")
                        .build()
        ).toString();
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/board/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andDo(print())
                .andExpect(status().isUnauthorized())
                .andReturn();
        log.error(mvcResult.getResponse().getContentAsString());
    }

    @Test
    @WithAnonymousUser
    void createBoardWithAnonymous() throws Exception {
        String requestBody = objectMapper.valueToTree(
                BoardDTO.builder()
                        .title(randomTitle)
                        .comment("Creating a board for testing in JUNIT")
                        .build()
        ).toString();
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/board/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andDo(print())
                .andExpect(status().isUnauthorized())
                .andReturn();
        log.error(mvcResult.getResponse().getContentAsString());
    }

    @Test
    @WithUserDetails("admin")
    void createBoard() throws Exception {
        String requestBody = objectMapper.valueToTree(
                BoardDTO.builder()
                        .title(randomTitle)
                        .comment("Creating a board for testing in JUNIT")
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