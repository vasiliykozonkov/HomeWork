import java.math.BigDecimal;

public class PurchaseRequest {

    private final String employeeName;
    private final String item;
    private final BigDecimal amount;

    public PurchaseRequest(String employeeName, String item, BigDecimal amount) {
        this.employeeName = employeeName;
        this.item = item;
        this.amount = amount;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public String getItem() {
        return item;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return String.format("Заявка от %s: %s на %s руб.", employeeName, item, amount);
    }
}