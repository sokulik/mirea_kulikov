package Practices.Practice7;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;

public class Task2 extends JFrame {
    private JFrame mainMenuFrame;
    private JTextArea resultArea;
    private JTextField dateField;
    private SimpleDateFormat sdf;

    public Task2(JFrame mainMenuFrame) {
        super("Задание 2: Сравнение дат");
        this.mainMenuFrame = mainMenuFrame;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);

        sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        initComponents();
        layoutComponents();
        addListeners();
    }

    private void initComponents() {
        resultArea = new JTextArea(15, 40);
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Consolas", Font.PLAIN, 12));
        resultArea.setBackground(new Color(248, 249, 250));

        dateField = new JTextField(20);
        dateField.setFont(new Font("Arial", Font.PLAIN, 14));
    }

    private void layoutComponents() {
        setLayout(new BorderLayout(10, 10));

        // Верхняя панель с вводом
        JPanel inputPanel = createInputPanel();
        add(inputPanel, BorderLayout.NORTH);

        // Центральная панель с результатами
        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Результаты сравнения"));
        add(scrollPane, BorderLayout.CENTER);

        // Панель снизу с кнопкой назад
        JPanel bottomPanel = createBottomPanel();
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private JPanel createInputPanel() {
        JPanel inputPanel = new JPanel(new BorderLayout(10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        inputPanel.setBackground(new Color(240, 240, 240));

        JLabel instructionLabel = new JLabel("Введите дату в формате ДД.ММ.ГГГГ ЧЧ:ММ:СС");
        instructionLabel.setFont(new Font("Arial", Font.BOLD, 12));

        JPanel fieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        fieldPanel.setBackground(new Color(240, 240, 240));
        fieldPanel.add(new JLabel("Дата:"));
        fieldPanel.add(dateField);

        JButton compareButton = new JButton("Сравнить");
        compareButton.setBackground(new Color(70, 130, 180));
        compareButton.setForeground(Color.WHITE);
        compareButton.addActionListener(e -> compareDates());

        inputPanel.add(instructionLabel, BorderLayout.NORTH);
        inputPanel.add(fieldPanel, BorderLayout.CENTER);
        inputPanel.add(compareButton, BorderLayout.EAST);

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

        JLabel infoLabel = new JLabel("Формат: ДД.ММ.ГГГГ ЧЧ:ММ:СС (например: 25.12.2024 14:30:00)");
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

    private void compareDates() {
        String userInput = dateField.getText().trim();

        if (userInput.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Введите дату для сравнения!", "Ошибка", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            Date now = new Date();
            Date userDate = sdf.parse(userInput);

            StringBuilder result = new StringBuilder();
            result.append("═══════════════════════════════════════\n");
            result.append("Введенная дата: ").append(sdf.format(userDate)).append("\n");
            result.append("Текущая дата: ").append(sdf.format(now)).append("\n\n");

            int comparison = userDate.compareTo(now);

            if (comparison == 0) {
                result.append("✅ Даты совпадают!\n");
            } else if (comparison < 0) {
                result.append("📅 Введенная дата раньше нынешней!\n\n");
                appendTimeDifference(result, now.getTime() - userDate.getTime());
            } else {
                result.append("🔮 Введенная дата позже нынешней!\n\n");
                appendTimeDifference(result, userDate.getTime() - now.getTime());
            }

            resultArea.append(result.toString() + "\n");
            dateField.setText("");
            dateField.requestFocus();

        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this,
                    "Ошибка формата! Используйте: ДД.ММ.ГГГГ ЧЧ:ММ:СС\nПример: 25.12.2024 14:30:00",
                    "Ошибка ввода", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void appendTimeDifference(StringBuilder result, long diff) {
        long diffSeconds = diff / 1000;
        long diffMinutes = diff / (60 * 1000);
        long diffHours = diff / (60 * 60 * 1000);
        long diffDays = diff / (24 * 60 * 60 * 1000);

        result.append("📊 Разница:\n");
        result.append(String.format("   %d дней\n", diffDays));
        result.append(String.format("   %d часов\n", diffHours % 24));
        result.append(String.format("   %d минут\n", diffMinutes % 60));
        result.append(String.format("   %d секунд\n", diffSeconds % 60));
    }

    private void addListeners() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                returnToMainMenu();
            }
        });

        // Обработка Enter в текстовом поле
        dateField.addActionListener(e -> compareDates());
    }

    private void returnToMainMenu() {
        dispose();
        if (mainMenuFrame != null) {
            mainMenuFrame.setVisible(true);
            mainMenuFrame.toFront();
        }
    }

    public static void task2(JFrame mainMenuFrame) {
        if (mainMenuFrame != null) {
            mainMenuFrame.setVisible(false);
        }

        SwingUtilities.invokeLater(() -> new Task2(mainMenuFrame).setVisible(true));
    }
}