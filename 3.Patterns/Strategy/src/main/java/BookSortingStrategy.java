import java.util.Comparator;

public interface BookSortingStrategy {
	Comparator<Book> getComparator();
}