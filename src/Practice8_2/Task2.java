package Practice8_2;

import Practice8_2.Classes.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Task2 extends JFrame {
    private static JFrame mainMenu;
    private List<Practice8_2.Classes.Shape> shapes;
    private JPanel drawingPanel;
    private JButton generateButton;

    public Task2(JFrame mainMenu) {
        this.mainMenu = mainMenu;
        this.shapes = new ArrayList<>();
        initializeFrame();
        setupComponents();
        generateInitialShapes();
    }

    private void initializeFrame() {
        setTitle("Задание 2: 20 случайных фигур");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                returnToMainMenu();
            }
        });
    }

    private void setupComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(240, 240, 240));
        topPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JButton backButton = createBackButton();
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftPanel.setBackground(new Color(240, 240, 240));
        leftPanel.add(backButton);

        generateButton = createGenerateButton();
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightPanel.setBackground(new Color(240, 240, 240));
        rightPanel.add(generateButton);

        topPanel.add(leftPanel, BorderLayout.WEST);
        topPanel.add(rightPanel, BorderLayout.EAST);

        mainPanel.add(topPanel, BorderLayout.NORTH);

        drawingPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (Practice8_2.Classes.Shape shape : shapes) {
                    shape.draw(g);
                }
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(800, 600);
            }
        };
        drawingPanel.setBackground(Color.WHITE);

        mainPanel.add(drawingPanel, BorderLayout.CENTER);
        add(mainPanel);
    }

    private void generateShapes(int count) {
        shapes.clear();
        for (int i = 0; i < count; i++) {
            shapes.add(ShapeFactory.createRandomShape(drawingPanel.getWidth(), drawingPanel.getHeight()));
        }
        drawingPanel.repaint();
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

    private JButton createGenerateButton() {
        JButton button = new JButton("Перегенерировать фигурки!");
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(50, 100, 150), 2),
                BorderFactory.createEmptyBorder(8, 15, 8, 15)
        ));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(100, 150, 200));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(70, 130, 180));
            }
        });

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                regenerateShapes();
            }
        });

        return button;
    }

    private void regenerateShapes() {
        generateShapes(20);

        generateButton.setText("Генерация..");
        generateButton.setBackground(new Color(100, 150, 200));

        Timer timer = new Timer(300, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateButton.setText("Перегенерировать фигурки!");
                generateButton.setBackground(new Color(70, 130, 180));
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    private void generateInitialShapes() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                generateShapes(20);
            }
        });
    }

    private void returnToMainMenu() {
        dispose();
        if (mainMenu != null) {
            mainMenu.setVisible(true);
        }
    }

    public static void start(JFrame mainMenu) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Task2(mainMenu).setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        start(null);
    }
}
