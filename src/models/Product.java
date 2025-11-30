package models;

import java.time.LocalDate;

public class Product {
    private final String id;
    private String name;
    private double price;
    private int quantity;
    private int minStock;
    private LocalDate expiryDate;

    public Product(String id, String name, double price, int quantity, int minStock, LocalDate expiryDate) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.minStock = minStock;
        this.expiryDate = expiryDate;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getMinStock() {
        return minStock;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setMinStock(int minStock) {
        this.minStock = minStock;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public boolean isLowStock(int minStock) {
        return this.quantity <= this.minStock;
    }

    public boolean isNearExpiry() {
        if (expiryDate == null) {
            return false;
        }

        LocalDate today = LocalDate.now();
        return !expiryDate.isBefore(today) && expiryDate.minusDays(3).isBefore(today);
    }
}
