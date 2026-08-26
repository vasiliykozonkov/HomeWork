public class BooksCountValidator extends OrderValidator {
	protected boolean check(Order order) {
		return !order.getBooks().isEmpty();
	}
	protected String getCheckName() {
		return "Заказ не пустой";
	}
}