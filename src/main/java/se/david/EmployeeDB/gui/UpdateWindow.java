package se.david.EmployeeDB.gui;

import se.david.EmployeeDB.workentity.WorkRole;
import se.david.EmployeeDB.workentity.WorkRoleDAO;
import se.david.EmployeeDB.workentity.WorkRoleDAOImpl;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.sql.SQLException;

public class UpdateWindow {

    WorkRoleDAO workRoleDAO = new WorkRoleDAOImpl();

    public UpdateWindow() {

        JFrame updateFrame = new JFrame("Update");
        updateFrame.setSize(400, 300);
        updateFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(6, 2, 5, 5));

        JLabel roleIdLabel = new JLabel("Role ID:");
        JTextField roleIdTextField = new JTextField(15);

        JLabel titleLabel = new JLabel("Title: ");
        JTextField titleField = new JTextField(15);

        JLabel descriptionLabel = new JLabel("Description: ");
        JTextField descriptionField = new JTextField(15);

        JLabel salaryLabel = new JLabel("Salary: ");
        JTextField salaryField = new JTextField(15);

        JLabel creationDateLabel = new JLabel("Creation date (yyyy-mm-dd): ");
        JTextField creationDateField = new JTextField(15);

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


        // Skapa två knappar för att hämta och uppdatera data

        JButton updateButton = new JButton("UPDATE");
        JButton fetchButton = new JButton("FETCH");
        panel.add(fetchButton);
        panel.add(updateButton);


        // Lägg till panelerna i fönstret

        updateFrame.add(panel);

        // Actionlistener för Update-knappen

        updateButton.addActionListener(e -> {

            try {

                //Hämta värden från textfälten
                String roleIdText = roleIdTextField.getText();
                String title = titleField.getText();
                String description = descriptionField.getText();
                String salaryText = salaryField.getText();
                String creationDate = creationDateField.getText();


                //Konvertera och validera data
                Date cd = null;
                if (!creationDate.isEmpty()) {

                    cd = Date.valueOf(creationDate); //Förutsätter "YYY-MM-DD" -format
                }

                Integer roleId = Integer.parseInt(roleIdText);
                double salary = Double.parseDouble(salaryText);

                WorkRole workrole = new WorkRole(roleId, title, description, salary, cd);

                workRoleDAO.updateWorkRole(workrole);

                JOptionPane.showMessageDialog(updateFrame, "Workrole updated successfully!");

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(updateFrame, "Something went wrong!");
            }
        });

        fetchButton.addActionListener(e -> {

            try {

                Integer roleId = Integer.parseInt(roleIdTextField.getText());

                WorkRole workrole = workRoleDAO.getWorkRole(roleId);

                if (workrole != null) {

                    titleField.setText(workrole.getTitle());
                    descriptionField.setText(workrole.getDescription());
                    salaryField.setText(workrole.getSalary().toString());
                    creationDateField.setText(workrole.getCreationDate().toString());

                    JOptionPane.showMessageDialog(updateFrame, "Fetched data successfully!");
                }
                else {
                    JOptionPane.showMessageDialog(updateFrame, "No Work Role found with ID: " + roleId, "Error", JOptionPane.ERROR_MESSAGE);
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(updateFrame, "Invalid Role ID format. Please enter a number.", "Error", JOptionPane.ERROR_MESSAGE);

            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(updateFrame, "Database error occurred while fetching data.", "Error", JOptionPane.ERROR_MESSAGE);

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(updateFrame, "An unexpected error occurred.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        updateFrame.setVisible(true);
        updateFrame.setLocationRelativeTo(null);

    }
}
