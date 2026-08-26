import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class LibraryApp {
	public static void main(String[] args) {
		FileReaderService readerService = new FileReaderService();
		LibraryProcessor processor = new LibraryProcessor();

		List<Student> students = readerService.readStudents();

		System.out.println("Выберите способ сортировки:");
		System.out.println("1. По количеству страниц");
		System.out.println("2. По году издания");
		System.out.println("3. По названию");
		System.out.print("Ваш выбор (1-3): ");

		Scanner scanner = new Scanner(System.in);
		int choice = scanner.nextInt();

		BookSortingStrategy strategy;
		switch (choice) {
		case 1:
			strategy = new SortByPagesStrategy();
			break;
		case 2:
			strategy = new SortByYearStrategy();
			break;
		case 3:
			strategy = new SortByTitleStrategy();
			break;
		default:
			System.out.println("Неверный выбор, используем сортировку по страницам");
			strategy = new SortByPagesStrategy();
		}

		System.out.println("\n=== Сортировка: " + strategy.getClass().getSimpleName() + " ===\n");

		processor.printStudentsAndBooks(students, strategy);

		Optional<Integer> resultYear = processor.findFirstBookYear(students, strategy);

		System.out.println("\n=== РЕЗУЛЬТАТ ===");
		resultYear.ifPresentOrElse(
			year -> System.out.println("Год выпуска найденной книги: " + year),
			() -> System.out.println("Такая книга отсутствует")
		);
	}
}