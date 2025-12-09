package view.dashboard;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


import managers.AuthService;
import view.CommonConstants;
import view.Form;
import view.LoginView;

public class SellerDashboard extends Form {
    private final AuthService auth;

    public SellerDashboard(AuthService auth) {
        super("Sellar Dashboard");
        setSize(CommonConstants.DASHBOARD_SIZE);
        setLocationRelativeTo(null);
        this.auth = auth;
        addGuiCommponents();
    }

    @Override
    protected void addGuiCommponents() {

        JLabel header = new JLabel("Seller Dashboard");
        header.setFont(new Font("Dialog", Font.BOLD, 36));
        header.setForeground(CommonConstants.TEXT_COLOR);
        header.setHorizontalAlignment(SwingConstants.CENTER);
        header.setBounds(0, 10, getWidth(), 60);

        add(header);

        JButton logoutButton = new JButton("Logout");
        logoutButton.setBounds(getWidth() - 120, 20, 100, 40);
        logoutButton.setBackground(CommonConstants.TEXT_COLOR);
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

        JButton btnViewProducts = new JButton("View Products");
        btnViewProducts.setBounds(20, 40, 200, 40);
        menu.add(btnViewProducts);

        JButton btnCreateOrder = new JButton("Create Order");
        btnCreateOrder.setBounds(20, 100, 200, 40);
        menu.add(btnCreateOrder);

        JButton btnOrders = new JButton("View Orders");
        btnOrders.setBounds(20, 160, 200, 40);
        menu.add(btnOrders);

        // Main content area
        JPanel content = new JPanel();
        content.setBackground(java.awt.Color.WHITE);
        content.setBounds(260, 80, getWidth() - 280, getHeight() - 100);
        add(content);

    }

}
