public class Book {
	private String title;
	private String author;
	private int year;
	private int pages;

	public Book(String title, String author, int year, int pages) {
		this.title = title;
		this.author = author;
		this.year = year;
		this.pages = pages;
	}

	public String getTitle() {
		return title;
	}
	public String getAuthor() {
		return author;
	}
	public int getYear() {
		return year;
	}
	public int getPages() {
		return pages;
	}

	@Override
	public String toString() {
		return "" + title + ", " + author + ", " + year + ", " + pages + " стр.";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Book)) return false;
		Book book = (Book) o;
		return year == book.year && pages == book.pages &&
			   title.equals(book.title) && author.equals(book.author);
	}

	@Override
	public int hashCode() {
		return java.util.Objects.hash(title, author, year, pages);
	}
}