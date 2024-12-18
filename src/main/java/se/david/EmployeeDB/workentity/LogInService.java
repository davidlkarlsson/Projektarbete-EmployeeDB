package se.david.EmployeeDB.workentity;

import se.david.EmployeeDB.tools.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LogInService {


    public Employee logInEmployee(String email, String password) throws SQLException {

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Employee employee = null;
        WorkRole workRole;

        try {

            conn = JDBCUtil.getConnection();

            String sql = """
                     SELECT
                     e.employee_id, e.fullname, e.email, e.password,
                     w.role_id, w.title, w.description, w.salary, w.creation_date
                     FROM employee e
                     LEFT JOIN work_role w
                     ON e.role_id = w.role_id
                     WHERE e.email = ? AND e.password = ?
                    """;

            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, email);
            pstmt.setString(2, password);

            rs = pstmt.executeQuery();

            if (rs.next()) {

                workRole = new WorkRole(

                        rs.getInt("role_id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getDouble("salary"),
                        rs.getDate("creation_date")
                );

                employee = new Employee(

                        rs.getInt("employee_id"),
                        rs.getString("fullname"),
                        rs.getString("email"),
                        rs.getString("password"),
                        workRole
                );
                

            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        finally {

            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closePreparedStatement(pstmt);
            JDBCUtil.closeConnection(conn);
        }
        
        return employee;

    }
}
