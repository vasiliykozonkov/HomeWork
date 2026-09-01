public class InsuranceDecorator extends OrderDecorator {
    public InsuranceDecorator(Order order) {
        super(order);
    }

    @Override
    public void process() {
        super.process();
        if (order.isInsurance()) {
            System.out.println("   🛡️ Добавляем страховку (+5% от суммы)");
        }
    }
}