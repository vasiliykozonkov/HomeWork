public class CryptoPayment implements PaymentStrategy {
	public boolean pay(double amount) {
		System.out.println("    ₿ Оплата криптовалютой: " + String.format("%.2f", amount) + " руб.");
		return true;
	}
	public String getMethodName() {
		return "Криптовалюта";
	}
}