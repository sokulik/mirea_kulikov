package Practices.Practice4;

import DOP.BaseTaskFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Task1 extends BaseTaskFrame {
    private JTextArea resultArea;

    public enum Seasons {
        Winter,
        Spring,
        Summer,
        Autumn
    }

    public Task1(JFrame mainMenuFrame) {
        super(mainMenuFrame,"Practice 4.1: Перечисления - Времена года");

        initComponents();
        layoutComponents();
        addListeners();
        demonstrateSeasons();
    }

    private void initComponents() {
        resultArea = new JTextArea(15, 50);
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Consolas", Font.PLAIN, 12));
        resultArea.setBackground(new Color(248, 249, 250));
    }

    private void layoutComponents() {
        setLayout(new BorderLayout(10, 10));

        // Верхняя панель с выбором сезона
        JPanel controlPanel = createControlPanel();
        add(controlPanel, BorderLayout.NORTH);

        // Центральная панель с результатами
        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Информация о временах года"));
        add(scrollPane, BorderLayout.CENTER);

        // Панель снизу с кнопкой назад
        JPanel bottomPanel = createBottomPanel();
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private JPanel createControlPanel() {
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        controlPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        controlPanel.setBackground(new Color(240, 240, 240));

        JLabel instructionLabel = new JLabel("Выберите время года:");
        instructionLabel.setFont(new Font("Arial", Font.BOLD, 12));

        JComboBox<Seasons> seasonComboBox = new JComboBox<>(Seasons.values());
        seasonComboBox.setFont(new Font("Arial", Font.PLAIN, 12));

        JButton showInfoButton = new JButton("Показать информацию");
        showInfoButton.setBackground(new Color(70, 130, 180));
        showInfoButton.setForeground(Color.WHITE);
        showInfoButton.addActionListener(e -> {
            Seasons selectedSeason = (Seasons) seasonComboBox.getSelectedItem();
            showSeasonInfo(selectedSeason);
        });

        JButton showAllButton = new JButton("Показать все сезоны");
        showAllButton.setBackground(new Color(40, 167, 69));
        showAllButton.setForeground(Color.WHITE);
        showAllButton.addActionListener(e -> demonstrateSeasons());

        controlPanel.add(instructionLabel);
        controlPanel.add(seasonComboBox);
        controlPanel.add(showInfoButton);
        controlPanel.add(showAllButton);

        return controlPanel;
    }

    private String iLove(Seasons season) {
        switch (season) {
            case Winter:
                return "Я не люблю Зиму";
            case Spring:
                return "Я не очень люблю Весну";
            case Summer:
                return "Я очень люблю лето!";
            case Autumn:
                return "Я люблю Осень";
            default:
                return "";
        }
    }

    public static int getAverageTemp(Seasons seasons) {
        switch (seasons) {
            case Winter:
                return -20;
            case Spring:
                return 5;
            case Summer:
                return 20;
            case Autumn:
                return 10;
            default:
                return 0;
        }
    }

    public static String getDescription(Seasons season) {
        if (season == Seasons.Summer) {
            return "Теплое время года";
        } else if (season == Seasons.Autumn) {
            return "Прохладное время года";
        } else if (season == Seasons.Spring) {
            return "Прохладное время года";
        } else {
            return "Холодное время года";
        }
    }

    private void showSeasonInfo(Seasons season) {
        StringBuilder output = new StringBuilder();
        output.append("=== Информация о ").append(season).append(" ===\n\n");
        output.append(iLove(season)).append("\n");
        output.append("Средняя температура: ").append(getAverageTemp(season)).append("°C\n");
        output.append("Описание: ").append(getDescription(season)).append("\n");
        output.append("─".repeat(50)).append("\n");

        resultArea.setText(output.toString());
    }

    private void demonstrateSeasons() {
        StringBuilder output = new StringBuilder();
        output.append("=== Все времена года ===\n\n");

        Seasons favorite = Seasons.Summer;
        output.append("❤️  Любимый сезон: ").append(favorite).append("\n");
        output.append(iLove(favorite)).append("\n");
        output.append("Средняя температура: ").append(getAverageTemp(favorite)).append("°C\n");
        output.append("Описание: ").append(getDescription(favorite)).append("\n\n");
        output.append("─".repeat(50)).append("\n\n");

        for (Seasons season : Seasons.values()) {
            output.append("🌿 ").append(season).append(":\n");
            output.append(iLove(season)).append("\n");
            output.append("Средняя температура: ").append(getAverageTemp(season)).append("°C\n");
            output.append("Описание: ").append(getDescription(season)).append("\n\n");
        }

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

        JLabel infoLabel = new JLabel("Перечисления: Winter, Spring, Summer, Autumn");
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

    public static void task1(JFrame mainMenuFrame) {
        if (mainMenuFrame != null) {
            mainMenuFrame.setVisible(false);
        }

        SwingUtilities.invokeLater(() -> new Task1(mainMenuFrame).setVisible(true));
    }
}