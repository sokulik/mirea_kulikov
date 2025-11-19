package Practices.Practice4;

import DOP.BaseTaskFrame;
import Practices.Practice4.Classes.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Task2 extends BaseTaskFrame {
    private JTextArea resultArea;

    public Task2(JFrame mainMenuFrame) {
        super(mainMenuFrame,"Practice 4.2: Ателье одежды");

        initComponents();
        layoutComponents();
        addListeners();
        demonstrateAtelier();
    }

    private void initComponents() {
        resultArea = new JTextArea(20, 60);
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Consolas", Font.PLAIN, 12));
        resultArea.setBackground(new Color(248, 249, 250));
    }

    private void layoutComponents() {
        setLayout(new BorderLayout(10, 10));

        // Верхняя панель с кнопками
        JPanel controlPanel = createControlPanel();
        add(controlPanel, BorderLayout.NORTH);

        // Центральная панель с результатами
        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Одежда в ателье"));
        add(scrollPane, BorderLayout.CENTER);

        // Панель снизу с кнопкой назад
        JPanel bottomPanel = createBottomPanel();
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private JPanel createControlPanel() {
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        controlPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        controlPanel.setBackground(new Color(240, 240, 240));

        JButton womenButton = new JButton("👩 Женская одежда");
        womenButton.setBackground(new Color(255, 182, 193));
        womenButton.setForeground(Color.BLACK);
        womenButton.addActionListener(e -> showWomenClothing());

        JButton menButton = new JButton("👨 Мужская одежда");
        menButton.setBackground(new Color(173, 216, 230));
        menButton.setForeground(Color.BLACK);
        menButton.addActionListener(e -> showMenClothing());

        JButton allButton = new JButton("👕 Вся одежда");
        allButton.setBackground(new Color(70, 130, 180));
        menButton.setForeground(Color.WHITE);
        allButton.addActionListener(e -> demonstrateAtelier());

        controlPanel.add(womenButton);
        controlPanel.add(menButton);
        controlPanel.add(allButton);

        return controlPanel;
    }

    private void demonstrateAtelier() {
        Clothes[] clothes = createClothesArray();
        StringBuilder output = new StringBuilder();
        output.append("=== Демонстрация работы ателье ===\n\n");

        output.append("👕 Созданный массив одежды:\n");
        for (Clothes item : clothes) {
            output.append("• ").append(item.getClass().getSimpleName())
                    .append(" | Размер: ").append(item.getSize())
                    .append(" | Цвет: ").append(item.getColor())
                    .append(" | Цена: ").append(item.getPrice()).append(" руб.\n");
        }
        output.append("\n");

        Atelier atelier = new Atelier();

        output.append("=== Женская одежда ===\n");
        atelier.dressWoman(clothes);

        output.append("\n=== Мужская одежда ===\n");
        atelier.dressMan(clothes);

        resultArea.setText(output.toString());
    }

    private void showWomenClothing() {
        Clothes[] clothes = createClothesArray();
        StringBuilder output = new StringBuilder();
        output.append("=== Женская одежда ===\n\n");

        Atelier atelier = new Atelier();
        atelier.dressWoman(clothes);

        resultArea.setText(output.toString());
    }

    private void showMenClothing() {
        Clothes[] clothes = createClothesArray();
        StringBuilder output = new StringBuilder();
        output.append("=== Мужская одежда ===\n\n");

        Atelier atelier = new Atelier();
        atelier.dressMan(clothes);

        resultArea.setText(output.toString());
    }

    private Clothes[] createClothesArray() {
        return new Clothes[]{
                new Clothes.TShirt(Size.S, 1500, "blue"),
                new Clothes.TShirt(Size.XXS, 1000, "black"),
                new Clothes.TShirt(Size.L, 2000, "white"),
                new Clothes.Pants(Size.M, 3000, "black"),
                new Clothes.Pants(Size.L, 3500, "black"),
                new Clothes.Pants(Size.M, 3000, "darkBlue"),
                new Clothes.Skirt(Size.S, 2200, "pink"),
                new Clothes.Skirt(Size.XXS, 2500, "red"),
                new Clothes.Tie(Size.L, 1500, "yellow"),
                new Clothes.Tie(Size.M, 6000, "black")
        };
    }

    private JPanel createBottomPanel() {
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(new Color(240, 240, 240));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton backButton = createBackButton();
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftPanel.setBackground(new Color(240, 240, 240));
        leftPanel.add(backButton);

        JLabel infoLabel = new JLabel("Интерфейсы: MenClothing, WomenClothing | Перечисления: Size");
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

    public static void task2(JFrame mainMenuFrame) {
        if (mainMenuFrame != null) {
            mainMenuFrame.setVisible(false);
        }

        SwingUtilities.invokeLater(() -> new Task2(mainMenuFrame).setVisible(true));
    }
}