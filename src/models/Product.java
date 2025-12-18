package models;

import java.time.LocalDate;

public class Product {
    private String id;
    private String name;
    private double price;
    private double originalPrice = price;
    private int quantity;
    private int minStock;
    private LocalDate expiryDate;
    private boolean isDamaged = false;
    private SpecialOffer activeOffer;

    public Product(String id, String name, double price, int quantity, int minStock, LocalDate expiryDate) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.originalPrice = price;
        this.quantity = quantity;
        this.minStock = minStock;
        this.expiryDate = expiryDate;
        this.activeOffer = null;
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
    public double getOriginalPrice() {
        return originalPrice;
    }
    public void setDamaged(boolean isDamaged) {
        this.isDamaged = isDamaged;
    }
   
    public boolean isDamaged() {
        return isDamaged;
    }
    public void setId(String id) {
        this.id = id;
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

    public void setActiveOffer(SpecialOffer activeOffer) {
        this.activeOffer = activeOffer;
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

    public void resetExpiredOffer() {
        if (activeOffer != null && LocalDate.now().isAfter(activeOffer.getEnd())) {
            this.price = originalPrice;
            this.activeOffer = null;
        }
    }
}
