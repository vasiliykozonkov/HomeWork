public class BookCatalogProxy implements BookCatalog {
	private RealBookCatalog realCatalog;
	public Book findBook(String title) {
		if (realCatalog == null) {
			System.out.println("   ⏳ Каталог ещё не загружен, инициализируем...");
			realCatalog = new RealBookCatalog();
		}
		return realCatalog.findBook(title);
	}
	public void updateStock(Book book, int newStock) {
		if (realCatalog == null) realCatalog = new RealBookCatalog();
		realCatalog.updateStock(book, newStock);
	}
}