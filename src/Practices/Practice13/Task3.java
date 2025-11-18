package Practices.Practice13;

import DOP.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import static DOP.DOP_FUNC.showMessageAndReturn;

public class Task3 extends JFrame {
    private JTextArea outputArea;
    private JButton generateButton, mergeButton, backButton;
    private List<Student> list1, list2;

    public Task3(JFrame mainMenuFrame) {
        super("Сортировка слиянием - Задание 3");
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

        generateButton = new JButton("Сгенерировать списки");
        mergeButton = new JButton("Объединить и отсортировать");
        backButton = new JButton("В главное меню");

        backButton.setBackground(new Color(70, 130, 180));
        backButton.setForeground(Color.WHITE);

        mergeButton.setEnabled(false);
    }

    private void layoutComponents() {
        setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(generateButton);
        buttonPanel.add(mergeButton);

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
                generateLists();
            }
        });

        mergeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mergeAndSort();
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

    private void generateLists() {
        String[] names1 = {"Иван", "Мария", "Петр", "Анна", "Сергей"};
        String[] names2 = {"Ольга", "Алексей", "Елена", "Дмитрий", "Наталья"};
        Random random = new Random();

        list1 = new ArrayList<>();
        list2 = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            String name = names1[random.nextInt(names1.length)] + " A" + (i + 1);
            int id = random.nextInt(500) + 100;
            double gpa = 2.0 + random.nextDouble() * 3.0;
            list1.add(new Student(name, id, gpa));
        }

        for (int i = 0; i < 5; i++) {
            String name = names2[random.nextInt(names2.length)] + " B" + (i + 1);
            int id = random.nextInt(500) + 600;
            double gpa = 2.0 + random.nextDouble() * 3.0;
            list2.add(new Student(name, id, gpa));
        }

        outputArea.setText("Первый список студентов:\n");
        outputArea.append("========================\n");
        for (int i = 0; i < list1.size(); i++) {
            outputArea.append((i + 1) + ". " + list1.get(i) + "\n");
        }

        outputArea.append("\nВторой список студентов:\n");
        outputArea.append("========================\n");
        for (int i = 0; i < list2.size(); i++) {
            outputArea.append((i + 1) + ". " + list2.get(i) + "\n");
        }

        mergeButton.setEnabled(true);
    }

    private void mergeAndSort() {
        if (list1 == null || list2 == null) return;

        List<Student> mergedList = new ArrayList<>();
        mergedList.addAll(list1);
        mergedList.addAll(list2);

        Student[] mergedArray = mergedList.toArray(new Student[0]);

        mergeSort(mergedArray, 0, mergedArray.length - 1);

        outputArea.append("\nОбъединенный и отсортированный список (сортировка слиянием по ID):\n");
        outputArea.append("==================================================================\n");
        for (int i = 0; i < mergedArray.length; i++) {
            outputArea.append((i + 1) + ". " + mergedArray[i] + "\n");
        }

        outputArea.append("\nАлгоритм сортировки слиянием:\n");
        outputArea.append("1. Рекурсивно разделяем массив пополам\n");
        outputArea.append("2. Сортируем каждую половину\n");
        outputArea.append("3. Объединяем отсортированные половины\n");
        outputArea.append("4. Сложность: O(n log n) в любом случае\n");
    }

    private void mergeSort(Student[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;

            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);

            merge(arr, left, mid, right);
        }
    }

    private void merge(Student[] arr, int left, int mid, int right) {
        Student[] leftArray = Arrays.copyOfRange(arr, left, mid + 1);
        Student[] rightArray = Arrays.copyOfRange(arr, mid + 1, right + 1);

        int i = 0, j = 0, k = left;

        while (i < leftArray.length && j < rightArray.length) {
            if (leftArray[i].getId() <= rightArray[j].getId()) {
                arr[k] = leftArray[i];
                i++;
            } else {
                arr[k] = rightArray[j];
                j++;
            }
            k++;
        }

        while (i < leftArray.length) {
            arr[k] = leftArray[i];
            i++;
            k++;
        }

        while (j < rightArray.length) {
            arr[k] = rightArray[j];
            j++;
            k++;
        }
    }

    public static void task3(JFrame mainMenuFrame) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Task3(mainMenuFrame).setVisible(true);
            }
        });
    }
}