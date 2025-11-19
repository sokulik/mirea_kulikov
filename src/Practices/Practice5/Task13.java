package Practices.Practice5;

import DOP.BaseTaskFrame;
import Practices.Practice5.Classes.T13.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class Task13 extends BaseTaskFrame {
    private JTextArea resultArea;
    private JTextArea observersArea;
    private ObservableStringBuilder osb;
    private List<StringBuilderObserver> observers;
    private JLabel statusLabel;

    public Task13(JFrame mainMenuFrame) {
        super(mainMenuFrame,"Practice 5.13: ObservableStringBuilder (Observer Pattern)");
        this.osb = new ObservableStringBuilderImpl();
        this.observers = new ArrayList<>();

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
        resultArea.setForeground(Color.CYAN);
        resultArea.setMargin(new Insets(10, 10, 10, 10));
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);

        observersArea = new JTextArea(15, 60);
        observersArea.setEditable(false);
        observersArea.setFont(new Font("Consolas", Font.PLAIN, 11));
        observersArea.setBackground(new Color(248, 249, 250));
        observersArea.setMargin(new Insets(10, 10, 10, 10));

        statusLabel = new JLabel("Готов к работе");
        statusLabel.setFont(new Font("Arial", Font.BOLD, 12));
        statusLabel.setForeground(new Color(70, 130, 180));
    }

    private void layoutComponents() {
        setLayout(new BorderLayout(10, 10));

        // Верхняя панель с управлением
        JPanel controlPanel = createControlPanel();
        add(controlPanel, BorderLayout.NORTH);

        // Центральная панель с результатами и наблюдателями
        JPanel centerPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Панель текущей строки
        JPanel resultPanel = new JPanel(new BorderLayout());
        resultPanel.setBorder(BorderFactory.createTitledBorder("📝 ObservableStringBuilder"));
        JScrollPane resultScroll = new JScrollPane(resultArea);
        resultPanel.add(resultScroll, BorderLayout.CENTER);

        // Статус бар
        JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        statusPanel.setBackground(new Color(240, 240, 240));
        statusPanel.add(statusLabel);
        resultPanel.add(statusPanel, BorderLayout.SOUTH);

        // Панель наблюдателей
        JPanel observersPanel = new JPanel(new BorderLayout());
        observersPanel.setBorder(BorderFactory.createTitledBorder("👀 Наблюдатели (Observers)"));
        JScrollPane observersScroll = new JScrollPane(observersArea);
        observersPanel.add(observersScroll, BorderLayout.CENTER);

        centerPanel.add(resultPanel);
        centerPanel.add(observersPanel);
        add(centerPanel, BorderLayout.CENTER);

        // Панель снизу с кнопкой назад
        JPanel bottomPanel = createBottomPanel();
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private JPanel createControlPanel() {
        JPanel mainControlPanel = new JPanel(new BorderLayout());
        mainControlPanel.setBackground(new Color(240, 240, 240));

        // Панель операций со строкой
        JPanel stringOpsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        stringOpsPanel.setBorder(BorderFactory.createTitledBorder("🛠️ Операции со строкой"));
        stringOpsPanel.setBackground(new Color(240, 240, 240));

        JButton appendButton = createOperationButton("➕ Append", new Color(40, 167, 69));
        appendButton.addActionListener(e -> showAppendDialog());

        JButton insertButton = createOperationButton("📥 Insert", new Color(70, 130, 180));
        insertButton.addActionListener(e -> showInsertDialog());

        JButton replaceButton = createOperationButton("🔄 Replace", new Color(255, 193, 7));
        replaceButton.addActionListener(e -> showReplaceDialog());

        JButton deleteButton = createOperationButton("🗑️ Delete", new Color(220, 53, 69));
        deleteButton.addActionListener(e -> showDeleteDialog());

        JButton reverseButton = createOperationButton("🔀 Reverse", new Color(153, 102, 204));
        reverseButton.addActionListener(e -> reverseString());

        JButton clearButton = createOperationButton("🧹 Clear", new Color(108, 117, 125));
        clearButton.addActionListener(e -> clearString());

        stringOpsPanel.add(appendButton);
        stringOpsPanel.add(insertButton);
        stringOpsPanel.add(replaceButton);
        stringOpsPanel.add(deleteButton);
        stringOpsPanel.add(reverseButton);
        stringOpsPanel.add(clearButton);

        // Панель управления наблюдателями
        JPanel observerControlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        observerControlPanel.setBorder(BorderFactory.createTitledBorder("👥 Управление наблюдателями"));
        observerControlPanel.setBackground(new Color(240, 240, 240));

        JButton addLoggerButton = createObserverButton("📝 Добавить Логгер", new Color(70, 130, 180));
        addLoggerButton.addActionListener(e -> addLoggerObserver());

        JButton addStatsButton = createObserverButton("📊 Добавить Статистику", new Color(40, 167, 69));
        addStatsButton.addActionListener(e -> addStatisticsObserver());

        JButton addTrackerButton = createObserverButton("🔄 Добавить Трекер", new Color(255, 140, 0));
        addTrackerButton.addActionListener(e -> addTrackerObserver());

        JButton removeObserverButton = createObserverButton("🚫 Удалить наблюдателя", new Color(220, 53, 69));
        removeObserverButton.addActionListener(e -> removeObserver());

        JButton clearObserversButton = createObserverButton("🧹 Очистить наблюдателей", new Color(108, 117, 125));
        clearObserversButton.addActionListener(e -> clearObservers());

        JButton demoButton = createObserverButton("🎭 Демо", new Color(153, 102, 204));
        demoButton.addActionListener(e -> runDemo());

        observerControlPanel.add(addLoggerButton);
        observerControlPanel.add(addStatsButton);
        observerControlPanel.add(addTrackerButton);
        observerControlPanel.add(removeObserverButton);
        observerControlPanel.add(clearObserversButton);
        observerControlPanel.add(demoButton);

        mainControlPanel.add(stringOpsPanel, BorderLayout.NORTH);
        mainControlPanel.add(observerControlPanel, BorderLayout.CENTER);

        return mainControlPanel;
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

    private JButton createObserverButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 10));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(6, 10, 6, 10));
        return button;
    }

    private void showAppendDialog() {
        String text = JOptionPane.showInputDialog(this,
                "Введите текст для добавления:", "➕ Append", JOptionPane.QUESTION_MESSAGE);

        if (text != null && !text.trim().isEmpty()) {
            osb.append(text);
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
                    osb.insert(position, text);
                    updateDisplay();
                    setStatus("✅ Вставлен текст \"" + text + "\" на позицию " + position);
                }
            } catch (NumberFormatException e) {
                showError("❌ Введите корректную позицию!");
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

                osb.replace(start, end, text);
                updateDisplay();
                setStatus("✅ Заменен текст с " + start + " по " + end + " на \"" + text + "\"");
            } catch (NumberFormatException e) {
                showError("❌ Введите корректные числа!");
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

                osb.delete(start, end);
                updateDisplay();
                setStatus("✅ Удален текст с " + start + " по " + end);
            } catch (NumberFormatException e) {
                showError("❌ Введите корректные числа!");
            }
        }
    }

    private void reverseString() {
        osb.reverse();
        updateDisplay();
        setStatus("✅ Строка перевернута");
    }

    private void clearString() {
        int result = JOptionPane.showConfirmDialog(this,
                "Очистить строку?",
                "🧹 Clear",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (result == JOptionPane.YES_OPTION) {
            // Создаем новый ObservableStringBuilder
            osb = new ObservableStringBuilderImpl();
            // Переподписываем всех наблюдателей
            for (StringBuilderObserver observer : observers) {
                osb.addObserver(observer);
            }
            updateDisplay();
            setStatus("🧹 Строка очищена");
        }
    }

    private void addLoggerObserver() {
        String name = JOptionPane.showInputDialog(this,
                "Введите имя для логгера:", "📝 Добавить Логгер", JOptionPane.QUESTION_MESSAGE);

        if (name != null && !name.trim().isEmpty()) {
            StringBuilderObserver logger = new LoggerObserver(name.trim());
            osb.addObserver(logger);
            observers.add(logger);
            updateDisplay();
            setStatus("✅ Добавлен логгер: " + name);
        }
    }

    private void addStatisticsObserver() {
        StringBuilderObserver stats = new StatisticsObserver();
        osb.addObserver(stats);
        observers.add(stats);
        updateDisplay();
        setStatus("✅ Добавлен статистический наблюдатель");
    }

    private void addTrackerObserver() {
        StringBuilderObserver tracker = new ChangeTrackerObserver();
        osb.addObserver(tracker);
        observers.add(tracker);
        updateDisplay();
        setStatus("✅ Добавлен трекер изменений");
    }

    private void removeObserver() {
        if (observers.isEmpty()) {
            showError("❌ Нет наблюдателей для удаления!");
            return;
        }

        String[] observerNames = new String[observers.size()];
        for (int i = 0; i < observers.size(); i++) {
            observerNames[i] = observers.get(i).getClass().getSimpleName() + " #" + (i + 1);
        }

        String selected = (String) JOptionPane.showInputDialog(this,
                "Выберите наблюдателя для удаления:",
                "🚫 Удалить наблюдателя",
                JOptionPane.QUESTION_MESSAGE,
                null,
                observerNames,
                observerNames[0]);

        if (selected != null) {
            int index = Integer.parseInt(selected.split("#")[1]) - 1;
            StringBuilderObserver removed = observers.remove(index);
            osb.removeObserver(removed);
            updateDisplay();
            setStatus("✅ Удален наблюдатель: " + selected);
        }
    }

    private void clearObservers() {
        if (observers.isEmpty()) {
            showError("❌ Нет наблюдателей для очистки!");
            return;
        }

        int result = JOptionPane.showConfirmDialog(this,
                "Удалить всех наблюдателей?",
                "🧹 Очистить наблюдателей",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (result == JOptionPane.YES_OPTION) {
            for (StringBuilderObserver observer : observers) {
                osb.removeObserver(observer);
            }
            observers.clear();
            updateDisplay();
            setStatus("🧹 Все наблюдатели удалены");
        }
    }

    private void runDemo() {
        // Сбрасываем состояние
        osb = new ObservableStringBuilderImpl();
        observers.clear();

        // Добавляем демонстрационных наблюдателей
        StringBuilderObserver logger = new LoggerObserver("Демо-Логгер");
        StringBuilderObserver stats = new StatisticsObserver();
        StringBuilderObserver tracker = new ChangeTrackerObserver();

        osb.addObserver(logger);
        osb.addObserver(stats);
        osb.addObserver(tracker);
        observers.add(logger);
        observers.add(stats);
        observers.add(tracker);

        StringBuilder demoLog = new StringBuilder();
        demoLog.append("=== 🎭 ДЕМОНСТРАЦИЯ OBSERVABLE STRING BUILDER ===\n\n");

        // Демонстрационные операции
        demoLog.append("1. append('Hello')\n");
        osb.append("Hello");

        demoLog.append("2. append(' World')\n");
        osb.append(" World");

        demoLog.append("3. insert(5, ',')\n");
        osb.insert(5, ",");

        demoLog.append("4. replace(7, 12, 'Java')\n");
        osb.replace(7, 12, "Java");

        demoLog.append("5. delete(11, 12)\n");
        osb.delete(11, 12);

        demoLog.append("6. reverse()\n");
        osb.reverse();

        updateDisplay();
        resultArea.setText(demoLog.toString());
        setStatus("🎭 Демонстрация завершена");
    }

    private void updateDisplay() {
        // Обновляем основную строку
        String currentString = osb.toString();
        if (currentString.isEmpty()) {
            resultArea.setText("[строка пуста]");
            resultArea.setForeground(Color.GRAY);
        } else {
            resultArea.setText(currentString);
            resultArea.setForeground(Color.CYAN);
        }

        // Обновляем информацию о наблюдателях
        StringBuilder observersText = new StringBuilder();
        observersText.append("📊 Информация о системе:\n");
        observersText.append("─".repeat(50)).append("\n");
        observersText.append("• Длина строки: ").append(osb.length()).append("\n");
        observersText.append("• Активных наблюдателей: ").append(observers.size()).append("\n\n");

        observersText.append("👀 Зарегистрированные наблюдатели:\n");
        observersText.append("─".repeat(50)).append("\n");

        if (observers.isEmpty()) {
            observersText.append("Нет активных наблюдателей\n");
            observersText.append("💡 Добавьте наблюдателей чтобы видеть изменения в реальном времени!\n");
        } else {
            for (int i = 0; i < observers.size(); i++) {
                StringBuilderObserver observer = observers.get(i);
                observersText.append("• ").append(i + 1).append(". ")
                        .append(observer.getClass().getSimpleName())
                        .append(" - ").append(getObserverDescription(observer))
                        .append("\n");
            }
        }

        observersText.append("\n💡 Паттерн Наблюдатель:\n");
        observersText.append("─".repeat(50)).append("\n");
        observersText.append("ObservableStringBuilder уведомляет всех наблюдателей\n");
        observersText.append("при каждом изменении строки. Каждый наблюдатель\n");
        observersText.append("получает новое состояние строки и описание операции.");

        observersArea.setText(observersText.toString());
    }

    private String getObserverDescription(StringBuilderObserver observer) {
        if (observer instanceof LoggerObserver) {
            return "Логирует все операции";
        } else if (observer instanceof StatisticsObserver) {
            return "Собирает статистику";
        } else if (observer instanceof ChangeTrackerObserver) {
            return "Отслеживает историю изменений";
        }
        return "Пользовательский наблюдатель";
    }

    private void setStatus(String message) {
        statusLabel.setText(message);

        // Автоматическое очищение статуса через 3 секунды
        Timer timer = new Timer(3000, e -> statusLabel.setText("Готов к работе"));
        timer.setRepeats(false);
        timer.start();
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Ошибка", JOptionPane.ERROR_MESSAGE);
    }

    private void showWelcomeScreen() {
        StringBuilder welcome = new StringBuilder();
        welcome.append("🎯 OBSERVABLESTRINGBUILDER - ПАТТЕРН НАБЛЮДАТЕЛЬ\n");
        welcome.append("═".repeat(70)).append("\n\n");
        welcome.append("💡 Принцип работы:\n");
        welcome.append("• ObservableStringBuilder уведомляет наблюдателей о изменениях\n");
        welcome.append("• Наблюдатели получают новое состояние и описание операции\n");
        welcome.append("• Поддерживаются все основные операции StringBuilder\n\n");

        welcome.append("👀 Типы наблюдателей:\n");
        welcome.append("• 📝 LoggerObserver - логирует операции\n");
        welcome.append("• 📊 StatisticsObserver - собирает статистику\n");
        welcome.append("• 🔄 ChangeTrackerObserver - отслеживает историю\n\n");

        welcome.append("🚀 Начните с добавления наблюдателей и выполнения операций!");

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

        JLabel infoLabel = new JLabel("Паттерн: Observer | Интерфейсы: ObservableStringBuilder, StringBuilderObserver");
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

    public static void task13(JFrame mainMenuFrame) {
        if (mainMenuFrame != null) {
            mainMenuFrame.setVisible(false);
        }

        SwingUtilities.invokeLater(() -> new Task13(mainMenuFrame).setVisible(true));
    }
}