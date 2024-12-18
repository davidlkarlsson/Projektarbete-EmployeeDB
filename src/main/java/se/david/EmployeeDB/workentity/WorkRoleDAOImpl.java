package se.david.EmployeeDB.workentity;

import se.david.EmployeeDB.tools.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WorkRoleDAOImpl implements WorkRoleDAO {


    @Override
    public void insertWorkRole(WorkRole workrole) throws SQLException {

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // Anslut till databasen
            conn = JDBCUtil.getConnection();

            String sql = "INSERT INTO WORK_ROLE (TITLE, DESCRIPTION, SALARY, CREATION_DATE) VALUES (?, ?, ?, ?)";

            //Skapa ett preparedstatement
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, workrole.getTitle());
            pstmt.setString(2, workrole.getDescription());
            pstmt.setDouble(3, workrole.getSalary());
            if (workrole.getCreationDate() != null) {
                pstmt.setDate(4, workrole.getCreationDate()); // Sätter datum om det finns
            } else {
                pstmt.setNull(4, java.sql.Types.DATE); // Sätter null om datum saknas
            }

            int rowsAfected = pstmt.executeUpdate();
            System.out.println("Inserted rows: " + rowsAfected);
            JDBCUtil.commit(conn);


        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            JDBCUtil.closePreparedStatement(pstmt);
            JDBCUtil.closeConnection(conn);
        }

    }

    @Override
    public void updateWorkRole(WorkRole workrole) throws SQLException {

        Connection conn = null;
        PreparedStatement pstmt = null;


        try {
            // Anslut till databasen
            conn = JDBCUtil.getConnection();

            String sql = "UPDATE WORK_ROLE SET TITLE = ?, DESCRIPTION = ?, SALARY = ?, CREATION_DATE = ? WHERE ROLE_ID = ?";

            //Skapa ett preparedstatement
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, workrole.getTitle());
            pstmt.setString(2, workrole.getDescription());
            pstmt.setDouble(3, workrole.getSalary());
            if (workrole.getCreationDate() != null) {
                pstmt.setDate(4, workrole.getCreationDate()); // Sätter datum om det finns
            } else {
                pstmt.setNull(4, java.sql.Types.DATE); // Sätter null om datum saknas
            }
            pstmt.setInt(5, workrole.getRoleId());

            int rowsAffected = pstmt.executeUpdate();

            JDBCUtil.commit(conn);

            if (rowsAffected == 0) {
                System.out.println("No work role matching ID: " + workrole.getRoleId() + " found");
            } else {
                System.out.println("Updating work role with ID: " + workrole.getRoleId());
                System.out.println("Rows affected: " + rowsAffected);
            }


        } catch (SQLException e) {
            e.printStackTrace();
            throw e;

        } finally {
            JDBCUtil.closePreparedStatement(pstmt);
            JDBCUtil.closeConnection(conn);
        }

    }

    @Override
    public void deleteWorkRole(WorkRole workrole) throws SQLException {

        Connection conn = null;
        PreparedStatement pstmt = null;


        try {
            conn = JDBCUtil.getConnection();

            String sql = "DELETE FROM WORK_ROLE WHERE ROLE_ID = ?";

            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, workrole.getRoleId());

            int rowsAffected = pstmt.executeUpdate();

            JDBCUtil.commit(conn);

            if (rowsAffected == 0) {
                System.out.println("No user with matching ID " + workrole.getRoleId()  + " found");
            } else {
                System.out.println("Deleting workrole: " + workrole.getTitle() + " with ID " + workrole.getRoleId());
                System.out.println("Rows affected: " + rowsAffected);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;

        } finally {
            JDBCUtil.closePreparedStatement(pstmt);
            JDBCUtil.closeConnection(conn);
        }

    }

    @Override
    public WorkRole getWorkRole(Integer roleId) throws SQLException {

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        WorkRole workrole = null;

        try {

            conn = JDBCUtil.getConnection();

            String sql = "SELECT * FROM WORK_ROLE WHERE ROLE_ID = ?";

            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, roleId);

            rs = pstmt.executeQuery();

            if (rs.next()) {

                workrole = new WorkRole(
                        rs.getInt("ROLE_ID"),
                        rs.getString("TITLE"),
                        rs.getString("DESCRIPTION"),
                        rs.getDouble("SALARY"),
                        rs.getDate("CREATION_DATE")
                );

            } else {
                System.out.println("No user with matching ID " + roleId + " was found.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;

        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closePreparedStatement(pstmt);
            JDBCUtil.closeConnection(conn);
        }
        return workrole;

    }
    @Override
    public List<WorkRole> getAllWorkRoles() throws SQLException {

        List <WorkRole> workRoles = new ArrayList<>();

        Connection conn = null;
        PreparedStatement pStmt = null;
        ResultSet resultSet = null;
        WorkRole workrole;

        try {

            conn = JDBCUtil.getConnection();

            String sql = "SELECT * FROM WORK_ROLE";

            pStmt = conn.prepareStatement(sql);

            resultSet = pStmt.executeQuery();


            while (resultSet.next()) {

                workrole = new WorkRole(
                        resultSet.getInt("ROLE_ID"),
                        resultSet.getString("TITLE"),
                        resultSet.getString("DESCRIPTION"),
                        resultSet.getDouble("SALARY"),
                        resultSet.getDate("CREATION_DATE")
                );

                workRoles.add(workrole);
            }


        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            JDBCUtil.closeResultSet(resultSet);
            JDBCUtil.closePreparedStatement(pStmt);
            JDBCUtil.closeConnection(conn);
        }
        return workRoles;


    }
}
