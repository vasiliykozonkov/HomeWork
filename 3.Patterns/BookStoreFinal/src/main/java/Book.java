public class Book {
	private String title;
	private String author;
	private int year;
	private double price;
	private int stock;

	public Book(String title, String author, int year, double price, int stock) {
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
	public double getPrice() {
		return price;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	@Override
	public String toString() {
		return String.format("«%s» (%s, %d г.) — %.2f руб. [остаток: %d]", title, author, year, price, stock);
	}
}