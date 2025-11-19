package Practices.Practice1;

import DOP.BaseTaskFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;

public class Task2 extends BaseTaskFrame {
    private JTextArea resultArea;
    private JTextField sizeField;
    private JTextField[] numberFields;
    private JPanel inputPanel;

    public Task2(JFrame mainMenuFrame) {
        super(mainMenuFrame,"Задание 2: Работа с массивами");

        initComponents();
        layoutComponents();
        addListeners();
    }

    private void initComponents() {
        resultArea = new JTextArea(10, 40);
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Consolas", Font.PLAIN, 12));
        resultArea.setBackground(new Color(248, 249, 250));

        sizeField = new JTextField(5);
        sizeField.setFont(new Font("Arial", Font.PLAIN, 14));

        inputPanel = new JPanel();
        inputPanel.setBackground(new Color(240, 240, 240));
    }

    private void layoutComponents() {
        setLayout(new BorderLayout(10, 10));

        // Верхняя панель с вводом размера
        JPanel sizePanel = createSizePanel();
        add(sizePanel, BorderLayout.NORTH);

        // Центральная панель с вводом чисел и результатами
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(inputPanel, BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Результаты"));
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        add(centerPanel, BorderLayout.CENTER);

        // Панель снизу с кнопкой назад
        JPanel bottomPanel = createBottomPanel();
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private JPanel createSizePanel() {
        JPanel sizePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        sizePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        sizePanel.setBackground(new Color(240, 240, 240));

        JLabel sizeLabel = new JLabel("Размер массива:");
        sizeLabel.setFont(new Font("Arial", Font.BOLD, 12));

        JButton createButton = new JButton("Создать массив");
        createButton.setBackground(new Color(70, 130, 180));
        createButton.setForeground(Color.WHITE);
        createButton.addActionListener(e -> createArrayFields());

        sizePanel.add(sizeLabel);
        sizePanel.add(sizeField);
        sizePanel.add(createButton);

        return sizePanel;
    }

    private void createArrayFields() {
        try {
            int size = Integer.parseInt(sizeField.getText().trim());
            if (size <= 0 || size > 20) {
                JOptionPane.showMessageDialog(this, "Размер должен быть от 1 до 20", "Ошибка", JOptionPane.ERROR_MESSAGE);
                return;
            }

            inputPanel.removeAll();
            inputPanel.setLayout(new GridLayout(size + 1, 2, 5, 5));

            numberFields = new JTextField[size];

            inputPanel.add(new JLabel("Элементы массива:"));
            inputPanel.add(new JLabel(""));

            for (int i = 0; i < size; i++) {
                inputPanel.add(new JLabel("Элемент " + (i + 1) + ":"));
                numberFields[i] = new JTextField(10);
                numberFields[i].setFont(new Font("Arial", Font.PLAIN, 12));
                inputPanel.add(numberFields[i]);
            }

            JButton calculateButton = new JButton("Рассчитать");
            calculateButton.setBackground(new Color(40, 167, 69));
            calculateButton.setForeground(Color.WHITE);
            calculateButton.addActionListener(e -> calculateArray());

            inputPanel.add(new JLabel(""));
            inputPanel.add(calculateButton);

            inputPanel.revalidate();
            inputPanel.repaint();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Введите корректный размер массива", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void calculateArray() {
        try {
            int size = numberFields.length;
            int[] massive = new int[size];

            for (int i = 0; i < size; i++) {
                massive[i] = Integer.parseInt(numberFields[i].getText().trim());
            }

            int summa = 0;
            int min = massive[0];
            int max = massive[0];

            for (int i = 0; i < size; i++) {
                summa += massive[i];
                if (massive[i] < min) min = massive[i];
                if (massive[i] > max) max = massive[i];
            }

            StringBuilder result = new StringBuilder();
            result.append("📊 Результаты анализа массива:\n");
            result.append("═".repeat(40) + "\n");
            result.append("Массив: " + Arrays.toString(massive) + "\n");
            result.append("Сумма: " + summa + "\n");
            result.append("Минимальное значение: " + min + "\n");
            result.append("Максимальное значение: " + max + "\n");
            result.append("Среднее арифметическое: " + String.format("%.2f", (double)summa/size) + "\n");

            resultArea.setText(result.toString());

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Введите корректные числовые значения", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    private JPanel createBottomPanel() {
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(new Color(240, 240, 240));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton backButton = createBackButton();
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftPanel.setBackground(new Color(240, 240, 240));
        leftPanel.add(backButton);

        bottomPanel.add(leftPanel, BorderLayout.WEST);
        return bottomPanel;
    }


    private void addListeners() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                returnToMainMenu();
            }
        });
    }


    public static void task2(JFrame mainMenuFrame) {
        if (mainMenuFrame != null) {
            mainMenuFrame.setVisible(false);
        }

        SwingUtilities.invokeLater(() -> new Task2(mainMenuFrame).setVisible(true));
    }
}