public abstract class ApprovalHandler {
	private ApprovalHandler nextHandler;

	public ApprovalHandler setNext(ApprovalHandler nextHandler) {
		this.nextHandler = nextHandler;
		return nextHandler;
	}

	public void handleRequest(PurchaseRequest request) {
		if (canHandle(request)) {
			approve(request);
		} else if (nextHandler != null) {
			System.out.println("➡️  " + getRole() + " передаёт заявку дальше...");
			nextHandler.handleRequest(request);
		} else {
			reject(request);
		}
	}

	protected abstract boolean canHandle(PurchaseRequest request);

	protected abstract void approve(PurchaseRequest request);

	protected void reject(PurchaseRequest request) {
		System.out.println("❌ Заявка отклонена: сумма " + request.getAmount() +
						   " руб. превышает все лимиты.");
	}

	protected abstract String getRole();
}