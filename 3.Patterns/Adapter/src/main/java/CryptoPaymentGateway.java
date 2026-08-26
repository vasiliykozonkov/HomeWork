public class CryptoPaymentGateway {

	public TransactionResult sendCrypto(double amount, String walletAddress) {
		System.out.println("₿ Отправка криптовалюты: " + amount + " на кошелёк " + walletAddress);
		return new TransactionResult("TX-" + System.currentTimeMillis(), "CONFIRMED");
	}

	public String checkTransaction(String transactionId) {
		return "Транзакция " + transactionId + ": подтверждена";
	}

	public static class TransactionResult {
		private String transactionId;
		private String status;

		public TransactionResult(String transactionId, String status) {
			this.transactionId = transactionId;
			this.status = status;
		}

		public String getTransactionId() {
			return transactionId;
		}
		public String getStatus() {
			return status;
		}
	}
}