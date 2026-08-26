import java.util.List;
import java.util.Optional;

public class LibraryProcessor {

	private static final int MIN_YEAR = 2000;
	private static final int BOOKS_LIMIT = 3;

	public void printStudentsAndBooks(List<Student> students, BookSortingStrategy strategy) {
		students.stream()
		.peek(student -> {
			System.out.println(student);

			student.getBooks().stream()
			.sorted(strategy.getComparator())
			.forEach(book -> System.out.println("    " + book));
		})
		.flatMap(student -> student.getBooks().stream())
		.sorted(strategy.getComparator())

		.distinct()
		.filter(book -> book.getYear() > MIN_YEAR)
		.limit(BOOKS_LIMIT)
		.map(book -> "   📚 " + book)

		.forEach(System.out::println);
	}

	public Optional<Integer> findFirstBookYear(List<Student> students, BookSortingStrategy strategy) {
		return students.stream()
			   .flatMap(student -> student.getBooks().stream())
			   .filter(book -> book.getYear() > MIN_YEAR)
			   .distinct()
			   .sorted(strategy.getComparator())

			   .limit(BOOKS_LIMIT)
			   .map(Book::getYear)
			   .findFirst();
	}
}