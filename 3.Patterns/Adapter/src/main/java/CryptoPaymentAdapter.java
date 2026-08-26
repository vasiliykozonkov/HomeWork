public class CryptoPaymentAdapter implements PaymentProcessor {
	private CryptoPaymentGateway cryptoGateway;
	private String walletAddress;
	private String lastTransactionId;
	private String status = "Ожидание";

	public CryptoPaymentAdapter(String walletAddress) {
		this.cryptoGateway = new CryptoPaymentGateway();
		this.walletAddress = walletAddress;
	}

	@Override
	public boolean pay(double amount, String currency) {

		System.out.println("🔌 Адаптер: конвертируем вызов pay() в sendCrypto()...");

		CryptoPaymentGateway.TransactionResult result =
			cryptoGateway.sendCrypto(amount, walletAddress);

		lastTransactionId = result.getTransactionId();

		if ("CONFIRMED".equals(result.getStatus())) {
			status = "Оплачено криптовалютой (TX: " + lastTransactionId + ")";
			return true;
		} else {
			status = "Ошибка оплаты криптовалютой";
			return false;
		}
	}

	@Override
	public String getPaymentStatus() {

		if (lastTransactionId != null) {
			return cryptoGateway.checkTransaction(lastTransactionId);
		}
		return status;
	}
}