package models;

import java.time.LocalDate;
import java.time.Period;

public class SpecialOffer {
    private final String productId;
    private double discountPercent;
    private LocalDate start, end; // could represent remaining days

    public SpecialOffer(String productId, double discountPercent, LocalDate start, LocalDate end) {
        this.productId = productId;
        this.discountPercent = discountPercent;
        this.start = start;
        this.end = end;
    }

    public String getProductId() {
        return productId;
    }
    public double getDiscountPercent() {
        return discountPercent;
    }
    public LocalDate getStart() {
        return start;
    }
    public LocalDate getEnd() {
        return end;
    }
    public Period getRemainingDays() {
        return Period.between(start, end);
    }
    public double getDiscountedPrice(double price) {
        return price - (price * (discountPercent / 100.0));
    }
    public void setDiscountPercent(double discountPercent) {
        this.discountPercent = discountPercent;
    }
    public void setStart(LocalDate start) {
        this.start = start;
    }
    public void setEnd(LocalDate end) {
        this.end = end;
    }
    @Override
    public String toString() {
        return productId + "," + discountPercent + "," + start + "," + end;
    }
}