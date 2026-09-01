import java.math.BigDecimal;

public class CardPayment implements PaymentStrategy {
    @Override
    public boolean pay(BigDecimal amount) {
        System.out.println("    💳 Оплата картой: " + amount + " руб.");
        return true;
    }

    @Override
    public String getMethodName() {
        return "Банковская карта";
    }
}