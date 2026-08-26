public class DirectorHandler extends ApprovalHandler {
	private static final double LIMIT = 200_000;

	@Override
	protected boolean canHandle(PurchaseRequest request) {
		return request.getAmount() <= LIMIT;
	}

	@Override
	protected void approve(PurchaseRequest request) {
		System.out.println("✅ Директор одобрил: " + request.getItem() +
						   " за " + request.getAmount() + " руб.");
	}

	@Override
	protected String getRole() {
		return "Директор";
	}
}