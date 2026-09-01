package com.HomeWork;

import java.math.BigDecimal;

public interface PaymentProcessor {

    boolean pay(BigDecimal amount, String currency);

    String getPaymentStatus();
}