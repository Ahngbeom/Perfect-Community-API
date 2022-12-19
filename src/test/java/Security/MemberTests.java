package Security;

import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@ExtendWith(SpringExtension.class)
@ContextConfiguration({"file:web/WEB-INF/dispatcher-servlet.xml", "file:web/WEB-INF/securityContext.xml"})
public class MemberTests {

    private static final Logger log = LogManager.getLogger();

    @Setter(onMethod_ = @Autowired)
    private PasswordEncoder pwEncoder;

    @Setter(onMethod_ = @Autowired)
    private DataSource  ds;

    @Test
    public void testInsertMember() {

        String sql = "insert into users(user_id, password, username) values(?,?,?)";

        for (int i = 0; i < 10; i++) {
            Connection con = null;
            PreparedStatement preparedStatement = null;

            try {
                con = ds.getConnection();
                preparedStatement = con.prepareStatement(sql);

//                if (i < 10) {
                    preparedStatement.setString(1, "user" + i);
                    preparedStatement.setString(2, pwEncoder.encode("user" + i));
                    preparedStatement.setString(3, "user_" + i);
//                }
//                else {
//                    preparedStatement.setString(1, "admin");
//                    preparedStatement.setString(2, pwEncoder.encode("admin"));
//                    preparedStatement.setString(3, "Administrator");
//                }
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (preparedStatement != null) {
                    try {
                        preparedStatement.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (con != null) {
                    try {
                        con.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }

    @Test
    public void testInsertAuth() {
        String sql = "insert into users_authorities(user_id, authority) values(?,?)";

        for (int i = 0; i < 10; i++) {
            Connection con = null;
            PreparedStatement preparedStatement = null;

            try {
                con = ds.getConnection();
                preparedStatement = con.prepareStatement(sql);

                if (i < 9) {
                    preparedStatement.setString(1, "user" + i);
                    preparedStatement.setString(2, "ROLE_USER");
                }
                else {
                    preparedStatement.setString(1, "admin");
                    preparedStatement.setString(2, "ROLE_ADMIN");
                }
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (preparedStatement != null) {
                    try {
                        preparedStatement.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (con != null) {
                    try {
                        con.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}

