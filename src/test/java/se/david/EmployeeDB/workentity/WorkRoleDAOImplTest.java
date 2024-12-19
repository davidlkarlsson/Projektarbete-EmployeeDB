package se.david.EmployeeDB.workentity;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import se.david.EmployeeDB.tools.JDBCUtil;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class WorkRoleDAOImplTest {

    private Connection connection;

    @BeforeEach
    public void setUp() throws SQLException {
        connection = JDBCUtil.getConnection();

        Statement stmt = connection.createStatement();

        String createTableSQL = """
            CREATE TABLE IF NOT EXISTS work_role (
                role_id         INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY,
                title           VARCHAR(20) NOT NULL,
                description     VARCHAR(20) NOT NULL,
                salary          DOUBLE NOT NULL,
                creation_date   DATE NOT NULL,
                PRIMARY KEY (role_id)
            )
        """;
        stmt.execute(createTableSQL);
        JDBCUtil.closeStatement(stmt);
    }

    @AfterEach
    public void cleanUp() {
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            stmt.execute("DELETE FROM work_role");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeStatement(stmt);
        }
    }

    @Test
    public void testConnectionIsOk() {
        try {
            Connection conn = JDBCUtil.getConnection();
            Assertions.assertNotNull(conn, "Connection should not be null.");
        } catch (SQLException e) {
            Assertions.fail("Database connection failed.", e);
        }
    }

    @Test
    public void insertAndFetchWorkRoleTest() {

        WorkRoleDAO dao = new WorkRoleDAOImpl();
        WorkRole workRole = new WorkRole("Student", "Student at STI", 0.0, Date.valueOf("2024-12-16"));

        try {
            dao.insertWorkRole(workRole);

            List<WorkRole> allWorkRoles = dao.getAllWorkRoles();
            assertEquals(1, allWorkRoles.size());

            WorkRole fetchedWorkRole = allWorkRoles.get(0);

            assertEquals("Student", fetchedWorkRole.getTitle());
            assertEquals("Student at STI", fetchedWorkRole.getDescription());
            assertEquals(0.0, fetchedWorkRole.getSalary());
            assertEquals(Date.valueOf("2024-12-16"), fetchedWorkRole.getCreationDate());
        } catch (SQLException e) {
            Assertions.fail("Test failed due to SQLException.", e);
        }
    }
}
