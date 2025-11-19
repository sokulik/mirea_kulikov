package Practices.Practice3;

import DOP.BaseTaskFrame;
import Practices.Practice3.Classes.*;
import Practices.Practice3.Classes.Rectangle;
import Practices.Practice3.Classes.Shape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Task1 extends BaseTaskFrame {
    private JTextArea resultArea;
    private JTabbedPane tabbedPane;

    public Task1(JFrame mainMenuFrame) {
        super(mainMenuFrame,"Practice 3.1: Наследование фигур");

        initComponents();
        layoutComponents();
        addListeners();
        demonstrateShapes();
    }

    private void initComponents() {
        resultArea = new JTextArea(20, 60);
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Consolas", Font.PLAIN, 12));
        resultArea.setBackground(new Color(248, 249, 250));

        tabbedPane = new JTabbedPane();
    }

    private void layoutComponents() {
        setLayout(new BorderLayout(10, 10));

        // Создаем вкладки для каждой фигуры
        createTabs();

        add(tabbedPane, BorderLayout.CENTER);

        // Панель снизу с кнопкой назад
        JPanel bottomPanel = createBottomPanel();
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void createTabs() {
        // Вкладка Circle
        JPanel circleTab = createCircleTab();
        tabbedPane.addTab("🔵 Circle", circleTab);

        // Вкладка Rectangle
        JPanel rectangleTab = createRectangleTab();
        tabbedPane.addTab("🟦 Rectangle", rectangleTab);

        // Вкладка Square
        JPanel squareTab = createSquareTab();
        tabbedPane.addTab("🟥 Square", squareTab);

        // Вкладка Полиморфизм
        JPanel polymorphismTab = createPolymorphismTab();
        tabbedPane.addTab("🎭 Полиморфизм", polymorphismTab);
    }

    private JPanel createCircleTab() {
        JPanel panel = new JPanel(new BorderLayout());

        JTextArea textArea = new JTextArea(15, 50);
        textArea.setEditable(false);
        textArea.setFont(new Font("Consolas", Font.PLAIN, 12));
        textArea.setBackground(new Color(248, 249, 250));

        JButton demoButton = new JButton("Демонстрация Circle");
        demoButton.setBackground(new Color(70, 130, 180));
        demoButton.setForeground(Color.WHITE);
        demoButton.addActionListener(e -> {
            StringBuilder output = new StringBuilder();
            output.append("=== Тестирование Circle ===\n\n");

            Circle circle1 = new Circle();
            Circle circle2 = new Circle(5.0);
            Circle circle3 = new Circle(3.0, "blue", true);

            output.append("Круг №1: " + circle1 + " | Площадь: " + String.format("%.2f", circle1.getArea())
                    + " | Периметр: " + String.format("%.2f", circle1.getPerimeter())
                    + "\nЦвет: " + circle1.getColor() + " | Заполнение: " + circle1.isFilled() + "\n\n");

            output.append("Круг №2: " + circle2 + " | Площадь: " + String.format("%.2f", circle2.getArea())
                    + " | Периметр: " + String.format("%.2f", circle2.getPerimeter())
                    + "\nЦвет: " + circle2.getColor() + " | Заполнение: " + circle2.isFilled() + "\n\n");

            output.append("Круг №3: " + circle3 + " | Площадь: " + String.format("%.2f", circle3.getArea())
                    + " | Периметр: " + String.format("%.2f", circle3.getPerimeter())
                    + "\nЦвет: " + circle3.getColor() + " | Заполнение: " + circle3.isFilled() + "\n\n");

            circle1.setRadius(2.5);
            circle1.setColor("Red&White");
            circle1.setFilled(false);
            output.append("Круг №1 после изменений: " + circle1 + "\nПлощадь: " + String.format("%.2f", circle1.getArea())
                    + " | Периметр: " + String.format("%.2f", circle1.getPerimeter())
                    + "\nЦвет: " + circle1.getColor() + " | Заполнение: " + circle1.isFilled() + "\n");

            textArea.setText(output.toString());
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(demoButton);

        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createRectangleTab() {
        JPanel panel = new JPanel(new BorderLayout());

        JTextArea textArea = new JTextArea(15, 50);
        textArea.setEditable(false);
        textArea.setFont(new Font("Consolas", Font.PLAIN, 12));
        textArea.setBackground(new Color(248, 249, 250));

        JButton demoButton = new JButton("Демонстрация Rectangle");
        demoButton.setBackground(new Color(70, 130, 180));
        demoButton.setForeground(Color.WHITE);
        demoButton.addActionListener(e -> {
            StringBuilder output = new StringBuilder();
            output.append("=== Тестирование Rectangle ===\n\n");

            Rectangle rectangle1 = new Rectangle();
            Rectangle rectangle2 = new Rectangle(10.0, 15.0);
            Rectangle rectangle3 = new Rectangle(10.3, 6.8, "Black", true);

            output.append("Прямоугольник №1: " + rectangle1 + " | Периметр: " + String.format("%.2f", rectangle1.getPerimeter())
                    + " | Площадь: " + String.format("%.2f", rectangle1.getArea())
                    + "\nЦвет: " + rectangle1.getColor() + " | Заполнение: " + rectangle1.isFilled() + "\n\n");

            output.append("Прямоугольник №2: " + rectangle2 + " | Периметр: " + String.format("%.2f", rectangle2.getPerimeter())
                    + " | Площадь: " + String.format("%.2f", rectangle2.getArea())
                    + "\nЦвет: " + rectangle2.getColor() + " | Заполнение: " + rectangle2.isFilled() + "\n\n");

            output.append("Прямоугольник №3: " + rectangle3 + " | Периметр: " + String.format("%.2f", rectangle3.getPerimeter())
                    + " | Площадь: " + String.format("%.2f", rectangle3.getArea())
                    + "\nЦвет: " + rectangle3.getColor() + " | Заполнение: " + rectangle3.isFilled() + "\n\n");

            rectangle1.setLength(150.0);
            rectangle1.setWidth(600);
            rectangle1.setColor("White&Black");
            rectangle1.setFilled(true);
            output.append("Прямоугольник №1 после изменений: " + rectangle1
                    + "\nПериметр: " + String.format("%.2f", rectangle1.getPerimeter()) + " | Площадь: " + String.format("%.2f", rectangle1.getArea())
                    + "\nЦвет: " + rectangle1.getColor() + " | Заполнение: " + rectangle1.isFilled() + "\n");

            textArea.setText(output.toString());
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(demoButton);

        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createSquareTab() {
        JPanel panel = new JPanel(new BorderLayout());

        JTextArea textArea = new JTextArea(15, 50);
        textArea.setEditable(false);
        textArea.setFont(new Font("Consolas", Font.PLAIN, 12));
        textArea.setBackground(new Color(248, 249, 250));

        JButton demoButton = new JButton("Демонстрация Square");
        demoButton.setBackground(new Color(70, 130, 180));
        demoButton.setForeground(Color.WHITE);
        demoButton.addActionListener(e -> {
            StringBuilder output = new StringBuilder();
            output.append("=== Тестирование Square ===\n\n");

            Square square1 = new Square();
            Square square2 = new Square(4.0);
            Square square3 = new Square(25.0, "Yellow", true);

            output.append("Квадрат №1: " + square1 + " | Периметр: " + String.format("%.2f", square1.getPerimeter())
                    + " | Площадь: " + String.format("%.2f", square1.getArea())
                    + "\nЦвет: " + square1.getColor() + " | Заполнение: " + square1.isFilled() + "\n\n");

            output.append("Квадрат №2: " + square2 + " | Периметр: " + String.format("%.2f", square2.getPerimeter())
                    + " | Площадь: " + String.format("%.2f", square2.getArea())
                    + "\nЦвет: " + square2.getColor() + " | Заполнение: " + square2.isFilled() + "\n\n");

            output.append("Квадрат №3: " + square3 + " | Периметр: " + String.format("%.2f", square3.getPerimeter())
                    + " | Площадь: " + String.format("%.2f", square3.getArea())
                    + "\nЦвет: " + square3.getColor() + " | Заполнение: " + square3.isFilled() + "\n\n");

            square1.setSide(225);
            square1.setColor("White&Blue&Red");
            square1.setFilled(true);
            output.append("Квадрат №1 после изменений: " + square1
                    + "\nПериметр: " + String.format("%.2f", square1.getPerimeter()) + " | Площадь: " + String.format("%.2f", square1.getArea())
                    + "\nЦвет: " + square1.getColor() + " | Заполнение: " + square1.isFilled() + "\n\n");

            output.append("=== Тестирование сеттеров Square ===\n\n");
            square1.setSide(95);
            output.append("После setSide(95): " + square1
                    + "\nПериметр: " + String.format("%.2f", square1.getPerimeter()) + " | Площадь: " + String.format("%.2f", square1.getArea()) + "\n\n");

            square1.setLength(50);
            output.append("После setLength(50): " + square1
                    + "\nПериметр: " + String.format("%.2f", square1.getPerimeter()) + " | Площадь: " + String.format("%.2f", square1.getArea()) + "\n\n");

            square1.setWidth(21);
            output.append("После setWidth(21): " + square1
                    + "\nПериметр: " + String.format("%.2f", square1.getPerimeter()) + " | Площадь: " + String.format("%.2f", square1.getArea()) + "\n");

            textArea.setText(output.toString());
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(demoButton);

        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createPolymorphismTab() {
        JPanel panel = new JPanel(new BorderLayout());

        JTextArea textArea = new JTextArea(15, 50);
        textArea.setEditable(false);
        textArea.setFont(new Font("Consolas", Font.PLAIN, 12));
        textArea.setBackground(new Color(248, 249, 250));

        JButton demoButton = new JButton("Демонстрация полиморфизма");
        demoButton.setBackground(new Color(70, 130, 180));
        demoButton.setForeground(Color.WHITE);
        demoButton.addActionListener(e -> {
            StringBuilder output = new StringBuilder();
            output.append("=== Демонстрация полиморфизма ===\n\n");

            Shape[] s = new Shape[3];
            s[0] = new Circle(5.0, "Orange", false);
            s[1] = new Rectangle(2.0, 4.0, "Green", true);
            s[2] = new Square(6.0, "Pink", true);

            for (Shape shape : s){
                output.append("Фигура: " + shape + "\nПериметр: " + String.format("%.2f", shape.getPerimeter())
                        + " | Площадь: " + String.format("%.2f", shape.getArea())
                        + "\nЦвет: " + shape.getColor() + " | Заполнение: " + shape.isFilled() + "\n\n");
            }

            textArea.setText(output.toString());
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(demoButton);

        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private void demonstrateShapes() {
        // Автоматически показываем демонстрацию при запуске
        ((JButton)((JPanel)((JPanel)tabbedPane.getComponentAt(0)).getComponent(1)).getComponent(0)).doClick();
    }

    private JPanel createBottomPanel() {
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(new Color(240, 240, 240));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton backButton = createBackButton();
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftPanel.setBackground(new Color(240, 240, 240));
        leftPanel.add(backButton);

        JLabel infoLabel = new JLabel("Наследование: Circle, Rectangle, Square ← Shape");
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

    public static void task1(JFrame mainMenuFrame) {
        if (mainMenuFrame != null) {
            mainMenuFrame.setVisible(false);
        }

        SwingUtilities.invokeLater(() -> new Task1(mainMenuFrame).setVisible(true));
    }
}