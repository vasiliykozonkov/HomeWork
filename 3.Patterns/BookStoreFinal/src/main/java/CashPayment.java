import java.math.BigDecimal;

public class CashPayment implements PaymentStrategy {
    @Override
    public boolean pay(BigDecimal amount) {
        System.out.println("    💵 Оплата наличными: " + amount + " руб.");
        return true;
    }

    @Override
    public String getMethodName() {
        return "Наличные";
    }
}