package models;

import java.time.LocalDate;

public class Order {
    private final String orderId;
    private final String productId;
    private final int quantity;
    private final LocalDate date;

    public Order(String orderId, String productId, int quantity) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.date = LocalDate.now();
    }
    public String getOrderId() {
        return orderId;
    }
    public String getProductId() {
        return productId;
    }
    public int getQuantity() {
        return quantity;
    }
    public LocalDate getDate() {
        return date;
    }
    @Override
    public String toString() {
        return orderId + "," + productId + "," + quantity + "," + date;
    }
}
