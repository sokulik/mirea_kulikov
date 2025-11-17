package Tasks;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;
import static DOP.DOP_FUNC.showMessageAndReturn;

public class Task13 extends JFrame{
    private JTextArea outputArea;
    private JButton startButton;
    private JButton backButton;
    private Scanner scanner;
    private StringBuilder result;
    private int position; // Позиция в последовательности

    public Task13(JFrame mainMenuFrame){
        setTitle("Числа с нечетными номерами");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        initComponents();
        layoutComponents();
        addListeners(mainMenuFrame);

        scanner = new Scanner(System.in);
        result = new StringBuilder();
    }

    private void initComponents(){
        outputArea = new JTextArea(15, 30);
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        startButton = new JButton("Начать ввод последовательности");
        backButton = new JButton("В главное меню");
    }

    private void layoutComponents(){
        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Вводите числа по одному (0 - завершение):"));
        topPanel.add(startButton);

        JScrollPane scrollPane = new JScrollPane(outputArea);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(backButton);

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10,10, 10));
        scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 10,10));
    }

    private void addListeners(JFrame mainMenuFrame) {
        startButton.addActionListener(e -> execution());
        backButton.addActionListener( e -> {
            if (scanner != null) {
                scanner.close();
            }
            dispose();
            mainMenuFrame.setVisible(true);
        });
    }

    private void execution(){
        try {
            outputArea.setText("Вводите натуральные числа (по одному в строке):\n");
            outputArea.append("Для завершения введите 0.\n\n");

            result.setLength(0);
            position = 1;
            printOddPositionNumbers();

            outputArea.append("\nЧисла с нечетными номерами:\n");
            outputArea.append(result.toString());

        } catch (Exception ex) {
            showMessageAndReturn(this, "Ошибка при вводе данных!");
        }
    }

    private void printOddPositionNumbers() {
        int number = readIntFromKeyboard();
        outputArea.append(position + ": " + number + "\n");

        if (number == 0) {
            return;
        }

        if (position % 2 != 0) {
            if (result.length() > 0) {
                result.append(" ");
            }
            result.append(number);
        }

        position++;
        printOddPositionNumbers();
    }

    private int readIntFromKeyboard() {
        String input = JOptionPane.showInputDialog(this,
                "Введите натуральное число:\n(0 - завершит ввод)",
                "Ввод числа",
                JOptionPane.QUESTION_MESSAGE);

        if (input == null) {
            return 0;
        }

        try {
            int num = Integer.parseInt(input.trim());
            if (num < 0) {
                throw new NumberFormatException();
            }
            return num;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Введите корректное натуральное число!",
                    "Ошибка ввода",
                    JOptionPane.ERROR_MESSAGE);
            return readIntFromKeyboard();
        }
    }

    public static void task13(JFrame mainMenuFrame) {
        SwingUtilities.invokeLater(() -> new Task13(mainMenuFrame).setVisible(true));
    }
}