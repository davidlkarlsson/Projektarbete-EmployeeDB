package se.david.EmployeeDB.gui;

import se.david.EmployeeDB.workentity.WorkRole;
import se.david.EmployeeDB.workentity.WorkRoleDAO;
import se.david.EmployeeDB.workentity.WorkRoleDAOImpl;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;

public class InsertWindow {

    WorkRoleDAO workRoleDAO = new WorkRoleDAOImpl();


    public InsertWindow() {

        JFrame insertFrame = new JFrame("Insert");
        insertFrame.setSize(400, 300);
        insertFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(6, 2, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titleLabel = new JLabel("Title: ");
        JTextField titleField = new JTextField(15);

        JLabel descriptionLabel = new JLabel("Description: ");
        JTextField descriptionField = new JTextField(15);

        JLabel salaryLabel = new JLabel("Salary: ");
        JTextField salaryField = new JTextField(15);

        JLabel creationDateLabel = new JLabel("Creation date (yyyy/mm/dd): ");
        JTextField creationDateField = new JTextField(15);

        panel.add(titleLabel);
        panel.add(titleField);

        panel.add(descriptionLabel);
        panel.add(descriptionField);

        panel.add(salaryLabel);
        panel.add(salaryField);

        panel.add(creationDateLabel);
        panel.add(creationDateField);


        // Skapa och lägg till en knapp för att skicka data

        JButton submitButton = new JButton("SUBMIT");
        panel.add(new JLabel("")); //Fyller ut tomrum
        panel.add(submitButton);

        // Lägg till panelen i fönstret

        insertFrame.add(panel);

        // Actionlistener för knappen

        submitButton.addActionListener(e -> {

            try {

                //Hämta värden från textfälten
                String title = titleField.getText();
                String description = descriptionField.getText();
                String salaryText = salaryField.getText();
                String creationDate = creationDateField.getText();


                //Konvertera och validera data
                Date cd = null;
                if (!creationDate.isEmpty()) {

                    cd = Date.valueOf(creationDate); //Förutsätter "YYY-MM-DD" -format
                }
                double salary = Double.parseDouble(salaryText);

                WorkRole workrole = new WorkRole(title, description, salary, cd);

                workRoleDAO.insertWorkRole(workrole);

                JOptionPane.showMessageDialog(insertFrame, "Workrole added successfully");

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(insertFrame, "Something went wrong");
            }
        });

        insertFrame.setVisible(true);
        insertFrame.setLocationRelativeTo(null);

    }


}
