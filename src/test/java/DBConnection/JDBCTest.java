package DBConnection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCTest {

    private static final Logger logger = LogManager.getLogger();

    @Test
    void JDBCTester() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/basicspringmvc", "root", "1234");
            logger.info(conn);
        } catch (Exception e) {
            logger.error(e);
        }
    }

}
