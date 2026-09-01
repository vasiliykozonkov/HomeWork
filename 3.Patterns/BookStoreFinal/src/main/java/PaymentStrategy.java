import java.math.BigDecimal;

public interface PaymentStrategy {
    boolean pay(BigDecimal amount);
    String getMethodName();
}