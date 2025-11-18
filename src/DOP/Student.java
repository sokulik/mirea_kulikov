package DOP;

public class Student implements Comparable<Student> {
    private String name;
    private int id;
    private double gpa;

    public Student(String name, int id, double gpa) {
        this.name = name;
        this.id = id;
        this.gpa = gpa;
    }

    // Геттеры
    public String getName() { return name; }
    public int getId() { return id; }
    public double getGpa() { return gpa; }

    // Реализация Comparable для сортировки по ID
    @Override
    public int compareTo(Student other) {
        return Integer.compare(this.id, other.id);
    }

    @Override
    public String toString() {
        return String.format("Student{name='%s', id=%d, gpa=%.2f}", name, id, gpa);
    }
}