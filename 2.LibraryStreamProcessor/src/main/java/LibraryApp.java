import java.util.List;
import java.util.Optional;

public class LibraryApp {
	public static void main(String[] args) {
		FileReaderService readerService = new FileReaderService();
		LibraryProcessor processor = new LibraryProcessor();

		List<Student> students = readerService.readStudents();

		processor.printStudentsAndBooks(students);

		Optional<Integer> resultYear = processor.findFirstBookYear(students);

		System.out.println("\n=== РЕЗУЛЬТАТ ===");
		resultYear.ifPresentOrElse(
				year -> System.out.println("Год выпуска найденной книги: " + year),
				() -> System.out.println("Такая книга отсутствует")
		);
	}
}