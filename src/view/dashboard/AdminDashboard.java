package view.dashboard;

import javax.swing.*;

import managers.AdminManager;
import managers.AuthService;

import java.awt.*;
import java.awt.event.*;


import managers.*;
import models.*;
import view.CommonConstants;
import view.Form;
import view.LoginView;

public class AdminDashboard extends Form {
    private final AuthService auth;
    private final AdminManager adminManager;

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
        // Main content area
        JPanel content = new JPanel();
        content.setBackground(Color.WHITE);
        content.setBounds(260, 80, getWidth() - 280, getHeight() - 100);
        content.setBorder(BorderFactory.createEmptyBorder(20, 20, 40, 20));

        add(content);
        content.setBackground(CommonConstants.BACKGROUND_COLOR);


        JLabel header = new JLabel("Admin Dashboard");
        header.setFont(new Font("Dialog", Font.BOLD, 36));
        header.setForeground(CommonConstants.ADMIN_COLOR.brighter());
        header.setHorizontalAlignment(SwingConstants.CENTER);
        header.setBounds(0, 10, getWidth(), 60);

        add(header);

        JLabel userLabel = new JLabel(auth.getCurrentUser().getUsername());
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


        JButton changeUserPass= new JButton("Change credentials");
        changeUserPass.setBounds(20, 40, 160, 30);
        changeUserPass.setBackground(CommonConstants.ADMIN_COLOR);
        changeUserPass.setForeground(Color.WHITE);
        changeUserPass.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        changeUserPass.setFont(new Font("Dialog", Font.PLAIN, 16));
        changeUserPass.setFocusable(false);
        changeUserPass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User currentUser = auth.getCurrentUser();
                String newUsername = JOptionPane.showInputDialog(content, "Enter new username:");
                if (newUsername == null || newUsername.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(content, "Username unchanged.");
                }

                String newPassword = JOptionPane.showInputDialog(content, "Enter new password:");
                if (newPassword == null || newPassword.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(content, "Password unchanged.");
                }

