import Practice8_1.Task1;
import Practice8_2.Task2;
//import Practice8_3.Task3;
//import Practice8_4.Task4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    private static JFrame mainMenuFrame;

    public static void main(String[] args) {
        createMainMenu();
    }

    private static void createMainMenu(){
        mainMenuFrame = new JFrame("Практическая работа 8 - Главное меню");
        mainMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainMenuFrame.setSize(500, 400);
        mainMenuFrame.setLocationRelativeTo(null);
        mainMenuFrame.setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(240));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("Практическая работа №8", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(70, 130, 180));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel tasksPanel = new JPanel(new GridLayout(4, 1, 15, 15));
        tasksPanel.setBackground(new Color(240));

        JButton task1Button = createTaskButton("Задание 1", "Таблица результата матчей");
        JButton task2Button = createTaskButton("Задание 2", "20 случаных фигур");
        JButton task3Button = createTaskButton("Задание 3", "");
        JButton task4Button = createTaskButton("Задание 4", "");

        task1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openTask1();
            }
        });

        task2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openTask2();
            }
        });

        task3Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openTask3();
            }
        });

        task4Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openTask4();
            }
        });

        tasksPanel.add(task1Button);
        tasksPanel.add(task2Button);
        tasksPanel.add(task3Button);
        tasksPanel.add(task4Button);

        mainPanel.add(tasksPanel, BorderLayout.CENTER);

        JLabel infoLabel = new JLabel("Выверите задание для выполнения", JLabel.CENTER);
        infoLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        infoLabel.setForeground(Color.GRAY);
        infoLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        mainPanel.add(infoLabel, BorderLayout.SOUTH);

        mainMenuFrame.add(mainPanel);
        mainMenuFrame.setVisible(true);
    }

    private static JButton createTaskButton(String taskNumber, String description){
        JButton button = new JButton(taskNumber);

        button.setToolTipText(description);

        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(Color.WHITE);
        button.setForeground(new Color(70, 130, 180));
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(20, 10, 20, 10)
        ));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(230, 240, 255));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.WHITE);
            }
        });

        return button;
    }

    private static void openTask1() {
        mainMenuFrame.setVisible(false);
        Task1.start(mainMenuFrame);
    }

    private static void openTask2() {
        mainMenuFrame.setVisible(false);
        Task2.start(mainMenuFrame);
    }

    private static void openTask3() {
        JOptionPane.showMessageDialog(mainMenuFrame,
                "Задание 3 будет реализовано позже",
                "Информация",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private static void openTask4() {
        JOptionPane.showMessageDialog(mainMenuFrame,
                "Задание 4 будет реализовано позже",
                "Информация",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public static void showMainMenu() {
        mainMenuFrame.setVisible(true);
    }
}



