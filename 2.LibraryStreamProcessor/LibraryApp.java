import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LibraryApp {
	public static void main(String[] args) {
		List<Student> students = new ArrayList<>();

		try (BufferedReader reader = new BufferedReader(
				new FileReader("/storage/emulated/0/Documents/Projects/HomeWork/2.LibraryStreamProcessor/src/main/java/students.txt"))) {
			String line;
			Student currentStudent = null;
			List<Book> currentBooks = new ArrayList<>();

			while ((line = reader.readLine()) != null) {
				String[] parts = line.split("\\|");

				if (parts[0].equals("Студент")) {
					if (currentStudent != null) {
						students.add(new Student(
										 currentStudent.getName(),
										 currentStudent.getAge(),
										 new ArrayList<>(currentBooks)
									 ));
						currentBooks.clear();
					}
					currentStudent = new Student(parts[1], Integer.parseInt(parts[2]), null);
				} else if (parts[0].equals("Книга")) {
					currentBooks.add(new Book(
										 parts[1],
										 parts[2],
										 Integer.parseInt(parts[3]),
										 Integer.parseInt(parts[4])
									 ));
				}
			}

			if (currentStudent != null) {
				students.add(new Student(
								 currentStudent.getName(),
								 currentStudent.getAge(),
								 new ArrayList<>(currentBooks)
							 ));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		students.forEach(student -> {
			System.out.println(student);
			student.getBooks().stream()
			.sorted((b1, b2) -> Integer.compare(b1.getPages(), b2.getPages()))
			.forEach(book -> System.out.println("Книга: " + book));
		});

		Optional<Integer> resultYear = students.stream()
									   .flatMap(student -> student.getBooks().stream())
									   .sorted((b1, b2) -> Integer.compare(b1.getPages(), b2.getPages()))
									   .distinct()
									   .filter(book -> book.getYear() > 2000)
									   .limit(3)
									   .map(Book::getYear)
									   .findFirst();

		System.out.println("\n=== РЕЗУЛЬТАТ ===");
		resultYear.ifPresentOrElse(
			year -> System.out.println("Год выпуска найденной книги: " + year),
			() -> System.out.println("Такая книга отсутствует")
		);
	}
}