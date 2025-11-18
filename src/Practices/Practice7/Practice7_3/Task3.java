package Practice7_3;
import java.util.*;
import Practice7_3.Classes.Student;

public class Task3 {
    public static void task3(){

        Calendar cal = Calendar.getInstance();
        Calendar cal1 = Calendar.getInstance();

        cal.set(2000, Calendar.MARCH, 15);
        cal1.set(2005, Calendar.OCTOBER,16);

        Student student = new Student("Иван Петров", cal.getTime());
        Student student1 = new Student("Иван Золо", cal1.getTime());

        System.out.println("=== Различные форматы вывода даты рождения ===");
        System.out.println(student.toString("short")+" - короткий формат");
        System.out.println(student.toString("medium")+" - средний формат");
        System.out.println(student.toString("long")+" - длинный формат");
        System.out.println(student.toString("full")+" - полный формат");
        System.out.println(student.toString("system")+" - системный формат");
        System.out.println(student.toString()+" - формат по умолячанию(средний)");

        System.out.println("\n=== Дополнительная информация ===");
        System.out.println("Короткий формат: " + student1.getFormattedBirthDate("short"));
        System.out.println("День рождения сегодня: " + student1.isBirthdayToday());
        System.out.println("Дней до дня рождения: " + student1.getDaysUntilBirthday());

        Calendar cal2 = Calendar.getInstance();
        cal2.set(1999, Calendar.DECEMBER, 5);
        Student student2 = new Student("Мария Сидорова", cal2.getTime());

        System.out.println("\n=== Второй студент ===");
        System.out.println(student2.toString("long"));
    }
}

