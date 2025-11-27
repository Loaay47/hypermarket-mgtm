package models;
import java.util.*;

public class Order {
    private final String orderId;
    private final Date orderDate;
    private final ArrayList<OrderItem> items;

    public Order(String orderId) {
        this.orderId = orderId;
        orderDate = new Date();
        this.items = new ArrayList<>();
    }

    public String getOrderId() {
        return orderId;
    }
    public Date getOrderDate() {
        return orderDate;
    }
    public ArrayList<OrderItem> getItems() {
        return items;
    }
    public double getTotalPrice() {
        double total = 0;
        for (OrderItem item: items) {
            total += item.getTotalPrice();
        }
        return total;
    }

    public void addItem(Product product, int quantity) {
        items.add(new OrderItem(product, quantity));
    }

    public void removeItem(OrderItem item) {
        items.remove(item);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order ID: ").append(orderId).append("\n");
        sb.append("Order Date: ").append(orderDate).append("\n");
        sb.append("Items:\n");
        for (OrderItem item: items) {
            sb.append("- ").append(item.getProduct().getName())
            .append("x ").append(item.getQuantity())
            .append(" = ").append(item.getTotalPrice()).append(" EGP\n");
        }
        sb.append("Total Price: ").append(getTotalPrice()).append(" EGP\n"); 
        return sb.toString();
    }
}
