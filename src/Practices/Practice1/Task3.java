package Practices.Practice1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Task3 extends JFrame {
    private JFrame mainMenuFrame;

    public Task3(JFrame mainMenuFrame) {
        super("Задание 3: Аргументы командной строки");
        this.mainMenuFrame = mainMenuFrame;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);

        initComponents();
        layoutComponents();
        addListeners();
    }

    private void initComponents() {
        // Компоненты будут созданы в layoutComponents
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
        JPanel infoPanel = new JPanel(new BorderLayout());
        infoPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        infoPanel.setBackground(new Color(240, 240, 240));

        JLabel titleLabel = new JLabel("🔧 Аргументы командной строки", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(new Color(70, 130, 180));

        // В графическом интерфейсе аргументы командной строки недоступны
        JTextArea infoArea = new JTextArea();
        infoArea.setEditable(false);
        infoArea.setFont(new Font("Arial", Font.PLAIN, 14));
        infoArea.setBackground(new Color(248, 249, 250));
        infoArea.setLineWrap(true);
        infoArea.setWrapStyleWord(true);

        String infoText = "В графическом интерфейсе аргументы командной строки недоступны.\n\n" +
                "📝 Информация о задании:\n" +
                "• В консольной версии программа выводила аргументы, переданные при запуске\n" +
                "• Например: java Main аргумент1 аргумент2\n" +
                "• Программа показывала все переданные аргументы по порядку\n\n" +
                "💡 Для тестирования этой функции нужно запустить программу из командной строки";

        infoArea.setText(infoText);
        infoArea.setCaretPosition(0);

        JScrollPane scrollPane = new JScrollPane(infoArea);

        infoPanel.add(titleLabel, BorderLayout.NORTH);
        infoPanel.add(scrollPane, BorderLayout.CENTER);

        return infoPanel;
    }

    private JPanel createBottomPanel() {
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(new Color(240, 240, 240));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton backButton = createBackButton();
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftPanel.setBackground(new Color(240, 240, 240));
        leftPanel.add(backButton);

        JLabel infoLabel = new JLabel("Аргументы доступны только в консольном режиме");
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

    public static void task3(JFrame mainMenuFrame) {
        if (mainMenuFrame != null) {
            mainMenuFrame.setVisible(false);
        }

        SwingUtilities.invokeLater(() -> new Task3(mainMenuFrame).setVisible(true));
    }
}