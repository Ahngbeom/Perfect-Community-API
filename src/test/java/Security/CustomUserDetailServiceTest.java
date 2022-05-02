package Security;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.zerock.mapper.MemberMapper;
import org.zerock.security.CustomUserDetailService;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration({"file:web/WEB-INF/dispatcher-servlet.xml", "file:web/WEB-INF/securityContext.xml"})
class CustomUserDetailServiceTest {

    private static final Logger log = LogManager.getLogger();

    @InjectMocks
    @Autowired
    private CustomUserDetailService service;

    @Mock
    @Autowired
    private MemberMapper mapper;

    @BeforeEach
    void setUp() {
        assertNotNull(service);
        assertNotNull(mapper);
    }

    @Test
    void loadUserByUsername() {
        service.loadUserByUsername("admin");
    }
}