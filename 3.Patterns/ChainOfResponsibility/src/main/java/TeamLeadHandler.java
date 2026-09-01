public class TeamLeadHandler extends ApprovalHandler {

    private static final double LIMIT = 10_000;

    @Override
    protected boolean canHandle(PurchaseRequest request) {
        return request.getAmount().doubleValue() <= LIMIT;
    }

    @Override
    protected void approve(PurchaseRequest request) {
        System.out.println("✅ Тимлид одобрил: " + request.getItem() +
                " за " + request.getAmount() + " руб.");
    }

    @Override
    protected String getRole() {
        return "Тимлид";
    }
}