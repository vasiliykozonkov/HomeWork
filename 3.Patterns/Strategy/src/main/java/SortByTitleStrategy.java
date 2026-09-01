import java.util.Comparator;

public class SortByTitleStrategy implements BookSortingStrategy {

    @Override
    public Comparator<Book> getComparator() {
        return Comparator.comparing(Book::getTitle);
    }
}