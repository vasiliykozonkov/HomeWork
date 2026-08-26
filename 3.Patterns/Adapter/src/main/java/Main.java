public class Main {
	public static void main(String[] args) {
		System.out.println("═══════════════════════════════════════");
		System.out.println("   ПАТТЕРН ADAPTER: ПЛАТЕЖНЫЕ СИСТЕМЫ");
		System.out.println("═══════════════════════════════════════\n");

		OnlineStore store = new OnlineStore();
		System.out.println("📋 ТЕСТ 1: Старая платежная система (SimplePayment)");
		PaymentProcessor oldPayment = new SimplePayment();
		store.setPaymentProcessor(oldPayment);
		store.checkout(1500.0, "RUB");
		System.out.println();

		System.out.println("📋 ТЕСТ 2: Новая платежная система через адаптер (CryptoPayment)");
		PaymentProcessor cryptoPayment = new CryptoPaymentAdapter("1A1zP1eP5QGefi2DMPTfTL5SLmv7DivfNa");
		store.setPaymentProcessor(cryptoPayment);
		store.checkout(0.05, "BTC");
		System.out.println();

		System.out.println("📋 ТЕСТ 3: Магазин работает с обоими процессорами одинаково");
		System.out.println("Магазин видит только интерфейс PaymentProcessor!");
		System.out.println("Он не знает, что внутри — SimplePayment или CryptoPaymentAdapter");
	}
}