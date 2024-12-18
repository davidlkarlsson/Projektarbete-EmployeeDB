package se.david.EmployeeDB.workentity;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import se.david.EmployeeDB.tools.JDBCUtil;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class WorkRoleDAOImplTest {

    @AfterEach
    public void cleanUp() {

        Connection conn = null;
        Statement stmt = null;

        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.createStatement();

            //ta bort work_role-tabellen
            stmt.execute("DROP TABLE IF EXISTS work_role");
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            //stäng ned resurser
            JDBCUtil.closeStatement(stmt);
            JDBCUtil.closeConnection(conn);
        }
    }

    @Test
    public void testConnectionIsOk() {

        try {
            JDBCUtil.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void insertWorkRoleTest() throws SQLException {

        WorkRoleDAO dao = new WorkRoleDAOImpl();
        WorkRole workrole = new WorkRole(1, "Student", "Student at STI", 0.0, Date.valueOf("2024-12-16"));
        WorkRole fetchedWorkRole;

        try {
            dao.insertWorkRole(workrole);

            fetchedWorkRole = dao.getWorkRole(workrole.getRoleId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        assertEquals("Student", fetchedWorkRole.getTitle());
    }
}