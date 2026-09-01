public class LegacyInventoryAdapter {
    private final LegacyInventorySystem legacySystem = new LegacyInventorySystem();

    public void syncOrder(Order order) {
        System.out.println("   🔌 Адаптер: конвертируем заказ для legacy-системы...");
        for (Book book : order.getBooks()) {
            legacySystem.syncItem(book.getTitle(), 1, book.getPrice());
        }
    }
}