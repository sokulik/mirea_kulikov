package Practices.Practice8;

import Practices.Practice8.Classes.Task4.AnimationPanel;
import Practices.Practice8.Classes.Task4.ControlPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class Task4 extends JFrame {
    private AnimationPanel animationPanel;
    private ControlPanel controlPanel;
    private JFrame mainMenuFrame;

    public Task4(JFrame mainMenuFrame) {
        super("Задание 4: Продвинутая анимация");
        this.mainMenuFrame = mainMenuFrame;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(900, 700); // Увеличил размер окна
        setLocationRelativeTo(null);

        try {
            initComponents();
            layoutComponents();
            addListeners();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this,
                    "Ошибка загрузки анимации: " + e.getMessage(),
                    "Ошибка", JOptionPane.ERROR_MESSAGE);
            dispose();
            returnToMainMenu();
        }
    }

    private void initComponents() throws IOException {
        animationPanel = new AnimationPanel();
        controlPanel = new ControlPanel(animationPanel);
    }

    private void layoutComponents() {
        setLayout(new BorderLayout(10, 10));

        // Панель управления сверху
        add(controlPanel, BorderLayout.NORTH);

        // Панель анимации по центру
        add(animationPanel, BorderLayout.CENTER);

        // Панель статуса снизу с кнопкой назад
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
        JLabel infoLabel = new JLabel("Анимация: 6 кадров | Управление: Старт/Стоп + регулятор скорости");
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
        System.out.println("Возврат в главное меню...");

        // Останавливаем анимацию
        if (animationPanel != null) {
            animationPanel.stopAnimation();
        }

        // Закрываем это окно
        dispose();

        // Показываем главное меню
        if (mainMenuFrame != null) {
            System.out.println("Показываем главное меню");
            mainMenuFrame.setVisible(true);
            mainMenuFrame.toFront(); // Переводим фокус на главное меню
        } else {
            System.out.println("mainMenuFrame is null!");
        }
    }

    public static void task4(JFrame mainMenuFrame) {
        // Скрываем главное меню перед показом анимации
        if (mainMenuFrame != null) {
            mainMenuFrame.setVisible(false);
        }

        SwingUtilities.invokeLater(() -> {
            Task4 task4 = new Task4(mainMenuFrame);
            task4.setVisible(true);
        });
    }
}