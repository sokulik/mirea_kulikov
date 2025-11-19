package Practices.Practice6;

import Practices.Practice6.Classes.T4.MathFunc;
import Practices.Practice6.Classes.T4.MathCalculable;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Task4 extends JFrame {
    private JFrame mainMenuFrame;
    private JTextArea resultArea;
    private MathCalculable mathCalc;
    private MathFunc mathFunc;

    public Task4(JFrame mainMenuFrame) {
        super("Practice 6.4: Математические вычисления и интерфейсы");
        this.mainMenuFrame = mainMenuFrame;
        this.mathCalc = new MathFunc();
        this.mathFunc = new MathFunc();

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 700);
        setLocationRelativeTo(null);

        initComponents();
        layoutComponents();
        addListeners();
        showWelcomeScreen();
    }

    private void initComponents() {
        resultArea = new JTextArea(20, 60);
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Consolas", Font.PLAIN, 12));
        resultArea.setBackground(new Color(248, 249, 250));
        resultArea.setMargin(new Insets(10, 10, 10, 10));
    }

    private void layoutComponents() {
        setLayout(new BorderLayout(10, 10));

        // Верхняя панель с кнопками операций
        JPanel controlPanel = createControlPanel();
        add(controlPanel, BorderLayout.NORTH);

        // Центральная панель с результатами
        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Результаты вычислений"));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane, BorderLayout.CENTER);

        // Панель снизу с кнопкой назад
        JPanel bottomPanel = createBottomPanel();
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private JPanel createControlPanel() {
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        controlPanel.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));
        controlPanel.setBackground(new Color(240, 240, 240));

        // Кнопки математических операций
        JButton powerButton = createMathButton("⚡ Возведение в степень", new Color(70, 130, 180));
        powerButton.addActionListener(e -> showPowerDialog());

        JButton complexButton = createMathButton("🔢 Модуль комплексного числа", new Color(40, 167, 69));
        complexButton.addActionListener(e -> showComplexDialog());

        JButton circleLengthButton = createMathButton("📏 Длина окружности", new Color(255, 193, 7));
        circleLengthButton.addActionListener(e -> showCircleLengthDialog());

        JButton circleAreaButton = createMathButton("🔵 Площадь круга", new Color(220, 53, 69));
        circleAreaButton.addActionListener(e -> showCircleAreaDialog());

        JButton constantsButton = createMathButton("π Константы", new Color(153, 102, 204));
        constantsButton.addActionListener(e -> showConstants());

        JButton examplesButton = createMathButton("📊 Примеры", new Color(108, 117, 125));
        examplesButton.addActionListener(e -> showExamples());

        controlPanel.add(powerButton);
        controlPanel.add(complexButton);
        controlPanel.add(circleLengthButton);
        controlPanel.add(circleAreaButton);
        controlPanel.add(constantsButton);
        controlPanel.add(examplesButton);

        return controlPanel;
    }

    private JButton createMathButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        return button;
    }

    private void showPowerDialog() {
        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));
        JTextField baseField = new JTextField();
        JTextField exponentField = new JTextField();

        panel.add(new JLabel("Основание:"));
        panel.add(baseField);
        panel.add(new JLabel("Показатель степени:"));
        panel.add(exponentField);

        int result = JOptionPane.showConfirmDialog(this, panel,
                "⚡ Возведение в степень", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            try {
                double base = Double.parseDouble(baseField.getText());
                double exponent = Double.parseDouble(exponentField.getText());

                double powerResult = mathFunc.Exponentiation(base, exponent);

                StringBuilder output = new StringBuilder();
                output.append("=== ⚡ ВОЗВЕДЕНИЕ В СТЕПЕНЬ ===\n\n");
                output.append(String.format("Основание: %.2f\n", base));
                output.append(String.format("Показатель степени: %.2f\n", exponent));
                output.append(String.format("Результат: %.2f^%.2f = %.4f\n\n", base, exponent, powerResult));

                // Дополнительная информация
                output.append("💡 Математические свойства:\n");
                output.append("─".repeat(40)).append("\n");
                if (exponent == 0) {
                    output.append("• Любое число в степени 0 равно 1\n");
                } else if (exponent == 1) {
                    output.append("• Любое число в степени 1 равно самому себе\n");
                } else if (exponent == 2) {
                    output.append("• Квадрат числа\n");
                } else if (exponent == 3) {
                    output.append("• Куб числа\n");
                } else if (exponent < 0) {
                    output.append("• Отрицательная степень = 1/(число^|степень|)\n");
                }

                if (base < 0 && exponent % 2 != 0) {
                    output.append("• Отрицательное основание в нечетной степени дает отрицательный результат\n");
                }

                resultArea.setText(output.toString());
            } catch (NumberFormatException e) {
                showError("❌ Введите корректные числовые значения!");
            }
        }
    }

    private void showComplexDialog() {
        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));
        JTextField realField = new JTextField();
        JTextField imaginaryField = new JTextField();

        panel.add(new JLabel("Действительная часть:"));
        panel.add(realField);
        panel.add(new JLabel("Мнимая часть:"));
        panel.add(imaginaryField);

        int result = JOptionPane.showConfirmDialog(this, panel,
                "🔢 Модуль комплексного числа", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            try {
                double real = Double.parseDouble(realField.getText());
                double imaginary = Double.parseDouble(imaginaryField.getText());

                double modulus = mathFunc.Complex(real, imaginary);

                StringBuilder output = new StringBuilder();
                output.append("=== 🔢 МОДУЛЬ КОМПЛЕКСНОГО ЧИСЛА ===\n\n");
                output.append(String.format("Комплексное число: %.1f + %.1fi\n", real, imaginary));
                output.append(String.format("Модуль: |%.1f + %.1fi| = %.4f\n\n", real, imaginary, modulus));

                // Дополнительная информация
                output.append("💡 О комплексных числах:\n");
                output.append("─".repeat(40)).append("\n");
                output.append("• Модуль = √(a² + b²), где:\n");
                output.append("  a - действительная часть\n");
                output.append("  b - мнимая часть\n");
                output.append("• Модуль всегда неотрицателен\n");
                output.append("• Представляет расстояние от начала координат\n");
                output.append("  до точки (a, b) на комплексной плоскости\n\n");

                output.append("📐 Геометрическая интерпретация:\n");
                output.append("─".repeat(40)).append("\n");
                output.append(String.format("• Действительная часть: %.1f\n", real));
                output.append(String.format("• Мнимая часть: %.1f\n", imaginary));
                output.append(String.format("• Модуль (расстояние): %.4f\n", modulus));

                if (real == 0 && imaginary != 0) {
                    output.append("• Чисто мнимое число\n");
                } else if (imaginary == 0) {
                    output.append("• Действительное число\n");
                }

                resultArea.setText(output.toString());
            } catch (NumberFormatException e) {
                showError("❌ Введите корректные числовые значения!");
            }
        }
    }

    private void showCircleLengthDialog() {
        String radiusStr = JOptionPane.showInputDialog(this,
                "Введите радиус окружности:", "📏 Длина окружности", JOptionPane.QUESTION_MESSAGE);

        if (radiusStr != null && !radiusStr.trim().isEmpty()) {
            try {
                double radius = Double.parseDouble(radiusStr.trim());

                if (radius <= 0) {
                    showError("❌ Радиус должен быть положительным числом!");
                    return;
                }

                double length = mathFunc.circleLenght(radius);

                StringBuilder output = new StringBuilder();
                output.append("=== 📏 ДЛИНА ОКРУЖНОСТИ ===\n\n");
                output.append(String.format("Радиус: %.2f\n", radius));
                output.append(String.format("Длина окружности: 2 × π × %.2f = %.4f\n\n", radius, length));

                // Дополнительная информация
                output.append("💡 Формулы и свойства:\n");
                output.append("─".repeat(40)).append("\n");
                output.append("• Формула: L = 2 × π × R\n");
                output.append("• π (пи) ≈ ").append(MathCalculable.PI).append("\n");
                output.append("• R - радиус окружности\n\n");

                output.append("📐 Сравнительная таблица:\n");
                output.append("─".repeat(40)).append("\n");
                output.append(String.format("Радиус %6.1f -> Длина %8.2f\n", radius, length));
                output.append(String.format("Радиус %6.1f -> Длина %8.2f\n", radius/2, mathFunc.circleLenght(radius/2)));
                output.append(String.format("Радиус %6.1f -> Длина %8.2f\n", radius*2, mathFunc.circleLenght(radius*2)));

                resultArea.setText(output.toString());
            } catch (NumberFormatException e) {
                showError("❌ Введите корректное числовое значение!");
            }
        }
    }

    private void showCircleAreaDialog() {
        String radiusStr = JOptionPane.showInputDialog(this,
                "Введите радиус круга:", "🔵 Площадь круга", JOptionPane.QUESTION_MESSAGE);

        if (radiusStr != null && !radiusStr.trim().isEmpty()) {
            try {
                double radius = Double.parseDouble(radiusStr.trim());

                if (radius <= 0) {
                    showError("❌ Радиус должен быть положительным числом!");
                    return;
                }

                double area = mathFunc.circleArea(radius);

                StringBuilder output = new StringBuilder();
                output.append("=== 🔵 ПЛОЩАДЬ КРУГА ===\n\n");
                output.append(String.format("Радиус: %.2f\n", radius));
                output.append(String.format("Площадь круга: π × %.2f² = %.4f\n\n", radius, area));

                // Дополнительная информация
                output.append("💡 Формулы и свойства:\n");
                output.append("─".repeat(40)).append("\n");
                output.append("• Формула: S = π × R²\n");
                output.append("• π (пи) ≈ ").append(MathCalculable.PI).append("\n");
                output.append("• R - радиус круга\n");
                output.append("• R² = ").append(mathFunc.Exponentiation(radius, 2)).append("\n\n");

                output.append("📐 Сравнительная таблица:\n");
                output.append("─".repeat(40)).append("\n");
                output.append(String.format("Радиус %6.1f -> Площадь %8.2f\n", radius, area));
                output.append(String.format("Радиус %6.1f -> Площадь %8.2f\n", radius/2, mathFunc.circleArea(radius/2)));
                output.append(String.format("Радиус %6.1f -> Площадь %8.2f\n", radius*2, mathFunc.circleArea(radius*2)));

                resultArea.setText(output.toString());
            } catch (NumberFormatException e) {
                showError("❌ Введите корректное числовое значение!");
            }
        }
    }

    private void showConstants() {
        StringBuilder output = new StringBuilder();
        output.append("=== π МАТЕМАТИЧЕСКИЕ КОНСТАНТЫ ===\n\n");

        output.append("📐 Основные константы:\n");
        output.append("─".repeat(40)).append("\n");
        output.append("• π (пи) = ").append(MathCalculable.PI).append("\n");
        output.append("• e (число Эйлера) ≈ 2.71828\n");
        output.append("• φ (золотое сечение) ≈ 1.61803\n\n");

        output.append("💡 Интересные факты о π:\n");
        output.append("─".repeat(40)).append("\n");
        output.append("• π - отношение длины окружности к её диаметру\n");
        output.append("• Иррациональное число (бесконечные неповторяющиеся десятичные)\n");
        output.append("• Трансцендентное число\n");
        output.append("• Используется в геометрии, физике, инженерии\n\n");

        output.append("🎯 Применение констант:\n");
        output.append("─".repeat(40)).append("\n");
        output.append("• π - круги, сферы, тригонометрия\n");
        output.append("• e - экспоненциальный рост, сложные проценты\n");
        output.append("• φ - искусство, архитектура, природа\n");

        resultArea.setText(output.toString());
    }

    private void showExamples() {
        StringBuilder output = new StringBuilder();
        output.append("=== 📊 ПРИМЕРЫ ВЫЧИСЛЕНИЙ ===\n\n");

        output.append("⚡ Возведение в степень:\n");
        output.append("─".repeat(40)).append("\n");
        output.append(String.format("2³ = %.2f\n", mathFunc.Exponentiation(2, 3)));
        output.append(String.format("5² = %.2f\n", mathFunc.Exponentiation(5, 2)));
        output.append(String.format("10⁰ = %.2f\n", mathFunc.Exponentiation(10, 0)));
        output.append(String.format("4⁻¹ = %.2f\n\n", mathFunc.Exponentiation(4, -1)));

        output.append("🔢 Модуль комплексного числа:\n");
        output.append("─".repeat(40)).append("\n");
        output.append(String.format("|3 + 4i| = %.2f\n", mathFunc.Complex(3, 4)));
        output.append(String.format("|1 + 1i| = %.2f\n", mathFunc.Complex(1, 1)));
        output.append(String.format("|5 + 0i| = %.2f\n\n", mathFunc.Complex(5, 0)));

        output.append("📏 Длина окружности:\n");
        output.append("─".repeat(40)).append("\n");
        output.append(String.format("R=1 -> L=%.4f\n", mathFunc.circleLenght(1)));
        output.append(String.format("R=2 -> L=%.4f\n", mathFunc.circleLenght(2)));
        output.append(String.format("R=5 -> L=%.4f\n\n", mathFunc.circleLenght(5)));

        output.append("🔵 Площадь круга:\n");
        output.append("─".repeat(40)).append("\n");
        output.append(String.format("R=1 -> S=%.4f\n", mathFunc.circleArea(1)));
        output.append(String.format("R=2 -> S=%.4f\n", mathFunc.circleArea(2)));
        output.append(String.format("R=5 -> S=%.4f\n\n", mathFunc.circleArea(5)));

        output.append("💡 Используемые формулы:\n");
        output.append("─".repeat(40)).append("\n");
        output.append("• Степень: base^exponent\n");
        output.append("• Модуль: √(real² + imaginary²)\n");
        output.append("• Длина окружности: 2πR\n");
        output.append("• Площадь круга: πR²\n");

        resultArea.setText(output.toString());
    }

    private void showWelcomeScreen() {
        StringBuilder welcome = new StringBuilder();
        welcome.append("🎯 МАТЕМАТИЧЕСКИЕ ВЫЧИСЛЕНИЯ\n");
        welcome.append("═".repeat(50)).append("\n\n");
        welcome.append("Добро пожаловать в программу математических вычислений!\n\n");

        welcome.append("📐 Поддерживаемые операции:\n");
        welcome.append("─".repeat(30)).append("\n");
        welcome.append("• ⚡ Возведение в степень\n");
        welcome.append("• 🔢 Модуль комплексного числа\n");
        welcome.append("• 📏 Длина окружности\n");
        welcome.append("• 🔵 Площадь круга\n\n");

        welcome.append("💡 Используемые интерфейсы:\n");
        welcome.append("─".repeat(30)).append("\n");
        welcome.append("• MathCalculable - интерфейс с константой PI\n");
        welcome.append("• MathFunc - класс, реализующий интерфейс\n\n");

        welcome.append("🚀 Выберите операцию для начала работы!");

        resultArea.setText(welcome.toString());
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Ошибка", JOptionPane.ERROR_MESSAGE);
    }

    private JPanel createBottomPanel() {
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(new Color(240, 240, 240));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton backButton = createBackButton();
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftPanel.setBackground(new Color(240, 240, 240));
        leftPanel.add(backButton);

        JLabel infoLabel = new JLabel("Интерфейс: MathCalculable | Класс: MathFunc | Константа: PI = " + MathCalculable.PI);
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

    public static void task4(JFrame mainMenuFrame) {
        if (mainMenuFrame != null) {
            mainMenuFrame.setVisible(false);
        }

        SwingUtilities.invokeLater(() -> new Task4(mainMenuFrame).setVisible(true));
    }
}