package view;

import java.awt.*;
import javax.swing.*;

public class LoginView extends Form {
    public LoginView() {
        super("Login");
        addGuiCommponents();

    }

    private void addGuiCommponents() {
        // login label
        JLabel loginLabel = new JLabel("Login");
        loginLabel.setBounds(0, 25, 520, 100);
        loginLabel.setForeground(CommonConstants.TEXT_COLOR);
        loginLabel.setFont(new Font("Dialog", Font.BOLD, 40));
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);

        add(loginLabel);

        // username label
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(30, 150, 400, 25);
        usernameLabel.setForeground(CommonConstants.TEXT_COLOR);
        usernameLabel.setFont(new Font("Dialog", Font.PLAIN, 18));

        JTextField usernameField = new JTextField();
        usernameField.setBounds(30, 185, 450, 55);
        usernameField.setBackground(CommonConstants.SECONDARY_COLOR);
        usernameField.setForeground(CommonConstants.TEXT_COLOR);
        usernameField.setFont(new Font("Dialog", Font.PLAIN, 24));

        add(usernameLabel);
        add(usernameField);

        // password label
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(30, 350, 400, 25);
        passwordLabel.setForeground(CommonConstants.TEXT_COLOR);
        passwordLabel.setFont(new Font("Dialog", Font.PLAIN, 18));

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(30, 385, 450, 55);
        passwordField.setBackground(CommonConstants.SECONDARY_COLOR);
        passwordField.setForeground(CommonConstants.TEXT_COLOR);
        passwordField.setFont(new Font("Dialog", Font.PLAIN, 24));

        add(passwordLabel);
        add(passwordField);

        // button login
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(125, 520, 250, 50);
        loginButton.setBackground(CommonConstants.TEXT_COLOR);
        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginButton.setFont(new Font("Dialog", Font.BOLD, 18));
        loginButton.setFocusable(false);

        add(loginButton);

        // register label
        JLabel registerLabel = new JLabel("Not a user?  Rigester Here");
        registerLabel.setBounds(125, 600, 250, 50);
        registerLabel.setForeground(CommonConstants.TEXT_COLOR);
        registerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        registerLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        add(registerLabel);

    }
}
