public class Main {
	public static void main(String[] args) {
		System.out.println("═══════════════════════════════════════");
		System.out.println("   КНИЖНЫЙ МАГАЗИН — ВСЕ ПАТТЕРНЫ");
		System.out.println("═══════════════════════════════════════");

		BookStore store = new BookStore();
		PaymentStrategy paymentMethod = new CryptoPayment();

		Order order = new Order.Builder("Василий Козонков")
		.addBook(new Book("Война и мир", "Толстой", 1869, 800, 5))
		.addBook(new Book("1984", "Оруэлл", 1949, 450, 3))
		.setDeliveryType("Экспресс")
		.withGiftWrap(true)
		.withInsurance(true)
		.build();

		System.out.println("\n" + order);

		store.processOrder(order, paymentMethod);
	}
}