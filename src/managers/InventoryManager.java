package managers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import models.*;
import java.util.*;

public class InventoryManager {
    private ArrayList<Product> products = new ArrayList<>();
    private final File file = new File("data/products.txt");

    public InventoryManager() {
        loadProductsFromFile();
    }

    public void addProduct(Product p) {
        products.add(p);
        saveProductstoFile();
    }

    public void removeProduct(String id) {
        for (Product p : products) {
            if (p.getId().equals(id)) {
                products.remove(p);
            }
        }
        saveProductstoFile();
    }

    public void updateProduct(Product p) {
        for (int i = 0 ; i < products.size(); i++) {
            if (products.get(i).getId().equals(p.getId())) {
                products.set(i, p);
                break;
            }
        }
        saveProductstoFile();
    }

    public void setDamaged(String id, boolean damaged) {
        Product p = searchProduct(id);
        if (p != null) {
            p.setDamaged(damaged);
            saveProductstoFile();
        }
    }

    public ArrayList<Product> listProducts() {
        ArrayList<Product> result = new ArrayList<>();
        for (Product p : products) {
            if (!p.isDamaged()) result.add(p);
        }
        return result;
    }

    public Product searchProduct(String id) {
        for (Product p : products) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }    

    public void setMinStock(String productId, int minStock) {
        Product p = searchProduct(productId);
        if (p != null) {
            p.setMinStock(minStock);
            saveProductstoFile();
        }
    }

    public ArrayList<Product> checkLowStock() {
        ArrayList<Product> lowStockProducts = new ArrayList<>();

        for (Product p: products) {
            if (p.isLowStock(p.getMinStock())) {
                lowStockProducts.add(p);
            }
        }
        return lowStockProducts;
    }

    public ArrayList<Product> checkNearExpiry() {
        ArrayList<Product> NearExpiryProducts = new ArrayList<>();

        for (Product p: products) {
            if (p.isNearExpiry()) {
                NearExpiryProducts.add(p);
            }
        } 
        return NearExpiryProducts;
    }

    public void handleDamagedItem(String id) {
        setDamaged(id, true);
    }

    public void handleReturn(Product p, int quant) {
        Product exists = searchProduct(p.getId());
        if (exists != null) {
            exists.setQuantity(exists.getQuantity() + quant);
            saveProductstoFile();
        }
    }

    private void loadProductsFromFile() {
        if(!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String id = parts[0];
                String name = parts[1];
                double price = Double.parseDouble(parts[2]);
                int quantity = Integer.parseInt(parts[3]);
                int minStock = Integer.parseInt(parts[4]);
                LocalDate expiryDate = LocalDate.parse(parts[5]);
                boolean damaged = Boolean.parseBoolean(parts[6]);

                Product p = new Product(id, name, price, quantity, minStock, expiryDate);
                p.setDamaged(damaged);
                products.add(p);
            }
        } catch (IOException e) {
            System.out.println("Error reading file.");
        }

    }
    private void saveProductstoFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (Product p : products) {
                    String line =
                        p.getId() + "," +
                        p.getName() + "," +
                        p.getPrice() + "," +
                        p.getQuantity() + "," +
                        p.getMinStock() + "," +
                        p.getExpiryDate() + "," +
                        p.isDamaged();
                bw.write(line);
                bw.newLine();
            }
        }
        catch (IOException e) {
            System.out.println("Can't write into file.");
        }
    }
}
