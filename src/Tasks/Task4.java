package Tasks;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.Random;
import static DOP.DOP_FUNC.showMessageAndReturn;

public class Task4 extends JFrame {
    private JTextArea outputArea;
    private JButton generateButton, sortButton, backButton;
    private CustomStudent[] students;

    public Task4(JFrame mainMenuFrame) {
        super("Собственная Comparable - Задание 4");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);

        initComponents();
        layoutComponents();
        addListeners(mainMenuFrame);
    }

    private void initComponents() {
        outputArea = new JTextArea(20, 60);
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 12));

        generateButton = new JButton("Сгенерировать студентов");
        sortButton = new JButton("Сортировать (Comparable)");
        backButton = new JButton("В главное меню");

        backButton.setBackground(new Color(70, 130, 180));
        backButton.setForeground(Color.WHITE);

        sortButton.setEnabled(false);
    }

    private void layoutComponents() {
        setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(generateButton);
        buttonPanel.add(sortButton);

        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Результаты"));

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.add(backButton);

        add(buttonPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        ((JComponent) getContentPane()).setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    private void addListeners(final JFrame mainMenuFrame) {
        generateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                generateStudents();
            }
        });

        sortButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sortStudents();
            }
        });

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                mainMenuFrame.setVisible(true);
            }
        });

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                mainMenuFrame.setVisible(true);
            }
        });
    }

    private void generateStudents() {
        String[] names = {"Иван", "Мария", "Петр", "Анна", "Сергей", "Ольга", "Алексей", "Елена"};
        String[] faculties = {"ФИИТ", "ПМИ", "ИВТ", "ФИЗ", "МАТ"};
        Random random = new Random();

        students = new CustomStudent[8];
        for (int i = 0; i < students.length; i++) {
            String name = names[random.nextInt(names.length)] + " " + (i + 1);
            int id = random.nextInt(900) + 100;
            double gpa = 2.0 + random.nextDouble() * 3.0;
            String faculty = faculties[random.nextInt(faculties.length)];
            int year = random.nextInt(4) + 1;

            students[i] = new CustomStudent(name, id, gpa, faculty, year);
        }

        outputArea.setText("Сгенерированный массив студентов (несортированный):\n");
        outputArea.append("===================================================\n");
        for (int i = 0; i < students.length; i++) {
            outputArea.append((i + 1) + ". " + students[i] + "\n");
        }

        outputArea.append("\nКритерии сортировки (собственная реализация Comparable):\n");
        outputArea.append("1. По факультету (алфавитный порядок)\n");
        outputArea.append("2. По курсу (по возрастанию)\n");
        outputArea.append("3. По GPA (по убыванию)\n");
        outputArea.append("4. По ID (по возрастанию)\n");

        sortButton.setEnabled(true);
    }

    private void sortStudents() {
        if (students == null) return;

        CustomStudent[] sortedStudents = Arrays.copyOf(students, students.length);

        Arrays.sort(sortedStudents);

        outputArea.append("\nОтсортированный массив (используя собственную Comparable):\n");
        outputArea.append("==========================================================\n");
        for (int i = 0; i < sortedStudents.length; i++) {
            outputArea.append((i + 1) + ". " + sortedStudents[i] + "\n");
        }

        outputArea.append("\nРеализация метода compareTo():\n");
        outputArea.append("public int compareTo(CustomStudent other) {\n");
        outputArea.append("    int facultyCompare = this.faculty.compareTo(other.faculty);\n");
        outputArea.append("    if (facultyCompare != 0) return facultyCompare;\n");
        outputArea.append("    \n");
        outputArea.append("    int yearCompare = Integer.compare(this.year, other.year);\n");
        outputArea.append("    if (yearCompare != 0) return yearCompare;\n");
        outputArea.append("    \n");
        outputArea.append("    int gpaCompare = Double.compare(other.gpa, this.gpa);\n");
        outputArea.append("    if (gpaCompare != 0) return gpaCompare;\n");
        outputArea.append("    \n");
        outputArea.append("    return Integer.compare(this.id, other.id);\n");
        outputArea.append("}\n");
    }

    class CustomStudent implements Comparable<CustomStudent> {
        private String name;
        private int id;
        private double gpa;
        private String faculty;
        private int year;

        public CustomStudent(String name, int id, double gpa, String faculty, int year) {
            this.name = name;
            this.id = id;
            this.gpa = gpa;
            this.faculty = faculty;
            this.year = year;
        }

        @Override
        public int compareTo(CustomStudent other) {
            int facultyCompare = this.faculty.compareTo(other.faculty);
            if (facultyCompare != 0) {
                return facultyCompare;
            }

            int yearCompare = Integer.compare(this.year, other.year);
            if (yearCompare != 0) {
                return yearCompare;
            }

            int gpaCompare = Double.compare(other.gpa, this.gpa);
            if (gpaCompare != 0) {
                return gpaCompare;
            }

            return Integer.compare(this.id, other.id);
        }

        @Override
        public String toString() {
            return String.format("Student{name='%s', id=%d, gpa=%.2f, faculty='%s', year=%d}",
                    name, id, gpa, faculty, year);
        }

        public String getName() { return name; }
        public int getId() { return id; }
        public double getGpa() { return gpa; }
        public String getFaculty() { return faculty; }
        public int getYear() { return year; }
    }

    public static void task4(JFrame mainMenuFrame) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Task4(mainMenuFrame).setVisible(true);
            }
        });
    }
}