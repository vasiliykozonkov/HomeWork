import java.util.Comparator;
import java.util.List;

public class LibraryProcessor {

	private static final int MIN_YEAR = 2000;
	private static final int BOOKS_LIMIT = 3;

	public void processLibrary(List<Student> students) {
		students.stream()
		.peek(student -> {
			System.out.println(student);
			student.getBooks().stream()
			.sorted(Comparator.comparingInt(Book::getPages))
			.filter(book -> book.getYear() > MIN_YEAR)
			.forEach(book -> System.out.println("   📚 " + book));
		})
		.map(Student::getBooks)
		.flatMap(java.util.Collection::stream)
		.sorted(Comparator.comparingInt(Book::getPages))
		.distinct()
		.filter(book -> book.getYear() > MIN_YEAR)
		.limit(BOOKS_LIMIT)
		.map(Book::getYear)
		.findFirst()
		.ifPresentOrElse(
			year -> System.out.println("\nГод выпуска найденной книги: " + year),
			() -> System.out.println("Такая книга отсутствует")
		);
	}
}