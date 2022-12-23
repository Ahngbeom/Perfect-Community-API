package com.perfect.community.api.controller.board;

import com.perfect.community.api.service.board.BoardService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@ExtendWith(SpringExtension.class)
@ContextConfiguration({"file:web/WEB-INF/dispatcher-servlet.xml", "file:web/WEB-INF/securityContext.xml"})
//@WebAppConfiguration
@RequiredArgsConstructor
public class BoardControllerTest {

    protected static final Logger log = LogManager.getLogger();

//    protected final WebApplicationContext ctx;
    protected MockMvc mockMvc;

    @Autowired
    protected FilterChainProxy filterChainProxy;

    @Autowired
    @InjectMocks
    protected BoardController controller;

    @Mock
    protected BoardService service;

    protected static ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).apply(springSecurity(filterChainProxy)).build();
//        mockMvc = MockMvcBuilders.webAppContextSetup(ctx).apply(springSecurity()).build();

        assertNotNull(log);
        assertNotNull(mockMvc);
        assertNotNull(controller);

        log.info("SUCCESS setup");
    }
}
