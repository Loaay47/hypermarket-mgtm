package managers;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import models.Product;

import models.SpecialOffer;

public class MarketingManager {
    private final InventoryManager inventory;
    private final ArrayList<SpecialOffer> offers = new ArrayList<>();
    private final File file = new File("data/offers.txt");

    public MarketingManager(InventoryManager inventory) {
        this.inventory = inventory;
        loadOffersFromFile();
    }

    public SpecialOffer createOffer(String productId, double discount, LocalDate start, LocalDate end) {
        SpecialOffer offer = new SpecialOffer(productId, discount, start, end);
        offers.add(offer);
        saveOffersToFile();
        return offer;
    }

    public void sendOfferToInventory(SpecialOffer offer) {
        inventory.applySpecialOffer(offer);
    }

    public ArrayList<SpecialOffer> listOffers() {
        return new ArrayList<>(offers);
    }

    public ArrayList<Product> reportLowStock() {
        return inventory.checkLowStock();
    }

    public ArrayList<Product> reportNearExpiry() {
        return inventory.checkNearExpiry();
    }

    public ArrayList<Product> reportMinStock() {
        return inventory.checkLowStock();
    }

    public ArrayList<SpecialOffer> getOffers() {
        return offers;
    }

    private void loadOffersFromFile() {
        try {
            if (!file.exists()) {
                file.createNewFile();
                return;
            }
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(",");

                    String id = parts[0];
                    double percent = Double.parseDouble(parts[1]);
                    LocalDate start = LocalDate.parse(parts[2]);
                    LocalDate end = LocalDate.parse(parts[3]);

                    SpecialOffer offer = new SpecialOffer(id, percent, start, end);
                    offers.add(offer);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading special offers file.");
        }
    }

    private void saveOffersToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (SpecialOffer o : offers) {
                bw.write(o.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to special offers file.");
        }
    }
}
