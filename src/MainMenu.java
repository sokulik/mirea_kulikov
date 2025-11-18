import Tasks.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu {
    private static JFrame mainMenuFrame;
    private static String commandLineImagePath;
    private static JPanel cardsPanel;
    private static CardLayout cardLayout;
    private static final int TASKS_PER_PAGE = 4;

    public static void createMainMenu(String[] args) {
        if (args.length > 0) {
            commandLineImagePath = args[0];
        }
        mainMenuFrame = new JFrame("Практическая работа №13 - Главное меню");
        mainMenuFrame.setSize(700, 600);
        mainMenuFrame.setLocationRelativeTo(null);
        mainMenuFrame.setResizable(false);
        mainMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        cardsPanel = new JPanel(cardLayout);

        JPanel page1 = createPage(1);

        cardsPanel.add(page1, "page1");

        mainMenuFrame.add(cardsPanel);
        mainMenuFrame.setVisible(true);
    }

    private static JPanel createPage(int pageNumber){
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(240,240, 240));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        JLabel titleLabel = new JLabel("Практическая работа №13", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(70, 130, 180));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel tasksPanel = new JPanel(new GridLayout(2, 2, 15, 15));
        tasksPanel.setBackground(new Color(240, 240, 240));

        for (int i = 1; i <= 4; i++) {
            JPanel taskPanel = createTaskPanel(i, getTaskDescription(i));
            tasksPanel.add(taskPanel);
        }

        mainPanel.add(tasksPanel, BorderLayout.CENTER);

        JPanel infoPanel = new JPanel(new BorderLayout());
        infoPanel.setBackground(new Color(240, 240, 240));

        JLabel infoLabel = new JLabel("Выберите задание для выполнения", JLabel.CENTER);
        infoLabel.setFont(new Font("Arial", Font.ITALIC, 14));
        infoLabel.setForeground(Color.GRAY);

        if (commandLineImagePath != null) {
            JLabel cmdInfoLabel = new JLabel("Аргумент командной строки: " + commandLineImagePath, JLabel.CENTER);
            cmdInfoLabel.setFont(new Font("Arial", Font.PLAIN, 10));
            cmdInfoLabel.setForeground(Color.BLUE);
            cmdInfoLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
            infoPanel.add(cmdInfoLabel, BorderLayout.NORTH);
        }

        infoPanel.add(infoLabel, BorderLayout.CENTER);
        infoPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        mainPanel.add(infoPanel, BorderLayout.SOUTH);

        return mainPanel;
    }

    private static JPanel createTaskPanel(int taskNumber, String description) {
        JPanel taskPanel = new JPanel(new BorderLayout());
        taskPanel.setBackground(Color.WHITE);
        taskPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        JButton taskButton = new JButton("Задание " + taskNumber);
        taskButton.setFont(new Font("Arial", Font.BOLD, 16));
        taskButton.setBackground(new Color(70, 130, 180));
        taskButton.setForeground(Color.WHITE);
        taskButton.setBorder(BorderFactory.createEmptyBorder(15, 5, 15, 5));
        taskButton.setFocusPainted(false);
        taskButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel descLabel = new JLabel(description, JLabel.CENTER);
        descLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        descLabel.setForeground(Color.DARK_GRAY);

        taskPanel.add(taskButton, BorderLayout.NORTH);
        taskPanel.add(descLabel, BorderLayout.CENTER);

        final int taskNum = taskNumber;
        taskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openTask(taskNum);
            }
        });

        taskPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                taskPanel.setBackground(new Color(230, 240, 255));
                taskButton.setBackground(new Color(100, 150, 200));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                taskPanel.setBackground(Color.WHITE);
                taskButton.setBackground(new Color(70, 130, 180));
            }
        });

        return taskPanel;
    }

    private static String getTaskDescription(int taskNumber){
        return switch (taskNumber) {
            case 1 -> "Сортировка вставками\n по ID";
            case 2 -> "Быстрая сортировка\n по GPA";
            case 3 -> "Сортировка слиянием\n объединенных списков";
            case 4 -> "Собственная реализация\n Comparable";
            default -> "Описание\n задания";
        };
    }

    private static void openTask(int taskNumber){
        mainMenuFrame.setVisible(false);

        switch (taskNumber){
            case 1:
                Task1.task1(mainMenuFrame);
                break;
            case 2:
                Task2.task2(mainMenuFrame);
                break;
            case 3:
                Task3.task3(mainMenuFrame);
                break;
            case 4:
                Task4.task4(mainMenuFrame);
                break;
        }
    }

    public static void showMainMenu() {
        cardLayout.show(cardsPanel, "page1");
        mainMenuFrame.setVisible(true);
    }
}