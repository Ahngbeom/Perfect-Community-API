package com.board.api.controller.board;

import com.board.api.dto.board.BoardDTO;
import com.board.api.service.utils.RelocateTablePrimaryKeyService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration({"file:web/WEB-INF/dispatcher-servlet.xml", "file:web/WEB-INF/securityContext.xml"})
@WebAppConfiguration
@RequiredArgsConstructor
class BoardControllerTest {

    private static final Logger log = LogManager.getLogger();

    private final WebApplicationContext ctx;

    @Autowired
    private FilterChainProxy springSecurityFilterChain;

    @Autowired
//    @InjectMocks
    private BoardController controller;

//    @Mock
//    private BoardService service;

    @Autowired
    private RelocateTablePrimaryKeyService relocateService;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).apply(springSecurity(springSecurityFilterChain)).build();
//        mockMvc = MockMvcBuilders.webAppContextSetup(ctx).apply(springSecurity()).build();

        assertNotNull(log);
        assertNotNull(mockMvc);
        assertNotNull(controller);
        assertNotNull(relocateService);
    }

    @AfterEach
    void tearDown() {
        relocateService.RelocateTablePK("boards");
    }

    @Test
    void getBoardList() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/board/list"))
                .andExpect(status().isOk())
//                .andDo(print())
                .andReturn();
        log.info(mvcResult.getResponse().getContentAsString());
    }

    @Test
    void getBoardInfo() {
    }

    @Test
    @WithUserDetails("admin")
    void createBoardWithNullTitle() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.valueToTree(
                BoardDTO.builder()
                        .title(null) // Board title must not be null
                        .build()
        ).toString();
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
        ObjectMapper objectMapper = new ObjectMapper();
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
    void createBoard() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.valueToTree(
                BoardDTO.builder()
                        .title("Creating a board for testing in JUNIT")
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

    @Test
    void updateBoard() {
    }

    @Test
    void deleteBoard() {
    }
}