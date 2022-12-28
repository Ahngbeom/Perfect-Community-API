package com.perfect.community.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@ExtendWith(SpringExtension.class)
@ContextConfiguration({
        "file:web/WEB-INF/interceptor-servlet.xml",
        "file:web/WEB-INF/securityContext.xml",
        "file:web/WEB-INF/dispatcher-servlet.xml"
})
@WebAppConfiguration
@RequiredArgsConstructor
@Transactional
public class ControllerIntegrationTest {

    protected static final Logger log = LogManager.getLogger(ControllerIntegrationTest.class);

    @Autowired
    protected WebApplicationContext ctx;

    protected MockMvc mockMvc;

    protected MvcResult mvcResult;
    @Autowired
    private FilterChainProxy filterChainProxy;

    protected static ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    public void setUp(Object controller) {
//        mockMvc = MockMvcBuilders.standaloneSetup(controller)
//                .addInterceptors(new AccessDeniedInterceptor())
//                .apply(springSecurity(filterChainProxy))
//                .build();
//        mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
        mockMvc = MockMvcBuilders.webAppContextSetup(ctx)
                .apply(springSecurity(filterChainProxy))
                .build();

        assertNotNull(log);
        assertNotNull(mockMvc);
        assertNotNull(controller);
    }
}
