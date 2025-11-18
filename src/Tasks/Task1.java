package Tasks;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;

public class Task1 extends JFrame {
    private JTextArea outputArea;
    private JButton generateButton, sortButton, backButton;
    private ArrayList<String> fruits;

    public Task1(JFrame mainMenuFrame) {
        super("Тестирование ArrayList - Задание 1");
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

        generateButton = new JButton("Сгенерировать список");
        sortButton = new JButton("Отсортировать список");
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
        scrollPane.setBorder(BorderFactory.createTitledBorder("Результаты работы с ArrayList"));

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
                generateArrayList();
            }
        });

        sortButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sortArrayList();
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

    private void generateArrayList() {
        fruits = new ArrayList<>();

        fruits.add("Яблоко");
        fruits.add("Банан");
        fruits.add("Апельсин");
        fruits.add(1, "Груша");
        fruits.add("Киви");
        fruits.add("Манго");

        outputArea.setText("Демонстрация работы с ArrayList\n");
        outputArea.append("================================\n\n");
        outputArea.append("Исходный список:\n");
        for (int i = 0; i < fruits.size(); i++) {
            outputArea.append((i + 1) + ". " + fruits.get(i) + "\n");
        }

        outputArea.append("\nРазмер списка: " + fruits.size() + "\n");
        outputArea.append("Содержит 'Банан': " + fruits.contains("Банан") + "\n");
        outputArea.append("Индекс 'Апельсин': " + fruits.indexOf("Апельсин") + "\n");

        fruits.set(0, "Зеленое яблоко");
        outputArea.append("\nПосле замены первого элемента:\n");
        for (int i = 0; i < fruits.size(); i++) {
            outputArea.append((i + 1) + ". " + fruits.get(i) + "\n");
        }

        sortButton.setEnabled(true);
    }

    private void sortArrayList() {
        if (fruits == null) return;

        outputArea.append("\n\nОтсортированный список:\n");
        outputArea.append("========================\n");

        Collections.sort(fruits);
        for (int i = 0; i < fruits.size(); i++) {
            outputArea.append((i + 1) + ". " + fruits.get(i) + "\n");
        }

        outputArea.append("\nПодсписок (элементы 2-4):\n");
        java.util.List<String> subList = fruits.subList(1, 4);
        for (String fruit : subList) {
            outputArea.append("- " + fruit + "\n");
        }

        Object[] fruitArray = fruits.toArray();
        outputArea.append("\nПреобразование в массив:\n");
        for (Object fruit : fruitArray) {
            outputArea.append(fruit + " ");
        }
        outputArea.append("\n");

        outputArea.append("\nОсновные методы ArrayList:\n");
        outputArea.append("- add(element) - добавление в конец\n");
        outputArea.append("- add(index, element) - добавление по индексу\n");
        outputArea.append("- get(index) - получение элемента\n");
        outputArea.append("- set(index, element) - замена элемента\n");
        outputArea.append("- remove(element/index) - удаление\n");
        outputArea.append("- size() - получение размера\n");
        outputArea.append("- contains(element) - проверка наличия\n");
    }

    public static void task1(JFrame mainMenuFrame) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Task1(mainMenuFrame).setVisible(true);
            }
        });
    }
}