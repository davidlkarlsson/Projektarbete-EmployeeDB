package se.david.EmployeeDB.workentity;

import java.sql.SQLException;
import java.util.List;

public interface WorkRoleDAO {


    public void insertWorkRole(WorkRole workrole) throws SQLException;


    public void updateWorkRole(WorkRole workrole) throws SQLException;


    public void deleteWorkRole(WorkRole workrole) throws SQLException;


    public WorkRole getWorkRole (Integer roleId) throws SQLException;


    public List<WorkRole> getAllWorkRoles() throws SQLException;






}
