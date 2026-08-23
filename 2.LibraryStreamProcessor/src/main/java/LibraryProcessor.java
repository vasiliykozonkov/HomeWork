import java.util.List;
import java.util.Optional;

public class LibraryProcessor {

    private static final int MIN_YEAR = 2000;
    private static final int BOOKS_LIMIT = 3;

    public void printStudentsAndBooks(List<Student> students) {
        students.stream()
                .flatMap(student -> student.getBooks().stream()
                        .sorted((b1, b2) -> Integer.compare(b1.getPages(), b2.getPages()))
                        .distinct()
                        .filter(book -> book.getYear() > MIN_YEAR)
                        .map(book -> student + " → Книга: " + book))
                .forEach(System.out::println);
    }

    public Optional<Integer> findFirstBookYear(List<Student> students) {
        return students.stream()
                .flatMap(student -> student.getBooks().stream())
                .filter(book -> book.getYear() > MIN_YEAR)
                .distinct()
                .sorted((b1, b2) -> Integer.compare(b1.getPages(), b2.getPages()))
                .limit(BOOKS_LIMIT)
                .map(Book::getYear)
                .findFirst();
    }

}