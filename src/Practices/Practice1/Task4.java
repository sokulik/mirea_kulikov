package Practices.Practice1;

import DOP.BaseTaskFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Task4 extends BaseTaskFrame {
    private JFrame mainMenuFrame;
    private JTextArea resultArea;

    public Task4(JFrame mainMenuFrame) {
        super(mainMenuFrame,"Задание 4: Гармонический ряд");

        initComponents();
        layoutComponents();
        addListeners();
        calculateHarmonicSeries();
    }

    private void initComponents() {
        resultArea = new JTextArea(15, 40);
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Consolas", Font.PLAIN, 12));
        resultArea.setBackground(new Color(248, 249, 250));
    }

    private void layoutComponents() {
        setLayout(new BorderLayout(10, 10));

        // Верхняя панель с информацией
        JPanel infoPanel = createInfoPanel();
        add(infoPanel, BorderLayout.NORTH);

        // Центральная панель с результатами
        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Первые 10 чисел гармонического ряда"));
        add(scrollPane, BorderLayout.CENTER);

        // Панель снизу с кнопкой назад
        JPanel bottomPanel = createBottomPanel();
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private JPanel createInfoPanel() {
        JPanel infoPanel = new JPanel(new BorderLayout());
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        infoPanel.setBackground(new Color(240, 240, 240));

        JLabel titleLabel = new JLabel("📐 Гармонический ряд", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setForeground(new Color(70, 130, 180));

        JLabel formulaLabel = new JLabel("Формула: 1/n, где n = 1, 2, 3, ...", JLabel.CENTER);
        formulaLabel.setFont(new Font("Arial", Font.PLAIN, 12));

        infoPanel.add(titleLabel, BorderLayout.CENTER);
        infoPanel.add(formulaLabel, BorderLayout.SOUTH);

        return infoPanel;
    }

    private void calculateHarmonicSeries() {
        StringBuilder output = new StringBuilder();
        output.append("Первые 10 чисел гармонического ряда:\n");
        output.append("═".repeat(50) + "\n\n");

        for (int i = 1; i <= 10; i++) {
            double chislo = 1.0 / i;
            output.append(String.format("1/%d = %10.7f\n", i, chislo));
        }

        output.append("\n" + "═".repeat(50) + "\n");
        output.append("Дополнительно: первые 100 чисел (первые 10 показаны выше)\n");

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

        JLabel infoLabel = new JLabel("Гармонический ряд расходится");
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

    public static void task4(JFrame mainMenuFrame) {
        if (mainMenuFrame != null) {
            mainMenuFrame.setVisible(false);
        }

        SwingUtilities.invokeLater(() -> new Task4(mainMenuFrame).setVisible(true));
    }
}