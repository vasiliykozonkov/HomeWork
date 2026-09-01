import java.util.Objects;

public class Book {

    private final String title;
    private final String author;
    private final int year;
    private final int pages;

    private Book(Builder builder) {
        this.title = builder.title;
        this.author = builder.author;
        this.year = builder.year;
        this.pages = builder.pages;
    }

    public static Builder builder(String title, String author) {
        return new Builder(title, author);
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
        return title + ", " + author + ", " + year + ", " + pages + " стр.";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return year == book.year && 
               pages == book.pages &&
               Objects.equals(title, book.title) && 
               Objects.equals(author, book.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, year, pages);
    }

    public static class Builder {
        
        private final String title;
        private final String author;
        private int year;
        private int pages;

        public Builder(String title, String author) {
            this.title = title;
            this.author = author;
        }

        public Builder year(int year) {
            this.year = year;
            return this;
        }

        public Builder pages(int pages) {
            this.pages = pages;
            return this;
        }

        public Book build() {
            return new Book(this);
        }
    }
}