package Practices.Practice13;

import DOP.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import static DOP.DOP_FUNC.showMessageAndReturn;

public class Task2 extends JFrame {
    private JTextArea outputArea;
    private JButton generateButton, sortButton, backButton;
    private Student[] students;
    private Comparator<Student> gpaComparator;

    public Task2(JFrame mainMenuFrame) {
        super("Быстрая сортировка по GPA - Задание 2");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);

        initComponents();
        layoutComponents();
        addListeners(mainMenuFrame);

        gpaComparator = new SortingStudentsByGPA();
    }

    private void initComponents() {
        outputArea = new JTextArea(20, 60);
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 12));

        generateButton = new JButton("Сгенерировать студентов");
        sortButton = new JButton("Быстрая сортировка по GPA");
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
        Random random = new Random();

        students = new Student[8];
        for (int i = 0; i < students.length; i++) {
            String name = names[random.nextInt(names.length)] + " " + (i + 1);
            int id = random.nextInt(900) + 100;
            double gpa = 2.0 + random.nextDouble() * 3.0;
            students[i] = new Student(name, id, gpa);
        }

        outputArea.setText("Сгенерированный массив студентов:\n");
        outputArea.append("=================================\n");
        for (int i = 0; i < students.length; i++) {
            outputArea.append((i + 1) + ". " + students[i] + "\n");
        }

        sortButton.setEnabled(true);
    }

    private void sortStudents() {
        if (students == null) return;

        Student[] sortedStudents = Arrays.copyOf(students, students.length);

        quickSort(sortedStudents, 0, sortedStudents.length - 1, gpaComparator);

        outputArea.append("\nОтсортированный массив (быстрая сортировка по GPA по убыванию):\n");
        outputArea.append("==============================================================\n");
        for (int i = 0; i < sortedStudents.length; i++) {
            outputArea.append((i + 1) + ". " + sortedStudents[i] + "\n");
        }

        outputArea.append("\nАлгоритм быстрой сортировки:\n");
        outputArea.append("1. Выбираем опорный элемент (pivot)\n");
        outputArea.append("2. Разделяем массив на элементы меньше и больше pivot\n");
        outputArea.append("3. Рекурсивно сортируем подмассивы\n");
        outputArea.append("4. Сложность: O(n log n) в среднем случае\n");
    }

    private void quickSort(Student[] arr, int low, int high, Comparator<Student> comparator) {
        if (low < high) {
            int pi = partition(arr, low, high, comparator);
            quickSort(arr, low, pi - 1, comparator);
            quickSort(arr, pi + 1, high, comparator);
        }
    }

    private int partition(Student[] arr, int low, int high, Comparator<Student> comparator) {
        Student pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (comparator.compare(arr[j], pivot) > 0) {
                i++;
                Student temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        Student temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    class SortingStudentsByGPA implements Comparator<Student> {
        @Override
        public int compare(Student s1, Student s2) {
            return Double.compare(s2.getGpa(), s1.getGpa());
        }
    }

    public static void task2(JFrame mainMenuFrame) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Task2(mainMenuFrame).setVisible(true);
            }
        });
    }
}