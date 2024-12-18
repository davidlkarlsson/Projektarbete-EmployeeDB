package se.david.EmployeeDB.gui;


import se.david.EmployeeDB.workentity.WorkRoleDAO;
import se.david.EmployeeDB.workentity.WorkRoleDAOImpl;
import javax.swing.*;
import java.awt.*;


public class DeleteWindow {

    WorkRoleDAO workRoleDAO = new WorkRoleDAOImpl();

    public DeleteWindow() {

        JFrame deleteFrame = new JFrame("Delete");
        deleteFrame.setSize(400, 300);
        deleteFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(2, 3, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(100, 10, 100, 10));

        JLabel roleIdLabel = new JLabel("Role ID: ");
        JTextField roleIdField = new JTextField(15);

        panel.add(roleIdLabel);
        panel.add(roleIdField);

        // Skapa och lägg till en knapp för att skicka data

        JButton deleteButton = new JButton("DELETE");
        panel.add(new JLabel(""));
        panel.add(deleteButton);

        // Lägg till panelen i fönstret

        deleteFrame.add(panel);

        deleteButton.addActionListener(e -> {

            try {

                Integer roleId = Integer.parseInt(roleIdField.getText());
                workRoleDAO.deleteWorkRole(workRoleDAO.getWorkRole(roleId));
                JOptionPane.showMessageDialog(deleteFrame, "Workrole deleted successfully");

            } catch (Exception ex){
                ex.printStackTrace();
                JOptionPane.showMessageDialog(deleteFrame, "Something went wrong");
            }
        });

        deleteFrame.setVisible(true);
        deleteFrame.setLocationRelativeTo(null);


    }
}


