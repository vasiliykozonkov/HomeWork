import java.util.ArrayList;
import java.util.List;

public class Order {
	private final String customerName;
	private final List<Book> books;
	private final String deliveryType;
	private final boolean giftWrap;
	private final boolean insurance;
	private final double totalAmount;

	private Order(Builder builder) {
		this.customerName = builder.customerName;
		this.books = builder.books;
		this.deliveryType = builder.deliveryType;
		this.giftWrap = builder.giftWrap;
		this.insurance = builder.insurance;
		this.totalAmount = builder.calculateTotal();
	}

	public String getCustomerName() {
		return customerName;
	}
	public List<Book> getBooks() {
		return books;
	}
	public String getDeliveryType() {
		return deliveryType;
	}
	public boolean isGiftWrap() {
		return giftWrap;
	}
	public boolean isInsurance() {
		return insurance;
	}
	public double getTotalAmount() {
		return totalAmount;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("📦 Заказ от " + customerName + "\n");
		for (Book book : books) sb.append("    ").append(book.getTitle()).append("\n");
		sb.append("    Доставка: ").append(deliveryType).append("\n");
		sb.append("   🎁 Упаковка: ").append(giftWrap ? "Да" : "Нет").append("\n");
		sb.append("   🛡️ Страховка: ").append(insurance ? "Да" : "Нет").append("\n");
		sb.append("   💰 Итого: ").append(String.format("%.2f", totalAmount)).append(" руб.\n");
		return sb.toString();
	}

	public static class Builder {
		private final String customerName;
		private final List<Book> books = new ArrayList<>();
		private String deliveryType = "Стандартная";
		private boolean giftWrap = false;
		private boolean insurance = false;

		public Builder(String customerName) {
			this.customerName = customerName;
		}
		public Builder addBook(Book book) {
			this.books.add(book);
			return this;
		}
		public Builder setDeliveryType(String deliveryType) {
			this.deliveryType = deliveryType;
			return this;
		}
		public Builder withGiftWrap(boolean giftWrap) {
			this.giftWrap = giftWrap;
			return this;
		}
		public Builder withInsurance(boolean insurance) {
			this.insurance = insurance;
			return this;
		}

		private double calculateTotal() {
			double total = books.stream().mapToDouble(Book::getPrice).sum();
			if (giftWrap) total += 200;
			if (insurance) total += total * 0.05;
			if ("Экспресс".equals(deliveryType)) total += 500;
			return total;
		}
		public Order build() {
			return new Order(this);
		}
	}
}