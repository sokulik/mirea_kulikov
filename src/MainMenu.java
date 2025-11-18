
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

    public static void createMainMenu(String[] args) {
        if (args.length > 0) {
            commandLineImagePath = args[0];
        }
        mainMenuFrame = new JFrame("Практическая работа 14 - Главное меню");
        mainMenuFrame.setSize(800, 700);
        mainMenuFrame.setLocationRelativeTo(null);
        mainMenuFrame.setResizable(false);
        mainMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        cardsPanel = new JPanel(cardLayout);

        JPanel page1 = createPage(1, 1, 3); // Задания 1-3
        JPanel page2 = createPage(2, 4, 7); // Задания 4-7

        cardsPanel.add(page1, "page1");
        cardsPanel.add(page2, "page2");

        mainMenuFrame.add(cardsPanel);
        mainMenuFrame.setVisible(true);
    }

    private static JPanel createPage(int pageNumber, int startTask, int endTask){
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(240,240, 240));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        String title = pageNumber == 1 ?
                "Практическая работа 14 - Коллекции" :
                "Практическая работа 14 - Работа с файлами";

        JLabel titleLabel = new JLabel(title, JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(70, 130, 180));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        int taskCount = endTask - startTask + 1;
        int rows = (taskCount + 1) / 2; // Округление вверх
        JPanel tasksPanel = new JPanel(new GridLayout(rows, 2, 15, 15));
        tasksPanel.setBackground(new Color(240, 240, 240));

        for (int i = startTask; i <= endTask; i++) {
            JPanel taskPanel = createTaskPanel(i, getTaskDescription(i));
            tasksPanel.add(taskPanel);
        }

        mainPanel.add(tasksPanel, BorderLayout.CENTER);

        JPanel infoPanel = new JPanel(new BorderLayout());
        infoPanel.setBackground(new Color(240, 240, 240));

        JLabel infoLabel = new JLabel("Выберите задание для выполнения", JLabel.CENTER);
        infoLabel.setFont(new Font("Arial", Font.ITALIC, 14));
        infoLabel.setForeground(Color.GRAY);

        JPanel navPanel = new JPanel(new FlowLayout());
        navPanel.setBackground(new Color(240, 240, 240));

        if (pageNumber == 1) {
            JButton nextPageButton = new JButton("Следующая страница →");
            nextPageButton.addActionListener(e -> cardLayout.show(cardsPanel, "page2"));
            navPanel.add(nextPageButton);
        } else {
            JButton prevPageButton = new JButton("← Предыдущая страница");
            prevPageButton.addActionListener(e -> cardLayout.show(cardsPanel, "page1"));
            navPanel.add(prevPageButton);
        }

        infoPanel.add(navPanel, BorderLayout.NORTH);
        infoPanel.add(infoLabel, BorderLayout.CENTER);

        if (commandLineImagePath != null) {
            JLabel cmdInfoLabel = new JLabel("Аргумент командной строки: " + commandLineImagePath, JLabel.CENTER);
            cmdInfoLabel.setFont(new Font("Arial", Font.PLAIN, 10));
            cmdInfoLabel.setForeground(Color.BLUE);
            cmdInfoLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
            infoPanel.add(cmdInfoLabel, BorderLayout.SOUTH);
        }

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
            case 1 -> "Тестирование работы\n коллекции ArrayList";
            case 2 -> "Тестирование работы\n коллекции LinkedList";
            case 3 -> "Создание своей коллекции\n аналогичной ArrayList";
            case 4 -> "Запись в файл информации\n введенной с клавиатуры";
            case 5 -> "Вывод информации\n из файла на экран";
            case 6 -> "Замена информации в файле\n на новую информацию";
            case 7 -> "Добавление в конец файла\n текста с клавиатуры";
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
            case 5:
                Task5.task5(mainMenuFrame);
                break;
            case 6:
                Task6.task6(mainMenuFrame);
                break;
            case 7:
                Task7.task7(mainMenuFrame);
                break;
        }
    }

    public static void showMainMenu() {
        cardLayout.show(cardsPanel, "page1");
        mainMenuFrame.setVisible(true);
    }
}