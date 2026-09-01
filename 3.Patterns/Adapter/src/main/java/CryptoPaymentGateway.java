package com.HomeWork;

import java.math.BigDecimal;

public class CryptoPaymentGateway {

    private static final String STATUS_CONFIRMED = "CONFIRMED";

    public TransactionResult sendCrypto(BigDecimal amount, String walletAddress) {
        System.out.println("₿ Отправка криптовалюты: " + amount + " на кошелёк " + walletAddress);
        return new TransactionResult("TX-" + System.currentTimeMillis(), STATUS_CONFIRMED);
    }

    public String checkTransaction(String transactionId) {
        return "Транзакция " + transactionId + ": подтверждена";
    }

    public static class TransactionResult {

        private final String transactionId;
        private final String status;

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