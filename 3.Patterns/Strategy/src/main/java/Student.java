import java.util.List;

public class Student {

    private final String name;
    private final int age;
    private final List<Book> books;

    private Student(Builder builder) {
        this.name = builder.name;
        this.age = builder.age;
        this.books = builder.books;
    }

    public static Builder builder(String name, int age) {
        return new Builder(name, age);
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public List<Book> getBooks() {
        return books;
    }

    @Override
    public String toString() {
        return "Студент " + name + ", " + age + " лет, книг: " + books.size();
    }

    public static class Builder {
        
        private final String name;
        private final int age;
        private List<Book> books;

        public Builder(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public Builder books(List<Book> books) {
            this.books = books;
            return this;
        }

        public Student build() {
            return new Student(this);
        }
    }
}