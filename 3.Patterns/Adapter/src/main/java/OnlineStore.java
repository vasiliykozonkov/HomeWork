public class OnlineStore {
	private PaymentProcessor paymentProcessor;

	public void setPaymentProcessor(PaymentProcessor paymentProcessor) {
		this.paymentProcessor = paymentProcessor;
	}

	public void checkout(double amount, String currency) {
		System.out.println("🛒 Оформление заказа на сумму: " + amount + " " + currency);

		boolean success = paymentProcessor.pay(amount, currency);

		if (success) {
			System.out.println("✅ Оплата прошла успешно!");
			System.out.println("📋 Статус: " + paymentProcessor.getPaymentStatus());
		} else {
			System.out.println("❌ Оплата не прошла!");
			System.out.println("📋 Статус: " + paymentProcessor.getPaymentStatus());
		}
	}
}