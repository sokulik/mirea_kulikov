package Practices.Practice6;

import Practices.Practice6.Classes.T56.ProcessString;
import Practices.Practice6.Classes.T56.CommandsString;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Task5_6 extends JFrame {
    private JFrame mainMenuFrame;
    private JTextArea resultArea;
    private JTextArea inputArea;
    private ProcessString processString;

    public Task5_6(JFrame mainMenuFrame) {
        super("Practice 6.5-6: Операции со строками");
        this.mainMenuFrame = mainMenuFrame;
        this.processString = new ProcessString();

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(900, 750);
        setLocationRelativeTo(null);

        initComponents();
        layoutComponents();
        addListeners();
        showWelcomeScreen();
    }

    private void initComponents() {
        // Область ввода
        inputArea = new JTextArea(3, 60);
        inputArea.setFont(new Font("Arial", Font.PLAIN, 14));
        inputArea.setLineWrap(true);
        inputArea.setWrapStyleWord(true);
        inputArea.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));

        // Область результатов
        resultArea = new JTextArea(20, 60);
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Consolas", Font.PLAIN, 12));
        resultArea.setBackground(new Color(248, 249, 250));
        resultArea.setMargin(new Insets(10, 10, 10, 10));
    }

    private void layoutComponents() {
        setLayout(new BorderLayout(10, 10));

        // Верхняя панель с вводом
        JPanel inputPanel = createInputPanel();
        add(inputPanel, BorderLayout.NORTH);

        // Центральная панель с кнопками операций
        JPanel operationsPanel = createOperationsPanel();
        add(operationsPanel, BorderLayout.CENTER);

        // Панель с результатами
        JScrollPane resultScroll = new JScrollPane(resultArea);
        resultScroll.setBorder(BorderFactory.createTitledBorder("Результаты анализа строки"));
        resultScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(resultScroll, BorderLayout.SOUTH);

        // Панель снизу с кнопкой назад
        JPanel bottomPanel = createBottomPanel();
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private JPanel createInputPanel() {
        JPanel inputPanel = new JPanel(new BorderLayout(10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        inputPanel.setBackground(new Color(240, 240, 240));

        JLabel inputLabel = new JLabel("📝 Введите строку для анализа:");
        inputLabel.setFont(new Font("Arial", Font.BOLD, 14));
        inputLabel.setForeground(new Color(70, 130, 180));

        JScrollPane inputScroll = new JScrollPane(inputArea);
        inputScroll.setPreferredSize(new Dimension(700, 80));

        JButton analyzeButton = new JButton("🔍 Анализировать строку");
        analyzeButton.setBackground(new Color(70, 130, 180));
        analyzeButton.setForeground(Color.WHITE);
        analyzeButton.setFont(new Font("Arial", Font.BOLD, 12));
        analyzeButton.addActionListener(e -> analyzeString());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(new Color(240, 240, 240));
        buttonPanel.add(analyzeButton);

        inputPanel.add(inputLabel, BorderLayout.NORTH);
        inputPanel.add(inputScroll, BorderLayout.CENTER);
        inputPanel.add(buttonPanel, BorderLayout.SOUTH);

        return inputPanel;
    }

    private JPanel createOperationsPanel() {
        JPanel operationsPanel = new JPanel(new GridLayout(2, 2, 15, 15));
        operationsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        operationsPanel.setBackground(new Color(240, 240, 240));

        // Кнопка подсчета символов
        JButton countButton = createOperationButton("🔢 Подсчет символов",
                "Количество символов в строке", new Color(40, 167, 69));
        countButton.addActionListener(e -> showCountOperation());

        // Кнопка символов на нечетных позициях
        JButton positionButton = createOperationButton("📋 Символы на нечетных позициях",
                "Символы на позициях 1, 3, 5...", new Color(70, 130, 180));
        positionButton.addActionListener(e -> showPositionOperation());

        // Кнопка инвертирования строки
        JButton reverseButton = createOperationButton("🔄 Инвертирование строки",
                "Перевернуть строку задом наперед", new Color(255, 193, 7));
        reverseButton.addActionListener(e -> showReverseOperation());

        // Кнопка полного анализа
        JButton fullAnalysisButton = createOperationButton("📊 Полный анализ",
                "Все операции сразу", new Color(153, 102, 204));
        fullAnalysisButton.addActionListener(e -> showFullAnalysis());

        operationsPanel.add(countButton);
        operationsPanel.add(positionButton);
        operationsPanel.add(reverseButton);
        operationsPanel.add(fullAnalysisButton);

        return operationsPanel;
    }

    private JButton createOperationButton(String text, String tooltip, Color color) {
        JButton button = new JButton("<html><center>" + text.replace(" ", "<br>") + "</center></html>");
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setToolTipText(tooltip);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(20, 15, 20, 15));
        button.setHorizontalAlignment(SwingConstants.CENTER);
        button.setVerticalAlignment(SwingConstants.CENTER);

        // Эффект при наведении
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(color.brighter());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(color);
            }
        });

        return button;
    }

    private void analyzeString() {
        String input = inputArea.getText().trim();
        if (input.isEmpty()) {
            showError("❌ Введите строку для анализа!");
            return;
        }
        showFullAnalysis();
    }

    private void showCountOperation() {
        String input = getInputString();
        if (input == null) return;

        int count = processString.Count(input);

        StringBuilder output = new StringBuilder();
        output.append("=== 🔢 ПОДСЧЕТ СИМВОЛОВ ===\n\n");
        output.append("Исходная строка: \"").append(input).append("\"\n");
        output.append("Количество символов: ").append(count).append("\n\n");

        output.append("📊 Детальный анализ длины:\n");
        output.append("─".repeat(40)).append("\n");
        output.append("• Всего символов: ").append(count).append("\n");
        output.append("• Символов без пробелов: ").append(input.replace(" ", "").length()).append("\n");
        output.append("• Пробелов: ").append(countCharacters(input, ' ')).append("\n");
        output.append("• Цифр: ").append(countDigits(input)).append("\n");
        output.append("• Букв: ").append(countLetters(input)).append("\n");
        output.append("• Специальных символов: ").append(countSpecialChars(input)).append("\n\n");

        output.append("💡 Классификация длины:\n");
        output.append("─".repeat(40)).append("\n");
        if (count == 0) {
            output.append("• Пустая строка\n");
        } else if (count <= 10) {
            output.append("• Короткая строка\n");
        } else if (count <= 50) {
            output.append("• Средняя строка\n");
        } else if (count <= 100) {
            output.append("• Длинная строка\n");
        } else {
            output.append("• Очень длинная строка\n");
        }

        resultArea.setText(output.toString());
    }

    private void showPositionOperation() {
        String input = getInputString();
        if (input == null) return;

        String positions = processString.Position(input);

        StringBuilder output = new StringBuilder();
        output.append("=== 📋 СИМВОЛЫ НА НЕЧЕТНЫХ ПОЗИЦИЯХ ===\n\n");
        output.append("Исходная строка: \"").append(input).append("\"\n");
        output.append("Символы на нечетных позициях: \"").append(positions).append("\"\n\n");

        output.append("📐 Позиционный анализ (индексация с 0):\n");
        output.append("─".repeat(50)).append("\n");
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            output.append(String.format("Позиция %2d: '%c'", i, c));
            if (i % 2 == 0) {
                output.append(" ← ВКЛЮЧЕН (четный индекс)");
            }
            output.append("\n");
        }

        output.append("\n💡 Пояснение:\n");
        output.append("─".repeat(40)).append("\n");
        output.append("• В программировании индексация начинается с 0\n");
        output.append("• Нечетные позиции = четные индексы (0, 2, 4...)\n");
        output.append("• Выбрано символов: ").append(positions.length()).append(" из ").append(input.length()).append("\n");

        resultArea.setText(output.toString());
    }

    private void showReverseOperation() {
        String input = getInputString();
        if (input == null) return;

        String reversed = processString.Reverse(input);

        StringBuilder output = new StringBuilder();
        output.append("=== 🔄 ИНВЕРТИРОВАНИЕ СТРОКИ ===\n\n");
        output.append("Исходная строка:  \"").append(input).append("\"\n");
        output.append("Инвертированная: \"").append(reversed).append("\"\n\n");

        output.append("🔍 Сравнительный анализ:\n");
        output.append("─".repeat(50)).append("\n");
        output.append("Оригинал:  ").append(input).append("\n");
        output.append("Реверс:    ").append(reversed).append("\n\n");

        if (input.equals(reversed)) {
            output.append("🎯 Это палиндром! Строка читается одинаково в обе стороны.\n\n");
        }

        output.append("📝 Посимвольное преобразование:\n");
        output.append("─".repeat(40)).append("\n");
        for (int i = 0; i < input.length(); i++) {
            char original = input.charAt(i);
            char reversedChar = reversed.charAt(input.length() - 1 - i);
            output.append(String.format("'%c' [поз.%2d] → '%c' [поз.%2d]%n",
                    original, i, reversedChar, input.length() - 1 - i));
        }

        resultArea.setText(output.toString());
    }

    private void showFullAnalysis() {
        String input = getInputString();
        if (input == null) return;

        StringBuilder output = new StringBuilder();
        output.append("=== 📊 ПОЛНЫЙ АНАЛИЗ СТРОКИ ===\n\n");

        output.append("📝 Исходная строка:\n");
        output.append("─".repeat(40)).append("\n");
        output.append("\"").append(input).append("\"\n\n");

        output.append("🔢 Базовая информация:\n");
        output.append("─".repeat(40)).append("\n");
        output.append("• Количество символов: ").append(processString.Count(input)).append("\n");
        output.append("• Символы на нечетных позициях: \"").append(processString.Position(input)).append("\"\n");
        output.append("• Инвертированная строка: \"").append(processString.Reverse(input)).append("\"\n\n");

        output.append("📈 Статистика символов:\n");
        output.append("─".repeat(40)).append("\n");
        output.append("• Пробелы: ").append(countCharacters(input, ' ')).append("\n");
        output.append("• Цифры: ").append(countDigits(input)).append("\n");
        output.append("• Буквы: ").append(countLetters(input)).append("\n");
        output.append("• Прописные: ").append(countUppercase(input)).append("\n");
        output.append("• Строчные: ").append(countLowercase(input)).append("\n");
        output.append("• Специальные: ").append(countSpecialChars(input)).append("\n\n");

        output.append("🎯 Особые свойства:\n");
        output.append("─".repeat(40)).append("\n");
        if (input.equals(processString.Reverse(input))) {
            output.append("• ✅ Палиндром\n");
        } else {
            output.append("• ❌ Не палиндром\n");
        }
        if (input.trim().isEmpty()) {
            output.append("• ✅ Пустая строка (только пробелы)\n");
        }
        if (input.length() > 0 && Character.isDigit(input.charAt(0))) {
            output.append("• ⚠️  Начинается с цифры\n");
        }
        if (input.length() > 0 && Character.isLetter(input.charAt(0))) {
            output.append("• ⚠️  Начинается с буквы\n");
        }

        resultArea.setText(output.toString());
    }

    private String getInputString() {
        String input = inputArea.getText().trim();
        if (input.isEmpty()) {
            showError("❌ Введите строку для анализа!");
            return null;
        }
        return input;
    }

    // Вспомогательные методы для подсчета символов
    private int countCharacters(String str, char ch) {
        int count = 0;
        for (char c : str.toCharArray()) {
            if (c == ch) count++;
        }
        return count;
    }

    private int countDigits(String str) {
        int count = 0;
        for (char c : str.toCharArray()) {
            if (Character.isDigit(c)) count++;
        }
        return count;
    }

    private int countLetters(String str) {
        int count = 0;
        for (char c : str.toCharArray()) {
            if (Character.isLetter(c)) count++;
        }
        return count;
    }

    private int countUppercase(String str) {
        int count = 0;
        for (char c : str.toCharArray()) {
            if (Character.isUpperCase(c)) count++;
        }
        return count;
    }

    private int countLowercase(String str) {
        int count = 0;
        for (char c : str.toCharArray()) {
            if (Character.isLowerCase(c)) count++;
        }
        return count;
    }

    private int countSpecialChars(String str) {
        int count = 0;
        for (char c : str.toCharArray()) {
            if (!Character.isLetterOrDigit(c) && !Character.isWhitespace(c)) {
                count++;
            }
        }
        return count;
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Ошибка", JOptionPane.ERROR_MESSAGE);
    }

    private void showWelcomeScreen() {
        StringBuilder welcome = new StringBuilder();
        welcome.append("🎯 ОПЕРАЦИИ СО СТРОКАМИ\n");
        welcome.append("═".repeat(50)).append("\n\n");
        welcome.append("Добро пожаловать в анализатор строк!\n\n");

        welcome.append("📋 Поддерживаемые операции:\n");
        welcome.append("─".repeat(30)).append("\n");
        welcome.append("• 🔢 Подсчет символов - общее количество\n");
        welcome.append("• 📋 Нечетные позиции - символы на позициях 1,3,5...\n");
        welcome.append("• 🔄 Инвертирование - переворот строки\n");
        welcome.append("• 📊 Полный анализ - все операции сразу\n\n");

        welcome.append("💡 Используемые интерфейсы:\n");
        welcome.append("─".repeat(30)).append("\n");
        welcome.append("• CommandsString - интерфейс операций\n");
        welcome.append("• ProcessString - реализация операций\n\n");

        welcome.append("🚀 Введите строку и выберите операцию!");

        resultArea.setText(welcome.toString());
    }

    private JPanel createBottomPanel() {
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(new Color(240, 240, 240));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton backButton = createBackButton();
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftPanel.setBackground(new Color(240, 240, 240));
        leftPanel.add(backButton);

        JLabel infoLabel = new JLabel("Интерфейс: CommandsString | Класс: ProcessString");
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

    private void addListeners() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                returnToMainMenu();
            }
        });
    }

    private void returnToMainMenu() {
        dispose();
        if (mainMenuFrame != null) {
            mainMenuFrame.setVisible(true);
            mainMenuFrame.toFront();
        }
    }

    public static void task5_6(JFrame mainMenuFrame) {
        if (mainMenuFrame != null) {
            mainMenuFrame.setVisible(false);
        }

        SwingUtilities.invokeLater(() -> new Task5_6(mainMenuFrame).setVisible(true));
    }
}