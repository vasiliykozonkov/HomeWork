import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class OrderTests {

	@Test
	public void testOrderBuilder_MinimalOrder() {
		Order order = new Order.Builder("Василий").build();

		assertNotNull(order);
		assertEquals("Василий", order.getCustomerName());
		assertEquals("Стандартная", order.getDeliveryType());
		assertFalse(order.isGiftWrap());
		assertFalse(order.isInsurance());
	}

	@Test
	public void testOrderBuilder_WithBooks() {
		Book book1 = new Book("Война и мир", "Толстой", 1869, 800, 5);
		Book book2 = new Book("1984", "Оруэлл", 1949, 450, 3);

		Order order = new Order.Builder("Петя")
		.addBook(book1)
		.addBook(book2)
		.build();

		assertEquals(2, order.getBooks().size());
		assertEquals(1250.0, order.getTotalAmount(), 0.01); // 800 + 450
	}

	@Test
	public void testOrderBuilder_WithGiftWrap() {
		Book book = new Book("Java для начинающих", "Смит", 2023, 900, 10);

		Order order = new Order.Builder("Маша")
		.addBook(book)
		.withGiftWrap(true)
		.build();

		assertTrue(order.isGiftWrap());
		assertEquals(1100.0, order.getTotalAmount(), 0.01); // 900 + 200
	}

	@Test
	public void testOrderBuilder_FullOrder() {
		Book book1 = new Book("Война и мир", "Толстой", 1869, 800, 5);
		Book book2 = new Book("1984", "Оруэлл", 1949, 450, 3);

		Order order = new Order.Builder("Василий Козонков")
		.addBook(book1)
		.addBook(book2)
		.setDeliveryType("Экспресс")
		.withGiftWrap(true)
		.withInsurance(true)
		.build();

		assertEquals(2, order.getBooks().size());
		assertTrue(order.isGiftWrap());
		assertTrue(order.isInsurance());
		assertEquals("Экспресс", order.getDeliveryType());

		// 800 + 450 = 1250, +200 упаковка = 1450, +5% страховка (72.5) = 1522.5, +500 экспресс = 2022.5
		assertEquals(2022.5, order.getTotalAmount(), 0.01);
	}
}