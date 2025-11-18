package Practices.Practice11;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static DOP.DOP_FUNC.showMessageAndReturn;

public class Task9 extends JFrame {
    private JTextField inputA;
    private JTextField inputB;
    private JTextArea outputArea;
    private JButton generateButton;
    private JButton backButton;
    private JButton saveButton;
    private List<String> sequences;
    private int currentCount;

    public Task9(JFrame mainMenuFrame){
        setTitle("Последовательность без двух нулей подряд");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 550);
        setLocationRelativeTo(null);

        initComponents();
        layoutComponents();
        addListeners(mainMenuFrame);

        setIconImage(new ImageIcon("/src/resources/IconPR.jpg").getImage());
    }

    private void initComponents(){
        inputA = new JTextField(10);
        inputB = new JTextField(10);
        outputArea = new JTextArea(15, 45);
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        generateButton = new JButton("Узнать");
        backButton = new JButton("В главное меню");
        saveButton = new JButton("Сохранить в файл");
        saveButton.setEnabled(false);
        sequences = new ArrayList<>();
    }

    private void layoutComponents(){
        setLayout(new BorderLayout(10, 10));

        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Нули: "));
        topPanel.add(inputA);
        topPanel.add(new JLabel("Единицы:"));
        topPanel.add(inputB);
        topPanel.add(generateButton);
        topPanel.add(saveButton);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(backButton);

        JScrollPane scrollPane = new JScrollPane(outputArea);

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10,10, 10));
        scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 10,10));

    }

    private void addListeners(JFrame mainMenuFrame) {
        generateButton.addActionListener(e -> execution());
        inputA.addActionListener(e -> execution());
        inputB.addActionListener(e -> execution());
        backButton.addActionListener( e -> {
            dispose();
            mainMenuFrame.setVisible(true);
        });
        saveButton.addActionListener(e -> saveToFile());
    }

    private void execution(){
        try{
            int a = Integer.parseInt(inputA.getText().trim());
            int b = Integer.parseInt(inputB.getText().trim());

            if (a < 0 || b < 0){
                showMessageAndReturn(this ,"Введите число больше 1!");
                return;
            }

            if (a > b + 1) {
                outputArea.setText("Результат: 0\n\n" +
                        "Невозможно построить последовательность:\n" +
                        "нулей слишком много относительно единиц.\n" +
                        "Максимальное количество нулей: " + (b + 1));
                saveButton.setEnabled(false);
                return;
            }

            sequences.clear();
            currentCount = 0;

            outputArea.setText("Расчет для a = " + a + " (нули), b = " + b + " (единицы)\n\n");

            generateSequences(a, b, "");

            if (currentCount > 0) {
                outputArea.append("Первые " + Math.min(20, currentCount) + " последовательностей:\n");
                for (int i = 0; i < Math.min(20, currentCount); i++) {
                    outputArea.append((i + 1) + ". " + sequences.get(i) + "\n");
                }
                if (currentCount > 20) {
                    outputArea.append("... и еще " + (currentCount - 20) + " последовательностей\n");
                }
            }

            outputArea.append("\nНажмите 'Сохранить в файл' для записи всех последовательностей");

            saveButton.setEnabled(currentCount > 0);


        }catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Введите корректное число!",
                    "Ошибка",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void generateSequences(int zeros, int ones, String current) {
        if (zeros == 0 && ones == 0) {
            sequences.add(current);
            currentCount++;
            return;
        }
        if (zeros < 0 || ones < 0) {
            return;
        }

        if (ones > 0) {
            generateSequences(zeros, ones - 1, current + "1");
        }

        if (zeros > 0 && (current.isEmpty() || current.charAt(current.length() - 1) != '0')) {
            generateSequences(zeros - 1, ones, current + "0");
        }
    }

    private void saveToFile() {
        try {
            File resourcesDir = new File("src/resources");
            if (!resourcesDir.exists()) {
                resourcesDir.mkdirs();
            }

            File file = new File("src/tasksComplited/task9.txt");


            try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
                writer.println("Последовательности без двух нулей подряд");
                writer.println("a = " + inputA.getText() + " (нули), b = " + inputB.getText() + " (единицы)");
                writer.println("Количество последовательностей: " + currentCount);
                writer.println("Время создания: " + new java.util.Date());
                writer.println();
                writer.println("Список последовательностей:");
                writer.println();

                for (int i = 0; i < sequences.size(); i++) {
                    writer.printf("%3d. %s%n", i + 1, sequences.get(i));
                }
            }

            outputArea.append("\n\n Успешно сохранено в файл: " + file.getAbsolutePath());
            outputArea.append("\nСохранено последовательностей: " + sequences.size());

            JOptionPane.showMessageDialog(this,
                    "Файл успешно сохранен!\n" + file.getAbsolutePath(),
                    "Сохранение завершено",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this,
                    "Ошибка при сохранении файла:\n" + ex.getMessage(),
                    "Ошибка",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void task9(JFrame mainMenuFrame) {
        SwingUtilities.invokeLater(() -> new Task9(mainMenuFrame).setVisible(true));
    }
}




