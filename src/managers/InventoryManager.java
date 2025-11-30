package managers;

import java.io.File;
import models.*;
import java.util.*;
public class InventoryManager {
    private ArrayList<InventoryEmployee> inventoryEmployees = new ArrayList<>();
    private ArrayList<Product> products = new ArrayList<>();
    private final File file = new File("data/products.txt");

    public void addProduct(Product p) {
        products.add(p);
    }
    public void removeProduct(String id) {
        for (Product p : products) {
            if (p.getId().equals(id)) {
                products.remove(p);
            }
        }
    }
    public void updateProduct(Product p) {
        for (int i = 0 ; i < products.size(); i++) {
            if (products.get(i).getId().equals(p.getId())) {
                products.set(i, p);
                break;
            }
        }
    }
    public ArrayList<Product> listProducts() {
        return new ArrayList<>(products);
    }

    public Product searchProduct(String id) {
        for (Product p : products) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }    

}
