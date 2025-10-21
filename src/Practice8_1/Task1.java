package Practice8_1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Task1 extends JFrame {
    private int milanScore = 0;
    private int madridScore = 0;
    private String lastScorer = "N/A";
    private String winner = "DRAW";

    private JLabel resultLabel;
    private JLabel lastScorerLabel;
    private JLabel winnerLabel;

    public Task1() {
        setTitle("Football Match: AC Milan vs Real Madrid");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Создание кнопок
        JButton milanButton = new JButton("AC Milan");
        JButton madridButton = new JButton("Real Madrid");

        // Создание меток
        resultLabel = new JLabel("Result: 0 X 0", JLabel.CENTER);
        lastScorerLabel = new JLabel("Last Scorer: N/A", JLabel.CENTER);
        winnerLabel = new JLabel("Winner: DRAW", JLabel.CENTER);

        // Установка шрифтов для лучшей читаемости
        Font labelFont = new Font("Arial", Font.BOLD, 14);
        resultLabel.setFont(labelFont);
        lastScorerLabel.setFont(labelFont);
        winnerLabel.setFont(labelFont);

        // Добавление обработчиков событий для кнопок
        milanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                milanScore++;
                lastScorer = "AC Milan";
                updateMatchData();
            }
        });

        madridButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                madridScore++;
                lastScorer = "Real Madrid";
                updateMatchData();
            }
        });

        // Добавление компонентов на панель
        panel.add(milanButton);
        panel.add(madridButton);
        panel.add(resultLabel);
        panel.add(lastScorerLabel);
        panel.add(winnerLabel);

        // Добавление панели на фрейм
        add(panel);
    }

    private void updateMatchData() {
        // Обновление счета
        resultLabel.setText("Result: " + milanScore + " X " + madridScore);

        // Обновление последнего забившего
        lastScorerLabel.setText("Last Scorer: " + lastScorer);

        // Определение победителя
        if (milanScore > madridScore) {
            winner = "AC Milan";
        } else if (madridScore > milanScore) {
            winner = "Real Madrid";
        } else {
            winner = "DRAW";
        }

        // Обновление информации о победителе
        winnerLabel.setText("Winner: " + winner);
    }

    public static void task1() {
        // Запуск приложения в потоке обработки событий
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Task1().setVisible(true);
            }
        });
    }
    public static void main(String[] args) {
        task1();
}
}

