import java.util.Comparator;

public class SortByPagesStrategy implements BookSortingStrategy {

    @Override
    public Comparator<Book> getComparator() {
        return Comparator.comparingInt(Book::getPages);
    }
}