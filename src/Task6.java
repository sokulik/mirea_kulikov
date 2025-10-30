import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Task6 extends JFrame{
    private StopWatch stopWatch;
    private JLabel timeLabel;
    private JButton startButton;
    private JButton endButton;
    private JButton resetButton;
    private Timer timer;

    public Task6(){
        stopWatch = new StopWatch();
        initializeGUI();
    }

    private void initializeGUI(){
        setTitle("Секундомер");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);

        timeLabel = new JLabel("00:00:00:000", SwingConstants.CENTER);
        timeLabel.setFont(new Font("Arial", Font.BOLD, 48));
        timeLabel.setForeground(Color.BLUE);
        timeLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(timeLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());

        startButton = new JButton("Старт");
        endButton = new JButton("Стоп");
        resetButton = new JButton("Сброс");

        startButton.setFont(new Font("Arial", Font.BOLD, 16));
        endButton.setFont(new Font("Arial", Font.BOLD, 16));
        resetButton.setFont(new Font("Arial", Font.BOLD, 16));

        startButton.setBackground(Color.GREEN);
        endButton.setBackground(Color.RED);
        resetButton.setBackground(Color.YELLOW);

        buttonPanel.add(startButton);
        buttonPanel.add(endButton);
        buttonPanel.add(resetButton);

        add(buttonPanel, BorderLayout.SOUTH);

        timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTimeDisplay();
            }
        });

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopWatch.start();
                timer.start();
                updateButtons(true);
            }
        });

        endButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopWatch.end();
                timer.stop();
                updateButtons(false);
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopWatch.start(); // Сброс путем перезапуска
                stopWatch.end(); // Останавливаем для сброса
                timer.stop();
                updateTimeDisplay();
                updateButtons(false);
            }
        });

        updateButtons(false);

        pack();
        setLocationRelativeTo(null);
    }

    private void updateTimeDisplay() {
        long elapsedTime;
        if (timer.isRunning()) {
            elapsedTime = System.currentTimeMillis() - stopWatch.getStartTime();
        } else {
            elapsedTime = stopWatch.getElapsedTime();
        }

        timeLabel.setText(formatTime(elapsedTime));
    }

    private String formatTime(long milliseconds) {
        long hours = milliseconds / 3600000;
        long minutes = (milliseconds % 3600000) / 60000;
        long seconds = (milliseconds % 60000) / 1000;
        long ms = milliseconds % 1000;

        return String.format("%02d:%02d:%02d.%03d", hours, minutes, seconds, ms);
    }

    private void updateButtons(boolean running) {
        startButton.setEnabled(!running);
        endButton.setEnabled(running);
        resetButton.setEnabled(!running);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getLookAndFeel());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Запуск приложения в Event Dispatch Thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Task6().setVisible(true);
            }
        });
    }




    }
