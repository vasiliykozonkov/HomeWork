public class CashPayment implements PaymentStrategy {
	public boolean pay(double amount) {
		System.out.println("    💵 Оплата наличными: " + String.format("%.2f", amount) + " руб.");
		return true;
	}
	public String getMethodName() {
		return "Наличные";
	}
}