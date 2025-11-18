package Practices.Practice7;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Task4 extends JFrame {
    private JFrame mainMenuFrame;
    private JTextArea resultArea;
    private JTextField yearField, monthField, dayField;
    private SimpleDateFormat sdf;

    public Task4(JFrame mainMenuFrame) {
        super("Задание 4: Форматирование даты");
        this.mainMenuFrame = mainMenuFrame;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);

        sdf = new SimpleDateFormat("EEEE, d MMMM yyyy 'г.'");
        initComponents();
        layoutComponents();
        addListeners();
    }

    private void initComponents() {
        resultArea = new JTextArea(12, 40);
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Consolas", Font.PLAIN, 12));
        resultArea.setBackground(new Color(248, 249, 250));

        yearField = new JTextField(4);
        monthField = new JTextField(2);
        dayField = new JTextField(2);

        yearField.setFont(new Font("Arial", Font.PLAIN, 14));
        monthField.setFont(new Font("Arial", Font.PLAIN, 14));
        dayField.setFont(new Font("Arial", Font.PLAIN, 14));
    }

    private void layoutComponents() {
        setLayout(new BorderLayout(10, 10));

        // Верхняя панель с вводом
        JPanel inputPanel = createInputPanel();
        add(inputPanel, BorderLayout.NORTH);

        // Центральная панель с результатами
        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Результат форматирования"));
        add(scrollPane, BorderLayout.CENTER);

        // Панель снизу с кнопкой назад
        JPanel bottomPanel = createBottomPanel();
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private JPanel createInputPanel() {
        JPanel inputPanel = new JPanel(new GridLayout(3, 1, 5, 5));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        inputPanel.setBackground(new Color(240, 240, 240));

        JLabel instructionLabel = new JLabel("Введите дату для форматирования:", JLabel.CENTER);
        instructionLabel.setFont(new Font("Arial", Font.BOLD, 14));

        JPanel fieldsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        fieldsPanel.setBackground(new Color(240, 240, 240));

        fieldsPanel.add(new JLabel("Год:"));
        fieldsPanel.add(yearField);
        fieldsPanel.add(new JLabel("Месяц:"));
        fieldsPanel.add(monthField);
        fieldsPanel.add(new JLabel("День:"));
        fieldsPanel.add(dayField);

        JButton formatButton = new JButton("Форматировать");
        formatButton.setBackground(new Color(70, 130, 180));
        formatButton.setForeground(Color.WHITE);
        formatButton.addActionListener(e -> formatDate());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(240, 240, 240));
        buttonPanel.add(formatButton);

        inputPanel.add(instructionLabel);
        inputPanel.add(fieldsPanel);
        inputPanel.add(buttonPanel);

        return inputPanel;
    }

    private JPanel createBottomPanel() {
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(new Color(240, 240, 240));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton backButton = createBackButton();
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftPanel.setBackground(new Color(240, 240, 240));
        leftPanel.add(backButton);

        JLabel infoLabel = new JLabel("Пример: Год=2024, Месяц=12, День=25");
        infoLabel.setFont(new Font("Arial", Font.PLAIN, 11));
        infoLabel.setForeground(Color.DARK_GRAY);

        bottomPanel.add(leftPanel, BorderLayout.WEST);
        bottomPanel.add(infoLabel, BorderLayout.EAST);

        return bottomPanel;
    }

    private JButton createBackButton() {
        JButton backButton = new JButton("← Назад к меню");
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

        backButton.addActionListener(e -> returnToMainMenu());
        return backButton;
    }

    private void formatDate() {
        try {
            int year = Integer.parseInt(yearField.getText().trim());
            int month = Integer.parseInt(monthField.getText().trim()) - 1;
            int day = Integer.parseInt(dayField.getText().trim());

            // Валидация ввода
            if (year < 1 || year > 9999) {
                JOptionPane.showMessageDialog(this, "Год должен быть от 1 до 9999", "Ошибка", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (month < 0 || month > 11) {
                JOptionPane.showMessageDialog(this, "Месяц должен быть от 1 до 12", "Ошибка", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (day < 1) {
                JOptionPane.showMessageDialog(this, "День не может быть меньше 1", "Ошибка", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Проверка корректности дня для месяца
            boolean isLeapYear = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
            if (month == 1) { // Февраль
                if (!isLeapYear && day > 28) {
                    JOptionPane.showMessageDialog(this, "Год не високосный, в феврале максимум 28 дней", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    return;
                } else if (isLeapYear && day > 29) {
                    JOptionPane.showMessageDialog(this, "Год високосный, в феврале максимум 29 дней", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            if ((month == 0 || month == 2 || month == 4 || month == 6 || month == 7 || month == 9 || month == 11) && day > 31) {
                JOptionPane.showMessageDialog(this, "В данном месяце не может быть больше 31 дня", "Ошибка", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if ((month == 3 || month == 5 || month == 8 || month == 10) && day > 30) {
                JOptionPane.showMessageDialog(this, "В данном месяце не может быть больше 30 дней", "Ошибка", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR, year);
            cal.set(Calendar.MONTH, month);
            cal.set(Calendar.DAY_OF_MONTH, day);

            Date date = cal.getTime();
            String formattedDate = sdf.format(date);

            resultArea.append("📅 Введенная дата: " + year + "-" + (month + 1) + "-" + day + "\n");
            resultArea.append("✨ Форматированная дата: " + formattedDate + "\n");
            resultArea.append("─".repeat(50) + "\n");

            // Очистка полей
            yearField.setText("");
            monthField.setText("");
            dayField.setText("");
            yearField.requestFocus();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Пожалуйста, введите корректные числовые значения", "Ошибка ввода", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addListeners() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                returnToMainMenu();
            }
        });

        // Обработка Enter в полях ввода
        yearField.addActionListener(e -> monthField.requestFocus());
        monthField.addActionListener(e -> dayField.requestFocus());
        dayField.addActionListener(e -> formatDate());
    }

    private void returnToMainMenu() {
        dispose();
        if (mainMenuFrame != null) {
            mainMenuFrame.setVisible(true);
            mainMenuFrame.toFront();
        }
    }

    public static void task4(JFrame mainMenuFrame) {
        if (mainMenuFrame != null) {
            mainMenuFrame.setVisible(false);
        }

        SwingUtilities.invokeLater(() -> new Task4(mainMenuFrame).setVisible(true));
    }
}