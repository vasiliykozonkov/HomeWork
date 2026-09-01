public class BooksCountValidator extends OrderValidator {
    @Override
    protected boolean check(Order order) {
        return !order.getBooks().isEmpty();
    }

    @Override
    protected String getCheckName() {
        return "Заказ не пустой";
    }
}