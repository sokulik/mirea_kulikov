package Practices.Practice8;

import Practices.Practice8.Classes.Task3.Components.ImageDisplay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Task3 extends JFrame {
    private ImageDisplay imageDisplay;
    private JFrame mainMenuFrame;
    private String imagePath;

    public Task3(JFrame mainMenuFrame, String imagePath) {
        super("Задание 3: Просмотр изображений");
        this.mainMenuFrame = mainMenuFrame;
        this.imagePath = imagePath;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        initComponents();
        layoutComponents();
        addListeners();
    }

    private void initComponents() {
        imageDisplay = new ImageDisplay();

        if (imagePath != null && !imagePath.trim().isEmpty()) {
            imageDisplay.loadImage(imagePath);
        } else {
            imageDisplay.showNoImageMessage("Путь к изображению не был предоставлен");
        }
    }

    private void layoutComponents() {
        setLayout(new BorderLayout(10, 10));

        // Панель анимации по центру
        add(imageDisplay, BorderLayout.CENTER);

        // Панель снизу с кнопкой назад
        JPanel bottomPanel = createBottomPanel();
        add(bottomPanel, BorderLayout.SOUTH);
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
        JLabel infoLabel = new JLabel("Просмотр изображений | Поддержка: JPG, PNG, GIF, BMP");
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

    public void returnToMainMenu() {
        System.out.println("Возврат в главное меню из Task3...");

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

    public static void task3(JFrame mainMenuFrame, String imagePath) {
        // Скрываем главное меню перед показом окна просмотра
        if (mainMenuFrame != null) {
            mainMenuFrame.setVisible(false);
        }

        SwingUtilities.invokeLater(() -> {
            Task3 task3 = new Task3(mainMenuFrame, imagePath);
            task3.setVisible(true);
        });
    }

    // Перегруженный метод для вызова без изображения
    public static void task3(JFrame mainMenuFrame) {
        task3(mainMenuFrame, null);
    }
}