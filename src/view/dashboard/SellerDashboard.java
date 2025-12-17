package view.dashboard;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import managers.AuthService;
import managers.SalesManager;
import models.Order;
import models.Product;
import view.CommonConstants;
import view.Form;
import view.LoginView;

public class SellerDashboard extends Form {
    private final AuthService auth;
    private final SalesManager salesManager;
    private JPanel content;

    public SellerDashboard(AuthService auth, SalesManager salesManager) {
        super("Seller Dashboard");
        setSize(CommonConstants.DASHBOARD_SIZE);
        setLocationRelativeTo(null);
        this.auth = auth;
        this.salesManager = salesManager;
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

        // left menu panel
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
        searchProductsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String prodId = JOptionPane.showInputDialog(
                        null,
                        "Enter Product ID to search:",
                        "Search Product",
                        JOptionPane.PLAIN_MESSAGE);

                if (prodId != null && !prodId.isBlank()) {
                    Product p = salesManager.searchProduct(prodId);
                    if (p != null) {
                        JOptionPane.showMessageDialog(
                                null,
                                "Product: " + p.getName() + "\nPrice: " + p.getPrice() + "\nQty: " + p.getQuantity(),
                                "Product Info",
                                JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(
                                null,
                                "Product not found",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }

            }
        });

        menu.add(searchProductsBtn);

        JButton listProductsBtn = new JButton("List Products");
        listProductsBtn.setBounds(20, 100, 200, 40);
        listProductsBtn.setBackground(CommonConstants.SELLER_COLOR);
        listProductsBtn.setForeground(Color.WHITE);
        listProductsBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        listProductsBtn.setFont(new Font("Dialog", Font.BOLD, 18));
        listProductsBtn.setFocusable(false);
        listProductsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Product> allProducts = salesManager.listProducts();
                content.removeAll();

                if (allProducts.isEmpty()) {
                    JLabel emptyLabel = new JLabel("No products available");
                    emptyLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
                    emptyLabel.setForeground(Color.RED);
                    emptyLabel.setHorizontalAlignment(SwingConstants.CENTER);
                    content.add(emptyLabel, BorderLayout.CENTER);
                } else {
                    String[] columns = { "ID", "Name", "Price", "Quantity", "Min Stock", "Expiry Date", "Damaged" };
                    Object[][] data = new Object[allProducts.size()][columns.length];

                    for (int i = 0; i < allProducts.size(); i++) {
                        Product p = allProducts.get(i);
                        data[i][0] = p.getId();
                        data[i][1] = p.getName();
                        data[i][2] = p.getPrice();
                        data[i][3] = p.getQuantity();
                        data[i][4] = p.getMinStock();
                        data[i][5] = p.getExpiryDate();
                        data[i][6] = p.isDamaged();
                    }

                    JTable table = new JTable(data, columns);
                    table.setFillsViewportHeight(true);
                    table.setFont(new Font("Dialog", Font.PLAIN, 16));
                    table.setRowHeight(25);
                    table.setEnabled(false);

                    JScrollPane scrollPane = new JScrollPane(table);
                    content.setLayout(new BorderLayout());
                    content.add(scrollPane, BorderLayout.CENTER);
                }

                content.revalidate();
                content.repaint();
            }
        });

        menu.add(listProductsBtn);

        JButton ordersBtn = new JButton("View Orders");
        ordersBtn.setBounds(20, 160, 200, 40);
        ordersBtn.setBackground(CommonConstants.SELLER_COLOR);
        ordersBtn.setForeground(Color.WHITE);
        ordersBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        ordersBtn.setFont(new Font("Dialog", Font.BOLD, 18));
        ordersBtn.setFocusable(false);
        ordersBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ArrayList<Order> allOrders = salesManager.listOrders();
                content.removeAll();

                content.revalidate();
                content.repaint();
            }
        });

        menu.add(ordersBtn);

        JButton createOrderBtn = new JButton("Place an Order");
        createOrderBtn.setBounds(20, 220, 200, 40);
        createOrderBtn.setBackground(CommonConstants.SELLER_COLOR);
        createOrderBtn.setForeground(Color.WHITE);
        createOrderBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        createOrderBtn.setFont(new Font("Dialog", Font.BOLD, 18));
        createOrderBtn.setFocusable(false);
        createOrderBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String prodId = JOptionPane.showInputDialog(
                        null,
                        "Enter Product ID:",
                        "Create Order",
                        JOptionPane.PLAIN_MESSAGE);

                if (prodId == null || prodId.isBlank())
                    return;
                String qtyStr = JOptionPane.showInputDialog(
                        null,
                        "Enter Quantity:",
                        "Create Order",
                        JOptionPane.PLAIN_MESSAGE);

                if (qtyStr == null || qtyStr.isBlank())
                    return;

                int qty = Integer.parseInt(qtyStr);
                Product p = salesManager.searchProduct(prodId);
                content.removeAll();

                if (p == null) {
                    JLabel errorLabel = new JLabel("Product not found: " + prodId);
                    errorLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
                    errorLabel.setForeground(Color.RED);
                    errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
                    content.setLayout(new BorderLayout());
                    content.add(errorLabel, BorderLayout.CENTER);
                } else if (p.getQuantity() < qty) {
                    JLabel errorLabel = new JLabel("Not enough stock. Available: " + p.getQuantity());
                    errorLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
                    errorLabel.setForeground(Color.RED);
                    errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
                    content.setLayout(new BorderLayout());
                    content.add(errorLabel, BorderLayout.CENTER);
                } else {
                    salesManager.makeOrder(prodId, qty);

                    JLabel successLabel = new JLabel(
                            "<html>Order placed successfully!<br>" +
                                    "Product: " + p.getName() + "<br>" +
                                    "Quantity: " + qty + "<br>" +
                                    "Remaining Stock: " + p.getQuantity() + "</html>");
                    successLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
                    successLabel.setForeground(CommonConstants.SELLER_COLOR.darker());
                    successLabel.setHorizontalAlignment(SwingConstants.CENTER);
                    content.setLayout(new BorderLayout());
                    content.add(successLabel, BorderLayout.CENTER);
                }

                content.revalidate();
                content.repaint();
            }
        });

        menu.add(createOrderBtn);

        JButton cancelOrderBtn = new JButton("Cancel Order");
        cancelOrderBtn.setBounds(20, 280, 200, 40);
        cancelOrderBtn.setBackground(CommonConstants.SELLER_COLOR);
        cancelOrderBtn.setForeground(Color.WHITE);
        cancelOrderBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        cancelOrderBtn.setFont(new Font("Dialog", Font.BOLD, 18));
        cancelOrderBtn.setFocusable(false);
        cancelOrderBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                content.removeAll();

                content.revalidate();
                content.repaint();
            }
        });

        menu.add(cancelOrderBtn);

        // content area
        content = new JPanel();
        content.setBackground(CommonConstants.BACKGROUND_COLOR);
        content.setBounds(260, 80, getWidth() - 280, getHeight() - 100);
        content.setLayout(new BorderLayout());
        add(content);

    }

}
