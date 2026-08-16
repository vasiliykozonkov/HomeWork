import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== ТЕСТ ИММУТАБЕЛЬНОСТИ ===\n");
        
        Department originalDept = new Department("IT", "Москва");
        List<String> originalSkills = new ArrayList<>();
        originalSkills.add("Java");
        originalSkills.add("SQL");
        Date originalDate = new Date();
        
        ImmutableEmployee employee = new ImmutableEmployee(
            "Василий", 35, originalDept, originalSkills, originalDate
        );
        
        System.out.println("1. Создан сотрудник:");
        System.out.println(employee);
        System.out.println();
        
        System.out.println("2. Пытаемся изменить оригинальные объекты...\n");
        originalDept.setName("HR");
        originalDept.setLocation("Питер");
        System.out.println("Изменили Department: " + originalDept);
        
        originalSkills.add("Python");
        originalSkills.remove("Java");
        System.out.println("Изменили список навыков: " + originalSkills);
        
        System.out.println();
        System.out.println("3. Проверяем сотрудника после изменений:");
        System.out.println(employee);
        System.out.println();
        
        System.out.println("=== РЕЗУЛЬТАТ ===");
        boolean deptUnchanged = employee.getDepartment() != null && 
                                employee.getDepartment().getName().equals("IT");
        boolean skillsUnchanged = employee.getSkills().size() == 2 && 
                                  employee.getSkills().contains("Java");
        
        if (deptUnchanged && skillsUnchanged) {
            System.out.println("✅ УСПЕХ! Класс действительно иммутабельный!");
        } else {
            System.out.println("❌ ОШИБКА! Класс изменяемый!");
        }
    }
}