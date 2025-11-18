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
    private static final int TASKS_PER_PAGE = 8;

    public static void createMainMenu(String[] args) {
        if (args.length > 0) {
            commandLineImagePath = args[0];
        }
        mainMenuFrame = new JFrame("Практическая работа №12 - Главное меню");
        mainMenuFrame.setSize(700, 600);
        mainMenuFrame.setLocationRelativeTo(null);
        mainMenuFrame.setResizable(false);
        mainMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        cardsPanel = new JPanel(cardLayout);

        JPanel page1 = createPage(1);
        JPanel page2 = createPage(2);

        cardsPanel.add(page1, "page1");
        cardsPanel.add(page2, "page2");

        mainMenuFrame.add(cardsPanel);
        mainMenuFrame.setVisible(true);
    }

    private static JPanel createPage(int pageNumber){
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(240,240, 240));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        JLabel titleLabel = new JLabel("Практическая работа №12 - Обработка исключений", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(70, 130, 180));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel tasksPanel = new JPanel(new GridLayout(4, 2, 15, 15));
        tasksPanel.setBackground(new Color(240, 240, 240));

        int startTask = (pageNumber - 1) * 4 + 1;
        int endTask = Math.min(pageNumber * 4, 8);

        for (int i = startTask; i <= endTask; i++) {
            JPanel taskPanel = createTaskPanel(i, getTaskDescription(i));
            tasksPanel.add(taskPanel);
        }

        for (int i = endTask + 1; i <= pageNumber * 4; i++) {
            tasksPanel.add(new JPanel());
        }

        mainPanel.add(tasksPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(new Color(240, 240, 240));

        JPanel navPanel = new JPanel(new FlowLayout());
        navPanel.setBackground(new Color(240, 240, 240));

        if (pageNumber == 1) {
            JButton nextButton = createNavButton("Следующая страница →", "next");
            navPanel.add(nextButton);
        } else {
            JButton prevButton = createNavButton("← Предыдущая страница", "prev");
            navPanel.add(prevButton);
        }

        bottomPanel.add(navPanel, BorderLayout.NORTH);

        JPanel infoPanel = new JPanel(new BorderLayout());
        infoPanel.setBackground(new Color(240, 240, 240));

        JLabel infoLabel = new JLabel("Выберите задание для выполнения", JLabel.CENTER);
        infoLabel.setFont(new Font("Arial", Font.ITALIC, 14));
        infoLabel.setForeground(Color.GRAY);

        if (commandLineImagePath != null && pageNumber == 1) {
            JLabel cmdInfoLabel = new JLabel("Аргумент командной строки: " + commandLineImagePath, JLabel.CENTER);
            cmdInfoLabel.setFont(new Font("Arial", Font.PLAIN, 10));
            cmdInfoLabel.setForeground(Color.BLUE);
            cmdInfoLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
            infoPanel.add(cmdInfoLabel, BorderLayout.NORTH);
        }

        infoPanel.add(infoLabel, BorderLayout.CENTER);
        infoPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        bottomPanel.add(infoPanel, BorderLayout.CENTER);

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

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
        taskButton.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5));
        taskButton.setFocusPainted(false);
        taskButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel descLabel = new JLabel(description, JLabel.CENTER);
        descLabel.setFont(new Font("Arial", Font.PLAIN, 11));
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
            case 1 -> "Деление на ноль\nи try-catch";
            case 2 -> "Обработка ввода\nпользователя";
            case 3 -> "Общий Exception\nв catch";
            case 4 -> "Блок finally\nв обработке";
            case 5 -> "Генерация\nсобственных исключений";
            case 6 -> "Цепочка вызовов\nи исключения";
            case 7 -> "Проброс исключений\nчерез методы";
            case 8 -> "Цикл ввода\nс обработкой ошибок";
            default -> "Описание\nзадания";
        };
    }

    private static JButton createNavButton(String text, String type) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(new Color(100, 150, 200));
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ("next".equals(type)) {
                    cardLayout.show(cardsPanel, "page2");
                } else if ("prev".equals(type)) {
                    cardLayout.show(cardsPanel, "page1");
                }
            }
        });

        return button;
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
            case 5:
                Task5.task5(mainMenuFrame);
                break;
            case 6:
                Task6.task6(mainMenuFrame);
                break;
            case 7:
                Task7.task7(mainMenuFrame);
                break;
            case 8:
                Task8.task8(mainMenuFrame);
                break;
        }
    }

    public static void showMainMenu() {
        mainMenuFrame.setVisible(true);
    }

}