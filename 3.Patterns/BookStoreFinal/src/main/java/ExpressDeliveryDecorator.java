public class ExpressDeliveryDecorator extends OrderDecorator {
    public ExpressDeliveryDecorator(Order order) {
        super(order);
    }

    @Override
    public void process() {
        super.process();
        if (order.getDeliveryType() == DeliveryType.EXPRESS) {
            System.out.println("   ⚡ Оформляем экспресс-доставку (+500 руб.)");
        }
    }
}