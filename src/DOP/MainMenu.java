package DOP;

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
        // Устанавливаем путь к фону из командной строки
        if (args.length > 0) {
            commandLineImagePath = args[0];
            BackgroundManager.setBackgroundPath(commandLineImagePath);
        }

        mainMenuFrame = new JFrame("Практические работы 1-14 - Главное меню");
        mainMenuFrame.setSize(900, 700);
        mainMenuFrame.setLocationRelativeTo(null);
        mainMenuFrame.setResizable(false);
        mainMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ПРИМЕНЯЕМ ФОН К ГЛАВНОМУ МЕНЮ
        BackgroundManager.applyBackgroundToFrame(mainMenuFrame);

        cardLayout = new CardLayout();
        cardsPanel = new JPanel(cardLayout);
        cardsPanel.setOpaque(false); // Делаем панель прозрачной

        // Создаем страницы с практиками
        JPanel page1 = createPracticesPage(1, 1, 7);  // Практики 1-7
        JPanel page2 = createPracticesPage(2, 8, 14); // Практики 8-14

        cardsPanel.add(page1, "page1");
        cardsPanel.add(page2, "page2");

        mainMenuFrame.add(cardsPanel);
        mainMenuFrame.setVisible(true);
    }

    private static JPanel createPracticesPage(int pageNumber, int startPractice, int endPractice) {
        // Используем полупрозрачную панель
        JPanel mainPanel = BackgroundManager.createSemiTransparentPanel(new Color(240, 240, 240));
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        String title = "Практические работы " + startPractice + "-" + endPractice;
        JLabel titleLabel = new JLabel(title, JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(new Color(70, 130, 180));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Создаем сетку для практик
        int practiceCount = endPractice - startPractice + 1;
        int rows = (practiceCount + 2) / 3;
        JPanel practicesPanel = BackgroundManager.createTransparentPanel();
        practicesPanel.setLayout(new GridLayout(rows, 3, 15, 15));

        for (int i = startPractice; i <= endPractice; i++) {
            JPanel practicePanel = createPracticePanel(i, getPracticeDescription(i));
            practicesPanel.add(practicePanel);
        }

        mainPanel.add(practicesPanel, BorderLayout.CENTER);

        // Панель навигации и информации
        JPanel navInfoPanel = BackgroundManager.createTransparentPanel();
        navInfoPanel.setLayout(new BorderLayout());

        // Панель навигации
        JPanel navPanel = BackgroundManager.createTransparentPanel();
        navPanel.setLayout(new FlowLayout());

        if (pageNumber == 1) {
            JButton nextPageButton = BackgroundManager.createStyledButton("Следующая страница →", new Color(70, 130, 180));
            nextPageButton.addActionListener(e -> cardLayout.show(cardsPanel, "page2"));
            navPanel.add(nextPageButton);
        } else {
            JButton prevPageButton = BackgroundManager.createStyledButton("← Предыдущая страница", new Color(70, 130, 180));
            prevPageButton.addActionListener(e -> cardLayout.show(cardsPanel, "page1"));
            navPanel.add(prevPageButton);
        }

        // Информационная панель
        JPanel infoPanel = BackgroundManager.createSemiTransparentPanel(new Color(240, 240, 240));
        infoPanel.setLayout(new BorderLayout());

        JLabel infoLabel = new JLabel("Выберите практическую работу для просмотра заданий", JLabel.CENTER);
        infoLabel.setFont(new Font("Arial", Font.ITALIC, 14));
        infoLabel.setForeground(Color.DARK_GRAY);

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
        JPanel practicePanel = BackgroundManager.createSemiTransparentPanel(Color.WHITE);
        practicePanel.setLayout(new BorderLayout());
        practicePanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        JButton practiceButton = BackgroundManager.createStyledButton("Практика " + practiceNumber, new Color(70, 130, 180));
        practiceButton.setFont(new Font("Arial", Font.BOLD, 16));
        practiceButton.setBorder(BorderFactory.createEmptyBorder(15, 5, 15, 5));
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
                practicePanel.setBackground(new Color(230, 240, 255, 180));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                practicePanel.setBackground(new Color(255, 255, 255, 180));
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