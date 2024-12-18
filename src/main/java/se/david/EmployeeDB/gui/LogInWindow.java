package se.david.EmployeeDB.gui;

import se.david.EmployeeDB.workentity.Employee;
import se.david.EmployeeDB.workentity.LogInService;


import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class LogInWindow extends JFrame {

    public LogInWindow() {

        // Ställ in JFrame-egenskaper
        JFrame loginFrame = new JFrame("Login");
        loginFrame.setSize(400, 300);
        loginFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Skapar panel
        JPanel panel = new JPanel(new GridLayout(4,2,10,10));

        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField(15);

        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(15);

        panel.add(emailLabel);
        panel.add(emailField);

        panel.add(passwordLabel);
        panel.add(passwordField);


        // Skapa och lägg till en knapp för att logga in

        JButton loginButton = new JButton("Login");
        panel.add(new JLabel("")); //Fyller ut tomrum
        panel.add(loginButton);

        // Lägg till panelen i fönstret

        loginFrame.add(panel);

        // Actionlistener för knappen

        loginButton.addActionListener(e -> {

            String email = emailField.getText();
            String password = passwordField.getText();

            if (email.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(loginFrame, "Email and Password are required", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                LogInService loginService = new LogInService();
                Employee employee = loginService.logInEmployee(email, password);

                if (employee != null && employee.getEmail().equals(email) && employee.getPassword().equals(password)) {
                    JOptionPane.showMessageDialog(loginFrame, "Logged in successfully. Welcome," + employee.getFullName() + "!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    showEmployeeInfo(employee);

                } else {
                    JOptionPane.showMessageDialog(loginFrame, "Invalid email or password", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(loginFrame, "An error occurred while trying to log in. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);

            }
    });

        loginFrame.setVisible(true);
        loginFrame.setLocationRelativeTo(null);

    }

    private void showEmployeeInfo(Employee employee) {

        JFrame employeeInfoFrame = new JFrame("Employee Info");
        employeeInfoFrame.setSize(400, 300);
        employeeInfoFrame.setLocationRelativeTo(null);

        JPanel infoPanel = new JPanel(new GridLayout(7, 1, 10, 10));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JLabel idLabel = new JLabel("Employee ID: " + employee.getEmployeeId());
        JLabel roleIdLabel = new JLabel("Role ID: " + employee.getWorkRole().getRoleId());
        JLabel nameLabel = new JLabel("Name: " + employee.getFullName());
        JLabel titleLabel = new JLabel("Work role title: " + employee.getWorkRole().getTitle());
        JLabel descriptionLabel = new JLabel("Description: " + employee.getWorkRole().getDescription());
        JLabel salaryLabel = new JLabel("Salary: " + employee.getWorkRole().getSalary());
        JLabel creationDateLabel = new JLabel("Creation date: " + employee.getWorkRole().getCreationDate());

        infoPanel.add(idLabel);
        infoPanel.add(roleIdLabel);
        infoPanel.add(nameLabel);
        infoPanel.add(titleLabel);
        infoPanel.add(descriptionLabel);
        infoPanel.add(salaryLabel);
        infoPanel.add(creationDateLabel);

        employeeInfoFrame.add(infoPanel);
        employeeInfoFrame.setVisible(true);
    }


    }

