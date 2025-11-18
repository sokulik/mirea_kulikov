package Tasks;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;

public class Task2 extends JFrame {
    private JTextArea outputArea;
    private JButton generateButton, demoButton, backButton;
    private LinkedList<String> countries;

    public Task2(JFrame mainMenuFrame) {
        super("Тестирование LinkedList - Задание 2");
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

        generateButton = new JButton("Создать LinkedList");
        demoButton = new JButton("Показать методы");
        backButton = new JButton("В главное меню");

        backButton.setBackground(new Color(70, 130, 180));
        backButton.setForeground(Color.WHITE);

        demoButton.setEnabled(false);
    }

    private void layoutComponents() {
        setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(generateButton);
        buttonPanel.add(demoButton);

        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Результаты работы с LinkedList"));

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
                createLinkedList();
            }
        });

        demoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                demonstrateMethods();
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

    private void createLinkedList() {
        countries = new LinkedList<>();

        outputArea.setText("Демонстрация работы с LinkedList\n");
        outputArea.append("=================================\n\n");

        outputArea.append("1. Добавление элементов:\n");
        countries.add("Россия");
        countries.addFirst("США"); // В начало
        countries.addLast("Китай"); // В конец
        countries.add(1, "Германия"); // По индексу
        countries.offerFirst("Франция"); // Альтернативный метод
        countries.offerLast("Япония");

        outputArea.append("Список после добавления:\n");
        for (int i = 0; i < countries.size(); i++) {
            outputArea.append((i + 1) + ". " + countries.get(i) + "\n");
        }

        outputArea.append("\nРазмер списка: " + countries.size() + "\n");
        outputArea.append("Первый элемент: " + countries.getFirst() + "\n");
        outputArea.append("Последний элемент: " + countries.getLast() + "\n");

        demoButton.setEnabled(true);
    }

    private void demonstrateMethods() {
        outputArea.append("\n\n2. Демонстрация специальных методов LinkedList:\n");
        outputArea.append("================================================\n");

        outputArea.append("peekFirst(): " + countries.peekFirst() + "\n");
        outputArea.append("peekLast(): " + countries.peekLast() + "\n");

        outputArea.append("\nУдаление элементов:\n");
        String removedFirst = countries.removeFirst();
        outputArea.append("removeFirst(): удален '" + removedFirst + "'\n");

        String removedLast = countries.removeLast();
        outputArea.append("removeLast(): удален '" + removedLast + "'\n");

        outputArea.append("\nСписок после удаления:\n");
        for (int i = 0; i < countries.size(); i++) {
            outputArea.append((i + 1) + ". " + countries.get(i) + "\n");
        }

        outputArea.append("\n3. Проверка наличия элементов:\n");
        outputArea.append("Содержит 'Россия': " + countries.contains("Россия") + "\n");
        outputArea.append("Содержит 'США': " + countries.contains("США") + "\n");
        outputArea.append("Индекс 'Германия': " + countries.indexOf("Германия") + "\n");

        outputArea.append("\n4. Преимущества LinkedList:\n");
        outputArea.append("- Быстрое добавление/удаление в начале и конце\n");
        outputArea.append("- Динамическое изменение размера\n");
        outputArea.append("- Реализация Deque и Queue\n");
        outputArea.append("- Эффективные операции addFirst/removeLast\n");
    }

    public static void task2(JFrame mainMenuFrame) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Task2(mainMenuFrame).setVisible(true);
            }
        });
    }
}