import java.math.BigDecimal;

public class AmountValidator extends OrderValidator {
    @Override
    protected boolean check(Order order) {
        return order.getTotalAmount().compareTo(BigDecimal.ZERO) > 0;
    }

    @Override
    protected String getCheckName() {
        return "Сумма заказа > 0";
    }
}