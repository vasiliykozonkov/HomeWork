public interface BookCatalog {
    Book findBook(String title);
    void updateStock(Book book, int newStock);
}