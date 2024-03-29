package com.perfect.community.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.perfect.community.api.service.utils.RelocateService;
import dummy.UtilsForTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@ExtendWith(SpringExtension.class)
@ContextConfiguration({
        "file:web/WEB-INF/applicationContext.xml", "file:web/WEB-INF/dispatcher-context.xml", "file:web/WEB-INF/security-context.xml"
})
@WebAppConfiguration
@Transactional
@WithUserDetails(value = "admin")
public class ControllerIntegrationTest {

    protected static final Logger log = LogManager.getLogger(ControllerIntegrationTest.class);

    @Autowired
    protected WebApplicationContext ctx;

    protected MockMvc mockMvc;

    @Autowired
    protected FilterChainProxy filterChainProxy;

    protected MockHttpSession mockHttpSession;
    protected MockHttpServletRequest mockHttpServletRequest;

    protected MvcResult mvcResult;

    @Autowired
    protected ObjectMapper objectMapper/* = new ObjectMapper().registerModule(new JavaTimeModule())*/;

    protected static final UtilsForTest utilsForTest = new UtilsForTest();

    @Autowired
    protected RelocateService relocateService;

    @BeforeEach
    public void setUpWithWebAppCtx() {
        mockMvc = MockMvcBuilders.webAppContextSetup(ctx)
                .apply(springSecurity(filterChainProxy))
                .build();

        assertNotNull(log);
        assertNotNull(ctx);
        assertNotNull(mockMvc);
        assertNotNull(filterChainProxy);
        assertNotNull(objectMapper);
        assertNotNull(utilsForTest);
        assertNotNull(relocateService);
    }

    @AfterEach
    void checkMvcResult() throws UnsupportedEncodingException {
        if (mvcResult != null) {
            HttpStatus status = HttpStatus.resolve(mvcResult.getResponse().getStatus());
            assert status != null;
            if (status.is2xxSuccessful()) {
                log.info("[" + status + "] ResponseBody: " + mvcResult.getResponse().getContentAsString());
            } else if (status.is3xxRedirection()) {
                log.warn("[" + status + "] ResponseBody: " + mvcResult.getResponse().getContentAsString()
                        + "\n[" + status + "] Servlet Error: " + mvcResult.getResponse().getErrorMessage());
            } else {
                log.error("[" + status + "] ResponseBody: " + mvcResult.getResponse().getContentAsString()
                        + "\n [" + status + "] Servlet Error: " + mvcResult.getResponse().getErrorMessage());
            }
            log.info("Header's Authorization = {}", mvcResult.getResponse().getHeader("Authorization"));
        }
    }

    public ResultActions request(HttpMethod httpMethod, String requestURI) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.request(httpMethod, requestURI));
    }
}
