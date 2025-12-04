package managers;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import models.InventoryEmployee;

import models.SpecialOffer;

public class MarketingManager {
   private InventoryManager invManager = null;
   // private ArrayList<SpecialOffer> offers = new ArrayList<>(); 
   private final File file = new File("data/offers.txt");

   public MarketingManager(InventoryManager invManager) {
      this.invManager = invManager;
   }

   public SpecialOffer createOffer(String productId, double discount, LocalDate start, LocalDate end) {
      SpecialOffer offer = new SpecialOffer(productId, discount, start, end);
   } 
   
}
