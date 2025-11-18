package Practices.Practice7;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

public class Task_DOP extends JFrame {
    private JFrame mainMenuFrame;
    private JTextArea resultArea;
    private JTabbedPane tabbedPane;

    public Task_DOP(JFrame mainMenuFrame) {
        super("Дополнительные задания");
        this.mainMenuFrame = mainMenuFrame;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        initComponents();
        layoutComponents();
        addListeners();
    }

    private void initComponents() {
        resultArea = new JTextArea(20, 60);
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Consolas", Font.PLAIN, 12));
        resultArea.setBackground(new Color(248, 249, 250));

        tabbedPane = new JTabbedPane();
    }

    private void layoutComponents() {
        setLayout(new BorderLayout(10, 10));

        // Создаем вкладки для каждого задания
        createTabs();

        add(tabbedPane, BorderLayout.CENTER);

        // Панель снизу с кнопкой назад
        JPanel bottomPanel = createBottomPanel();
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void createTabs() {
        // Вкладка 1 - Время с 1970 года
        JPanel tab1 = createTab1();
        tabbedPane.addTab("📅 Задание 1", tab1);

        // Вкладка 2 - Случайные числа
        JPanel tab2 = createTab2();
        tabbedPane.addTab("🎲 Задание 2", tab2);

        // Вкладка 3 - GregorianCalendar
        JPanel tab3 = createTab3();
        tabbedPane.addTab("📊 Задание 3", tab3);
    }

    private JPanel createTab1() {
        JPanel panel = new JPanel(new BorderLayout());

        JTextArea textArea = new JTextArea(15, 50);
        textArea.setEditable(false);
        textArea.setFont(new Font("Consolas", Font.PLAIN, 12));
        textArea.setBackground(new Color(248, 249, 250));

        JButton runButton = new JButton("Показать временные метки");
        runButton.setBackground(new Color(70, 130, 180));
        runButton.setForeground(Color.WHITE);
        runButton.addActionListener(e -> {
            StringBuilder output = new StringBuilder();
            output.append("Время с 1 января 1970 года | Дата и время\n");
            output.append("═".repeat(80) + "\n");

            long[] timeValues = {
                    10000L, 100000L, 1000000L, 10000000L, 100000000L,
                    1000000000L, 10000000000L, 100000000000L,
                    1000000000000L, 10000000000000L, 100000000000000L,
                    1000000000000000L
            };

            for(long time : timeValues){
                Date date = new Date(time);
                output.append(String.format("%,30d мс | %s\n", time, date.toString()));
            }

            textArea.setText(output.toString());
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(runButton);

        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createTab2() {
        JPanel panel = new JPanel(new BorderLayout());

        JTextArea textArea = new JTextArea(15, 50);
        textArea.setEditable(false);
        textArea.setFont(new Font("Consolas", Font.PLAIN, 12));
        textArea.setBackground(new Color(248, 249, 250));

        JButton runButton = new JButton("Сгенерировать числа");
        runButton.setBackground(new Color(70, 130, 180));
        runButton.setForeground(Color.WHITE);
        runButton.addActionListener(e -> {
            StringBuilder output = new StringBuilder();
            Random random = new Random(1);

            output.append("Первые 50 случайных чисел от 0 до 100\n");
            output.append("═".repeat(80) + "\n");

            for (int i = 1; i <= 50; i++) {
                int number = random.nextInt(100);
                output.append(String.format("%3d", number));

                if (i % 10 == 0) {
                    output.append("\n");
                } else {
                    output.append(" ");
                }
            }

            Random sameRandom = new Random(1);
            output.append("═".repeat(80) + "\n");
            output.append("Демонстрация работы воспроизводимости одного и того же сида\n");
            output.append("═".repeat(80) + "\n");
            output.append("Первые 5 чисел: ");
            for (int i = 1; i <= 5; i++) {
                int number = sameRandom.nextInt(100);
                output.append(String.format("%3d", number));
            }

            textArea.setText(output.toString());
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(runButton);

        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createTab3() {
        JPanel panel = new JPanel(new BorderLayout());

        JTextArea textArea = new JTextArea(15, 50);
        textArea.setEditable(false);
        textArea.setFont(new Font("Consolas", Font.PLAIN, 12));
        textArea.setBackground(new Color(248, 249, 250));

        JButton runButton = new JButton("Показать даты");
        runButton.setBackground(new Color(70, 130, 180));
        runButton.setForeground(Color.WHITE);
        runButton.addActionListener(e -> {
            StringBuilder output = new StringBuilder();
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

            GregorianCalendar currentDate = new GregorianCalendar();
            int currentYear = currentDate.get(GregorianCalendar.YEAR);
            int currentMonth = currentDate.get(GregorianCalendar.MONTH) + 1;
            int currentDay = currentDate.get(GregorianCalendar.DAY_OF_MONTH);

            output.append("1. Текущая дата\n");
            output.append("═".repeat(80) + "\n");
            output.append("Год: " + currentYear + "\n");
            output.append("Месяц: " + currentMonth + "\n");
            output.append("День: " + currentDay + "\n");
            output.append("Общая дата: " + sdf.format(currentDate.getTime()) + "\n\n");

            GregorianCalendar staticDate = new GregorianCalendar();
            staticDate.setTimeInMillis(1234567898765L);
            int staticYear = staticDate.get(GregorianCalendar.YEAR);
            int staticMonth = staticDate.get(GregorianCalendar.MONTH) + 1;
            int staticDay = staticDate.get(GregorianCalendar.DAY_OF_MONTH);

            output.append("2. Кастомная дата (1234567898765 мс)\n");
            output.append("═".repeat(80) + "\n");
            output.append("Год: " + staticYear + "\n");
            output.append("Месяц: " + staticMonth + "\n");
            output.append("День: " + staticDay + "\n");
            output.append("Общая дата: " + sdf.format(staticDate.getTime()) + "\n");

            textArea.setText(output.toString());
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(runButton);

        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createBottomPanel() {
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(new Color(240, 240, 240));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton backButton = createBackButton();
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftPanel.setBackground(new Color(240, 240, 240));
        leftPanel.add(backButton);

        JLabel infoLabel = new JLabel("Дополнительные задания по работе с датами и коллекциями");
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
        dispose();
        if (mainMenuFrame != null) {
            mainMenuFrame.setVisible(true);
            mainMenuFrame.toFront();
        }
    }

    public static void task_dop(JFrame mainMenuFrame) {
        if (mainMenuFrame != null) {
            mainMenuFrame.setVisible(false);
        }

        SwingUtilities.invokeLater(() -> new Task_DOP(mainMenuFrame).setVisible(true));
    }
}