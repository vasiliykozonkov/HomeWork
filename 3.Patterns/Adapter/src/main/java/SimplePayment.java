package com.HomeWork;

import java.math.BigDecimal;

public class SimplePayment implements PaymentProcessor {

    private static final String STATUS_PENDING = "Ожидание";
    private static final String STATUS_SUCCESS = "Успешно оплачено через SimplePayment";

    private String status = STATUS_PENDING;

    @Override
    public boolean pay(BigDecimal amount, String currency) {
        System.out.println("💳 Обработка платежа: " + amount + " " + currency);
        status = STATUS_SUCCESS;
        return true;
    }

    @Override
    public String getPaymentStatus() {
        return status;
    }
}