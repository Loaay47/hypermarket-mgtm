package view.dashboard;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.*;

import managers.InventoryManager;
import models.*;
import managers.AuthService;
import managers.MarketingManager;
import view.CommonConstants;
import view.Form;
import view.LoginView;

public class MarketingDashboard extends Form {
    private SpecialOffer lastOffer;
    private final AuthService auth;
    private MarketingManager marketingManager;
    private InventoryManager inventoryManager;
    private JPanel content;

    public MarketingDashboard(AuthService auth, MarketingManager marketingManager, InventoryManager inventoryManager) {
        super("Marketing Dashboard");
        setSize(CommonConstants.DASHBOARD_SIZE);
        setLocationRelativeTo(null);
        this.auth = auth;
        this.marketingManager = marketingManager;
        this.inventoryManager = inventoryManager;
        addGuiCommponents();

    }

    @Override
    protected void addGuiCommponents() {
        JLabel header = new JLabel("Marketing Dashboard");
        header.setFont(new Font("Dialog", Font.BOLD, 36));
        header.setForeground(CommonConstants.MARKETING_COLOR);
        header.setHorizontalAlignment(SwingConstants.CENTER);
        header.setBounds(0, 10, getWidth(), 60);

        add(header);

        JLabel userLabel = new JLabel(auth.getCurrentUsername());
        userLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
        userLabel.setForeground(CommonConstants.MARKETING_COLOR.brighter());
        userLabel.setBounds(20, 10, 300, 30);

        add(userLabel);

        JButton logoutButton = new JButton("Logout");
        logoutButton.setBounds(getWidth() - 120, 20, 100, 40);
        logoutButton.setBackground(CommonConstants.MARKETING_COLOR);
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

        JButton productReportBtn = new JButton("Product Report");
        productReportBtn.setBounds(20, 40, 200, 40);
        productReportBtn.setBackground(CommonConstants.MARKETING_COLOR);
        productReportBtn.setForeground(Color.WHITE);
        productReportBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        productReportBtn.setFont(new Font("Dialog", Font.BOLD, 18));
        productReportBtn.setFocusable(false);
        productReportBtn.addActionListener(e -> {
            content.removeAll();
            content.setLayout(new BorderLayout());
            ArrayList<Product> products = marketingManager.generateProductReport(inventoryManager);

            String[] columns = {"ID", "Name", "Price", "Quantity", "Low Stock", "Near Expiry", "Damaged"};
            Object[][] data = new Object[products.size()][7];

            for (int i = 0; i < products.size(); i++) {
                Product p = products.get(i);
                data[i][0] = p.getId();
                data[i][1] = p.getName();
                data[i][2] = p.getPrice();
                data[i][3] = p.getQuantity();
                data[i][4] = p.isLowStock(p.getMinStock()) ? "Yes" : "No";
                data[i][5] = p.isNearExpiry() ? "Yes" : "No";
                data[i][6] = p.isDamaged() ? "Yes" : "No";
            }

            JTable table = new JTable(data, columns);
            table.setFont(new Font("Dialog", Font.PLAIN, 15));
            table.setRowHeight(25);
            table.getTableHeader().setFont(new Font("Dialog", Font.BOLD, 16));

            JScrollPane scrollPane = new JScrollPane(table);

            content.add(scrollPane, BorderLayout.CENTER);
            content.revalidate();
            content.repaint();
            });

        menu.add(productReportBtn);

        JButton specialOfferBtn = new JButton("Special Offer");
        specialOfferBtn.setBounds(20, 100, 200, 40);
        specialOfferBtn.setBackground(CommonConstants.MARKETING_COLOR);
        specialOfferBtn.setForeground(Color.WHITE);
        specialOfferBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        specialOfferBtn.setFont(new Font("Dialog", Font.BOLD, 18));
        specialOfferBtn.setFocusable(false);
        specialOfferBtn.addActionListener(e -> {
        content.removeAll();
        content.setLayout(new GridLayout(5, 2, 15, 10));

        JLabel productIdLabel = new JLabel("Product ID:");
        productIdLabel.setForeground(Color.WHITE);
        productIdLabel.setFont(new Font("Dialog", Font.BOLD, 20));
        JTextField productIdField = new JTextField();
        productIdField.setFont(new Font("Dialog", Font.PLAIN, 20));
        productIdField.setMargin(new Insets(5,5,5,5));

        JLabel discountLabel = new JLabel("Discount %:");
        discountLabel.setForeground(Color.WHITE);
        discountLabel.setFont(new Font("Dialog", Font.BOLD, 20));
        JTextField discountField = new JTextField();
        discountField.setFont(new Font("Dialog", Font.PLAIN, 20));
        discountField.setMargin(new Insets(5,5,5,5));

        JLabel startDateLabel = new JLabel("Start Date (YYYY-MM-DD):");
        startDateLabel.setForeground(Color.WHITE);
        startDateLabel.setFont(new Font("Dialog", Font.BOLD, 20));
        JTextField startDateField = new JTextField();
        startDateField.setFont(new Font("Dialog", Font.PLAIN, 20));
        startDateField.setMargin(new Insets(5,5,5,5));

        JLabel endDateLabel = new JLabel("End Date (YYYY-MM-DD):");
        endDateLabel.setForeground(Color.WHITE);
        endDateLabel.setFont(new Font("Dialog", Font.BOLD, 20));
        JTextField endDateField = new JTextField();
        endDateField.setFont(new Font("Dialog", Font.PLAIN, 20));
        endDateField.setMargin(new Insets(5,5,5,5));

        JButton createBtn = new JButton("Create Offer");
        createBtn.setBackground(CommonConstants.MARKETING_COLOR);
        createBtn.setForeground(Color.WHITE);
        createBtn.setFont(new Font("Dialog", Font.PLAIN, 18));
        createBtn.setFocusable(false);

        createBtn.addActionListener(ev -> {
            String productId = productIdField.getText().trim();
            if (productId.isEmpty()) { JOptionPane.showMessageDialog(content, "Product ID required"); return; }

            double discount;
            try { discount = Double.parseDouble(discountField.getText().trim()); }
            catch (Exception ex) { JOptionPane.showMessageDialog(content, "Invalid discount"); return; }

            LocalDate startDate, endDate;
            try {
                startDate = LocalDate.parse(startDateField.getText().trim());
                endDate = LocalDate.parse(endDateField.getText().trim());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(content, "Invalid date format"); return;
            }

            lastOffer = marketingManager.createOffer(productId, discount, startDate, endDate);
            JOptionPane.showMessageDialog(content, "Special offer created successfully!");
        });

        content.add(productIdLabel);
        content.add(productIdField);
        content.add(discountLabel);
        content.add(discountField);
        content.add(startDateLabel);
        content.add(startDateField);
        content.add(endDateLabel);
        content.add(endDateField);
        content.add(new JLabel());
        content.add(createBtn);

        content.revalidate();
        content.repaint();
        });

        menu.add(specialOfferBtn);

        JButton sendOfferBtn = new JButton("Offer to INV");
        sendOfferBtn.setBounds(20, 160, 200, 40);
        sendOfferBtn.setBackground(CommonConstants.MARKETING_COLOR);
        sendOfferBtn.setForeground(Color.WHITE);
        sendOfferBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        sendOfferBtn.setFont(new Font("Dialog", Font.BOLD, 18));
        sendOfferBtn.setFocusable(false);
        sendOfferBtn.addActionListener(e -> {
        if (lastOffer == null) {
            JOptionPane.showMessageDialog(content, "No offer created yet!");
            return;
        }

        boolean success = marketingManager.sendOfferToInventory(lastOffer, inventoryManager);
        if (success) {
            JOptionPane.showMessageDialog(content, "Offer sent to inventory successfully!");
            lastOffer = null;
        } else {
            JOptionPane.showMessageDialog(content, "Failed to send offer. Check Product ID.");
        }
    });
        menu.add(sendOfferBtn);
        // content area
        content = new JPanel();
        content.setBackground(CommonConstants.BACKGROUND_COLOR);
        content.setBounds(260, 80, getWidth() - 280, getHeight() - 100);
        content.setLayout(new BorderLayout());
        add(content);

    }

}
