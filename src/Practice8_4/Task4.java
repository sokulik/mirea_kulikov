package Practice8_4;

import Practice8_4.Classes.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Task4 extends JFrame {
    private static JFrame mainMenu;
    private AnimationPanel animationPanel;
    private ControlPanel controlPanel;

    public Task4(JFrame mainMenu) {
        Task4.mainMenu = mainMenu;
        try {
            initializeFrame();
            setupComponents();
            startAnimation();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this,
                    "Ошибка загрузки анимационных кадров: " + e.getMessage() +
                            "\nУбедитесь, что файлы Anim1.png, Anim2.png, Anim3.png находятся в папке resources",
                    "Ошибка",
                    JOptionPane.ERROR_MESSAGE);
            returnToMainMenu();
        }
    }

    private void initializeFrame() {
        setTitle("Задание 4: Анимация из 6 кадров");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1200, 1500);
        setLocationRelativeTo(null);
        setResizable(true);

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                returnToMainMenu();
            }
        });
    }

    private void setupComponents() throws IOException {
        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel topPanel = createTopPanel();
        mainPanel.add(topPanel, BorderLayout.NORTH);

        animationPanel = new AnimationPanel();
        mainPanel.add(animationPanel, BorderLayout.CENTER);

        controlPanel = new ControlPanel(animationPanel);
        mainPanel.add(controlPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private JPanel createTopPanel() {
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(240, 240, 240));
        topPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JButton backButton = createBackButton();
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftPanel.setBackground(new Color(240, 240, 240));
        leftPanel.add(backButton);

        JLabel titleLabel = new JLabel("Анимация из 6 кадров", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setForeground(new Color(70, 130, 180));

        topPanel.add(leftPanel, BorderLayout.WEST);
        topPanel.add(titleLabel, BorderLayout.CENTER);

        return topPanel;
    }

    private JButton createBackButton() {
        JButton backButton = new JButton("← Назад к меню");
        backButton.setFont(new Font("Arial", Font.PLAIN, 12));
        backButton.setBackground(new Color(200, 200, 200));
        backButton.setFocusPainted(false);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                returnToMainMenu();
            }
        });

        return backButton;
    }

    private void startAnimation() {
        Timer timer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                animationPanel.startAnimation();
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    private void returnToMainMenu() {
        if (animationPanel != null) {
            animationPanel.stopAnimation();
        }

        dispose();
        if (mainMenu != null) {
            mainMenu.setVisible(true);
        }
    }

    public static void start(JFrame mainMenu) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Task4(mainMenu).setVisible(true);
            }
        });
    }
}