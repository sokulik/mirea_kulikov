package DOP;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PracticeTasksWindow extends JFrame {
    private JFrame mainMenuFrame;
    private int practiceNumber;

    public PracticeTasksWindow(JFrame mainMenuFrame, int practiceNumber) {
        super("Практика " + practiceNumber + " - Задания");
        this.mainMenuFrame = mainMenuFrame;
        this.practiceNumber = practiceNumber;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(false);

        initComponents();
        layoutComponents();
        addListeners();
    }

    private void initComponents() {
        // Здесь можно инициализировать компоненты, если нужно
    }

    private void layoutComponents() {
        // Устанавливаем отступы для contentPane
        ((JComponent) getContentPane()).setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        getContentPane().setBackground(new Color(240, 240, 240));

        setLayout(new BorderLayout());

        // Заголовок
        JLabel titleLabel = new JLabel("Практика " + practiceNumber + " - Выбор задания", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(70, 130, 180));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));
        add(titleLabel, BorderLayout.NORTH);

        // Панель с заданиями
        JPanel tasksPanel = createTasksPanel();
        add(tasksPanel, BorderLayout.CENTER);

        // Кнопка назад
        JButton backButton = new JButton("← Назад к выбору практик");
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setBackground(new Color(70, 130, 180));
        backButton.setForeground(Color.WHITE);
        backButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                mainMenuFrame.setVisible(true);
            }
        });

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(240, 240, 240));
        bottomPanel.add(backButton);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private JPanel createTasksPanel() {
        // Определяем количество заданий для каждой практики
        int taskCount = getTaskCountForPractice(practiceNumber);
        int rows = (taskCount + 2) / 3; // Максимум 3 в строке

        JPanel tasksPanel = new JPanel(new GridLayout(rows, 3, 15, 15));
        tasksPanel.setBackground(new Color(240, 240, 240));
        tasksPanel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 50));

        for (int i = 1; i <= taskCount; i++) {
            JButton taskButton = createTaskButton(i);
            tasksPanel.add(taskButton);
        }

        // Добавляем пустые панели если нужно для заполнения сетки
        int totalCells = rows * 3;
        for (int i = taskCount; i < totalCells; i++) {
            JPanel emptyPanel = new JPanel();
            emptyPanel.setBackground(new Color(240, 240, 240));
            tasksPanel.add(emptyPanel);
        }

        return tasksPanel;
    }

    private JButton createTaskButton(int taskNumber) {
        JButton taskButton = new JButton("Задание " + taskNumber);
        taskButton.setFont(new Font("Arial", Font.BOLD, 16));
        taskButton.setBackground(new Color(70, 130, 180));
        taskButton.setForeground(Color.WHITE);
        taskButton.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        taskButton.setFocusPainted(false);
        taskButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        taskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openTask(practiceNumber, taskNumber);
            }
        });

        return taskButton;
    }

    private int getTaskCountForPractice(int practiceNumber) {
        // Возвращаем количество заданий для каждой практики
        return switch (practiceNumber) {
            case 1, 2, 9 -> 1;
            case 3 -> 2;
            case 4, 8, 10, 13 -> 4;
            case 5, 7 -> 6;
            case 6 -> 3;
            case 11 -> 17;
            case 12 -> 8;
            case 14 -> 7;
            default -> Math.min(9, practiceNumber);
        };
    }

    private void openTask(int practiceNumber, int taskNumber) {
        // Закрываем окно заданий
        dispose();

        // Открываем конкретное задание
        /*if (practiceNumber == 1) {
            switch (taskNumber) {
                case 1 -> Practices.Practice1.Task1.task1(mainMenuFrame);
                default -> showTaskNotImplemented(practiceNumber, taskNumber);
            }
        } else if (practiceNumber == 2){
            switch (taskNumber) {
                case 1 -> Practices.Practice2.Task1.task1(mainMenuFrame);
                default -> showTaskNotImplemented(practiceNumber, taskNumber);
            }
        }else if (practiceNumber == 3){
            switch (taskNumber) {
                case 1 -> Practices.Practice3.Task1.task1(mainMenuFrame);
                case 2 -> Practices.Practice3.Task2.task2(mainMenuFrame);
                default -> showTaskNotImplemented(practiceNumber, taskNumber);
            }
        }else if (practiceNumber == 4){
            switch (taskNumber) {
                case 1 -> Practices.Practice4.Task1.task1(mainMenuFrame);
                case 2 -> Practices.Practice4.Task2.task2(mainMenuFrame);
                case 3 -> Practices.Practice4.Task3.task3(mainMenuFrame);
                case 4 -> Practices.Practice4.Task4.task4(mainMenuFrame);
                default -> showTaskNotImplemented(practiceNumber, taskNumber);
            }
        }else if (practiceNumber == 5){
            switch (taskNumber) {
                case 1 -> Practices.Practice5.Task3to4.task3to4(mainMenuFrame);
                case 2 -> Practices.Practice5.Task6to9.task6to9(mainMenuFrame);
                case 3 -> Practices.Practice5.Task10.task10(mainMenuFrame);
                case 4 -> Practices.Practice5.Task11.task11(mainMenuFrame);
                case 5 -> Practices.Practice5.Task12.task12(mainMenuFrame);
                case 6 -> Practices.Practice5.Task13.task13(mainMenuFrame);
                default -> showTaskNotImplemented(practiceNumber, taskNumber);
            }
        }else if (practiceNumber == 6){
            switch (taskNumber) {
                case 1 -> Practices.Practice6.Task4.task4(mainMenuFrame);
                case 2 -> Practices.Practice6.Task5to6.task5to6(mainMenuFrame);
                case 3 -> Practices.Practice6.Task7to8.task7to8(mainMenuFrame);
                default -> showTaskNotImplemented(practiceNumber, taskNumber);
            }
        }else if (practiceNumber == 7){
            switch (taskNumber) {
                case 1 -> Practices.Practice7.Task1.task1(mainMenuFrame);
                case 2 -> Practices.Practice7.Task2.task2(mainMenuFrame);
                case 3 -> Practices.Practice7.Task3.task3(mainMenuFrame);
                case 4 -> Practices.Practice7.Task4.task4(mainMenuFrame);
                case 5 -> Practices.Practice7.Task5.task5(mainMenuFrame);
                case 6 -> Practices.Practice7.TaskDOP.taskDOP(mainMenuFrame);
                default -> showTaskNotImplemented(practiceNumber, taskNumber);
            }
        }else if (practiceNumber == 8){
            switch (taskNumber) {
                case 1 -> Practices.Practice8.Task1.task1(mainMenuFrame);
                case 2 -> Practices.Practice8.Task2.task2(mainMenuFrame);
                case 3 -> Practices.Practice8.Task3.task3(mainMenuFrame);
                case 4 -> Practices.Practice8.Task4.task4(mainMenuFrame);
                default -> showTaskNotImplemented(practiceNumber, taskNumber);
            }
        }else if (practiceNumber == 9){
            switch (taskNumber) {
                case 1 -> Practices.Practice9.Task6.task6(mainMenuFrame);
                default -> showTaskNotImplemented(practiceNumber, taskNumber);
            }
        }else if (practiceNumber == 10){
            switch (taskNumber) {
                case 1 -> Practices.Practice10.Task1.task1(mainMenuFrame);
                case 2 -> Practices.Practice10.Task2.task2(mainMenuFrame);
                case 3 -> Practices.Practice10.Task3.task3(mainMenuFrame);
                case 4 -> Practices.Practice10.Task4.task4(mainMenuFrame);
                default -> showTaskNotImplemented(practiceNumber, taskNumber);
            }
        }else */if (practiceNumber == 11){
            switch (taskNumber) {
                case 1 -> Practices.Practice11.Task1.task1(mainMenuFrame);
                case 2 -> Practices.Practice11.Task2.task2(mainMenuFrame);
                case 3 -> Practices.Practice11.Task3.task3(mainMenuFrame);
                case 4 -> Practices.Practice11.Task4.task4(mainMenuFrame);
                case 5 -> Practices.Practice11.Task5.task5(mainMenuFrame);
                case 6 -> Practices.Practice11.Task6.task6(mainMenuFrame);
                case 7 -> Practices.Practice11.Task7.task7(mainMenuFrame);
                case 8 -> Practices.Practice11.Task8.task8(mainMenuFrame);
                case 9 -> Practices.Practice11.Task9.task9(mainMenuFrame);
                case 10 -> Practices.Practice11.Task10.task10(mainMenuFrame);
                case 11 -> Practices.Practice11.Task11.task11(mainMenuFrame);
                case 12 -> Practices.Practice11.Task12.task12(mainMenuFrame);
                case 13 -> Practices.Practice11.Task13.task13(mainMenuFrame);
                case 14 -> Practices.Practice11.Task14.task14(mainMenuFrame);
                case 15 -> Practices.Practice11.Task15.task15(mainMenuFrame);
                case 16 -> Practices.Practice11.Task16.task16(mainMenuFrame);
                case 17 -> Practices.Practice11.Task17.task17(mainMenuFrame);

                default -> showTaskNotImplemented(practiceNumber, taskNumber);
            }
        }else if (practiceNumber == 12){
            switch (taskNumber) {
                case 1 -> Practices.Practice12.Task1.task1(mainMenuFrame);
                case 2 -> Practices.Practice12.Task2.task2(mainMenuFrame);
                case 3 -> Practices.Practice12.Task3.task3(mainMenuFrame);
                case 4 -> Practices.Practice12.Task4.task4(mainMenuFrame);
                case 5 -> Practices.Practice12.Task5.task5(mainMenuFrame);
                case 6 -> Practices.Practice12.Task6.task6(mainMenuFrame);
                case 7 -> Practices.Practice12.Task7.task7(mainMenuFrame);
                case 8 -> Practices.Practice12.Task8.task8(mainMenuFrame);
                default -> showTaskNotImplemented(practiceNumber, taskNumber);
            }
        }else if (practiceNumber == 13){
            switch (taskNumber) {
                case 1 -> Practices.Practice13.Task1.task1(mainMenuFrame);
                case 2 -> Practices.Practice13.Task2.task2(mainMenuFrame);
                case 3 -> Practices.Practice13.Task3.task3(mainMenuFrame);
                case 4 -> Practices.Practice13.Task4.task4(mainMenuFrame);
                default -> showTaskNotImplemented(practiceNumber, taskNumber);
            }
        }else if (practiceNumber == 14){
            switch (taskNumber) {
                case 1 -> Practices.Practice14.Task1.task1(mainMenuFrame);
                case 2 -> Practices.Practice14.Task2.task2(mainMenuFrame);
                case 3 -> Practices.Practice14.Task3.task3(mainMenuFrame);
                case 4 -> Practices.Practice14.Task4.task4(mainMenuFrame);
                case 5 -> Practices.Practice14.Task5.task5(mainMenuFrame);
                case 6 -> Practices.Practice14.Task6.task6(mainMenuFrame);
                case 7 -> Practices.Practice14.Task7.task7(mainMenuFrame);
                default -> showTaskNotImplemented(practiceNumber, taskNumber);
            }
        } else {
            showTaskNotImplemented(practiceNumber, taskNumber);
        }
    }

    private void showTaskNotImplemented(int practiceNumber, int taskNumber) {
        JOptionPane.showMessageDialog(this,
                "Задание " + taskNumber + " практики " + practiceNumber + "\n" +
                        "Функциональность будет добавлена позже",
                "Информация",
                JOptionPane.INFORMATION_MESSAGE);
        mainMenuFrame.setVisible(true);
    }

    private void addListeners() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                mainMenuFrame.setVisible(true);
            }
        });
    }
}