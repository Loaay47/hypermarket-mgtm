package managers;

import java.util.List;

import models.User;

public class IdGenerator {
    private static int prodNumber = 0, orderNumber = 0, invNumber = 0, marketingNumber = 0, sellerNumber = 0, offerNumber = 0;
    public IdGenerator() {

    }
    public static String nextUserId(String role) {
        if (role.equalsIgnoreCase("marketing")) { return nextMarketingId(); }
        else if (role.equalsIgnoreCase("inventory")) { return nextInventoryId(); }
        else if (role.equalsIgnoreCase("seller")) { return nextSellerId(); }

        return null;
    }

    public void initializeFromUsers(List<User> users) {
        for (User u : users) {
            String id = u.getId();

            if (id.startsWith("I")) {
                invNumber = Math.max(invNumber, Integer.parseInt(id.substring(1)));
            } else if (id.startsWith("M")) {
                marketingNumber = Math.max(marketingNumber, Integer.parseInt(id.substring(1)));
            } else if (id.startsWith("S")) {
                sellerNumber = Math.max(sellerNumber, Integer.parseInt(id.substring(1)));
            }
        }
    }

    public String nextProductId() {
        prodNumber++;
        return String.format("P%03d", prodNumber);
    }

    public String nextOrderId() {
        orderNumber++;
        return String.format("O%03d", orderNumber);
    }

    public static String nextInventoryId() {
        invNumber++;
        return String.format("I%03d", invNumber);
    }

    public static String nextMarketingId() {
        marketingNumber++;
        return String.format("M%03d", marketingNumber);
    }

    public static String nextSellerId() {
        sellerNumber++;
        return String.format("S%03d", sellerNumber);
    }

    public String nextOfferId() {
        offerNumber++;
        return String.format("S%03d", offerNumber);
    }
}
