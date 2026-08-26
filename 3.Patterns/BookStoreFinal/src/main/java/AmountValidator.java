public class AmountValidator extends OrderValidator {
	protected boolean check(Order order) {
		return order.getTotalAmount() > 0;
	}
	protected String getCheckName() {
		return "Сумма заказа > 0";
	}
}