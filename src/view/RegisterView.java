package view;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import managers.AuthService;
import models.User;

public class RegisterView extends Form {
    private final AuthService auth;

    public RegisterView(AuthService auth) {
        super("Register");
        this.auth = auth;
        setSize(CommonConstants.LOGIN_SIZE);
        setLocationRelativeTo(null);
        addGuiCommponents();

    }

    @Override
    protected void addGuiCommponents() {

        // register label
        JLabel registerLabel = new JLabel("Login");
        registerLabel.setBounds(0, 20, 400, 60);
        registerLabel.setForeground(CommonConstants.TEXT_COLOR);
        registerLabel.setFont(new Font("Dialog", Font.BOLD, 32));
        registerLabel.setHorizontalAlignment(SwingConstants.CENTER);

        add(registerLabel);

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

        // confirm password label
        JLabel conPasswordLabel = new JLabel("Confirm Password:");
        conPasswordLabel.setBounds(30, 265, 340, 20);
        conPasswordLabel.setForeground(CommonConstants.TEXT_COLOR);
        conPasswordLabel.setFont(new Font("Dialog", Font.PLAIN, 16));

        JPasswordField conPasswordField = new JPasswordField();
        conPasswordField.setBounds(30, 290, 340, 45);
        conPasswordField.setBackground(CommonConstants.SECONDARY_COLOR);
        conPasswordField.setForeground(CommonConstants.TEXT_COLOR);
        conPasswordField.setCaretColor(CommonConstants.TEXT_COLOR);
        conPasswordField.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        conPasswordField.setFont(new Font("Dialog", Font.PLAIN, 20));

        add(conPasswordLabel);
        add(conPasswordField);

        // button login
        JButton registerButton = new JButton("Register");
        registerButton.setBounds(80, 390, 240, 45);
        registerButton.setBackground(CommonConstants.TEXT_COLOR);
        registerButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        registerButton.setFont(new Font("Dialog", Font.BOLD, 18));
        registerButton.setFocusable(false);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText().trim();
                String password = new String(passwordField.getPassword());
                String confirmPassword = new String(conPasswordField.getPassword());

                String error = validateUserInput(username, password, confirmPassword);
                if (error != null) {
                    JOptionPane.showMessageDialog(null, error, "Error Registering", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                User u = auth.registerSellar(username, password);

                if (u == null) {
                    JOptionPane.showMessageDialog(null, "Username already taken. Try adding numbers or underscores");
                    return;
                }

                JOptionPane.showMessageDialog(null, "Registration successful!");

                dispose();
                new LoginView(auth).setVisible(true);

            }
        });

        add(registerButton);

        // register label
        JLabel loginLabel = new JLabel("Have an account? Sign here");
        loginLabel.setBounds(80, 480, 240, 40);
        loginLabel.setForeground(CommonConstants.TEXT_COLOR);
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
        loginLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        loginLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                RegisterView.this.dispose();

                new LoginView(auth).setVisible(true);
            }
        });

        add(loginLabel);

    }

    private String validateUserInput(String username, String password, String confirmPassword) {
        if (username.length() == 0 || password.length() == 0 || confirmPassword.length() == 0) {
            return "All fields are required";
        }
        if (password.length() < 4 || confirmPassword.length() < 4) {
            return "Password must be at least 4 characters";
        }
        if (!password.equals(confirmPassword)) {
            return "Passwords don't match";
        }
        return null;
    }

}
