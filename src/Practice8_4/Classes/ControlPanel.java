package Practice8_4.Classes;

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

    public ControlPanel(AnimationPanel animationPanel) {
        this.animationPanel = animationPanel;
        initializeComponents();
        setupLayout();
        setupEventListeners();
        updateStatus();
    }

    private void initializeComponents() {
        speedSlider = new JSlider(100, 1000, 100);
        speedSlider.setMajorTickSpacing(200);
        speedSlider.setMinorTickSpacing(100);
        speedSlider.setPaintTicks(true);
        speedSlider.setPaintLabels(true);

        startButton = new JButton("▶ Старт");
        stopButton = new JButton("⏹ Стоп");

        startButton.setBackground(new Color(100, 200, 100));
        stopButton.setBackground(new Color(200, 100, 100));

        statusLabel = new JLabel("Готов к запуску");
        statusLabel.setFont(new Font("Arial", Font.BOLD, 14));

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

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(new JLabel("Скорость анимации:"), gbc);

        gbc.gridy = 1;
        add(speedSlider, gbc);

        gbc.gridx = 2;
        gbc.gridwidth = 1;
        add(speedValueLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        add(startButton, gbc);

        gbc.gridx = 1;
        add(stopButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        add(statusLabel, gbc);
    }

    private void setupEventListeners() {
        speedSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int sliderValue = speedSlider.getValue();
                animationPanel.setDelay(sliderValue);
                speedValueLabel.setText(sliderValue + " мс");
                updateStatus();
            }
        });

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                animationPanel.startAnimation();
                updateStatus();
            }
        });

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                animationPanel.stopAnimation();
                updateStatus();
            }
        });
    }

    private void updateStatus() {
        String status = String.format(
                "Статус: %s | Кадр: %d/%d | Задержка: %d мс",
                animationPanel.isRunning() ? "Запущена" : "Остановлена",
                animationPanel.getCurrentFrameNumber(),
                animationPanel.getTotalFrames(),
                animationPanel.getDelay()
        );
        statusLabel.setText(status);

        if (animationPanel.isRunning()) {
            statusLabel.setForeground(new Color(0, 150, 0));
        } else {
            statusLabel.setForeground(Color.RED);
        }
    }
}