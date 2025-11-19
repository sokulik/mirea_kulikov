package Practices.Practice5;

import DOP.BaseTaskFrame;
import Practices.Practice5.Classes.T11.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Task11 extends BaseTaskFrame {
    private JTextArea resultArea;
    private Convertable toKelvin;
    private Convertable toFahrenheit;

    public Task11(JFrame mainMenuFrame) {
        super(mainMenuFrame,"Practice 5.11: Конвертер температур");
        this.toKelvin = new CtK();
        this.toFahrenheit = new CtF();

        initComponents();
        layoutComponents();
        addListeners();
        showWelcomeScreen();
    }

    private void initComponents() {
        resultArea = new JTextArea(20, 50);
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Consolas", Font.PLAIN, 14));
        resultArea.setBackground(new Color(248, 249, 250));
        resultArea.setMargin(new Insets(10, 10, 10, 10));
    }

    private void layoutComponents() {
        setLayout(new BorderLayout(10, 10));

        // Верхняя панель с управлением
        JPanel controlPanel = createControlPanel();
        add(controlPanel, BorderLayout.NORTH);

        // Центральная панель с результатами
        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Результаты конвертации"));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane, BorderLayout.CENTER);

        // Панель снизу с кнопкой назад
        JPanel bottomPanel = createBottomPanel();
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private JPanel createControlPanel() {
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        controlPanel.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));
        controlPanel.setBackground(new Color(240, 240, 240));

        // Поле ввода температуры
        JLabel tempLabel = new JLabel("🌡️ Температура в °C:");
        tempLabel.setFont(new Font("Arial", Font.BOLD, 14));

        JTextField tempField = new JTextField(10);
        tempField.setFont(new Font("Arial", Font.PLAIN, 14));
        tempField.setHorizontalAlignment(JTextField.CENTER);

        // Кнопки конвертации
        JButton convertButton = new JButton("🔄 Конвертировать");
        convertButton.setBackground(new Color(70, 130, 180));
        convertButton.setForeground(Color.WHITE);
        convertButton.setFont(new Font("Arial", Font.BOLD, 12));
        convertButton.addActionListener(e -> convertTemperature(tempField.getText()));

        JButton clearButton = new JButton("🧹 Очистить");
        clearButton.setBackground(new Color(108, 117, 125));
        clearButton.setForeground(Color.WHITE);
        clearButton.addActionListener(e -> {
            tempField.setText("");
            showWelcomeScreen();
        });

        JButton examplesButton = new JButton("📊 Примеры");
        examplesButton.setBackground(new Color(40, 167, 69));
        examplesButton.setForeground(Color.WHITE);
        examplesButton.addActionListener(e -> showExamples());

        JButton formulaButton = new JButton("🧮 Формулы");
        examplesButton.setBackground(new Color(255, 193, 7));
        examplesButton.setForeground(Color.BLACK);
        formulaButton.addActionListener(e -> showFormulas());

        controlPanel.add(tempLabel);
        controlPanel.add(tempField);
        controlPanel.add(convertButton);
        controlPanel.add(clearButton);
        controlPanel.add(examplesButton);
        controlPanel.add(formulaButton);

        return controlPanel;
    }

    private void convertTemperature(String input) {
        if (input == null || input.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "❌ Введите значение температуры!",
                    "Ошибка",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            double celsius = Double.parseDouble(input.trim());
            convertAndDisplay(celsius);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "❌ Введите корректное числовое значение!",
                    "Ошибка",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void convertAndDisplay(double celsius) {
        double kelvin = toKelvin.convert(celsius);
        double fahrenheit = toFahrenheit.convert(celsius);

        StringBuilder output = new StringBuilder();
        output.append("=== 🌡️ РЕЗУЛЬТАТЫ КОНВЕРТАЦИИ ===\n\n");

        // Основные результаты
        output.append("📊 Основные шкалы:\n");
        output.append("─".repeat(50)).append("\n");
        output.append(String.format("🌡️  Цельсий:    %10.2f °C\n", celsius));
        output.append(String.format("🔥 Кельвин:    %10.2f K\n", kelvin));
        output.append(String.format("🌡️  Фаренгейт: %10.2f °F\n\n", fahrenheit));

        // Сравнительная таблица
        output.append("📈 Сравнительная таблица:\n");
        output.append("─".repeat(50)).append("\n");
        output.append(String.format("• %6.2f °C = %6.2f K\n", celsius, kelvin));
        output.append(String.format("• %6.2f °C = %6.2f °F\n\n", celsius, fahrenheit));

        // Интересные факты
        output.append("💡 Интересные температурные точки:\n");
        output.append("─".repeat(50)).append("\n");
        displayInterestingPoints(output, celsius);

        // Формулы
        output.append("\n🧮 Используемые формулы:\n");
        output.append("─".repeat(50)).append("\n");
        output.append("• K = °C + 273.15\n");
        output.append("• °F = (°C × 9/5) + 32\n");

        resultArea.setText(output.toString());
    }

    private void displayInterestingPoints(StringBuilder output, double celsius) {
        // Критические температурные точки
        double[] criticalPoints = {-273.15, -40, 0, 37, 100, 1000};
        String[] descriptions = {
                "Абсолютный ноль",
                "Точка совпадения Цельсия и Фаренгейта",
                "Температура замерзания воды",
                "Нормальная температура тела человека",
                "Температура кипения воды",
                "Температура красного каления"
        };

        for (int i = 0; i < criticalPoints.length; i++) {
            double point = criticalPoints[i];
            double diff = Math.abs(celsius - point);

            if (diff < 50) { // Показываем точки близкие к введенной температуре
                double k = toKelvin.convert(point);
                double f = toFahrenheit.convert(point);

                output.append(String.format("• %s: %.2f°C = %.2fK = %.2f°F\n",
                        descriptions[i], point, k, f));
            }
        }

        // Особые случаи
        if (celsius < -273.15) {
            output.append("❄️  Ниже абсолютного нуля! (Теоретически невозможно)\n");
        }
        if (Math.abs(celsius + 40) < 0.1) {
            output.append("🎯 Особый случай: -40°C = -40°F\n");
        }
    }

    private void showWelcomeScreen() {
        StringBuilder output = new StringBuilder();
        output.append("🎯 КОНВЕРТЕР ТЕМПЕРАТУР\n");
        output.append("═".repeat(50)).append("\n\n");
        output.append("Добро пожаловать в конвертер температур!\n\n");

        output.append("🌡️ Поддерживаемые шкалы:\n");
        output.append("─".repeat(30)).append("\n");
        output.append("• °C - Градусы Цельсия\n");
        output.append("• K  - Кельвины\n");
        output.append("• °F - Градусы Фаренгейта\n\n");

        output.append("🚀 Как использовать:\n");
        output.append("─".repeat(30)).append("\n");
        output.append("1. Введите температуру в °C в поле выше\n");
        output.append("2. Нажмите кнопку '🔄 Конвертировать'\n");
        output.append("3. Получите результат в K и °F\n\n");

        output.append("💡 Попробуйте ввести:\n");
        output.append("─".repeat(30)).append("\n");
        output.append("• 0    (точка замерзания воды)\n");
        output.append("• 100  (точка кипения воды)\n");
        output.append("• -40  (особая точка)\n");
        output.append("• 37   (температура тела)\n");

        resultArea.setText(output.toString());
    }

    private void showExamples() {
        StringBuilder output = new StringBuilder();
        output.append("📊 ТАБЛИЦА КОНВЕРТАЦИИ\n");
        output.append("═".repeat(60)).append("\n\n");

        output.append(String.format("%-12s %-12s %-12s %-20s\n",
                "Цельсий (°C)", "Кельвин (K)", "Фаренгейт (°F)", "Описание"));
        output.append("─".repeat(60)).append("\n");

        double[] examples = {-273.15, -40, -20, 0, 20, 37, 100, 500, 1000};
        String[] descs = {
                "Абсолютный ноль",
                "Особая точка C=F",
                "Морозный день",
                "Замерзание воды",
                "Комнатная температура",
                "Температура тела",
                "Кипение воды",
                "Температура пиццы",
                "Красное каление"
        };

        for (int i = 0; i < examples.length; i++) {
            double c = examples[i];
            double k = toKelvin.convert(c);
            double f = toFahrenheit.convert(c);

            output.append(String.format("%-12.2f %-12.2f %-12.2f %-20s\n", c, k, f, descs[i]));
        }

        output.append("\n💡 Интересные факты:\n");
        output.append("─".repeat(40)).append("\n");
        output.append("• Абсолютный ноль: -273.15°C = 0K\n");
        output.append("• Особенная точка: -40°C = -40°F\n");
        output.append("• Разница: 1°C = 1K, но 1°C = 1.8°F\n");

        resultArea.setText(output.toString());
    }

    private void showFormulas() {
        StringBuilder output = new StringBuilder();
        output.append("🧮 ФОРМУЛЫ КОНВЕРТАЦИИ\n");
        output.append("═".repeat(50)).append("\n\n");

        output.append("📐 Основные формулы преобразования:\n");
        output.append("─".repeat(40)).append("\n");
        output.append("Из Цельсия в Кельвин:\n");
        output.append("K = °C + 273.15\n\n");

        output.append("Из Цельсия в Фаренгейт:\n");
        output.append("°F = (°C × 9/5) + 32\n\n");

        output.append("📚 Обратные преобразования:\n");
        output.append("─".repeat(40)).append("\n");
        output.append("Из Кельвина в Цельсий:\n");
        output.append("°C = K - 273.15\n\n");

        output.append("Из Фаренгейта в Цельсий:\n");
        output.append("°C = (°F - 32) × 5/9\n\n");

        output.append("🎯 Прямое преобразование K ↔ °F:\n");
        output.append("─".repeat(40)).append("\n");
        output.append("K → °F: °F = (K - 273.15) × 9/5 + 32\n");
        output.append("°F → K: K = (°F - 32) × 5/9 + 273.15\n\n");

        output.append("💡 Историческая справка:\n");
        output.append("─".repeat(40)).append("\n");
        output.append("• Цельсий: предложена Андерсом Цельсием (1742)\n");
        output.append("• Фаренгейт: предложена Габриелем Фаренгейтом (1724)\n");
        output.append("• Кельвин: предложена лордом Кельвином (1848)\n");

        resultArea.setText(output.toString());
    }

    private JPanel createBottomPanel() {
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(new Color(240, 240, 240));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton backButton = createBackButton();
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftPanel.setBackground(new Color(240, 240, 240));
        leftPanel.add(backButton);

        JLabel infoLabel = new JLabel("Интерфейс: Convertable | Классы: CtK, CtF");
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

    public static void task11(JFrame mainMenuFrame) {
        if (mainMenuFrame != null) {
            mainMenuFrame.setVisible(false);
        }

        SwingUtilities.invokeLater(() -> new Task11(mainMenuFrame).setVisible(true));
    }
}