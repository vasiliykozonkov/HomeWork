public class SimplePayment implements PaymentProcessor {
	private String status = "Ожидание";

	@Override
	public boolean pay(double amount, String currency) {
		System.out.println("💳 Обработка платежа: " + amount + " " + currency);
		status = "Успешно оплачено через SimplePayment";
		return true;
	}

	@Override
	public String getPaymentStatus() {
		return status;
	}
}