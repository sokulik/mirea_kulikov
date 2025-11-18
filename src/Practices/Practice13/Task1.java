package Practices.Practice13;

import DOP.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.Random;
import static DOP.DOP_FUNC.showMessageAndReturn;

public class Task1 extends JFrame {
    private JTextArea outputArea;
    private JButton generateButton, sortButton, backButton;
    private Student[] students;

    public Task1(JFrame mainMenuFrame) {
        super("Сортировка вставками - Задание 1");
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
        sortButton = new JButton("Отсортировать вставками");
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
        String[] names = {"Иван", "Мария", "Петр", "Анна", "Сергей", "Ольга", "Алексей", "Елена", "Дмитрий", "Наталья"};
        Random random = new Random();

        students = new Student[8];
        for (int i = 0; i < students.length; i++) {
            String name = names[random.nextInt(names.length)] + " " + (i + 1);
            int id = random.nextInt(900) + 100; // ID от 100 до 999
            double gpa = 2.0 + random.nextDouble() * 3.0; // GPA от 2.0 до 5.0
            students[i] = new Student(name, id, gpa);
        }

        outputArea.setText("Сгенерированный массив студентов (несортированный):\n");
        outputArea.append("===================================================\n");
        for (int i = 0; i < students.length; i++) {
            outputArea.append((i + 1) + ". " + students[i] + "\n");
        }

        sortButton.setEnabled(true);
    }

    private void sortStudents() {
        if (students == null) return;

        Student[] sortedStudents = Arrays.copyOf(students, students.length);

        insertionSort(sortedStudents);

        outputArea.append("\nОтсортированный массив (сортировка вставками по ID):\n");
        outputArea.append("====================================================\n");
        for (int i = 0; i < sortedStudents.length; i++) {
            outputArea.append((i + 1) + ". " + sortedStudents[i] + "\n");
        }

        outputArea.append("\nАлгоритм сортировки вставками:\n");
        outputArea.append("1. Проходим по массиву слева направо\n");
        outputArea.append("2. Для каждого элемента находим его правильную позицию\n");
        outputArea.append("3. Сдвигаем элементы и вставляем текущий элемент\n");
        outputArea.append("4. Сложность: O(n²) в худшем случае\n");
    }

    private void insertionSort(Student[] arr) {
        for (int i = 1; i < arr.length; i++) {
            Student key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j].getId() > key.getId()) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }

    public static void task1(JFrame mainMenuFrame) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Task1(mainMenuFrame).setVisible(true);
            }
        });
    }
}