import java.math.BigDecimal;

public class LegacyInventorySystem {
    public void syncItem(String itemName, int quantity, BigDecimal priceRub) {
        System.out.println("   🔄 [LEGACY] Синхронизация: Товар=" + itemName + 
                ", Кол-во=" + quantity + ", Цена=" + priceRub + " руб.");
    }
}