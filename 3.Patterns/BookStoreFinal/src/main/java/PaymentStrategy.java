public interface PaymentStrategy {
	boolean pay(double amount);
	String getMethodName();
}