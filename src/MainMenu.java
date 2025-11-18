package Tasks;

import DOP.PracticeTasksWindow;

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
        mainMenuFrame = new JFrame("Практические работы 1-14 - Главное меню");
        mainMenuFrame.setSize(900, 700);
        mainMenuFrame.setLocationRelativeTo(null);
        mainMenuFrame.setResizable(false);
        mainMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        cardsPanel = new JPanel(cardLayout);

        // Создаем страницы с практиками
        JPanel page1 = createPracticesPage(1, 1, 7);  // Практики 1-7
        JPanel page2 = createPracticesPage(2, 8, 14); // Практики 8-14

        cardsPanel.add(page1, "page1");
        cardsPanel.add(page2, "page2");

        mainMenuFrame.add(cardsPanel);
        mainMenuFrame.setVisible(true);
    }

    private static JPanel createPracticesPage(int pageNumber, int startPractice, int endPractice) {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(240, 240, 240));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        String title = "Практические работы " + startPractice + "-" + endPractice;
        JLabel titleLabel = new JLabel(title, JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(new Color(70, 130, 180));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Создаем сетку для практик
        int practiceCount = endPractice - startPractice + 1;
        int rows = (practiceCount + 2) / 3; // Максимум 3 в строке
        JPanel practicesPanel = new JPanel(new GridLayout(rows, 3, 15, 15));
        practicesPanel.setBackground(new Color(240, 240, 240));

        for (int i = startPractice; i <= endPractice; i++) {
            JPanel practicePanel = createPracticePanel(i, getPracticeDescription(i));
            practicesPanel.add(practicePanel);
        }

        mainPanel.add(practicesPanel, BorderLayout.CENTER);

        // Панель навигации и информации
        JPanel navInfoPanel = new JPanel(new BorderLayout());
        navInfoPanel.setBackground(new Color(240, 240, 240));

        // Панель навигации
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

        // Информационная панель
        JPanel infoPanel = new JPanel(new BorderLayout());
        infoPanel.setBackground(new Color(240, 240, 240));

        JLabel infoLabel = new JLabel("Выберите практическую работу для просмотра заданий", JLabel.CENTER);
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

        navInfoPanel.add(navPanel, BorderLayout.NORTH);
        navInfoPanel.add(infoPanel, BorderLayout.CENTER);
        navInfoPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        mainPanel.add(navInfoPanel, BorderLayout.SOUTH);

        return mainPanel;
    }

    private static JPanel createPracticePanel(int practiceNumber, String description) {
        JPanel practicePanel = new JPanel(new BorderLayout());
        practicePanel.setBackground(Color.WHITE);
        practicePanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        JButton practiceButton = new JButton("Практика " + practiceNumber);
        practiceButton.setFont(new Font("Arial", Font.BOLD, 16));
        practiceButton.setBackground(new Color(70, 130, 180));
        practiceButton.setForeground(Color.WHITE);
        practiceButton.setBorder(BorderFactory.createEmptyBorder(15, 5, 15, 5));
        practiceButton.setFocusPainted(false);
        practiceButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel descLabel = new JLabel(description, JLabel.CENTER);
        descLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        descLabel.setForeground(Color.DARK_GRAY);

        practicePanel.add(practiceButton, BorderLayout.NORTH);
        practicePanel.add(descLabel, BorderLayout.CENTER);

        final int practiceNum = practiceNumber;
        practiceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openPracticeTasks(practiceNum);
            }
        });

        practicePanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                practicePanel.setBackground(new Color(230, 240, 255));
                practiceButton.setBackground(new Color(100, 150, 200));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                practicePanel.setBackground(Color.WHITE);
                practiceButton.setBackground(new Color(70, 130, 180));
            }
        });

        return practicePanel;
    }

    private static String getPracticeDescription(int practiceNumber) {
        return switch (practiceNumber) {
            case 1 -> "Введение в Java";
            case 2 -> "Базовые конструкции";
            case 3 -> "Массивы и строки";
            case 4 -> "Классы и объекты";
            case 5 -> "Наследование";
            case 6 -> "Интерфейсы";
            case 7 -> "Исключения";
            case 8 -> "Ввод-вывод";
            case 9 -> "Коллекции";
            case 10 -> "Графический интерфейс";
            case 11 -> "Многопоточность";
            case 12 -> "Сети";
            case 13 -> "Базы данных";
            case 14 -> "Финальный проект";
            default -> "Практическая работа";
        };
    }

    private static void openPracticeTasks(int practiceNumber) {
        // Создаем новое окно для заданий выбранной практики
        PracticeTasksWindow tasksWindow = new PracticeTasksWindow(mainMenuFrame, practiceNumber);
        tasksWindow.setVisible(true);
        mainMenuFrame.setVisible(false);
    }

    public static void showMainMenu() {
        cardLayout.show(cardsPanel, "page1");
        mainMenuFrame.setVisible(true);
    }
}