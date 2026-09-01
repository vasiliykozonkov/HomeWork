import java.math.BigDecimal;
import java.util.Objects;

public class Book {
    private final String title;
    private final String author;
    private final int year;
    private final BigDecimal price;
    private int stock; // Оставляем mutable, так как каталог обновляет остаток

    public Book(String title, String author, int year, BigDecimal price, int stock) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.price = price;
        this.stock = stock;
    }

    public String getTitle() {
        return title;
    }

    public int getStock() {
        return stock;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return String.format("«%s» (%s, %d г.) — %s руб. [остаток: %d]", 
                title, author, year, price, stock);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return year == book.year && stock == book.stock &&
               Objects.equals(title, book.title) && 
               Objects.equals(author, book.author) &&
               Objects.equals(price, book.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, year, price, stock);
    }
}