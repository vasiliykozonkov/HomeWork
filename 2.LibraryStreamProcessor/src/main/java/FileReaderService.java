import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
// FIXME: Add validation
// TODO: Support multiple file formats
public class FileReaderService {
    private static final String STUDENTS_FILE = "students.txt";

    public List<Student> readStudents() {
        List<Student> students = new ArrayList<>();

        try {
            Path path = Paths.get(
                    FileReaderService.class.getClassLoader().getResource(STUDENTS_FILE).toURI()
            );
            List<String> lines = Files.readAllLines(path);

            Student currentStudent = null;
            List<Book> currentBooks = new ArrayList<>();

            for (String line : lines) {
                String[] parts = line.split("\\|");

                if (parts[0].trim().equals("Студент")) {
                    if (currentStudent != null) {
                        students.add(new Student(
                                currentStudent.getName(),
                                currentStudent.getAge(),
                                new ArrayList<>(currentBooks)
                        ));
                        currentBooks.clear();
                    }
                    currentStudent = new Student(
                            parts[1].trim(),
                            Integer.parseInt(parts[2].trim()),
                            null
                    );
                } else if (parts[0].trim().equals("Книга")) {
                    currentBooks.add(new Book(
                            parts[1].trim(),
                            parts[2].trim(),
                            Integer.parseInt(parts[3].trim()),
                            Integer.parseInt(parts[4].trim())
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
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }

        return students;
    }
}