package se.david.EmployeeDB.gui;

import se.david.EmployeeDB.workentity.WorkRole;
import se.david.EmployeeDB.workentity.WorkRoleDAO;
import se.david.EmployeeDB.workentity.WorkRoleDAOImpl;

import javax.swing.*;
import java.awt.*;



public class GetWorkRoleWindow {

    WorkRoleDAO workRoleDAO = new WorkRoleDAOImpl();

    public GetWorkRoleWindow() {

        JFrame getWorkRoleFrame = new JFrame("Fetch Work Role");
        getWorkRoleFrame.setSize(400, 300);
        getWorkRoleFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(6, 2, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel roleIdLabel = new JLabel("Role ID:");
        JTextField roleIdTextField = new JTextField(15);

        JLabel titleLabel = new JLabel("Title: ");
        JTextField titleField = new JTextField(15);
        titleField.setEditable(false);

        JLabel descriptionLabel = new JLabel("Description: ");
        JTextField descriptionField = new JTextField(15);
        descriptionField.setEditable(false);

        JLabel salaryLabel = new JLabel("Salary: ");
        JTextField salaryField = new JTextField(15);
        salaryField.setEditable(false);

        JLabel creationDateLabel = new JLabel("Creation date (yyyy-mm-dd): ");
        JTextField creationDateField = new JTextField(15);
        creationDateField.setEditable(false);

        panel.add(roleIdLabel);
        panel.add(roleIdTextField);

        panel.add(titleLabel);
        panel.add(titleField);

        panel.add(descriptionLabel);
        panel.add(descriptionField);

        panel.add(salaryLabel);
        panel.add(salaryField);

        panel.add(creationDateLabel);
        panel.add(creationDateField);

        // Skapa och lägg till en knapp för att hämta data

        JButton fetchButton = new JButton("Fetch Data");
        panel.add(new JLabel("")); //Fyller ut tomrum
        panel.add(fetchButton);

        // Lägg till panelen i fönstret

        getWorkRoleFrame.add(panel);



        fetchButton.addActionListener(e -> {

            String roleIdText = roleIdTextField.getText();

            if (roleIdText.isEmpty()) {
                JOptionPane.showMessageDialog(getWorkRoleFrame, "Please enter a role ID", "Error", JOptionPane.ERROR_MESSAGE);
            }

            try {

                Integer roleId = Integer.parseInt(roleIdText);
                WorkRole workrole = workRoleDAO.getWorkRole(roleId);

                if (workrole != null) {

                    titleField.setText(workrole.getTitle());
                    descriptionField.setText(workrole.getDescription());
                    salaryField.setText(workrole.getSalary().toString());
                    creationDateField.setText(workrole.getCreationDate().toString());

                } else {
                    JOptionPane.showMessageDialog(getWorkRoleFrame, "No Work Role found with ID: " + roleId, "Not Found", JOptionPane.WARNING_MESSAGE);
                }
            }

            catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(getWorkRoleFrame, "Role ID must be a number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            catch (Exception ex) {
                JOptionPane.showMessageDialog(getWorkRoleFrame, "An error occurred while fetching data.", "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }

        });

        getWorkRoleFrame.setVisible(true);
        getWorkRoleFrame.setLocationRelativeTo(null);



    }
}
