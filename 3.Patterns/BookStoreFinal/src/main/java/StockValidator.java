public class StockValidator extends OrderValidator {
	protected boolean check(Order order) {
		return order.getBooks().stream().allMatch(b -> b.getStock() > 0);
	}
	protected String getCheckName() {
		return "Наличие на складе";
	}
}