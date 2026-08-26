public abstract class OrderValidator {
	private OrderValidator next;
	public OrderValidator setNext(OrderValidator next) {
		this.next = next;
		return next;
	}

	public boolean validate(Order order) {
		if (!check(order)) {
			System.out.println("   ❌ Проверка не пройдена: " + getCheckName());
			return false;
		}
		return next != null ? next.validate(order) : true;
	}
	protected abstract boolean check(Order order);
	protected abstract String getCheckName();
}