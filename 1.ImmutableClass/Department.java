public class Department {
    private String name;
    private String location;
    
    public Department(String name, String location) {
        this.name = name;
        this.location = location;
    }
    
    // Конструктор копирования (добавлено по замечанию)
    public Department(Department other) {
        this.name = other.name;
        this.location = other.location;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }
    
    public String getName() {
        return name;
    }
    
    public String getLocation() {
        return location;
    }
    
    @Override
    public String toString() {
        return "Department{name='" + name + "', location='" + location + "'}";
    }
}