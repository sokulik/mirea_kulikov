package Practices.Practice11;

import javax.swing.*;
import java.awt.*;
import static DOP.DOP_FUNC.showMessageAndReturn;

public class Task15 extends JFrame{
    private JTextField inputField;
    private JTextArea outputArea;
    private JButton generateButton;
    private JButton backButton;
    private StringBuilder result;

    public Task15(JFrame mainMenuFrame){
        setTitle("Цифры числа справа налево");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        initComponents();
        layoutComponents();
        addListeners(mainMenuFrame);

        result = new StringBuilder();
    }

    private void initComponents(){
        inputField = new JTextField(20);
        outputArea = new JTextArea(15, 30);
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        generateButton = new JButton("Вывести цифры");
        backButton = new JButton("В главное меню");
    }

    private void layoutComponents(){
        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Введите натуральное число: "));
        topPanel.add(inputField);
        topPanel.add(generateButton);

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
        generateButton.addActionListener(e -> execution());
        inputField.addActionListener(e -> execution());
        backButton.addActionListener( e -> {
            dispose();
            mainMenuFrame.setVisible(true);
        });
    }

    private void execution(){
        try{
            int n = Integer.parseInt(inputField.getText().trim());

            if (n <= 0) {
                throw new IllegalArgumentException("Число должно быть натуральным!");
            }

            outputArea.setText("Число: " + n + "\n\n");
            outputArea.append("Цифры справа налево:\n");

            result.setLength(0);
            printDigitsRightToLeft(n);

            outputArea.append(result.toString());

        } catch (NumberFormatException ex) {
            showMessageAndReturn(this, "Введите корректное число!");
        } catch (IllegalArgumentException ex) {
            showMessageAndReturn(this, ex.getMessage());
        }
    }

    private void printDigitsRightToLeft(int n) {
        if (n > 0) {
            result.append(n % 10).append("\n");
            printDigitsRightToLeft(n / 10);
        }
    }

    public static void task15(JFrame mainMenuFrame) {
        SwingUtilities.invokeLater(() -> new Task15(mainMenuFrame).setVisible(true));
    }
}