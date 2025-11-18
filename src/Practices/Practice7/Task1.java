package Practices.Practice7;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Task1 extends JFrame {
    private JFrame mainMenuFrame;

    public Task1(JFrame mainMenuFrame) {
        super("Задание 1: Информация о разработчике");
        this.mainMenuFrame = mainMenuFrame;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);

        initComponents();
        layoutComponents();
        addListeners();
    }

    private void initComponents() {
        // Инициализация компонентов (если нужны)
    }

    private void layoutComponents() {
        setLayout(new BorderLayout(10, 10));

        // Основная панель с информацией
        JPanel infoPanel = createInfoPanel();
        add(infoPanel, BorderLayout.CENTER);

        // Панель снизу с кнопкой назад
        JPanel bottomPanel = createBottomPanel();
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private JPanel createInfoPanel() {
        JPanel infoPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        infoPanel.setBackground(new Color(240, 240, 240));

        String developer = "Гнатюк Даниил Александрович";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy, MMMM, dd | HH:mm:ss");

        Calendar cal1 = Calendar.getInstance();
        cal1.set(2025, Calendar.AUGUST, 25, 11, 25, 43);

        Date now = new Date();
        Date startDate = cal1.getTime();

        JLabel titleLabel = new JLabel("📋 Информация о проекте", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(new Color(70, 130, 180));

        JLabel devLabel = new JLabel("👨‍💻 Разработчик: " + developer);
        devLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel startLabel = new JLabel("🚀 Дата начала: " + sdf.format(startDate));
        startLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel endLabel = new JLabel("✅ Дата окончания: " + sdf.format(now));
        endLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        infoPanel.add(titleLabel);
        infoPanel.add(devLabel);
        infoPanel.add(startLabel);
        infoPanel.add(endLabel);

        return infoPanel;
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

        bottomPanel.add(leftPanel, BorderLayout.WEST);
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

    public static void task1(JFrame mainMenuFrame) {
        if (mainMenuFrame != null) {
            mainMenuFrame.setVisible(false);
        }

        SwingUtilities.invokeLater(() -> new Task1(mainMenuFrame).setVisible(true));
    }
}