package Practices.Practice3;

import DOP.BaseTaskFrame;
import Practices.Practice3.Classes.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Task2 extends BaseTaskFrame {
    private JTextArea resultArea;
    private JTabbedPane tabbedPane;

    public Task2(JFrame mainMenuFrame) {
        super(mainMenuFrame,"Practice 3.2: Интерфейсы Movable");

        initComponents();
        layoutComponents();
        addListeners();
        demonstrateMovable();
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

        // Создаем вкладки для каждого Movable объекта
        createTabs();

        add(tabbedPane, BorderLayout.CENTER);

        // Панель снизу с кнопкой назад
        JPanel bottomPanel = createBottomPanel();
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void createTabs() {
        // Вкладка MovablePoint
        JPanel pointTab = createPointTab();
        tabbedPane.addTab("📍 Point", pointTab);

        // Вкладка MovableCircle
        JPanel circleTab = createCircleTab();
        tabbedPane.addTab("🔵 Circle", circleTab);

        // Вкладка MovableRectangle
        JPanel rectangleTab = createRectangleTab();
        tabbedPane.addTab("🟦 Rectangle", rectangleTab);
    }

    private JPanel createPointTab() {
        JPanel panel = new JPanel(new BorderLayout());

        JTextArea textArea = new JTextArea(15, 50);
        textArea.setEditable(false);
        textArea.setFont(new Font("Consolas", Font.PLAIN, 12));
        textArea.setBackground(new Color(248, 249, 250));

        JButton demoButton = new JButton("Демонстрация MovablePoint");
        demoButton.setBackground(new Color(70, 130, 180));
        demoButton.setForeground(Color.WHITE);
        demoButton.addActionListener(e -> {
            StringBuilder output = new StringBuilder();
            output.append("=== Тестирование MovablePoint ===\n\n");

            MovablePoint a = new MovablePoint(0, 0, 2, 2);
            output.append("СТАРТ: " + a + "\n");
            a.moveUp();
            a.moveLeft();
            output.append("ФИНИШ (после moveUp и moveLeft): " + a + "\n");

            textArea.setText(output.toString());
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(demoButton);

        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createCircleTab() {
        JPanel panel = new JPanel(new BorderLayout());

        JTextArea textArea = new JTextArea(15, 50);
        textArea.setEditable(false);
        textArea.setFont(new Font("Consolas", Font.PLAIN, 12));
        textArea.setBackground(new Color(248, 249, 250));

        JButton demoButton = new JButton("Демонстрация MovableCircle");
        demoButton.setBackground(new Color(70, 130, 180));
        demoButton.setForeground(Color.WHITE);
        demoButton.addActionListener(e -> {
            StringBuilder output = new StringBuilder();
            output.append("=== Тестирование MovableCircle ===\n\n");

            MovableCircle b = new MovableCircle(0, 0, 10, 10, 5);
            output.append("СТАРТ: " + b + "\n\n");
            b.moveDown();
            b.moveRight();
            output.append("ФИНИШ (после moveDown и moveRight): " + b + "\n");

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

        JButton demoButton = new JButton("Демонстрация MovableRectangle");
        demoButton.setBackground(new Color(70, 130, 180));
        demoButton.setForeground(Color.WHITE);
        demoButton.addActionListener(e -> {
            StringBuilder output = new StringBuilder();
            output.append("=== Тестирование MovableRectangle ===\n\n");

            MovableRectangle r = new MovableRectangle(0, 0, 4, 4, 2, 2);
            output.append("СТАРТ: " + r + "\n\n");
            r.moveLeft();
            r.moveUp();
            output.append("ФИНИШ (после moveLeft и moveUp): " + r + "\n\n");

            output.append("=== Проверка на разные скорости ===\n");
            output.append("===(ставим xSpeed = 2 и ySpeed = 3) ===\n\n");
            MovableRectangle r1 = new MovableRectangle(0, 0, 1, 1, 2, 3);
            output.append("Прямоугольник с разными скоростями: " + r1 + "\n");
            output.append("Попытка moveUp(): ");
            r1.moveUp(); // Должно вывести сообщение об ошибке

            textArea.setText(output.toString());
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(demoButton);

        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private void demonstrateMovable() {
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

        JLabel infoLabel = new JLabel("Интерфейсы: MovablePoint, MovableCircle, MovableRectangle ← Movable");
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

    public static void task2(JFrame mainMenuFrame) {
        if (mainMenuFrame != null) {
            mainMenuFrame.setVisible(false);
        }

        SwingUtilities.invokeLater(() -> new Task2(mainMenuFrame).setVisible(true));
    }
}