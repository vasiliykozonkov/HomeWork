public abstract class OrderDecorator {
    protected final Order order;

    public OrderDecorator(Order order) {
        this.order = order;
    }

    public void process() {
        System.out.println("   📋 Обработка заказа: " + order.getCustomerName());
    }
}