package Practices.Practice1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Task5 extends JFrame {
    private JFrame mainMenuFrame;
    private JTextArea resultArea;
    private JTextField numberField;

    public Task5(JFrame parentFrame) {
        super("Задание 5: Вычисление факториала");
        this.mainMenuFrame = mainMenuFrame;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);

        initComponents();
        layoutComponents();
        addListeners();
    }

    private void initComponents() {
        resultArea = new JTextArea(10, 30);
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Consolas", Font.PLAIN, 12));
        resultArea.setBackground(new Color(248, 249, 250));

        numberField = new JTextField(10);
        numberField.setFont(new Font("Arial", Font.PLAIN, 14));
    }

    private void layoutComponents() {
        setLayout(new BorderLayout(10, 10));

        // Верхняя панель с вводом
        JPanel inputPanel = createInputPanel();
        add(inputPanel, BorderLayout.NORTH);

        // Центральная панель с результатами
        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Результат вычисления"));
        add(scrollPane, BorderLayout.CENTER);

        // Панель снизу с кнопкой назад
        JPanel bottomPanel = createBottomPanel();
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private JPanel createInputPanel() {
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        inputPanel.setBackground(new Color(240, 240, 240));

        JLabel instructionLabel = new JLabel("Введите число для вычисления факториала:");
        instructionLabel.setFont(new Font("Arial", Font.BOLD, 12));

        JButton calculateButton = new JButton("Вычислить");
        calculateButton.setBackground(new Color(70, 130, 180));
        calculateButton.setForeground(Color.WHITE);
        calculateButton.addActionListener(e -> calculateFactorial());

        inputPanel.add(instructionLabel);
        inputPanel.add(numberField);
        inputPanel.add(calculateButton);

        return inputPanel;
    }

    private void calculateFactorial() {
        try {
            int chislo = Integer.parseInt(numberField.getText().trim());

            if (chislo < 0) {
                JOptionPane.showMessageDialog(this, "Факториал определен только для неотрицательных чисел", "Ошибка", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (chislo > 20) {
                JOptionPane.showMessageDialog(this, "Число слишком большое (максимум 20)", "Ошибка", JOptionPane.ERROR_MESSAGE);
                return;
            }

            long fact = Factorial(chislo);

            StringBuilder result = new StringBuilder();
            result.append("🧮 Вычисление факториала\n");
            result.append("═".repeat(40) + "\n\n");
            result.append(String.format("Число: %d\n", chislo));
            result.append(String.format("Факториал: %d! = %,d\n\n", chislo, fact));

            result.append("📝 Процесс вычисления:\n");
            for (int i = 1; i <= chislo; i++) {
                long partialFact = Factorial(i);
                result.append(String.format("%2d! = %,d\n", i, partialFact));
            }

            resultArea.setText(result.toString());
            numberField.setText("");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Введите корректное целое число", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static long Factorial(int n) {
        long factorial = 1;
        for (int i = 2; i <= n; i++) {
            factorial *= i;
        }
        return factorial;
    }

    private JPanel createBottomPanel() {
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(new Color(240, 240, 240));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton backButton = createBackButton();
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftPanel.setBackground(new Color(240, 240, 240));
        leftPanel.add(backButton);

        JLabel infoLabel = new JLabel("Факториал n! = 1 × 2 × 3 × ... × n");
        infoLabel.setFont(new Font("Arial", Font.PLAIN, 11));
        infoLabel.setForeground(Color.DARK_GRAY);

        bottomPanel.add(leftPanel, BorderLayout.WEST);
        bottomPanel.add(infoLabel, BorderLayout.EAST);

        return bottomPanel;
    }

    private JButton createBackButton() {
        JButton backButton = new JButton("← Назад");
        backButton.setFont(new Font("Arial", Font.BOLD, 12));
        backButton.setBackground(new Color(108, 117, 125));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(80, 90, 100), 2),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));

        backButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                backButton.setBackground(new Color(130, 140, 150));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                backButton.setBackground(new Color(108, 117, 125));
            }
        });

        backButton.addActionListener(e -> returnTomainMenu());
        return backButton;
    }

    private void addListeners() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                returnTomainMenu();
            }
        });

        // Обработка Enter в текстовом поле
        numberField.addActionListener(e -> calculateFactorial());
    }

    private void returnTomainMenu() {
        dispose();
        if (mainMenuFrame != null) {
            mainMenuFrame.setVisible(true);
        }
    }

    public static void task5(JFrame mainMenuFrame) {
        if (mainMenuFrame != null) {
            mainMenuFrame.setVisible(false);
        }

        SwingUtilities.invokeLater(() -> new Task5(mainMenuFrame).setVisible(true));
    }
}