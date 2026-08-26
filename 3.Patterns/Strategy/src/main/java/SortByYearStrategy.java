import java.util.Comparator;

public class SortByYearStrategy implements BookSortingStrategy {
	@Override
	public Comparator<Book> getComparator() {
		return Comparator.comparingInt(Book::getYear);
	}
}