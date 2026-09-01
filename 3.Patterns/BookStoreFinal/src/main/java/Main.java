import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        System.out.println("═══════════════════════════════════════");
        System.out.println("   КНИЖНЫЙ МАГАЗИН — ВСЕ ПАТТЕРНЫ");
        System.out.println("═══════════════════════════════════════");

        BookStore store = new BookStore();
        PaymentStrategy paymentMethod = new CryptoPayment();

        Order order = Order.builder("Василий Козонков")
                .addBook(new Book("Война и мир", "Толстой", 1869, new BigDecimal("800"), 5))
                .addBook(new Book("1984", "Оруэлл", 1949, new BigDecimal("450"), 3))
                .deliveryType(DeliveryType.EXPRESS)
                .giftWrap(true)
                .insurance(true)
                .build();

        System.out.println("\n" + order);

        store.processOrder(order, paymentMethod);
    }
}