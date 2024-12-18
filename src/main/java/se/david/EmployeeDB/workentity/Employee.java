package se.david.EmployeeDB.workentity;

public class Employee {

    private Integer employeeId;
    private String fullName;
    private String email;
    private String password;
    private WorkRole workRole;

    public Employee(Integer employeeId, String fullName, String email, String password, WorkRole workRole) {

        this.employeeId = employeeId;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.workRole = workRole;
    }

    public Employee(String fullName, String email, String password, WorkRole workRole) {

        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.workRole = workRole;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public WorkRole getWorkRole() {
        return workRole;
    }

    public void setWorkRole(WorkRole workRole) {
        this.workRole = workRole;
    }
}
