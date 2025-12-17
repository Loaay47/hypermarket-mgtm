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
        super("Seller Dashboard");
        setSize(CommonConstants.DASHBOARD_SIZE);
        setLocationRelativeTo(null);
        this.auth = auth;
        addGuiCommponents();
    }

    @Override
    protected void addGuiCommponents() {

        JLabel header = new JLabel("Seller Dashboard");
        header.setFont(new Font("Dialog", Font.BOLD, 36));
        header.setForeground(CommonConstants.SELLER_COLOR.brighter());
        header.setHorizontalAlignment(SwingConstants.CENTER);
        header.setBounds(0, 10, getWidth(), 60);

        add(header);

        JLabel userLabel = new JLabel(auth.getCurrentUsername());
        userLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
        userLabel.setForeground(CommonConstants.SELLER_COLOR.brighter());
        userLabel.setBounds(20, 10, 300, 30);

        add(userLabel);

        JButton logoutButton = new JButton("Logout");
        logoutButton.setBounds(getWidth() - 120, 20, 100, 40);
        logoutButton.setBackground(CommonConstants.SELLER_COLOR);
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

        JButton searchProductsBtn = new JButton("Search Products");
        searchProductsBtn.setBounds(20, 40, 200, 40);
        searchProductsBtn.setBackground(CommonConstants.SELLER_COLOR);
        searchProductsBtn.setForeground(Color.WHITE);
        searchProductsBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        searchProductsBtn.setFont(new Font("Dialog", Font.BOLD, 18));
        searchProductsBtn.setFocusable(false);
        menu.add(searchProductsBtn);

        JButton listProductsBtn = new JButton("List Products");
        listProductsBtn.setBounds(20, 100, 200, 40);
        listProductsBtn.setBackground(CommonConstants.SELLER_COLOR);
        listProductsBtn.setForeground(Color.WHITE);
        listProductsBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        listProductsBtn.setFont(new Font("Dialog", Font.BOLD, 18));
        listProductsBtn.setFocusable(false);
        menu.add(listProductsBtn);

        JButton ordersBtn = new JButton("View Orders");
        ordersBtn.setBounds(20, 160, 200, 40);
        ordersBtn.setBackground(CommonConstants.SELLER_COLOR);
        ordersBtn.setForeground(Color.WHITE);
        ordersBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        ordersBtn.setFont(new Font("Dialog", Font.BOLD, 18));
        ordersBtn.setFocusable(false);
        menu.add(ordersBtn);

        JButton createOrderBtn = new JButton("Create Order");
        createOrderBtn.setBounds(20, 220, 200, 40);
        createOrderBtn.setBackground(CommonConstants.SELLER_COLOR);
        createOrderBtn.setForeground(Color.WHITE);
        createOrderBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        createOrderBtn.setFont(new Font("Dialog", Font.BOLD, 18));
        createOrderBtn.setFocusable(false);
        menu.add(createOrderBtn);

        JButton cancelOrderBtn = new JButton("Cancel Order");
        cancelOrderBtn.setBounds(20, 280, 200, 40);
        cancelOrderBtn.setBackground(CommonConstants.SELLER_COLOR);
        cancelOrderBtn.setForeground(Color.WHITE);
        cancelOrderBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        cancelOrderBtn.setFont(new Font("Dialog", Font.BOLD, 18));
        cancelOrderBtn.setFocusable(false);
        menu.add(cancelOrderBtn);

        // Main content area
        JPanel content = new JPanel();
        content.setBackground(Color.WHITE);
        content.setBounds(260, 80, getWidth() - 280, getHeight() - 100);
        add(content);

    }

}
