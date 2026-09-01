import java.math.BigDecimal;

public class RealBookCatalog implements BookCatalog {
    private final Book[] books = {
        new Book("Война и мир", "Толстой", 1869, new BigDecimal("800"), 5),
        new Book("1984", "Оруэлл", 1949, new BigDecimal("450"), 3),
        new Book("Java для начинающих", "Смит", 2023, new BigDecimal("900"), 10)
    };

    @Override
    public Book findBook(String title) {
        System.out.println("   🔍 Поиск в каталоге: " + title);
        for (Book book : books) {
            if (book.getTitle().contains(title)) {
                return book;
            }
        }
        return null;
    }

    @Override
    public void updateStock(Book book, int newStock) {
        book.setStock(newStock);
        System.out.println("   📦 Остаток «" + book.getTitle() + "»: " + newStock);
    }
}