import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

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

                if (parts.length < 2) {
                    System.err.println("️ Пропущена некорректная строка: " + line);
                    continue;
                }

                String type = parts[0].trim();
                
                if (type.equals("Студент")) {
                    if (parts.length < 3) {
                        System.err.println("⚠️ Некорректная строка студента: " + line);
                        continue;
                    }
                    
                    if (currentStudent != null) {
                        students.add(currentStudent.builder(currentStudent.getName(), currentStudent.getAge())
                                .books(new ArrayList<>(currentBooks))
                                .build());
                        currentBooks.clear();
                    }
                    
                    String name = parts[1].trim();
                    int age = Integer.parseInt(parts[2].trim());
                    
                    currentStudent = Student.builder(name, age).build();
                    
                } else if (type.equals("Книга")) {
                    if (parts.length < 5) {
                        System.err.println("⚠️ Некорректная строка книги: " + line);
                        continue;
                    }
                    
                    String title = parts[1].trim();
                    String author = parts[2].trim();
                    int year = Integer.parseInt(parts[3].trim());
                    int pages = Integer.parseInt(parts[4].trim());
                    
                    currentBooks.add(Book.builder(title, author)
                            .year(year)
                            .pages(pages)
                            .build());
                }
            }

            if (currentStudent != null) {
                students.add(currentStudent.builder(currentStudent.getName(), currentStudent.getAge())
                        .books(new ArrayList<>(currentBooks))
                        .build());
            }
            
        } catch (IOException | URISyntaxException e) {
            System.err.println("❌ Ошибка чтения файла: " + e.getMessage());
            e.printStackTrace();
        }

        return students;
    }
}