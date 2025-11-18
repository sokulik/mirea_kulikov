package Practices.Practice14;

import DOP.MyArrayList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Task3 extends JFrame {
    private JTextArea outputArea;
    private JButton createButton, testButton, backButton;
    private MyArrayList<String> myList;

    public Task3(JFrame mainMenuFrame) {
        super("Собственная коллекция - Задание 3");
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

        createButton = new JButton("Создать коллекцию");
        testButton = new JButton("Протестировать методы");
        backButton = new JButton("В главное меню");

        backButton.setBackground(new Color(70, 130, 180));
        backButton.setForeground(Color.WHITE);

        testButton.setEnabled(false);
    }

    private void layoutComponents() {
        setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(createButton);
        buttonPanel.add(testButton);

        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Моя реализация ArrayList"));

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.add(backButton);

        add(buttonPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        ((JComponent) getContentPane()).setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    private void addListeners(final JFrame mainMenuFrame) {
        createButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createCollection();
            }
        });

        testButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                testMethods();
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

    private void createCollection() {
        myList = new MyArrayList<>();

        outputArea.setText("Создание собственной коллекции MyArrayList\n");
        outputArea.append("==========================================\n\n");

        outputArea.append("1. Добавление элементов:\n");
        myList.add("Первый");
        myList.add("Второй");
        myList.add("Третий");
        myList.add(1, "Новый второй");

        outputArea.append("Состояние коллекции: " + myList + "\n");
        outputArea.append("Размер: " + myList.size() + "\n");

        outputArea.append("\n2. Получение элементов:\n");
        outputArea.append("Элемент с индексом 2: " + myList.get(2) + "\n");
        outputArea.append("Элемент с индексом 0: " + myList.get(0) + "\n");

        testButton.setEnabled(true);
    }

    private void testMethods() {
        outputArea.append("\n\n3. Тестирование методов коллекции:\n");
        outputArea.append("==================================\n");

        outputArea.append("Изменение элемента по индексу 0:\n");
        String oldValue = myList.set(0, "Измененный первый");
        outputArea.append("Старое значение: " + oldValue + "\n");
        outputArea.append("Новое состояние: " + myList + "\n");

        outputArea.append("\nПоиск элементов:\n");
        outputArea.append("Индекс 'Третий': " + myList.indexOf("Третий") + "\n");
        outputArea.append("Содержит 'Второй': " + myList.contains("Второй") + "\n");
        outputArea.append("Содержит 'Несуществующий': " + myList.contains("Несуществующий") + "\n");

        outputArea.append("\nУдаление элементов:\n");
        myList.remove(1);
        outputArea.append("После удаления по индексу 1: " + myList + "\n");

        myList.remove("Третий");
        outputArea.append("После удаления по значению 'Третий': " + myList + "\n");

        outputArea.append("\nПеребор с помощью итератора:\n");
        outputArea.append("Элементы: ");
        for (String element : myList) {
            outputArea.append(element + " ");
        }
        outputArea.append("\n");

        outputArea.append("\n4. Особенности реализации:\n");
        outputArea.append("- Динамическое расширение массива\n");
        outputArea.append("- Поддержка интерфейса Iterable\n");
        outputArea.append("- Проверка границ индексов\n");
        outputArea.append("- Эффективное копирование массива\n");
        outputArea.append("- Поддержка дженериков\n");
    }

    public static void task3(JFrame mainMenuFrame) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Task3(mainMenuFrame).setVisible(true);
            }
        });
    }
}