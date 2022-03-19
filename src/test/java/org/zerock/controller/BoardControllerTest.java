package org.zerock.controller;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:web/WEB-INF/dispatcher-servlet.xml")
@WebAppConfiguration
@RequiredArgsConstructor
class BoardControllerTest {

    private static final Logger logger = LogManager.getLogger();

    private final WebApplicationContext ctx;

    @Autowired
    private BoardController controller;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
//        mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();

        assertNotNull(logger);
        assertNotNull(controller);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void board() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/board/list"))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
        logger.info(mvcResult.getModelAndView().getModel().get("list"));
    }

    @Test
    void posts() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/board/posts")
                .param("bno", String.valueOf(1)))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
        logger.info(mvcResult.getModelAndView().getModel().get("posts"));
    }
}