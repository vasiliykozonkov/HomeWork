public class StockValidator extends OrderValidator {
    @Override
    protected boolean check(Order order) {
        return order.getBooks().stream().allMatch(b -> b.getStock() > 0);
    }

    @Override
    protected String getCheckName() {
        return "Наличие на складе";
    }
}