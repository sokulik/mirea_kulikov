package Practices.Practice7;

import Practices.Practice7.Classes.Student;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;
import java.util.Date;

public class Task3 extends JFrame {
    private JFrame mainMenuFrame;
    private JTextArea outputArea;

    public Task3(JFrame mainMenuFrame) {
        super("Задание 3: Студенты и даты");
        this.mainMenuFrame = mainMenuFrame;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);

        initComponents();
        layoutComponents();
        addListeners();
        displayStudentInfo();
    }

    private void initComponents() {
        outputArea = new JTextArea(20, 50);
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Consolas", Font.PLAIN, 12));
        outputArea.setBackground(new Color(248, 249, 250));
    }

    private void layoutComponents() {
        setLayout(new BorderLayout(10, 10));

        // Центральная панель с выводом
        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Информация о студентах"));
        add(scrollPane, BorderLayout.CENTER);

        // Панель снизу с кнопкой назад
        JPanel bottomPanel = createBottomPanel();
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private JPanel createBottomPanel() {
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(new Color(240, 240, 240));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton backButton = createBackButton();
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftPanel.setBackground(new Color(240, 240, 240));
        leftPanel.add(backButton);

        JLabel infoLabel = new JLabel("Различные форматы отображения дат рождения");
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

    private void displayStudentInfo() {
        StringBuilder output = new StringBuilder();

        Calendar cal = Calendar.getInstance();
        Calendar cal1 = Calendar.getInstance();

        cal.set(2000, Calendar.MARCH, 15);
        cal1.set(2005, Calendar.OCTOBER, 16);

        Student student = new Student("Иван Петров", cal.getTime());
        Student student1 = new Student("Иван Золо", cal1.getTime());

        output.append("=== Различные форматы вывода даты рождения ===\n\n");
        output.append(student.toString("short") + " - короткий формат\n");
        output.append(student.toString("medium") + " - средний формат\n");
        output.append(student.toString("long") + " - длинный формат\n");
        output.append(student.toString("full") + " - полный формат\n");
        output.append(student.toString("system") + " - системный формат\n");
        output.append(student.toString() + " - формат по умолчанию(средний)\n");

        output.append("\n=== Дополнительная информация ===\n");
        output.append("Короткий формат: " + student1.getFormattedBirthDate("short") + "\n");
        output.append("День рождения сегодня: " + student1.isBirthdayToday() + "\n");
        output.append("Дней до дня рождения: " + student1.getDaysUntilBirthday() + "\n");

        Calendar cal2 = Calendar.getInstance();
        cal2.set(1999, Calendar.DECEMBER, 5);
        Student student2 = new Student("Мария Сидорова", cal2.getTime());

        output.append("\n=== Второй студент ===\n");
        output.append(student2.toString("long") + "\n");

        outputArea.setText(output.toString());
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

    public static void task3(JFrame mainMenuFrame) {
        if (mainMenuFrame != null) {
            mainMenuFrame.setVisible(false);
        }

        SwingUtilities.invokeLater(() -> new Task3(mainMenuFrame).setVisible(true));
    }
}