package se.david.EmployeeDB.gui;


import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame() {

        setTitle("Database Employees");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new GridLayout(6, 1));


        //Skapa knapparna

        JButton insertButton = new JButton("INSERT");
        JButton deleteButton = new JButton("DELETE");
        JButton updateButton = new JButton("UPDATE");
        JButton getWorkRoleButton = new JButton("FETCH SINGLE WORK ROLE");
        JButton getAllWorkRolesButton = new JButton("FETCH ALL WORK ROLES");
        JButton loginButton = new JButton("LOGIN EMPLOYEE");


        //Lägg till knapparna

        add(insertButton);
        add(deleteButton);
        add(updateButton);
        add(getWorkRoleButton);
        add(getAllWorkRolesButton);
        add(loginButton);


        //Lägg till actions för varje knapp

        insertButton.addActionListener(e -> new InsertWindow());
        deleteButton.addActionListener(e -> new DeleteWindow());
        updateButton.addActionListener(e -> new UpdateWindow());
        getWorkRoleButton.addActionListener(e -> new GetWorkRoleWindow());
        getAllWorkRolesButton.addActionListener(e -> new GetAllWorkRolesWindow());
        loginButton.addActionListener(e -> new LogInWindow());
    }
}