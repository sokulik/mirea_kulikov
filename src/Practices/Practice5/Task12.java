package Practices.Practice5;

import DOP.BaseTaskFrame;
import Practices.Practice5.Classes.T12.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Task12 extends BaseTaskFrame {
    private JTextArea resultArea;
    private JTextArea historyArea;
    private UndoableStringBuilder usb;
    private JLabel statusLabel;

    public Task12(JFrame mainMenuFrame) {
        super(mainMenuFrame,"Practice 5.12: UndoableStringBuilder");
        this.usb = new UndoableStringBuilder();

        initComponents();
        layoutComponents();
        addListeners();
        showWelcomeScreen();
    }

    private void initComponents() {
        resultArea = new JTextArea(8, 60);
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Consolas", Font.BOLD, 14));
        resultArea.setBackground(new Color(30, 30, 30));
        resultArea.setForeground(Color.GREEN);
        resultArea.setMargin(new Insets(10, 10, 10, 10));
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);

        historyArea = new JTextArea(15, 60);
        historyArea.setEditable(false);
        historyArea.setFont(new Font("Consolas", Font.PLAIN, 12));
        historyArea.setBackground(new Color(248, 249, 250));
        historyArea.setMargin(new Insets(10, 10, 10, 10));

        statusLabel = new JLabel("Готов к работе");
        statusLabel.setFont(new Font("Arial", Font.BOLD, 12));
        statusLabel.setForeground(new Color(70, 130, 180));
    }

    private void layoutComponents() {
        setLayout(new BorderLayout(10, 10));

        // Верхняя панель с управлением
        JPanel controlPanel = createControlPanel();
        add(controlPanel, BorderLayout.NORTH);

        // Центральная панель с результатами и историей
        JPanel centerPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Панель текущей строки
        JPanel resultPanel = new JPanel(new BorderLayout());
        resultPanel.setBorder(BorderFactory.createTitledBorder("📝 Текущая строка"));
        JScrollPane resultScroll = new JScrollPane(resultArea);
        resultPanel.add(resultScroll, BorderLayout.CENTER);

        // Статус бар
        JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        statusPanel.setBackground(new Color(240, 240, 240));
        statusPanel.add(statusLabel);
        resultPanel.add(statusPanel, BorderLayout.SOUTH);

        // Панель истории
        JPanel historyPanel = new JPanel(new BorderLayout());
        historyPanel.setBorder(BorderFactory.createTitledBorder("📜 История операций"));
        JScrollPane historyScroll = new JScrollPane(historyArea);
        historyPanel.add(historyScroll, BorderLayout.CENTER);

        centerPanel.add(resultPanel);
        centerPanel.add(historyPanel);
        add(centerPanel, BorderLayout.CENTER);

        // Панель снизу с кнопкой назад
        JPanel bottomPanel = createBottomPanel();
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private JPanel createControlPanel() {
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        controlPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        controlPanel.setBackground(new Color(240, 240, 240));

        // Основные операции
        JButton appendButton = createOperationButton("➕ Append", new Color(40, 167, 69));
        appendButton.addActionListener(e -> showAppendDialog());

        JButton insertButton = createOperationButton("📥 Insert", new Color(70, 130, 180));
        insertButton.addActionListener(e -> showInsertDialog());

        JButton replaceButton = createOperationButton("🔄 Replace", new Color(255, 193, 7));
        replaceButton.addActionListener(e -> showReplaceDialog());

        JButton deleteButton = createOperationButton("🗑️ Delete", new Color(220, 53, 69));
        deleteButton.addActionListener(e -> showDeleteDialog());

        JButton reverseButton = createOperationButton("🔄 Reverse", new Color(108, 117, 125));
        reverseButton.addActionListener(e -> reverseString());

        // Управление историей
        JButton undoButton = createOperationButton("⏪ Undo", new Color(153, 102, 204));
        undoButton.addActionListener(e -> undoOperation());

        JButton redoButton = createOperationButton("⏩ Redo", new Color(255, 140, 0));
        redoButton.addActionListener(e -> redoOperation());

        JButton clearButton = createOperationButton("🧹 Clear", new Color(108, 117, 125));
        clearButton.addActionListener(e -> clearAll());

        JButton demoButton = createOperationButton("🎭 Демо", new Color(70, 130, 180));
        demoButton.addActionListener(e -> runDemo());

        controlPanel.add(appendButton);
        controlPanel.add(insertButton);
        controlPanel.add(replaceButton);
        controlPanel.add(deleteButton);
        controlPanel.add(reverseButton);
        controlPanel.add(undoButton);
        controlPanel.add(redoButton);
        controlPanel.add(clearButton);
        controlPanel.add(demoButton);

        return controlPanel;
    }

    private JButton createOperationButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 11));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
        return button;
    }

    private void showAppendDialog() {
        String text = JOptionPane.showInputDialog(this,
                "Введите текст для добавления:", "➕ Append", JOptionPane.QUESTION_MESSAGE);

        if (text != null && !text.trim().isEmpty()) {
            usb.append(text);
            updateDisplay();
            setStatus("✅ Добавлен текст: \"" + text + "\"");
        }
    }

    private void showInsertDialog() {
        JPanel panel = new JPanel(new GridLayout(2, 2, 5, 5));
        JTextField positionField = new JTextField();
        JTextField textField = new JTextField();

        panel.add(new JLabel("Позиция:"));
        panel.add(positionField);
        panel.add(new JLabel("Текст:"));
        panel.add(textField);

        int result = JOptionPane.showConfirmDialog(this, panel,
                "📥 Insert", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            try {
                int position = Integer.parseInt(positionField.getText());
                String text = textField.getText();

                if (!text.isEmpty()) {
                    usb.insert(position, text);
                    updateDisplay();
                    setStatus("✅ Вставлен текст \"" + text + "\" на позицию " + position);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "❌ Введите корректную позицию!",
                        "Ошибка", JOptionPane.ERROR_MESSAGE);
            } catch (StringIndexOutOfBoundsException e) {
                JOptionPane.showMessageDialog(this, "❌ Недопустимая позиция!",
                        "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void showReplaceDialog() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));
        JTextField startField = new JTextField();
        JTextField endField = new JTextField();
        JTextField textField = new JTextField();

        panel.add(new JLabel("Начало:"));
        panel.add(startField);
        panel.add(new JLabel("Конец:"));
        panel.add(endField);
        panel.add(new JLabel("Новый текст:"));
        panel.add(textField);

        int result = JOptionPane.showConfirmDialog(this, panel,
                "🔄 Replace", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            try {
                int start = Integer.parseInt(startField.getText());
                int end = Integer.parseInt(endField.getText());
                String text = textField.getText();

                usb.replace(start, end, text);
                updateDisplay();
                setStatus("✅ Заменен текст с " + start + " по " + end + " на \"" + text + "\"");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "❌ Введите корректные числа!",
                        "Ошибка", JOptionPane.ERROR_MESSAGE);
            } catch (StringIndexOutOfBoundsException e) {
                JOptionPane.showMessageDialog(this, "❌ Недопустимый диапазон!",
                        "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void showDeleteDialog() {
        JPanel panel = new JPanel(new GridLayout(2, 2, 5, 5));
        JTextField startField = new JTextField();
        JTextField endField = new JTextField();

        panel.add(new JLabel("Начало:"));
        panel.add(startField);
        panel.add(new JLabel("Конец:"));
        panel.add(endField);

        int result = JOptionPane.showConfirmDialog(this, panel,
                "🗑️ Delete", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            try {
                int start = Integer.parseInt(startField.getText());
                int end = Integer.parseInt(endField.getText());

                usb.delete(start, end);
                updateDisplay();
                setStatus("✅ Удален текст с " + start + " по " + end);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "❌ Введите корректные числа!",
                        "Ошибка", JOptionPane.ERROR_MESSAGE);
            } catch (StringIndexOutOfBoundsException e) {
                JOptionPane.showMessageDialog(this, "❌ Недопустимый диапазон!",
                        "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void reverseString() {
        usb.reverse();
        updateDisplay();
        setStatus("✅ Строка перевернута");
    }

    private void undoOperation() {
        if (usb.undo()) {
            updateDisplay();
            setStatus("⏪ Операция отменена");
        } else {
            setStatus("❌ Нет операций для отмены");
        }
    }

    private void redoOperation() {
        if (usb.redo()) {
            updateDisplay();
            setStatus("⏩ Операция повторена");
        } else {
            setStatus("❌ Нет операций для повтора");
        }
    }

    private void clearAll() {
        int result = JOptionPane.showConfirmDialog(this,
                "Очистить всю строку и историю?",
                "🧹 Clear",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (result == JOptionPane.YES_OPTION) {
            usb = new UndoableStringBuilder();
            updateDisplay();
            setStatus("🧹 Все очищено");
        }
    }

    private void runDemo() {
        usb = new UndoableStringBuilder();

        StringBuilder demoLog = new StringBuilder();
        demoLog.append("=== 🎭 ДЕМОНСТРАЦИЯ UNDOABLESTRINGBUILDER ===\n\n");

        // Демонстрационные операции
        usb.append("Hello");
        demoLog.append("1. append('Hello'): ").append(usb).append("\n");

        usb.append(" World");
        demoLog.append("2. append(' World'): ").append(usb).append("\n");

        usb.insert(5, ",");
        demoLog.append("3. insert(5, ','): ").append(usb).append("\n");

        usb.replace(7, 12, "Java");
        demoLog.append("4. replace(7, 12, 'Java'): ").append(usb).append("\n");

        usb.reverse();
        demoLog.append("5. reverse(): ").append(usb).append("\n");

        usb.undo();
        demoLog.append("6. undo(): ").append(usb).append("\n");

        usb.undo();
        demoLog.append("7. undo(): ").append(usb).append("\n");

        usb.redo();
        demoLog.append("8. redo(): ").append(usb).append("\n");

        updateDisplay();
        resultArea.setText(demoLog.toString());
        setStatus("🎭 Демонстрация завершена");
    }

    private void updateDisplay() {
        // Обновляем основную строку
        String currentString = usb.toString();
        if (currentString.isEmpty()) {
            resultArea.setText("[строка пуста]");
            resultArea.setForeground(Color.GRAY);
        } else {
            resultArea.setText(currentString);
            resultArea.setForeground(Color.GREEN);
        }

        // Обновляем историю
        StringBuilder historyText = new StringBuilder();
        historyText.append("📊 Статистика:\n");
        historyText.append("• Длина строки: ").append(usb.length()).append("\n");
        historyText.append("• Можно отменить: ").append(usb.canUndo() ? "✅" : "❌").append("\n");
        historyText.append("• Можно повторить: ").append(usb.canRedo() ? "✅" : "❌").append("\n\n");

        historyText.append("📜 История операций:\n");
        historyText.append("─".repeat(50)).append("\n");

        java.util.List<String> history = usb.getHistory();
        if (history.isEmpty()) {
            historyText.append("История операций пуста\n");
        } else {
            for (String entry : history) {
                historyText.append("• ").append(entry).append("\n");
            }
        }

        historyArea.setText(historyText.toString());
    }

    private void setStatus(String message) {
        statusLabel.setText(message);

        // Автоматическое очищение статуса через 3 секунды
        Timer timer = new Timer(3000, e -> statusLabel.setText("Готов к работе"));
        timer.setRepeats(false);
        timer.start();
    }

    private void showWelcomeScreen() {
        StringBuilder welcome = new StringBuilder();
        welcome.append("🎯 UNDOABLESTRINGBUILDER - РАБОТА СО СТРОКАМИ\n");
        welcome.append("═".repeat(60)).append("\n\n");
        welcome.append("💡 Возможности:\n");
        welcome.append("• ➕ Append - добавить текст в конец\n");
        welcome.append("• 📥 Insert - вставить текст в позицию\n");
        welcome.append("• 🔄 Replace - заменить часть текста\n");
        welcome.append("• 🗑️ Delete - удалить часть текста\n");
        welcome.append("• 🔄 Reverse - перевернуть строку\n");
        welcome.append("• ⏪ Undo - отменить последнюю операцию\n");
        welcome.append("• ⏩ Redo - повторить отмененную операцию\n\n");
        welcome.append("🚀 Начните работу, выбрав операцию выше!");

        resultArea.setText(welcome.toString());
        resultArea.setForeground(Color.WHITE);
        updateDisplay();
    }

    private JPanel createBottomPanel() {
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(new Color(240, 240, 240));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton backButton = createBackButton();
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftPanel.setBackground(new Color(240, 240, 240));
        leftPanel.add(backButton);

        JLabel infoLabel = new JLabel("Паттерн: Command | Классы: UndoableStringBuilder, CommandHistory");
        infoLabel.setFont(new Font("Arial", Font.PLAIN, 11));
        infoLabel.setForeground(Color.DARK_GRAY);

        bottomPanel.add(leftPanel, BorderLayout.WEST);
        bottomPanel.add(infoLabel, BorderLayout.EAST);

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

    public static void task12(JFrame mainMenuFrame) {
        if (mainMenuFrame != null) {
            mainMenuFrame.setVisible(false);
        }

        SwingUtilities.invokeLater(() -> new Task12(mainMenuFrame).setVisible(true));
    }
}