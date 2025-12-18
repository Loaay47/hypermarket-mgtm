package view.dashboard;

import javax.swing.*;

import managers.AuthService;
import managers.InventoryManager;

import java.awt.*;
import java.awt.event.*;

import view.CommonConstants;
import view.Form;
import view.LoginView;

public class InventoryDashboard extends Form {
    private final AuthService auth;
    private InventoryManager inventoryManager;
    private JPanel content;

    public InventoryDashboard(AuthService auth, InventoryManager inventoryManager) {
        super("Inventory Dashboard");
        setSize(CommonConstants.DASHBOARD_SIZE);
        setLocationRelativeTo(null);
        this.auth = auth;
        this.inventoryManager = inventoryManager;
        addGuiCommponents();
    }

    @Override
    protected void addGuiCommponents() {
        JLabel header = new JLabel("Inventory Dashboard");
        header.setFont(new Font("Dialog", Font.BOLD, 36));
        header.setForeground(CommonConstants.INVENTORY_COLOR);
        header.setHorizontalAlignment(SwingConstants.CENTER);
        header.setBounds(0, 10, getWidth(), 60);

        add(header);

        JLabel userLabel = new JLabel(auth.getCurrentUsername());
        userLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
        userLabel.setForeground(CommonConstants.INVENTORY_COLOR.brighter());
        userLabel.setBounds(20, 10, 300, 30);

        add(userLabel);

        JButton logoutButton = new JButton("Logout");
        logoutButton.setBounds(getWidth() - 120, 20, 100, 40);
        logoutButton.setBackground(CommonConstants.INVENTORY_COLOR);
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
        menu.setBounds(0, 80, 250, getHeight() - 100);
        add(menu);

        JButton addProductBtn = new JButton("Add Product");
        addProductBtn.setBounds(20, 10, 200, 40);
        addProductBtn.setBackground(CommonConstants.INVENTORY_COLOR);
        addProductBtn.setForeground(Color.WHITE);
        addProductBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addProductBtn.setFont(new Font("Dialog", Font.BOLD, 18));
        addProductBtn.setFocusable(false);
        addProductBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        menu.add(addProductBtn);

        JButton deleteProductBtn = new JButton("Delete Product");
        deleteProductBtn.setBounds(20, 70, 200, 40);
        deleteProductBtn.setBackground(CommonConstants.INVENTORY_COLOR);
        deleteProductBtn.setForeground(Color.WHITE);
        deleteProductBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        deleteProductBtn.setFont(new Font("Dialog", Font.BOLD, 18));
        deleteProductBtn.setFocusable(false);
        deleteProductBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        menu.add(deleteProductBtn);

        JButton updateProductBtn = new JButton("Update Product");
        updateProductBtn.setBounds(20, 130, 200, 40);
        updateProductBtn.setBackground(CommonConstants.INVENTORY_COLOR);
        updateProductBtn.setForeground(Color.WHITE);
        updateProductBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        updateProductBtn.setFont(new Font("Dialog", Font.BOLD, 18));
        updateProductBtn.setFocusable(false);
        updateProductBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        menu.add(updateProductBtn);

        JButton listProductBtn = new JButton("List Products");
        listProductBtn.setBounds(20, 190, 200, 40);
        listProductBtn.setBackground(CommonConstants.INVENTORY_COLOR);
        listProductBtn.setForeground(Color.WHITE);
        listProductBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        listProductBtn.setFont(new Font("Dialog", Font.BOLD, 18));
        listProductBtn.setFocusable(false);
        listProductBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        menu.add(listProductBtn);

        JButton searchProductBtn = new JButton("Search Products");
        searchProductBtn.setBounds(20, 250, 200, 40);
        searchProductBtn.setBackground(CommonConstants.INVENTORY_COLOR);
        searchProductBtn.setForeground(Color.WHITE);
        searchProductBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        searchProductBtn.setFont(new Font("Dialog", Font.BOLD, 18));
        searchProductBtn.setFocusable(false);
        searchProductBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        menu.add(searchProductBtn);

        JButton setMinStockBtn = new JButton("Set Min Stock");
        setMinStockBtn.setBounds(20, 310, 200, 40);
        setMinStockBtn.setBackground(CommonConstants.INVENTORY_COLOR);
        setMinStockBtn.setForeground(Color.WHITE);
        setMinStockBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setMinStockBtn.setFont(new Font("Dialog", Font.BOLD, 18));
        setMinStockBtn.setFocusable(false);
        setMinStockBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        menu.add(setMinStockBtn);

        JButton manageDamageBtn = new JButton("Manage Damage");
        manageDamageBtn.setBounds(20, 370, 200, 40);
        manageDamageBtn.setBackground(CommonConstants.INVENTORY_COLOR);
        manageDamageBtn.setForeground(Color.WHITE);
        manageDamageBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        manageDamageBtn.setFont(new Font("Dialog", Font.BOLD, 18));
        manageDamageBtn.setFocusable(false);
        manageDamageBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        menu.add(manageDamageBtn);

        JButton productReportBtn = new JButton("Product Report");
        productReportBtn.setBounds(20, 430, 200, 40);
        productReportBtn.setBackground(CommonConstants.INVENTORY_COLOR);
        productReportBtn.setForeground(Color.WHITE);
        productReportBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        productReportBtn.setFont(new Font("Dialog", Font.BOLD, 18));
        productReportBtn.setFocusable(false);
        productReportBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        menu.add(productReportBtn);

        // content area
        content = new JPanel();
        content.setBackground(CommonConstants.BACKGROUND_COLOR);
        content.setBounds(260, 80, getWidth() - 280, getHeight() - 100);
        content.setLayout(new BorderLayout());
        add(content);

    }

}
