package Practices.Practice8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Task1 extends JFrame {
    private int milanScore = 0;
    private int madridScore = 0;
    private String lastScorer = "N/A";
    private String winner = "DRAW";

    private JLabel resultLabel;
    private JLabel lastScorerLabel;
    private JLabel winnerLabel;
    private JFrame mainMenuFrame;

    public Task1(JFrame mainMenuFrame) {
        super("Football Match: AC Milan vs Real Madrid");
        this.mainMenuFrame = mainMenuFrame;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);

        initComponents();
        layoutComponents();
        addListeners();
    }

    private void initComponents() {
        resultLabel = new JLabel("Result: 0 X 0", JLabel.CENTER);
        lastScorerLabel = new JLabel("Last Scorer: N/A", JLabel.CENTER);
        winnerLabel = new JLabel("Winner: DRAW", JLabel.CENTER);

        Font labelFont = new Font("Arial", Font.BOLD, 16);
        resultLabel.setFont(labelFont);
        lastScorerLabel.setFont(labelFont);
        winnerLabel.setFont(labelFont);
    }

    private void layoutComponents() {
        setLayout(new BorderLayout(10, 10));

        // Панель игры по центру
        JPanel gamePanel = createGamePanel();
        add(gamePanel, BorderLayout.CENTER);

        // Панель снизу с кнопкой назад
        JPanel bottomPanel = createBottomPanel();
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private JPanel createGamePanel() {
        JPanel gamePanel = new JPanel(new GridLayout(5, 1, 15, 15));
        gamePanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        gamePanel.setBackground(new Color(240, 240, 240));

        // Заголовок
        JLabel titleLabel = new JLabel("⚽ Football Match", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(new Color(70, 130, 180));
        gamePanel.add(titleLabel);

        // Кнопки команд
        JButton milanButton = createTeamButton("AC Milan", new Color(200, 0, 0));
        JButton madridButton = createTeamButton("Real Madrid", new Color(0, 0, 200));

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        buttonPanel.setBackground(new Color(240, 240, 240));
        buttonPanel.add(milanButton);
        buttonPanel.add(madridButton);

        gamePanel.add(buttonPanel);
        gamePanel.add(resultLabel);
        gamePanel.add(lastScorerLabel);
        gamePanel.add(winnerLabel);

        // Добавляем слушатели к кнопкам
        milanButton.addActionListener(e -> {
            milanScore++;
            lastScorer = "AC Milan";
            updateMatchData();
        });

        madridButton.addActionListener(e -> {
            madridScore++;
            lastScorer = "Real Madrid";
            updateMatchData();
        });

        return gamePanel;
    }

    private JButton createTeamButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(color.darker(), 2),
                BorderFactory.createEmptyBorder(10, 15, 10, 15)
        ));

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

    private JPanel createBottomPanel() {
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(new Color(240, 240, 240));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Кнопка назад слева
        JButton backButton = createBackButton();
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftPanel.setBackground(new Color(240, 240, 240));
        leftPanel.add(backButton);

        // Информация справа
        JLabel infoLabel = new JLabel("Нажимайте на кнопки команд для забития голов");
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

        // Добавляем эффект при наведении
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
        System.out.println("Возврат в главное меню из Task1...");

        // Закрываем это окно
        dispose();

        // Показываем главное меню
        if (mainMenuFrame != null) {
            System.out.println("Показываем главное меню");
            mainMenuFrame.setVisible(true);
            mainMenuFrame.toFront();
        } else {
            System.out.println("mainMenuFrame is null!");
        }
    }

    private void updateMatchData() {
        resultLabel.setText("Result: " + milanScore + " X " + madridScore);
        lastScorerLabel.setText("Last Scorer: " + lastScorer);

        if (milanScore > madridScore) {
            winner = "AC Milan";
            winnerLabel.setForeground(new Color(200, 0, 0)); // Красный для Милана
        } else if (madridScore > milanScore) {
            winner = "Real Madrid";
            winnerLabel.setForeground(new Color(0, 0, 200)); // Синий для Мадрида
        } else {
            winner = "DRAW";
            winnerLabel.setForeground(Color.BLACK); // Черный для ничьи
        }
        winnerLabel.setText("Winner: " + winner);
    }

    public static void task1(JFrame mainMenuFrame) {
        // Скрываем главное меню перед показом окна с игрой
        if (mainMenuFrame != null) {
            mainMenuFrame.setVisible(false);
        }

        SwingUtilities.invokeLater(() -> {
            Task1 task1 = new Task1(mainMenuFrame);
            task1.setVisible(true);
        });
    }
}