package Practices.Practice9;

import Practices.Practice9.Classes.StopWatch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Task6 extends JFrame {
    private StopWatch stopWatch;
    private JLabel timeLabel;
    private JButton startButton;
    private JButton stopButton;
    private JButton resetButton;
    private JButton lapButton;
    private Timer timer;
    private JTextArea lapTimesArea;
    private int lapCounter = 1;
    private JFrame mainMenuFrame;

    public Task6(JFrame mainMenuFrame) {
        super("Секундомер Professional");
        this.mainMenuFrame = mainMenuFrame;
        stopWatch = new StopWatch();
        initializeGUI();
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                returnToMainMenu();
            }
        });
    }

    private void initializeGUI() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        setResizable(true);
        getContentPane().setBackground(new Color(240, 240, 240));

        // Создаем компоненты
        createTimeDisplay();
        createControlPanel();
        createLapTimesPanel();

        // Настраиваем таймер для обновления времени
        timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTimeDisplay();
            }
        });

        setupEventListeners();
        updateButtons();

        pack();
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(500, 600));
    }

    private void createTimeDisplay() {
        JPanel timePanel = new JPanel(new BorderLayout());
        timePanel.setBackground(new Color(30, 30, 40));
        timePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        timeLabel = new JLabel("00:00:00.000", SwingConstants.CENTER);
        timeLabel.setFont(new Font("Consolas", Font.BOLD, 48));
        timeLabel.setForeground(Color.GREEN);
        timeLabel.setBackground(Color.BLACK);
        timeLabel.setOpaque(true);
        timeLabel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));

        timePanel.add(timeLabel, BorderLayout.CENTER);
        add(timePanel, BorderLayout.NORTH);
    }

    private void createControlPanel() {
        JPanel controlPanel = new JPanel(new GridLayout(1, 4, 10, 10));
        controlPanel.setBackground(new Color(240, 240, 240));
        controlPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        startButton = createStyledButton("▶ Старт", new Color(40, 167, 69));
        stopButton = createStyledButton("⏹ Стоп", new Color(220, 53, 69));
        resetButton = createStyledButton("↺ Сброс", new Color(255, 193, 7));
        lapButton = createStyledButton("⏱ Круг", new Color(23, 162, 184));

        // Добавляем кнопку назад
        JButton backButton = createStyledButton("← Меню", new Color(108, 117, 125));
        backButton.addActionListener(e -> returnToMainMenu());

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(controlPanel, BorderLayout.CENTER);

        JPanel backPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        backPanel.setBackground(new Color(240, 240, 240));
        backPanel.add(backButton);

        mainPanel.add(backPanel, BorderLayout.NORTH);

        controlPanel.add(startButton);
        controlPanel.add(stopButton);
        controlPanel.add(resetButton);
        controlPanel.add(lapButton);

        add(mainPanel, BorderLayout.CENTER);
    }

    private void createLapTimesPanel() {
        JPanel lapPanel = new JPanel(new BorderLayout());
        lapPanel.setBorder(BorderFactory.createTitledBorder("Время кругов"));
        lapPanel.setBackground(Color.WHITE);

        lapTimesArea = new JTextArea(10, 30);
        lapTimesArea.setFont(new Font("Consolas", Font.PLAIN, 12));
        lapTimesArea.setEditable(false);
        lapTimesArea.setBackground(new Color(248, 249, 250));
        lapTimesArea.setText("Круги:\n" + "=".repeat(40) + "\n");

        JScrollPane scrollPane = new JScrollPane(lapTimesArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        lapPanel.add(scrollPane, BorderLayout.CENTER);
        add(lapPanel, BorderLayout.SOUTH);
    }

    private JButton createStyledButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(color.darker(), 2),
                BorderFactory.createEmptyBorder(8, 15, 8, 15)
        ));

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

    private void setupEventListeners() {
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopWatch.start();
                timer.start();
                updateButtons();
            }
        });

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopWatch.stop();
                timer.stop();
                updateButtons();
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopWatch.reset();
                timer.stop();
                lapCounter = 1;
                lapTimesArea.setText("Круги:\n" + "=".repeat(40) + "\n");
                updateTimeDisplay();
                updateButtons();
            }
        });

        lapButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (stopWatch.isRunning()) {
                    addLapTime();
                }
            }
        });
    }

    private void updateTimeDisplay() {
        long elapsedTime = stopWatch.getElapsedTime();
        timeLabel.setText(formatTime(elapsedTime));
    }

    private String formatTime(long milliseconds) {
        long hours = milliseconds / 3600000;
        long minutes = (milliseconds % 3600000) / 60000;
        long seconds = (milliseconds % 60000) / 1000;
        long ms = milliseconds % 1000;

        return String.format("%02d:%02d:%02d.%03d", hours, minutes, seconds, ms);
    }

    private void updateButtons() {
        boolean running = stopWatch.isRunning();

        startButton.setEnabled(!running);
        stopButton.setEnabled(running);
        resetButton.setEnabled(!running && stopWatch.getElapsedTime() > 0);
        lapButton.setEnabled(running);

        if (running) {
            timeLabel.setForeground(Color.GREEN);
        } else if (stopWatch.getElapsedTime() > 0) {
            timeLabel.setForeground(Color.ORANGE);
        } else {
            timeLabel.setForeground(Color.GRAY);
        }
    }

    private void addLapTime() {
        long lapTime = stopWatch.getElapsedTime();
        String lapText = String.format("Круг %2d: %s",
                lapCounter++, formatTime(lapTime));

        lapTimesArea.append(lapText + "\n");
        lapTimesArea.setCaretPosition(lapTimesArea.getDocument().getLength());
    }

    private void returnToMainMenu() {
        if (timer != null) {
            timer.stop();
        }
        dispose();
        if (mainMenuFrame != null) {
            mainMenuFrame.setVisible(true);
        }
    }

    public static void task6(JFrame mainMenuFrame) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Task6(mainMenuFrame).setVisible(true);
            }
        });
    }
}