package Security;

import com.perfect.community.api.mapper.user.UsersMapper;
import com.perfect.community.api.security.detail.CustomUserDetailService;
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

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration({"file:web/WEB-INF/applicationContext.xml", "file:web/WEB-INF/dispatcher-context.xml", "file:web/WEB-INF/security-context.xml"})
class CustomUserDetailsDetailServiceTest {

    private static final Logger log = LogManager.getLogger();

    @InjectMocks
    @Autowired
    private CustomUserDetailService service;

    @Mock
    @Autowired
    private UsersMapper mapper;

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