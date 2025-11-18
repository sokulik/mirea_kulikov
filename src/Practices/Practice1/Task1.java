package Practices.Practice1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;

public class Task1 extends JFrame {
    private JFrame mainMenuFrame;

    public Task1(JFrame mainMenuFrame) {
        super("Задание 1: Сумма и среднее арифметическое");
        this.mainMenuFrame = mainMenuFrame;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);

        initComponents();
        layoutComponents();
        addListeners();
    }

    private void initComponents() {
        // Компоненты будут созданы в layoutComponents
    }

    private void layoutComponents() {
        setLayout(new BorderLayout(10, 10));

        // Основная панель с результатами
        JPanel resultPanel = createResultPanel();
        add(resultPanel, BorderLayout.CENTER);

        // Панель снизу с кнопкой назад
        JPanel bottomPanel = createBottomPanel();
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private JPanel createResultPanel() {
        JPanel resultPanel = new JPanel(new GridLayout(5, 1, 10, 10));
        resultPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        resultPanel.setBackground(new Color(240, 240, 240));

        int[] massiv = {5, 1, 9, 13, 4, 25, 37};
        int summa = 0;

        for (int i = 0; i < massiv.length; i++) {
            summa += massiv[i]; // Исправлено: было =, нужно +=
        }
        double avg = (double) summa / massiv.length;

        JLabel titleLabel = new JLabel("📊 Сумма и среднее арифметическое", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(new Color(70, 130, 180));

        JLabel arrayLabel = new JLabel("Массив: " + Arrays.toString(massiv));
        arrayLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel sumLabel = new JLabel("Сумма: " + summa);
        sumLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel avgLabel = new JLabel("Среднее арифметическое: " + String.format("%.2f", avg));
        avgLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel infoLabel = new JLabel("Размер массива: " + massiv.length + " элементов");
        infoLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        infoLabel.setForeground(Color.DARK_GRAY);

        resultPanel.add(titleLabel);
        resultPanel.add(arrayLabel);
        resultPanel.add(sumLabel);
        resultPanel.add(avgLabel);
        resultPanel.add(infoLabel);

        return resultPanel;
    }

    private JPanel createBottomPanel() {
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(new Color(240, 240, 240));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton backButton = createBackButton();
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftPanel.setBackground(new Color(240, 240, 240));
        leftPanel.add(backButton);

        bottomPanel.add(leftPanel, BorderLayout.WEST);
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

    public static void task1(JFrame mainMenuFrame) {
        if (mainMenuFrame != null) {
            mainMenuFrame.setVisible(false);
        }

        SwingUtilities.invokeLater(() -> new Task1(mainMenuFrame).setVisible(true));
    }
}