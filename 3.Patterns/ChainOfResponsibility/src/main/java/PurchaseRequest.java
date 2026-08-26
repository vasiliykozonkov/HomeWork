public class PurchaseRequest {
	private String employeeName;
	private String item;
	private double amount;

	public PurchaseRequest(String employeeName, String item, double amount) {
		this.employeeName = employeeName;
		this.item = item;
		this.amount = amount;
	}

	public String getEmployeeName() {
		return employeeName;
	}
	public String getItem() {
		return item;
	}
	public double getAmount() {
		return amount;
	}

	@Override
	public String toString() {
		return String.format("Заявка от %s: %s на %.2f руб.", employeeName, item, amount);
	}
}