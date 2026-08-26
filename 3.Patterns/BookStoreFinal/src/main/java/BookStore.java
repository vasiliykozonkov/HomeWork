public class BookStore {
	private BookCatalog catalog;
	private OrderValidator validatorChain;
	private LegacyInventoryAdapter legacyAdapter;

	public BookStore() {
		this.catalog = new BookCatalogProxy();
		this.legacyAdapter = new LegacyInventoryAdapter();

		OrderValidator stockCheck = new StockValidator();
		OrderValidator amountCheck = new AmountValidator();
		OrderValidator booksCheck = new BooksCountValidator();
		stockCheck.setNext(amountCheck).setNext(booksCheck);
		this.validatorChain = stockCheck;
	}

	public boolean processOrder(Order order, PaymentStrategy paymentStrategy) {
		System.out.println("\n═══════════════════════════════════════");
		System.out.println("🛒 ОБРАБОТКА ЗАКАЗА");
		System.out.println("═══════════════════════════════════════");

		if (!validatorChain.validate(order)) {
			System.out.println("❌ Заказ отклонён!");
			return false;
		}

		new GiftWrapDecorator(order).process();
		new InsuranceDecorator(order).process();
		new ExpressDeliveryDecorator(order).process();

		System.out.println("\n📋 Шаг 3: Оплата через " + paymentStrategy.getMethodName());
		if (!paymentStrategy.pay(order.getTotalAmount())) return false;

		System.out.println("\n📋 Шаг 4: Обновление остатков");
		for (Book book : order.getBooks()) catalog.updateStock(book, book.getStock() - 1);

		System.out.println("\n📋 Шаг 5: Синхронизация с legacy-системой");
		legacyAdapter.syncOrder(order);

		System.out.println("\n✅ Заказ успешно оформлен!");
		return true;
	}
}