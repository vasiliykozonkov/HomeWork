public class ExpressDeliveryDecorator extends OrderDecorator {
	public ExpressDeliveryDecorator(Order order) {
		super(order);
	}
	public void process() {
		super.process();
		if ("Экспресс".equals(order.getDeliveryType())) System.out.println("   ⚡ Оформляем экспресс-доставку (+500 руб.)");
	}
}