package Security;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration({"file:web/WEB-INF/dispatcher-servlet.xml", "file:web/WEB-INF/securityContext.xml"})
@WebAppConfiguration
@RequiredArgsConstructor
public class LoginTests {

    private static final Logger log = LogManager.getLogger();

    private final WebApplicationContext ctx;

//    @InjectMocks
//    @Autowired
//    private MemberController controller;
//
//    @Mock
//    private UserDetailsService service;

    private MockMvc mockMvc;

//    private UserDetails userDetails;

    @BeforeEach
    void setUp() {
//        mockMvc = MockMvcBuilders.standaloneSetup(controller).apply(springSecurity()).build();
        mockMvc = MockMvcBuilders.webAppContextSetup(ctx).apply(springSecurity()).build();

//        userDetails = service.loadUserByUsername("admin");

//        assertNotNull(controller);
//        assertNotNull(service);
    }

    @Test
    void info() {
    }

    @Test
    void login() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/login")
                        .param("username", "admin")
                        .param("password", "admin"))
                .andExpect(status().is3xxRedirection())
                .andReturn();
        log.warn("Type: " + mvcResult.getRequest().getAttribute("type"));
        log.warn("State: " + mvcResult.getRequest().getAttribute("state"));
    }
}
