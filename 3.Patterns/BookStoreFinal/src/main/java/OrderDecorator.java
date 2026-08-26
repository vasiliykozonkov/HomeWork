public abstract class OrderDecorator {
	protected Order order;
	public OrderDecorator(Order order) {
		this.order = order;
	}
	public void process() {
		System.out.println("   📋 Обработка заказа: " + order.getCustomerName());
	}
}