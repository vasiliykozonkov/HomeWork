package com.HomeWork;

import java.math.BigDecimal;

public class CryptoPaymentAdapter implements PaymentProcessor {

    private static final String STATUS_PENDING = "Ожидание";
    private static final String STATUS_CONFIRMED = "CONFIRMED";
    private static final String STATUS_SUCCESS_PREFIX = "Оплачено криптовалютой (TX: ";
    private static final String STATUS_ERROR = "Ошибка оплаты криптовалютой";

    private final CryptoPaymentGateway cryptoGateway;
    private final String walletAddress;
    private String lastTransactionId;
    private String status = STATUS_PENDING;

    public CryptoPaymentAdapter(String walletAddress) {
        this.cryptoGateway = new CryptoPaymentGateway();
        this.walletAddress = walletAddress;
    }

    @Override
    public boolean pay(BigDecimal amount, String currency) {
        System.out.println("🔌 Адаптер: конвертируем вызов pay() в sendCrypto()...");

        CryptoPaymentGateway.TransactionResult result =
                cryptoGateway.sendCrypto(amount, walletAddress);

        lastTransactionId = result.getTransactionId();

        if (STATUS_CONFIRMED.equals(result.getStatus())) {
            status = STATUS_SUCCESS_PREFIX + lastTransactionId + ")";
            return true;
        } else {
            status = STATUS_ERROR;
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