package view;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

public class RegisterView extends Form {
    public RegisterView() {
        super("Register");

        addGuiCommponents();
    }

    private void addGuiCommponents() {
        // register label
        JLabel loginLabel = new JLabel("Register");
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
        usernameField.setCaretColor(CommonConstants.TEXT_COLOR);
        usernameField.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        usernameField.setFont(new Font("Dialog", Font.PLAIN, 24));

        add(usernameLabel);
        add(usernameField);

        // password label
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(30, 250, 400, 25);
        passwordLabel.setForeground(CommonConstants.TEXT_COLOR);
        passwordLabel.setFont(new Font("Dialog", Font.PLAIN, 18));

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(30, 285, 450, 55);
        passwordField.setBackground(CommonConstants.SECONDARY_COLOR);
        passwordField.setForeground(CommonConstants.TEXT_COLOR);
        passwordField.setCaretColor(CommonConstants.TEXT_COLOR);
        passwordField.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        passwordField.setFont(new Font("Dialog", Font.PLAIN, 24));

        add(passwordLabel);
        add(passwordField);

        // confirm password label
        JLabel conPasswordLabel = new JLabel("Confirm Password:");
        conPasswordLabel.setBounds(30, 350, 400, 25);
        conPasswordLabel.setForeground(CommonConstants.TEXT_COLOR);
        conPasswordLabel.setFont(new Font("Dialog", Font.PLAIN, 18));

        JPasswordField conPasswordField = new JPasswordField();
        conPasswordField.setBounds(30, 385, 450, 55);
        conPasswordField.setBackground(CommonConstants.SECONDARY_COLOR);
        conPasswordField.setForeground(CommonConstants.TEXT_COLOR);
        conPasswordField.setCaretColor(CommonConstants.TEXT_COLOR);
        conPasswordField.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        conPasswordField.setFont(new Font("Dialog", Font.PLAIN, 24));

        add(conPasswordLabel);
        add(conPasswordField);

        // button login
        JButton loginButton = new JButton("Register");
        loginButton.setBounds(125, 520, 250, 50);
        loginButton.setBackground(CommonConstants.TEXT_COLOR);
        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginButton.setFont(new Font("Dialog", Font.BOLD, 18));
        loginButton.setFocusable(false);

        add(loginButton);

        // register label
        JLabel registerLabel = new JLabel("Have an account? Sign here");
        registerLabel.setBounds(125, 600, 250, 50);
        registerLabel.setForeground(CommonConstants.TEXT_COLOR);
        registerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        registerLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        registerLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                RegisterView.this.dispose();

                new LoginView().setVisible(true);
            }
        });

        add(registerLabel);

    }
}
