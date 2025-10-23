package Practice8_1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Task1 extends JFrame {

    private static JFrame mainMenu;
    private int milanScore = 0;
    private int madridScore = 0;
    private String lastScorer = "N/A";
    private String winner = "DRAW";

    private JLabel resultLabel;
    private JLabel lastScorerLabel;
    private JLabel winnerLabel;

    public Task1(JFrame mainMenu) {
        this.mainMenu = mainMenu;
        initializeFrame();
        setupComponents();
    }

    private void initializeFrame() {
        setTitle("Football Match: AC Milan vs Real Madrid");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                returnToMainMenu();
            }
        });
    }

    private void setupComponents(){
        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setBackground(new Color(240, 240, 240));
        topPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JButton backButton = createBackButton();
        topPanel.add(backButton);

        mainPanel.add(topPanel, BorderLayout.NORTH);

        JPanel gamePanel = new JPanel();
        gamePanel.setLayout(new GridLayout(5, 1, 10, 10));
        gamePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton milanButton = new JButton("AC Milan");
        JButton madridButton = new JButton("Real Madrid");

        resultLabel = new JLabel("Result: 0 X 0", JLabel.CENTER);
        lastScorerLabel = new JLabel("Last Scorer: N/A", JLabel.CENTER);
        winnerLabel = new JLabel("Winner: DRAW", JLabel.CENTER);

        Font labelFont = new Font("Arial", Font.BOLD, 14);
        resultLabel.setFont(labelFont);
        lastScorerLabel.setFont(labelFont);
        winnerLabel.setFont(labelFont);

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

        gamePanel.add(milanButton);
        gamePanel.add(madridButton);
        gamePanel.add(resultLabel);
        gamePanel.add(lastScorerLabel);
        gamePanel.add(winnerLabel);

        mainPanel.add(gamePanel, BorderLayout.CENTER);
        add(mainPanel);
    }

    private JButton createBackButton() {
        JButton backButton = new JButton("← Назад к меню");
        backButton.setFont(new Font("Arial", Font.PLAIN, 12));
        backButton.setBackground(new Color(200, 200, 200));
        backButton.setFocusPainted(false);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                returnToMainMenu();
            }
        });

        return backButton;
    }

    private void updateMatchData() {
        resultLabel.setText("Result: " + milanScore + " X " + madridScore);

        lastScorerLabel.setText("Last Scorer: " + lastScorer);

        if (milanScore > madridScore) {
            winner = "AC Milan";
        } else if (madridScore > milanScore) {
            winner = "Real Madrid";
        } else {
            winner = "DRAW";
        }

        winnerLabel.setText("Winner: " + winner);
    }

    private void returnToMainMenu() {
        // Закрываем текущее окно и показываем главное меню
        dispose();
        mainMenu.setVisible(true);
    }

    public static void start(JFrame mainMenu) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Task1(mainMenu).setVisible(true);
            }
        });
    }

    public static void task1() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Task1(null).setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        task1();
    }
}

