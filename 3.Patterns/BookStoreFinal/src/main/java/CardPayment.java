public class CardPayment implements PaymentStrategy {
	public boolean pay(double amount) {
		System.out.println("    💳 Оплата картой: " + String.format("%.2f", amount) + " руб.");
		return true;
	}
	public String getMethodName() {
		return "Банковская карта";
	}
}