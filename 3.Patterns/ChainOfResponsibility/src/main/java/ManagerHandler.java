public class ManagerHandler extends ApprovalHandler {
	private static final double LIMIT = 50_000;

	@Override
	protected boolean canHandle(PurchaseRequest request) {
		return request.getAmount() <= LIMIT;
	}

	@Override
	protected void approve(PurchaseRequest request) {
		System.out.println("✅ Менеджер одобрил: " + request.getItem() +
						   " за " + request.getAmount() + " руб.");
	}

	@Override
	protected String getRole() {
		return "Менеджер";
	}
}