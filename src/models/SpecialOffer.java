package models;

import java.time.LocalDate;
import java.time.Period;

public class SpecialOffer {
    private final String productId;
    private double discount;
    private LocalDate start, end; // could represent remaining days

    public SpecialOffer(String productId, double discount, LocalDate start, LocalDate end) {
        this.productId = productId;
        this.discount= discount;
        this.start = start;
        this.end = end;
    }

    public String getProductId() {
        return productId;
    }
    public double getDiscount() {
        return discount;
    }
    public LocalDate getStart() {
        return start;
    }
    public LocalDate getEnd() {
        return end;
    }
    public Period getRemainingDays() {
        return Period.between( start, end);
    }

    public void setDiscountAmount(double discount) {
        this.discount= discount;
    }
    public void setStart(LocalDate start) {
        this.start = start;
    }
    public void setEnd(LocalDate end) {
        this.end = end;
    }
    
}