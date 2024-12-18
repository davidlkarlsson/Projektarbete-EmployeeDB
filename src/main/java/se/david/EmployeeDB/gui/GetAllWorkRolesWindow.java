package se.david.EmployeeDB.gui;

import se.david.EmployeeDB.workentity.WorkRole;
import se.david.EmployeeDB.workentity.WorkRoleDAO;
import se.david.EmployeeDB.workentity.WorkRoleDAOImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;


public class GetAllWorkRolesWindow {

    WorkRoleDAO workRoleDAO = new WorkRoleDAOImpl();

    public GetAllWorkRolesWindow() {


            JFrame getAllWorkRolesFrame = new JFrame("Fetch All Work Roles");
            getAllWorkRolesFrame.setSize(1000, 400);
            getAllWorkRolesFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


            //Skapa en tabell för att visa data

            String [] allColumnNames = {"Role ID", "Title", "Description", "Salary", "Creation Date"};
            DefaultTableModel tableModel = new DefaultTableModel(allColumnNames, 0);
            JTable table = new JTable(tableModel);

            // Hämtar alla work roles från databasen

        try {

            //getAllWorkRoles returnerar en lista

            List<WorkRole> workRolesList = workRoleDAO.getAllWorkRoles();


            // Lägg till data i tabellen

            for (WorkRole workRole : workRolesList) {

                Object [] rowData = {

                        workRole.getRoleId(),
                        workRole.getTitle(),
                        workRole.getDescription(),
                        workRole.getSalary(),
                        workRole.getCreationDate()
                };
                tableModel.addRow(rowData);
            }

        }

        catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(getAllWorkRolesFrame, "Error when trying to fetch all work roles from database!", "Error", JOptionPane.ERROR_MESSAGE);
        }

        // Lägg till tabellen i en scrollpanel

        JScrollPane scrollPane = new JScrollPane(table);
        getAllWorkRolesFrame.add(scrollPane, BorderLayout.CENTER);

        // Centrera och visa fönstret

        getAllWorkRolesFrame.setLocationRelativeTo(null);
        getAllWorkRolesFrame.setVisible(true);

        }
    }