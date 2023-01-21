package com.perfect.community.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.perfect.community.api.UtilsForTest;
import com.perfect.community.api.interceptor.AccessDeniedInterceptor;
import com.perfect.community.api.service.utils.RelocateService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@ExtendWith(SpringExtension.class)
@ContextConfiguration({
        "file:web/WEB-INF/interceptor-servlet.xml",
        "file:web/WEB-INF/securityContext.xml",
        "file:web/WEB-INF/dispatcher-servlet.xml"
})
@RequiredArgsConstructor
@Transactional
public class ControllerUnitTest {

    protected static final Logger log = LogManager.getLogger(ControllerUnitTest.class);

    protected MockMvc mockMvc;

    protected MvcResult mvcResult;
    @Autowired
    private FilterChainProxy filterChainProxy;
//    @Autowired
//    private AccessDeniedInterceptor accessDeniedInterceptor;

    @Autowired
    protected ObjectMapper objectMapper/* = new ObjectMapper().registerModule(new JavaTimeModule())*/;

    protected static final UtilsForTest utilsForTest = new UtilsForTest();

    @Autowired
    protected RelocateService relocateService;

    public void setUp(Object controller) {
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
//                .addMappedInterceptors(new String[]{"/**"}, accessDeniedInterceptor)
                .apply(springSecurity(filterChainProxy))
                .build();

        assertNotNull(log);
        assertNotNull(mockMvc);
        assertNotNull(filterChainProxy);
        assertNotNull(objectMapper);
        assertNotNull(utilsForTest);
        assertNotNull(relocateService);
        assertNotNull(controller);
    }

}
