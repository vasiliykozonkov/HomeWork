import java.util.List;

public class LibraryApp {
	public static void main(String[] args) {
		FileReaderService readerService = new FileReaderService();
		LibraryProcessor processor = new LibraryProcessor();

		List<Student> students = readerService.readStudents();

		processor.processLibrary(students);
	}
}