import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private final String customerName;
    private final List<Book> books;
    private final DeliveryType deliveryType;
    private final boolean giftWrap;
    private final boolean insurance;
    private final BigDecimal totalAmount;

    private Order(Builder builder) {
        this.customerName = builder.customerName;
        this.books = new ArrayList<>(builder.books);
        this.deliveryType = builder.deliveryType;
        this.giftWrap = builder.giftWrap;
        this.insurance = builder.insurance;
        this.totalAmount = builder.calculateTotal();
    }

    public static Builder builder(String customerName) {
        return new Builder(customerName);
    }

    public String getCustomerName() {
        return customerName;
    }

    public List<Book> getBooks() {
        return books;
    }

    public DeliveryType getDeliveryType() {
        return deliveryType;
    }

    public boolean isGiftWrap() {
        return giftWrap;
    }

    public boolean isInsurance() {
        return insurance;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("📦 Заказ от " + customerName + "\n");
        for (Book book : books) {
            sb.append("    - ").append(book.getTitle()).append("\n");
        }
        sb.append("    Доставка: ").append(deliveryType.getDisplayName()).append("\n");
        sb.append("   🎁 Упаковка: ").append(giftWrap ? "Да" : "Нет").append("\n");
        sb.append("   🛡️ Страховка: ").append(insurance ? "Да" : "Нет").append("\n");
        sb.append("   💰 Итого: ").append(totalAmount).append(" руб.\n");
        return sb.toString();
    }

    public static class Builder {
        private final String customerName;
        private final List<Book> books = new ArrayList<>();
        private DeliveryType deliveryType = DeliveryType.STANDARD;
        private boolean giftWrap = false;
        private boolean insurance = false;

        public Builder(String customerName) {
            this.customerName = customerName;
        }

        public Builder addBook(Book book) {
            this.books.add(book);
            return this;
        }

        public Builder deliveryType(DeliveryType deliveryType) {
            this.deliveryType = deliveryType;
            return this;
        }

        public Builder giftWrap(boolean giftWrap) {
            this.giftWrap = giftWrap;
            return this;
        }

        public Builder insurance(boolean insurance) {
            this.insurance = insurance;
            return this;
        }

        private BigDecimal calculateTotal() {
            BigDecimal total = books.stream()
                    .map(Book::getPrice)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            if (giftWrap) {
                total = total.add(new BigDecimal("200"));
            }
            if (insurance) {
                total = total.add(total.multiply(new BigDecimal("0.05")));
            }
            if (deliveryType == DeliveryType.EXPRESS) {
                total = total.add(new BigDecimal("500"));
            }
            return total;
        }

        public Order build() {
            return new Order(this);
        }
    }
}