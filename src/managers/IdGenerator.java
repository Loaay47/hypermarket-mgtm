package managers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;

import models.User;

public class IdGenerator {
    private static int prodNumber = 0, orderNumber = 0, invNumber = 0, marketingNumber = 0, sellerNumber = 0,
            offerNumber = 0;
    private final File products = new File("data/products.txt");
    private final File orders = new File("data/orders.txt");
    private final File offers = new File("data/offers.txt");

    public IdGenerator() {
        initializeFromProducts(products);
        initializeFromOrders(orders);
        initializeFromOffers(offers);
    }

    public static String nextUserId(String role) {
        if (role.equalsIgnoreCase("marketing")) {
            return nextMarketingId();
        } else if (role.equalsIgnoreCase("inventory")) {
            return nextInventoryId();
        } else if (role.equalsIgnoreCase("seller")) {
            return nextSellerId();
        }

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

    public void initializeFromProducts(File products) {
        if (!products.exists())
            return;
        try (BufferedReader br = new BufferedReader(new FileReader(products))) {
            String line;
            while ((line = br.readLine()) != null) {
                String id = line.split(",")[0];
                if (id.startsWith("P")) {
                    int num = Integer.parseInt(id.substring(1));
                    prodNumber = Math.max(prodNumber, num);
                }
            }
        } catch (Exception ignored) {
        }
    }

    public void initializeFromOrders(File orders) {
        if (!orders.exists())
            return;
        try (BufferedReader br = new BufferedReader(new FileReader(orders))) {
            String line;
            while ((line = br.readLine()) != null) {
                String id = line.split(",")[0];
                if (id.startsWith("R")) {
                    int num = Integer.parseInt(id.substring(1));
                    orderNumber = Math.max(orderNumber, num);
                }
            }
        } catch (Exception ignored) {
        }
    }

    public void initializeFromOffers(File offers) {
        if (!offers.exists())
            return;
        try (BufferedReader br = new BufferedReader(new FileReader(offers))) {
            String line;
            while ((line = br.readLine()) != null) {
                String id = line.split(",")[0];
                if (id.startsWith("F")) {
                    int num = Integer.parseInt(id.substring(1));
                    offerNumber = Math.max(offerNumber, num);
                }
            }
        } catch (Exception ignored) {
        }
    }

    public static String nextProductId() {
        prodNumber++;
        return String.format("P%03d", prodNumber);
    }

    public String nextOrderId() {
        orderNumber++;
        return String.format("R%03d", orderNumber);
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
        return String.format("F%03d", offerNumber);
    }
}
