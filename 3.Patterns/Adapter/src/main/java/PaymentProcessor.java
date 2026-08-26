public interface PaymentProcessor {
	boolean pay(double amount, String currency);
	String getPaymentStatus();
}