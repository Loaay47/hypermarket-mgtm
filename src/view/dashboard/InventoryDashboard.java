package view.dashboard;

import javax.swing.*;

import managers.AuthService;
import managers.InventoryManager;
import managers.IdGenerator;
import models.Product;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.util.ArrayList;

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

        JButton notifications= new JButton("Alerts");
        notifications.setBounds(20, 40, 100, 40);
        notifications.setBackground(CommonConstants.INVENTORY_COLOR);
        notifications.setForeground(Color.WHITE);
        notifications.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        notifications.setFont(new Font("Dialog", Font.BOLD, 18));
        notifications.setFocusable(false);
        notifications.addActionListener(e -> {
            content.removeAll();
            content.setLayout(new BorderLayout());

            JLabel title = new JLabel("Alerts");
            title.setFont(new Font("Dialog", Font.BOLD, 22));
            title.setForeground(CommonConstants.INVENTORY_COLOR);
            title.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            ArrayList<String> notes = inventoryManager.getNotifications();

            DefaultListModel<String> model = new DefaultListModel<>();
            if (notes.isEmpty()) {
                model.addElement("No notifications 🎉");
            } else {
                for (String n : notes) model.addElement(n);
            }

            JList<String> list = new JList<>(model);
            list.setFont(new Font("Dialog", Font.PLAIN, 20));

            JScrollPane scroll = new JScrollPane(list);

            content.add(title, BorderLayout.NORTH);
            content.add(scroll, BorderLayout.CENTER);

            content.revalidate();
            content.repaint();
        });

        add(notifications);
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
                content.removeAll();
                content.setLayout(new GridLayout(6, 2, 15, 10));

                JLabel nameLabel = new JLabel("Product Name:");
                nameLabel.setForeground(Color.WHITE);
                nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
                nameLabel.setFont(new Font("Dialog", Font.BOLD, 20));
                JTextField nameField = new JTextField();
                nameField.setFont(new Font("Dialog", Font.PLAIN, 20));
                nameField.setMargin(new Insets(5, 5, 5, 5));

                JLabel priceLabel = new JLabel("Price:");
                priceLabel.setForeground(Color.WHITE);
                priceLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
                priceLabel.setFont(new Font("Dialog", Font.BOLD, 20));
                JTextField priceField = new JTextField();
                priceField.setFont(new Font("Dialog", Font.PLAIN, 20));
                priceField.setMargin(new Insets(5, 5, 5, 5));

                JLabel qtyLabel = new JLabel("Quantity:");
                qtyLabel.setForeground(Color.WHITE);
                qtyLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
                qtyLabel.setFont(new Font("Dialog", Font.BOLD, 20));
                JTextField qtyField = new JTextField();
                qtyField.setFont(new Font("Dialog", Font.PLAIN, 20));
                qtyField.setMargin(new Insets(5, 5, 5, 5));

                JLabel minStockLabel = new JLabel("Min Stock:");
                minStockLabel.setForeground(Color.WHITE);
                minStockLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
                JTextField minStockField = new JTextField();
                minStockField.setFont(new Font("Dialog", Font.PLAIN, 20));
                minStockField.setMargin(new Insets(5, 5, 5, 5));

                JLabel expiryLabel = new JLabel("Expiry Date (YYYY-MM-DD):");
                expiryLabel.setForeground(Color.WHITE);
                expiryLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
                JTextField expiryField = new JTextField();
                expiryField.setFont(new Font("Dialog", Font.PLAIN, 20));
                expiryField.setMargin(new Insets(5, 5, 5, 5));

                JButton addBtn = new JButton("Add Product");
                addBtn.setBackground(CommonConstants.INVENTORY_COLOR);
                addBtn.setForeground(Color.WHITE);
                addBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                addBtn.setFont(new Font("Dialog", Font.PLAIN, 18));
                addBtn.setFocusable(false);

                addBtn.addActionListener(ev -> {
                    String name = nameField.getText().trim();
                    String priceText = priceField.getText().trim();
                    String qtyText = qtyField.getText().trim();

                    if (name.isEmpty()) {
                        JOptionPane.showMessageDialog(content, "Product name is empty!");
                        return;
                    }

                    double price;
                    int quantity;

                    try {
                        price = Double.parseDouble(priceText);
                        if (price <= 0)
                            throw new NumberFormatException();
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(content, "Invalid price!");
                        return;
                    }

                    try {
                        quantity = Integer.parseInt(qtyText);
                        if (quantity < 0)
                            throw new NumberFormatException();
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(content, "Invalid quantity!");
                        return;
                    }

                    String id = IdGenerator.nextProductId();

                    int minStock;
                    LocalDate expiryDate;

                    try {
                        minStock = Integer.parseInt(minStockField.getText().trim());
                        if (minStock < 0)
                            throw new NumberFormatException();
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(content, "Invalid minimum stock");
                        return;
                    }

                    try {
                        expiryDate = LocalDate.parse(expiryField.getText().trim());
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(content, "Invalid expiry date format");
                        return;
                    }

                    Product p = new Product(
                            id,
                            name,
                            price,
                            quantity,
                            minStock,
                            expiryDate);

                    inventoryManager.addProduct(p);

                    JOptionPane.showMessageDialog(
                            content,
                            "Product added successfully!\nID: " + id);

                    nameField.setText("");
                    priceField.setText("");
                    qtyField.setText("");
                });

                content.add(nameLabel);
                content.add(nameField);
                content.add(priceLabel);
                content.add(priceField);
                content.add(qtyLabel);
                content.add(qtyField);
                content.add(minStockLabel);
                content.add(minStockField);
                content.add(expiryLabel);
                content.add(expiryField);
                content.add(new JLabel());
                content.add(addBtn);

                content.revalidate();
                content.repaint();
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
                content.removeAll();
                content.setLayout(new GridLayout(2, 2, 15, 10));

                JLabel idLabel = new JLabel("Product ID:");
                idLabel.setForeground(Color.WHITE);
                idLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
                idLabel.setFont(new Font("Dialog", Font.BOLD, 20));

                JTextField idField = new JTextField();
                idField.setFont(new Font("Dialog", Font.PLAIN, 20));
                idField.setMargin(new Insets(5, 5, 5, 5));

                JButton deleteBtn = new JButton("Delete Product");
                deleteBtn.setBackground(CommonConstants.INVENTORY_COLOR);
                deleteBtn.setForeground(Color.WHITE);
                deleteBtn.setFont(new Font("Dialog", Font.PLAIN, 18));
                deleteBtn.setFocusable(false);

                deleteBtn.addActionListener(ev -> {
                    String id = idField.getText().trim();

                    if (id.isEmpty()) {
                        JOptionPane.showMessageDialog(content, "Product ID is empty!");
                        return;
                    }

                    boolean removed = inventoryManager.removeProduct(id);

                    if (removed) {
                        JOptionPane.showMessageDialog(
                                content,
                                "Product deleted successfully!\nID: " + id);
                        idField.setText("");
                    } else {
                        JOptionPane.showMessageDialog(
                                content,
                                "Product not found!");
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
                content.removeAll();
                content.setLayout(new GridLayout(7, 2, 15, 10));

                JLabel idLabel = new JLabel("Product ID:");
                idLabel.setForeground(Color.WHITE);
                idLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
                idLabel.setFont(new Font("Dialog", Font.BOLD, 18));
                JTextField idField = new JTextField();
                idField.setFont(new Font("Dialog", Font.PLAIN, 16));

                JLabel nameLabel = new JLabel("Product Name:");
                nameLabel.setForeground(Color.WHITE);
                nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
                nameLabel.setFont(new Font("Dialog", Font.BOLD, 18));
                JTextField nameField = new JTextField();
                nameField.setFont(new Font("Dialog", Font.PLAIN, 16));
                nameField.setMargin(new Insets(5, 5, 5, 5));

                JLabel priceLabel = new JLabel("Price:");
                priceLabel.setForeground(Color.WHITE);
                priceLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
                priceLabel.setFont(new Font("Dialog", Font.BOLD, 18));
                JTextField priceField = new JTextField();
                priceField.setFont(new Font("Dialog", Font.PLAIN, 16));
                priceField.setMargin(new Insets(5, 5, 5, 5));

                JLabel qtyLabel = new JLabel("Quantity:");
                qtyLabel.setForeground(Color.WHITE);
                qtyLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
                qtyLabel.setFont(new Font("Dialog", Font.BOLD, 18));
                JTextField qtyField = new JTextField();
                qtyField.setFont(new Font("Dialog", Font.PLAIN, 16));
                qtyField.setMargin(new Insets(5, 5, 5, 5));

                JLabel minStockLabel = new JLabel("Min Stock:");
                minStockLabel.setForeground(Color.WHITE);
                minStockLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
                minStockLabel.setFont(new Font("Dialog", Font.BOLD, 18));
                JTextField minStockField = new JTextField();
                minStockField.setFont(new Font("Dialog", Font.PLAIN, 16));
                minStockField.setMargin(new Insets(5, 5, 5, 5));

                JLabel expiryLabel = new JLabel("Expiry Date (YYYY-MM-DD):");
                expiryLabel.setForeground(Color.WHITE);
                expiryLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
                JTextField expiryField = new JTextField();
                expiryField.setFont(new Font("Dialog", Font.PLAIN, 20));
                expiryField.setMargin(new Insets(5, 5, 5, 5));

                JButton updateBtn = new JButton("Update Product");
                updateBtn.setBackground(CommonConstants.INVENTORY_COLOR);
                updateBtn.setForeground(Color.WHITE);
                updateBtn.setFont(new Font("Dialog", Font.PLAIN, 18));
                updateBtn.setFocusable(false);

                updateBtn.addActionListener(ev -> {
                    String id = idField.getText().trim();
                    if (id.isEmpty()) {
                        JOptionPane.showMessageDialog(content, "Product ID is empty!");
                        return;
                    }

                    String name = nameField.getText().trim();
                    if (name.isEmpty()) {
                        JOptionPane.showMessageDialog(content, "Name is empty!");
                        return;
                    }

                    double price;
                    int quantity;
                    int minStock;
                    LocalDate expiryDate;

                    try {
                        price = Double.parseDouble(priceField.getText().trim());
                        quantity = Integer.parseInt(qtyField.getText().trim());
                        minStock = Integer.parseInt(minStockField.getText().trim());
                        expiryDate = LocalDate.parse(expiryField.getText().trim());
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(content, "Invalid input values");
                        return;
                    }
                    Product p = new Product(
                            id,
                            name,
                            price,
                            quantity,
                            minStock,
                            expiryDate);

                    inventoryManager.updateProduct(p);

                    JOptionPane.showMessageDialog(
                            content,
                            "Product updated successfully!\nID: " + id);

                    idField.setText("");
                    nameField.setText("");
                    priceField.setText("");
                    qtyField.setText("");
                    minStockField.setText("");
                    expiryField.setText("");
                });

                content.add(idLabel);
                content.add(idField);
                content.add(nameLabel);
                content.add(nameField);
                content.add(priceLabel);
                content.add(priceField);
                content.add(qtyLabel);
                content.add(qtyField);
                content.add(minStockLabel);
                content.add(minStockField);
                content.add(expiryLabel);
                content.add(expiryField);
                content.add(new JLabel());
                content.add(updateBtn);

                content.revalidate();
                content.repaint();
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
                content.removeAll();
                content.setLayout(new BorderLayout());

                String[] columns = { "ID", "Name", "Price", "Quantity", "Min Stock", "Expiry Date" };

                ArrayList<Product> products = inventoryManager.getAllProducts();

                Object[][] data = new Object[products.size()][columns.length];
                for (int i = 0; i < products.size(); i++) {
                    Product p = products.get(i);
                    data[i][0] = p.getId();
                    data[i][1] = p.getName();
                    data[i][2] = p.getPrice();
                    data[i][3] = p.getQuantity();
                    data[i][4] = p.getMinStock();
                    data[i][5] = p.getExpiryDate();
                }

                JTable table = new JTable(data, columns);
                table.setFillsViewportHeight(true);
                table.setRowHeight(25);

                JScrollPane scrollPane = new JScrollPane(table);
                content.add(scrollPane, BorderLayout.CENTER);

                content.revalidate();
                content.repaint();
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
                String id = JOptionPane.showInputDialog(
                        content,
                        "Enter Product ID to search:");

                if (id == null || id.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(content, "No ID entered!");
                    return;
                }

                Product p = inventoryManager.searchProduct(id.trim());

                if (p != null) {
                    String info = "Product Found:\n" +
                            "ID: " + p.getId() + "\n" +
                            "Name: " + p.getName() + "\n" +
                            "Price: " + p.getPrice() + "\n" +
                            "Quantity: " + p.getQuantity() + "\n" +
                            "Min Stock: " + p.getMinStock() + "\n" +
                            "Expiry Date: " + p.getExpiryDate();
                    JOptionPane.showMessageDialog(content, info);
                } else {
                    JOptionPane.showMessageDialog(content, "Product not found!");
                }
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
                String id = JOptionPane.showInputDialog(content, "Enter Product ID:");
                if (id == null || id.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(content, "No ID entered!");
                    return;
                }

                Product p = inventoryManager.searchProduct(id.trim());
                if (p == null) {
                    JOptionPane.showMessageDialog(content, "Product not found!");
                    return;
                }

                String minStockStr = JOptionPane.showInputDialog(
                        content,
                        "Enter new minimum stock for " + p.getName() + ":");
                if (minStockStr == null || minStockStr.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(content, "No value entered!");
                    return;
                }

                int minStock;
                try {
                    minStock = Integer.parseInt(minStockStr.trim());
                    if (minStock < 0)
                        throw new NumberFormatException();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(content, "Invalid minimum stock value!");
                    return;
                }

                inventoryManager.setMinStock(p.getId(), minStock);
                JOptionPane.showMessageDialog(
                        content,
                        "Minimum stock updated successfully!\nProduct: " + p.getName() +
                                "\nNew Min Stock: " + minStock);
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
                String id = JOptionPane.showInputDialog(content, "Enter Product ID to mark as damaged:");
                if (id == null || id.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(content, "No ID entered!");
                    return;
                }

                Product p = inventoryManager.searchProduct(id.trim());
                if (p == null) {
                    JOptionPane.showMessageDialog(content, "Product not found!");
                    return;
                }

                inventoryManager.handleDamagedItem(p.getId());
                JOptionPane.showMessageDialog(
                        content,
                        "Product marked as damaged!\nProduct: " + p.getName());
            }
        });

        menu.add(manageDamageBtn);

        // content area
        content = new JPanel();
        content.setBackground(CommonConstants.BACKGROUND_COLOR);
        content.setBounds(260, 80, getWidth() - 280, getHeight() - 100);
        content.setLayout(new BorderLayout());
        add(content);

    }

}
