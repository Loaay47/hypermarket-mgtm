package view;

import java.awt.*;
import javax.swing.*;

import view.dashboard.*;
import managers.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginView extends Form {
    private final AuthService auth;
    private AdminManager adminManager;
    private SalesManager salesManager;
    private MarketingManager marketingManager;
    private InventoryManager inventoryManager;

    public LoginView(AuthService auth) {

        super("Login");
        setSize(CommonConstants.LOGIN_SIZE);
        setLocationRelativeTo(null);
        this.auth = auth;
        inventoryManager = new InventoryManager();
        IdGenerator idGen = new IdGenerator();
        salesManager = new SalesManager(inventoryManager, idGen);
        marketingManager = new MarketingManager(inventoryManager);
        adminManager = new AdminManager(auth);
        addGuiCommponents();

    }

    @Override
    protected void addGuiCommponents() {
        // login label
        JLabel loginLabel = new JLabel("Login");
        loginLabel.setBounds(0, 20, 400, 60);
        loginLabel.setForeground(CommonConstants.TEXT_COLOR);
        loginLabel.setFont(new Font("Dialog", Font.BOLD, 32));
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);

        add(loginLabel);

        // username label
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(30, 100, 340, 20);
        usernameLabel.setForeground(CommonConstants.TEXT_COLOR);
        usernameLabel.setFont(new Font("Dialog", Font.PLAIN, 16));

        JTextField usernameField = new JTextField();
        usernameField.setBounds(30, 125, 340, 45);
        usernameField.setBackground(CommonConstants.SECONDARY_COLOR);
        usernameField.setForeground(CommonConstants.TEXT_COLOR);
        usernameField.setCaretColor(CommonConstants.TEXT_COLOR);
        usernameField.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        usernameField.setFont(new Font("Dialog", Font.PLAIN, 20));

        add(usernameLabel);
        add(usernameField);

        // password label
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(30, 185, 340, 20);
        passwordLabel.setForeground(CommonConstants.TEXT_COLOR);
        passwordLabel.setFont(new Font("Dialog", Font.PLAIN, 16));

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(30, 205, 340, 45);
        passwordField.setBackground(CommonConstants.SECONDARY_COLOR);
        passwordField.setForeground(CommonConstants.TEXT_COLOR);
        passwordField.setCaretColor(CommonConstants.TEXT_COLOR);
        passwordField.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        passwordField.setFont(new Font("Dialog", Font.PLAIN, 20));

        add(passwordLabel);
        add(passwordField);

        // button login
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(80, 390, 240, 45);
        loginButton.setBackground(CommonConstants.TEXT_COLOR);
        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginButton.setFont(new Font("Dialog", Font.BOLD, 18));
        loginButton.setFocusable(false);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                boolean u = auth.login(username, password);

                if (u == false) {
                    JOptionPane.showMessageDialog(null, "Invalid username or password");
                    return;
                }

                JOptionPane.showMessageDialog(null, "Login successful!");

                dispose();
                String currentRole = auth.getCurrentRole();
                switch (currentRole) {
                    case "admin" -> new AdminDashboard(auth, adminManager).setVisible(true);
                    case "inventory" -> new InventoryDashboard(auth, inventoryManager).setVisible(true);
                    case "marketing" -> new MarketingDashboard(auth, marketingManager).setVisible(true);
                    case "seller" -> new SellerDashboard(auth, salesManager).setVisible(true);
                }

            }
        });

        add(loginButton);

        // register label
        JLabel registerLabel = new JLabel("Not a user?  Register Here");
        registerLabel.setBounds(80, 480, 240, 40);
        registerLabel.setForeground(CommonConstants.TEXT_COLOR);
        registerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        registerLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        registerLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                LoginView.this.dispose();
                new RegisterView(auth).setVisible(true);
            }
        });

        add(registerLabel);

    }
}
