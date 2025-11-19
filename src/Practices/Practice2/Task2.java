package Practices.Practice2;

import DOP.BaseTaskFrame;
import Practices.Practice2.Classes.Ball;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Task2 extends BaseTaskFrame {
    private JTextArea resultArea;
    private JTextField xField, yField, moveXField, moveYField;
    private Ball ball;

    public Task2(JFrame mainMenuFrame) {
        super(mainMenuFrame,"Practice 2: Работа с мячом");

        initComponents();
        layoutComponents();
        addListeners();
        demonstrateBall();
    }

    private void initComponents() {
        resultArea = new JTextArea(15, 40);
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Consolas", Font.PLAIN, 12));
        resultArea.setBackground(new Color(248, 249, 250));

        xField = new JTextField(5);
        yField = new JTextField(5);
        moveXField = new JTextField(5);
        moveYField = new JTextField(5);

        xField.setFont(new Font("Arial", Font.PLAIN, 12));
        yField.setFont(new Font("Arial", Font.PLAIN, 12));
        moveXField.setFont(new Font("Arial", Font.PLAIN, 12));
        moveYField.setFont(new Font("Arial", Font.PLAIN, 12));

        // Создаем начальный мяч
        ball = new Ball(100, 100);
    }

    private void layoutComponents() {
        setLayout(new BorderLayout(10, 10));

        // Верхняя панель с управлением
        JPanel controlPanel = createControlPanel();
        add(controlPanel, BorderLayout.NORTH);

        // Центральная панель с результатами
        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Действия с мячом"));
        add(scrollPane, BorderLayout.CENTER);

        // Панель снизу с кнопкой назад
        JPanel bottomPanel = createBottomPanel();
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private JPanel createControlPanel() {
        JPanel controlPanel = new JPanel(new GridLayout(3, 4, 5, 5));
        controlPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        controlPanel.setBackground(new Color(240, 240, 240));

        // Установка позиции
        controlPanel.add(new JLabel("Позиция X:"));
        controlPanel.add(xField);
        controlPanel.add(new JLabel("Позиция Y:"));
        controlPanel.add(yField);

        JButton setPosButton = new JButton("Установить позицию");
        setPosButton.setBackground(new Color(70, 130, 180));
        setPosButton.setForeground(Color.WHITE);
        setPosButton.addActionListener(e -> setPosition());

        controlPanel.add(setPosButton);
        controlPanel.add(new JLabel(""));

        // Перемещение
        controlPanel.add(new JLabel("Смещение X:"));
        controlPanel.add(moveXField);
        controlPanel.add(new JLabel("Смещение Y:"));
        controlPanel.add(moveYField);

        JButton moveButton = new JButton("Переместить");
        moveButton.setBackground(new Color(40, 167, 69));
        moveButton.setForeground(Color.WHITE);
        moveButton.addActionListener(e -> moveBall());

        controlPanel.add(moveButton);
        controlPanel.add(new JLabel(""));

        return controlPanel;
    }

    private void setPosition() {
        try {
            double x = Double.parseDouble(xField.getText().trim());
            double y = Double.parseDouble(yField.getText().trim());

            ball.setXY(x, y);

            resultArea.append("🎯 Установлена новая позиция: " + ball.toString() + "\n");
            resultArea.append("─".repeat(50) + "\n");

            xField.setText("");
            yField.setText("");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Введите корректные числовые значения", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void moveBall() {
        try {
            double xDisp = Double.parseDouble(moveXField.getText().trim());
            double yDisp = Double.parseDouble(moveYField.getText().trim());

            resultArea.append("🔄 Перемещение: (" + xDisp + ", " + yDisp + ")\n");
            resultArea.append("До: " + ball.toString() + "\n");

            ball.move(xDisp, yDisp);

            resultArea.append("После: " + ball.toString() + "\n");
            resultArea.append("─".repeat(50) + "\n");

            moveXField.setText("");
            moveYField.setText("");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Введите корректные числовые значения", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void demonstrateBall() {
        StringBuilder output = new StringBuilder();
        output.append("🏀 Демонстрация работы с мячом\n");
        output.append("═".repeat(50) + "\n\n");

        // Демонстрация как в оригинальном TestBall
        Ball bl = new Ball(100, 100);
        output.append("Создан мяч: " + bl.toString() + "\n");

        bl.move(30, 15);
        output.append("После move(30, 15): " + bl.toString() + "\n");

        bl.move(-180, 95);
        output.append("После move(-180, 95): " + bl.toString() + "\n\n");

        output.append("💡 Используйте панель управления для работы с мячом!\n");

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

        JLabel infoLabel = new JLabel("Класс Ball: координаты x, y, перемещение");
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