                if (newUsername != null || newPassword != null) {
                    currentUser.setUsername(newUsername);
                    currentUser.setPassword(newPassword);
                    boolean success = auth.updateUser(currentUser);

                    if (success) {
                        userLabel.setText(auth.getCurrentUsername());
                        JOptionPane.showMessageDialog(content, "Credentials updated successfully!");
                    } else {
                        JOptionPane.showMessageDialog(content, "Failed to update credentials.");
                    }
                }
            }
        });

        add(changeUserPass);
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
                content.removeAll();
                content.setLayout(new GridLayout(4, 2, 15, 10));

                JLabel usernameLabel = new JLabel("Username:");
                usernameLabel.setForeground(Color.WHITE);
                usernameLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
                usernameLabel.setFont(new Font("Dialog", Font.BOLD, 20));
                JTextField usernameField = new JTextField();
                usernameField.setFont(new Font("Dialog", Font.PLAIN, 20));
                usernameField.setMargin(new Insets(5, 5, 5, 5));

                JLabel passwordLabel = new JLabel("Password:");
                passwordLabel.setForeground(Color.WHITE);
                passwordLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
                passwordLabel.setFont(new Font("Dialog", Font.BOLD, 20));
                JTextField passwordField = new JTextField();
                passwordField.setFont(new Font("Dialog", Font.PLAIN, 20));
                passwordField.setMargin(new Insets(5, 5, 5, 5));

                JLabel roleLabel = new JLabel("Role:");
                roleLabel.setForeground(Color.WHITE);
                roleLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
                roleLabel.setFont(new Font("Dialog", Font.BOLD, 20));
                String[] roles = {"Inventory", "Marketing", "Seller"};
                JComboBox<String> roleCombo = new JComboBox<>(roles);
                roleCombo.setFont(new Font("Dialog", Font.PLAIN, 20));
                ((JLabel) roleCombo.getRenderer()).setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));

                JButton addBtn = new JButton("Add Employee");
                addBtn.setBackground(CommonConstants.ADMIN_COLOR);
                addBtn.setForeground(Color.WHITE);
                addBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                addBtn.setFont(new Font("Dialog", Font.PLAIN, 18));
                addBtn.setFocusable(false);
                addBtn.addActionListener(ev -> {
                    String username = usernameField.getText().trim();
                    String password = passwordField.getText().trim();
                    String role = (String) roleCombo.getSelectedItem();

                    if (username.isEmpty()) {
                        JOptionPane.showMessageDialog(content, "Username is empty!");
                        return;
                    }
                    if (password.isEmpty()) {
                        JOptionPane.showMessageDialog(content, "Password is empty!");
                        return;
                    }

                    String id = IdGenerator.nextUserId(role.toLowerCase());

                    User newEmp = adminManager.addEmployee(id, username, password, role.toLowerCase());
                    if (newEmp != null) {
                        JOptionPane.showMessageDialog(content, "Employee added successfully!\nID: " + newEmp.getId());
                        usernameField.setText("");
                        passwordField.setText("");
                        roleCombo.setSelectedIndex(0);
                    } else {
                        JOptionPane.showMessageDialog(content, "Failed to add employee.");
                    }
                });

                // Add components to content panel
                content.add(usernameLabel);
                content.add(usernameField);
                content.add(passwordLabel);
                content.add(passwordField);
                content.add(roleLabel);
                content.add(roleCombo);
                content.add(new JLabel());
                content.add(addBtn);

                content.revalidate();
                content.repaint();
            }
        });
        menu.add(addEmployee);


        JButton updateBtn = new JButton("Update Employee");
        updateBtn.setBounds(20, 100, 200, 40);
        updateBtn.setBackground(CommonConstants.ADMIN_COLOR);
        updateBtn.setForeground(Color.WHITE);
        updateBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        updateBtn.setFont(new Font("Dialog", Font.BOLD, 18));
        updateBtn.setFocusable(false);
        updateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent t) {
                content.removeAll();
                content.setLayout(new GridLayout(5, 2, 15, 10));

                JLabel idLabel = new JLabel("Employee ID:");
                idLabel.setForeground(Color.WHITE);
                idLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
                idLabel.setFont(new Font("Dialog", Font.BOLD, 20));
                JTextField idField = new JTextField();
                idField.setFont(new Font("Dialog", Font.BOLD, 20));
                idField.setMargin(new Insets(5,5,5,5));

                JLabel usernameLabel = new JLabel("New Username:");
                usernameLabel.setForeground(Color.WHITE);
                usernameLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
                usernameLabel.setFont(new Font("Dialog", Font.BOLD, 20));
                JTextField usernameField = new JTextField();
                usernameField.setFont(new Font("Dialog", Font.PLAIN, 20));
                usernameField.setMargin(new Insets(5, 5, 5, 5));

                JLabel passwordLabel = new JLabel("New Password:");
                passwordLabel.setForeground(Color.WHITE);
                passwordLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
                passwordLabel.setFont(new Font("Dialog", Font.BOLD, 20));
                JTextField passwordField = new JTextField();
                passwordField.setFont(new Font("Dialog", Font.PLAIN, 20));
                passwordField.setMargin(new Insets(5, 5, 5, 5));

                JLabel roleLabel = new JLabel("New Role:");
                roleLabel.setForeground(Color.WHITE);
                roleLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
                roleLabel.setFont(new Font("Dialog", Font.BOLD, 20));
                String[] roles = {"Inventory", "Marketing", "Seller"};
                JComboBox<String> roleCombo = new JComboBox<>(roles);
                roleCombo.setFont(new Font("Dialog", Font.PLAIN, 20));
                ((JLabel)roleCombo.getRenderer()).setBorder(BorderFactory.createEmptyBorder(2,5,2,5));

                JButton updateBtn = new JButton("Update Employee");
                updateBtn.setBounds(20, 100, 200, 40);
                updateBtn.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
                updateBtn.setBackground(CommonConstants.ADMIN_COLOR);
                updateBtn.setForeground(Color.WHITE);
                updateBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                updateBtn.setFont(new Font("Dialog", Font.PLAIN, 18));
                updateBtn.setFocusable(false);
                updateBtn.addActionListener(e -> {
                    String id = idField.getText().trim();
                    if (id.isEmpty()) {
                        JOptionPane.showMessageDialog(content, "Employee ID is required!");
                        return;
                    }

                    String newUsername = usernameField.getText().trim();
                    String newPassword = passwordField.getText().trim();
                    String newRole = (String) roleCombo.getSelectedItem();

                    if (newUsername.isEmpty()) { JOptionPane.showMessageDialog(content, "Username is empty!"); return; }
                    if (newPassword.isEmpty()) { JOptionPane.showMessageDialog(content, "Password is empty!"); return; }
                    boolean success = adminManager.updateEmployee(id, newUsername, newPassword, newRole.toLowerCase());
                    if (success) {
                        JOptionPane.showMessageDialog(content, "Employee updated successfully!");
                    } else {
                        JOptionPane.showMessageDialog(content, "Employee not found or update failed.");
                    }
                });

                content.add(idLabel);
                content.add(idField);
                content.add(usernameLabel);
                content.add(usernameField);
                content.add(passwordLabel);
                content.add(passwordField);
                content.add(roleLabel);
                content.add(roleCombo);
                content.add(new JLabel());
                content.add(updateBtn);

                content.revalidate();
                content.repaint();
            }
        });
        menu.add(updateBtn);

        JButton deleteEmployee = new JButton("Delete Employee");
        deleteEmployee.setBounds(20, 160, 200, 40);
        deleteEmployee.setBackground(CommonConstants.ADMIN_COLOR);
        deleteEmployee.setForeground(Color.WHITE);
        deleteEmployee.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        deleteEmployee.setFont(new Font("Dialog", Font.BOLD, 18));
        deleteEmployee.setFocusable(false);
        deleteEmployee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                content.removeAll();
                content.setLayout(new GridLayout(2, 2, 15, 10));

                JLabel idLabel = new JLabel("Employee ID:");
                idLabel.setForeground(Color.WHITE);
                idLabel.setFont(new Font("Dialog", Font.BOLD, 20));
                idLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));

                JTextField idField = new JTextField();
                idField.setFont(new Font("Dialog", Font.PLAIN, 20));
                idField.setMargin(new Insets(5, 5, 5, 5));

                JButton deleteBtn = new JButton("Delete Employee");
                deleteBtn.setBackground(CommonConstants.ADMIN_COLOR);
                deleteBtn.setForeground(Color.WHITE);
                deleteBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                deleteBtn.setFont(new Font("Dialog", Font.PLAIN, 18));
                deleteBtn.setFocusable(false);
                deleteBtn.addActionListener(ev -> {
                    String id = idField.getText().trim();
                    if (id.isEmpty()) {
                        JOptionPane.showMessageDialog(content, "Employee ID is required!");
                        return;
                    }

                    boolean success = adminManager.deleteEmployee(id);
                    if (success) {
                        JOptionPane.showMessageDialog(content, "Employee deleted successfully!");
                        idField.setText("");
                    } else {
                        JOptionPane.showMessageDialog(content, "Employee not found or deletion failed.");
                    }
                });

                content.add(idLabel);
                content.add(idField);
                content.add(new JLabel());
                content.add(deleteBtn);

                content.revalidate();
                content.repaint();
            }
        });

        menu.add(deleteEmployee);

        JButton listAllEmployees= new JButton("List all Employees");
        listAllEmployees.setBounds(20, 220, 200, 40);
        listAllEmployees.setBackground(CommonConstants.ADMIN_COLOR);
        listAllEmployees.setForeground(Color.WHITE);
        listAllEmployees.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        listAllEmployees.setFont(new Font("Dialog", Font.BOLD, 18));
        listAllEmployees.setFocusable(false);
        listAllEmployees.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                content.removeAll();
                content.setLayout(new BorderLayout(10, 10));

                // Header
                JLabel header = new JLabel("All Employees");
                header.setForeground(Color.WHITE);
                header.setFont(new Font("Dialog", Font.BOLD, 22));
                header.setHorizontalAlignment(SwingConstants.CENTER);
                content.add(header, BorderLayout.NORTH);

                // Table columns
                String[] columns = {"ID", "Username", "Role"};

                // Fetch employees from AdminManager
                java.util.List<User> employees = adminManager.listEmployees();
                Object[][] data = new Object[employees.size()][3];
                for (int i = 0; i < employees.size(); i++) {
                    User u = employees.get(i);
                    data[i][0] = u.getId();
                    data[i][1] = u.getUsername();
                    data[i][2] = u.getRole();
                }

                // JTable
                JTable table = new JTable(data, columns);
                table.setFont(new Font("Dialog", Font.PLAIN, 16));
                table.setRowHeight(25);
                table.setFillsViewportHeight(true);

                // Table header styling
                table.getTableHeader().setFont(new Font("Dialog", Font.BOLD, 18));
                table.getTableHeader().setBackground(CommonConstants.ADMIN_COLOR);
                table.getTableHeader().setForeground(Color.WHITE);

                // Scroll pane
                JScrollPane scrollPane = new JScrollPane(table);
                content.add(scrollPane, BorderLayout.CENTER);

                // Refresh panel
                content.revalidate();
                content.repaint();
            }
        });

        menu.add(listAllEmployees);

        JButton searchEmployee = new JButton("Search Employee");
        searchEmployee.setBounds(20, 280, 200, 40);
        searchEmployee.setBackground(CommonConstants.ADMIN_COLOR);
        searchEmployee.setForeground(Color.WHITE);
        searchEmployee.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        searchEmployee.setFont(new Font("Dialog", Font.BOLD, 18));
        searchEmployee.setFocusable(false);
        searchEmployee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                content.removeAll();
                content.setLayout(new GridLayout(5, 2, 15, 10)); // 5 rows: ID + 3 info + button

                JLabel idLabel = new JLabel("Employee ID:");
                idLabel.setForeground(Color.WHITE);
                idLabel.setFont(new Font("Dialog", Font.BOLD, 20));
                idLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));

                JTextField idField = new JTextField();
                idField.setFont(new Font("Dialog", Font.PLAIN, 20));
                idField.setMargin(new Insets(5, 5, 5, 5));

                JLabel usernameLabel = new JLabel("Username:");
                usernameLabel.setForeground(Color.WHITE);
                usernameLabel.setFont(new Font("Dialog", Font.BOLD, 20));
                usernameLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));

                JTextField usernameField = new JTextField();
                usernameField.setFont(new Font("Dialog", Font.PLAIN, 20));
                usernameField.setMargin(new Insets(5, 5, 5, 5));
                usernameField.setEditable(false); // read-only

                JLabel passwordLabel = new JLabel("Password:");
                passwordLabel.setForeground(Color.WHITE);
                passwordLabel.setFont(new Font("Dialog", Font.BOLD, 20));
                passwordLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));

                JTextField passwordField = new JTextField();
                passwordField.setFont(new Font("Dialog", Font.PLAIN, 20));
                passwordField.setMargin(new Insets(5, 5, 5, 5));
                passwordField.setEditable(false);

                JLabel roleLabel = new JLabel("Role:");
                roleLabel.setForeground(Color.WHITE);
                roleLabel.setFont(new Font("Dialog", Font.BOLD, 20));
                roleLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));

                JTextField roleField = new JTextField();
                roleField.setFont(new Font("Dialog", Font.PLAIN, 20));
                roleField.setMargin(new Insets(5, 5, 5, 5));
                roleField.setEditable(false);

                JButton searchBtn = new JButton("Search Employee");
                searchBtn.setBackground(CommonConstants.ADMIN_COLOR);
                searchBtn.setForeground(Color.WHITE);
                searchBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                searchBtn.setFont(new Font("Dialog", Font.PLAIN, 18));
                searchBtn.setFocusable(false);
                searchBtn.addActionListener(ev -> {
                    String id = idField.getText().trim();
                    if (id.isEmpty()) {
                        JOptionPane.showMessageDialog(content, "Employee ID is required!");
                        return;
                    }

                    User user = adminManager.searchEmployeeById(id);
                    if (user != null) {
                        usernameField.setText(user.getUsername());
                        passwordField.setText(user.getPassword());
                        roleField.setText(user.getRole());
                    } else {
                        JOptionPane.showMessageDialog(content, "Employee not found!");
                        usernameField.setText("");
                        passwordField.setText("");
                        roleField.setText("");
                    }
                });

                content.add(idLabel);
                content.add(idField);
                content.add(usernameLabel);
                content.add(usernameField);
                content.add(passwordLabel);
                content.add(passwordField);
                content.add(roleLabel);
                content.add(roleField);
                content.add(new JLabel());
                content.add(searchBtn);

                content.revalidate();
                content.repaint();
            }
        });

        menu.add(searchEmployee);


    }

}
