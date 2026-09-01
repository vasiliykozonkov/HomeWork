import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

public class OrderTests {

    @Test
    public void testOrderBuilder_MinimalOrder() {
        Order order = Order.builder("Василий").build();

        assertNotNull(order);
        assertEquals("Василий", order.getCustomerName());
        assertEquals(DeliveryType.STANDARD, order.getDeliveryType());
        assertFalse(order.isGiftWrap());
        assertFalse(order.isInsurance());
        assertEquals(BigDecimal.ZERO, order.getTotalAmount());
    }

    @Test
    public void testOrderBuilder_WithBooks() {
        Book book1 = new Book("Война и мир", "Толстой", 1869, new BigDecimal("800"), 5);
        Book book2 = new Book("1984", "Оруэлл", 1949, new BigDecimal("450"), 3);

        Order order = Order.builder("Петя")
                .addBook(book1)
                .addBook(book2)
                .build();

        assertEquals(2, order.getBooks().size());
        assertEquals(new BigDecimal("1250"), order.getTotalAmount());
    }

    @Test
    public void testOrderBuilder_WithGiftWrap() {
        Book book = new Book("Java для начинающих", "Смит", 2023, new BigDecimal("900"), 10);

        Order order = Order.builder("Маша")
                .addBook(book)
                .giftWrap(true)
                .build();

        assertTrue(order.isGiftWrap());
        assertEquals(new BigDecimal("1100"), order.getTotalAmount());
    }

    @Test
    public void testOrderBuilder_FullOrder() {
        Book book1 = new Book("Война и мир", "Толстой", 1869, new BigDecimal("800"), 5);
        Book book2 = new Book("1984", "Оруэлл", 1949, new BigDecimal("450"), 3);

        Order order = Order.builder("Василий Козонков")
                .addBook(book1)
                .addBook(book2)
                .deliveryType(DeliveryType.EXPRESS)
                .giftWrap(true)
                .insurance(true)
                .build();

        assertEquals(2, order.getBooks().size());
        assertTrue(order.isGiftWrap());
        assertTrue(order.isInsurance());
        assertEquals(DeliveryType.EXPRESS, order.getDeliveryType());

        assertEquals(new BigDecimal("2022.50"), order.getTotalAmount());
    }
}