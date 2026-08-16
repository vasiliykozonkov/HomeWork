import java.util.ArrayList;
import java.util.Date;
import java.util.List;import java.util.Collections;

public final class ImmutableEmployee {
    private final String name;
    private final int age;
    private final Department department;
    private final List<String> skills;
    private final Date hireDate;
    
    public ImmutableEmployee(String name, int age, Department department, 
                             List<String> skills, Date hireDate) {
        this.name = name;
        this.age = age;
        
        // Проверка на null (добавлено по замечанию)
        this.department = department != null 
            ? new Department(department) 
            : null;
        
        // List.copyOf вместо unmodifiableList (добавлено по замечанию)
        this.skills = skills != null 
            ? List.copyOf(skills) 
            : Collections.emptyList();
        
        this.hireDate = hireDate != null 
            ? new Date(hireDate.getTime()) 
            : null;
    }
    
    public String getName() {
        return name;
    }
    
    public int getAge() {
        return age;
    }
    
    public Department getDepartment() {
        return department != null ? new Department(department) : null;
    }
    
    public List<String> getSkills() {
        return skills; // List.copyOf уже создал неизменяемый список
    }
    
    public Date getHireDate() {
        return hireDate != null ? new Date(hireDate.getTime()) : null;
    }
    
    @Override
    public String toString() {
        return "ImmutableEmployee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", department=" + department +
                ", skills=" + skills +
                ", hireDate=" + hireDate +
                '}';
    }
}