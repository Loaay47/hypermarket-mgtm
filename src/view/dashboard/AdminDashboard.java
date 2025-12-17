package view.dashboard;

import javax.swing.*;

import managers.AdminManager;
import managers.AuthService;

import java.awt.*;
import java.awt.event.*;


import view.CommonConstants;
import view.Form;
import view.LoginView;

public class AdminDashboard extends Form {
    private final AuthService auth;
    private AdminManager adminManager;

    public AdminDashboard(AuthService auth, AdminManager adminManager) {
        super("Admin Dashboard");
        setSize(CommonConstants.DASHBOARD_SIZE);
        setLocationRelativeTo(null);
        this.auth = auth;
        this.adminManager = adminManager;
        addGuiCommponents();
    }

    @Override
    protected void addGuiCommponents() {
        JLabel header = new JLabel("Admin Dashboard");
        header.setFont(new Font("Dialog", Font.BOLD, 36));
        header.setForeground(CommonConstants.ADMIN_COLOR.brighter());
        header.setHorizontalAlignment(SwingConstants.CENTER);
        header.setBounds(0, 10, getWidth(), 60);

        add(header);

        JLabel userLabel = new JLabel(auth.getCurrentUsername());
        userLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
        userLabel.setForeground(CommonConstants.ADMIN_COLOR.brighter());
        userLabel.setBounds(20, 10, 300, 30);

        add(userLabel);

        JButton logoutButton = new JButton("Logout");
        logoutButton.setBounds(getWidth() - 140, 20, 100, 40);
        logoutButton.setBackground(CommonConstants.ADMIN_COLOR);
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        logoutButton.setFont(new Font("Dialog", Font.BOLD, 18));
        logoutButton.setFocusable(false);
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int choice = JOptionPane.showConfirmDialog(
                        null,
                        "Are you sure you want to logout?",
                        "Confirm Logout",
                        JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    auth.logout();
                    dispose();
                    new LoginView(auth).setVisible(true);
                }
            }
        });

        add(logoutButton);

        // Left menu panel
        JPanel menu = new JPanel(null);
        menu.setBackground(CommonConstants.PRIMARY_COLOR.darker());
        menu.setBounds(0, 80, 250, getHeight());
        add(menu);

        JButton addEmployee = new JButton("Add Employee");
        addEmployee.setBounds(20, 40, 200, 40);
        addEmployee.setBackground(CommonConstants.ADMIN_COLOR);
        addEmployee.setForeground(Color.WHITE);
        addEmployee.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addEmployee.setFont(new Font("Dialog", Font.BOLD, 18));
        addEmployee.setFocusable(false);
        addEmployee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = JOptionPane.showInputDialog(null, "Enter username:");
                if (username == null || username.isEmpty()) return;

                String password = JOptionPane.showInputDialog(null, "Enter password");
                if (password == null | password.isEmpty()) return;

            }
        });
        menu.add(addEmployee);


        JButton updateEmployee = new JButton("Update Employee");
        updateEmployee.setBounds(20, 100, 200, 40);
        updateEmployee.setBackground(CommonConstants.ADMIN_COLOR);
        updateEmployee.setForeground(Color.WHITE);
        updateEmployee.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        updateEmployee.setFont(new Font("Dialog", Font.BOLD, 18));
        updateEmployee.setFocusable(false);
        menu.add(updateEmployee);

        JButton deleteEmployee = new JButton("Delete Employee");
        deleteEmployee.setBounds(20, 160, 200, 40);
        deleteEmployee.setBackground(CommonConstants.ADMIN_COLOR);
        deleteEmployee.setForeground(Color.WHITE);
        deleteEmployee.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        deleteEmployee.setFont(new Font("Dialog", Font.BOLD, 18));
        deleteEmployee.setFocusable(false);
        menu.add(deleteEmployee);

        JButton listAllEmployees= new JButton("List all Employees");
        listAllEmployees.setBounds(20, 220, 200, 40);
        listAllEmployees.setBackground(CommonConstants.ADMIN_COLOR);
        listAllEmployees.setForeground(Color.WHITE);
        listAllEmployees.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        listAllEmployees.setFont(new Font("Dialog", Font.BOLD, 18));
        listAllEmployees.setFocusable(false);
        menu.add(listAllEmployees);

        JButton searchEmployee = new JButton("Search Employee");
        searchEmployee.setBounds(20, 280, 200, 40);
        searchEmployee.setBackground(CommonConstants.ADMIN_COLOR);
        searchEmployee.setForeground(Color.WHITE);
        searchEmployee.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        searchEmployee.setFont(new Font("Dialog", Font.BOLD, 18));
        searchEmployee.setFocusable(false);
        menu.add(searchEmployee);

        // Main content area
        JPanel content = new JPanel();
        content.setBackground(Color.WHITE);
        content.setBounds(260, 80, getWidth() - 280, getHeight() - 100);
        add(content);

    }

}
