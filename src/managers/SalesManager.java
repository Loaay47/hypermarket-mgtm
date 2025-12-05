package managers;

import models.*;
import java.io.*;
import java.util.*;

public class SalesManager {
    private final InventoryManager inventory;
    private final ArrayList<Order> orders = new ArrayList<>();
    private final File file = new File("data/orders.txt");
    private final IdGenerator idGenerator;

    public SalesManager(InventoryManager inv, IdGenerator idGenerator) {
        this.inventory = inv;
        this.idGenerator = idGenerator;
        loadOrdersFromFile();
    }

    public Product searchProduct(String id) {
        return inventory.searchProduct(id);
    }

    public ArrayList<Product> listProducts() {
        return inventory.listProducts();
    }
    
    public Order makeOrder(String productId, int qty) {
        Product p = inventory.searchProduct(productId);

        if (p == null) return null;
        if (p.getQuantity() < qty) return null;

        p.setQuantity(p.getQuantity() - qty);
        inventory.updateProduct(p);

        String orderId = idGenerator.nextOrderId();
        Order order = new Order(orderId, productId, qty);

        orders.add(order);
        saveOrdersToFile();
        return order;
    }

    public boolean cancelOrder(String orderId) {
        for (int i = 0; i < orders.size(); i++) {
            Order o = orders.get(i);

            if (o.getOrderId().equals(orderId)) {
                Product p = inventory.searchProduct(o.getProductId());
                if (p != null) {
                    p.setQuantity(o.getQuantity() + p.getQuantity());
                    inventory.updateProduct(p);
                }
                orders.remove(i);
                saveOrdersToFile();
                return true;
            }
        }
        return false;
    }

    public ArrayList<Order> listOrders() {
        return new ArrayList<>(orders);
    }

    private void loadOrdersFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String orderId = parts[0];
                String prodId = parts[1];
                int qty = Integer.parseInt(parts[2]);

                orders.add(new Order(orderId, prodId, qty));
            }
        } catch (IOException e) {
            System.out.println("Error reading orders file");
        }
    }
    private void saveOrdersToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (Order o : orders) {
                bw.write(o.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing orders file");
        }
    }
}
