public class RealBookCatalog implements BookCatalog {
	private Book[] books = {
		new Book("Война и мир", "Толстой", 1869, 800, 5),
		new Book("1984", "Оруэлл", 1949, 450, 3),
		new Book("Java для начинающих", "Смит", 2023, 900, 10)
	};
	public Book findBook(String title) {
		System.out.println("   🔍 Поиск в каталоге: " + title);
		for (Book book : books) if (book.getTitle().contains(title)) return book;
		return null;
	}
	public void updateStock(Book book, int newStock) {
		book.setStock(newStock);
		System.out.println("   📦 Остаток «" + book.getTitle() + "»: " + newStock);
	}
}