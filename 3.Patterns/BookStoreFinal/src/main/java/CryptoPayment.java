import java.math.BigDecimal;

public class CryptoPayment implements PaymentStrategy {
    @Override
    public boolean pay(BigDecimal amount) {
        System.out.println("    ₿ Оплата криптовалютой: " + amount + " руб.");
        return true;
    }

    @Override
    public String getMethodName() {
        return "Криптовалюта";
    }
}