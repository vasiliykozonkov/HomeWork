import java.util.List;
import java.util.Optional;

public class LibraryProcessor {

    private static final int MIN_YEAR = 2000;
    private static final int BOOKS_LIMIT = 3;

    public void printStudentsAndBooks(List<Student> students) {
        students.forEach(student -> {
            System.out.println(student);
            student.getBooks().stream()
                    .distinct()
                    .filter(book -> book.getYear() > MIN_YEAR)
                    .sorted((b1, b2) -> Integer.compare(b1.getPages(), b2.getPages()))
                    .forEach(book -> System.out.println("Книга: " + book));
        });
    }

    public Optional<Integer> findFirstBookYear(List<Student> students) {
        return students.stream()
                .flatMap(student -> student.getBooks().stream())
                .sorted((b1, b2) -> Integer.compare(b1.getPages(), b2.getPages()))
                .distinct()
                .filter(book -> book.getYear() > MIN_YEAR)
                .limit(BOOKS_LIMIT)
                .map(Book::getYear)
                .findFirst();
    }
}