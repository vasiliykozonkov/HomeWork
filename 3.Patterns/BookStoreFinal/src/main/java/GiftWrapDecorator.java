public class GiftWrapDecorator extends OrderDecorator {
    public GiftWrapDecorator(Order order) {
        super(order);
    }

    @Override
    public void process() {
        super.process();
        if (order.isGiftWrap()) {
            System.out.println("   🎁 Добавляем подарочную упаковку (+200 руб.)");
        }
    }
}