package models;

import java.time.LocalDate;

public class Order {

    private final String orderId;
    private final String userId;
    private final String productId;
    private final int quantity;
    private final double unitPrice;
    private final LocalDate date;

    public Order(String orderId, String userId, String productId, int quantity, double unitPrice) {
        this.orderId = orderId;
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.date = LocalDate.now();
    }

    public String getOrderId() {
        return orderId;
    }

    public String getUserId() {
        return userId;
    }

    public String getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public double getTotalPrice() {
        return unitPrice * quantity;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return orderId + "," +
               userId + "," +
               productId + "," +
               quantity + "," +
               unitPrice + "," +
               date;
    }
}

