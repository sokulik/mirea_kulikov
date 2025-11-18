package Practices.Practice8.Classes.Task4;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlPanel extends JPanel {
    private AnimationPanel animationPanel;
    private JSlider speedSlider;
    private JButton startButton;
    private JButton stopButton;
    private JLabel statusLabel;
    private JLabel speedValueLabel;
    private Timer statusUpdateTimer;

    public ControlPanel(AnimationPanel animationPanel) {
        this.animationPanel = animationPanel;
        initializeComponents();
        setupLayout();
        setupEventListeners();
        startStatusUpdateTimer();
        updateStatus();
    }

    private void initializeComponents() {
        speedSlider = new JSlider(50, 500, 100);
        speedSlider.setMajorTickSpacing(100);
        speedSlider.setMinorTickSpacing(50);
        speedSlider.setPaintTicks(true);
        speedSlider.setPaintLabels(true);
        speedSlider.setSnapToTicks(true);

        startButton = new JButton("▶ Старт");
        stopButton = new JButton("⏹ Стоп");

        startButton.setBackground(new Color(100, 200, 100));
        startButton.setForeground(Color.WHITE);
        stopButton.setBackground(new Color(200, 100, 100));
        stopButton.setForeground(Color.WHITE);

        startButton.setFont(new Font("Arial", Font.BOLD, 14));
        stopButton.setFont(new Font("Arial", Font.BOLD, 14));

        statusLabel = new JLabel("Готов к запуску");
        statusLabel.setFont(new Font("Arial", Font.BOLD, 12));

        speedValueLabel = new JLabel("100 мс");
        speedValueLabel.setFont(new Font("Arial", Font.BOLD, 12));
        speedValueLabel.setForeground(Color.BLUE);
    }

    private void setupLayout() {
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Управление анимацией"),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        setBackground(new Color(245, 245, 245));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Метка скорости
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        JLabel speedLabel = new JLabel("Скорость анимации (мс):");
        speedLabel.setFont(new Font("Arial", Font.BOLD, 12));
        add(speedLabel, gbc);

        // Слайдер скорости
        gbc.gridy = 1;
        add(speedSlider, gbc);

        // Значение скорости
        gbc.gridx = 2;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        add(speedValueLabel, gbc);

        // Кнопки управления
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        add(startButton, gbc);

        gbc.gridx = 1;
        add(stopButton, gbc);

        // Статус
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(statusLabel, gbc);
    }

    private void setupEventListeners() {
        speedSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int sliderValue = speedSlider.getValue();
                animationPanel.setDelay(sliderValue);
                speedValueLabel.setText(sliderValue + " мс");
            }
        });

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                animationPanel.startAnimation();
                updateButtonStates();
            }
        });

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                animationPanel.stopAnimation();
                updateButtonStates();
            }
        });
    }

    private void startStatusUpdateTimer() {
        statusUpdateTimer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateStatus();
            }
        });
        statusUpdateTimer.start();
    }

    private void updateButtonStates() {
        boolean running = animationPanel.isRunning();
        startButton.setEnabled(!running);
        stopButton.setEnabled(running);

        if (running) {
            startButton.setBackground(new Color(150, 150, 150));
            stopButton.setBackground(new Color(200, 100, 100));
        } else {
            startButton.setBackground(new Color(100, 200, 100));
            stopButton.setBackground(new Color(150, 150, 150));
        }
    }

    private void updateStatus() {
        String status = String.format(
                "Статус: %s | Кадр: %d/%d | Задержка: %d мс | FPS: %d",
                animationPanel.isRunning() ? "▶ Запущена" : "⏹ Остановлена",
                animationPanel.getCurrentFrameNumber(),
                animationPanel.getTotalFrames(),
                animationPanel.getDelay(),
                animationPanel.getFps()
        );
        statusLabel.setText(status);

        if (animationPanel.isRunning()) {
            statusLabel.setForeground(new Color(0, 150, 0));
        } else {
            statusLabel.setForeground(Color.RED);
        }

        updateButtonStates();
    }
}