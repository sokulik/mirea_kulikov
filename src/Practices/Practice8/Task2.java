package Practices.Practice8;

import Practices.Practice8.Classes.Task2.*;
import Practices.Practice8.Classes.Task2.Shape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class Task2 extends JFrame {
    private List<Shape> shapes;
    private JPanel drawingPanel;
    private JButton generateButton;
    private JFrame mainMenuFrame;

    public Task2(JFrame mainMenuFrame) {
        super("Задание 2: 20 случайных фигур");
        this.mainMenuFrame = mainMenuFrame;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        this.shapes = new ArrayList<>();
        initComponents();
        layoutComponents();
        addListeners();
        generateInitialShapes();
    }

    private void initComponents() {
        drawingPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (Shape shape : shapes) {
                    shape.draw(g);
                }
            }
        };
        drawingPanel.setBackground(Color.WHITE);
    }

    private void layoutComponents() {
        setLayout(new BorderLayout(10, 10));

        // Верхняя панель с кнопкой генерации
        JPanel topPanel = createTopPanel();
        add(topPanel, BorderLayout.NORTH);

        // Панель рисования по центру
        add(drawingPanel, BorderLayout.CENTER);

        // Панель снизу с кнопкой назад
        JPanel bottomPanel = createBottomPanel();
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private JPanel createTopPanel() {
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(240, 240, 240));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Заголовок по центру
        JLabel titleLabel = new JLabel("20 случайных фигур", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setForeground(new Color(70, 130, 180));
        topPanel.add(titleLabel, BorderLayout.CENTER);

        // Кнопка генерации справа
        generateButton = createGenerateButton();
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightPanel.setBackground(new Color(240, 240, 240));
        rightPanel.add(generateButton);
        topPanel.add(rightPanel, BorderLayout.EAST);

        return topPanel;
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

        // Информация справа
        JLabel infoLabel = new JLabel("Фигуры: Круги, Прямоугольники, Квадраты, Треугольники");
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

        // Добавляем эффект при наведении
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

    private JButton createGenerateButton() {
        JButton button = new JButton("🔄 Перегенерировать фигуры");
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

    private void addListeners() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                returnToMainMenu();
            }
        });
    }

    private void returnToMainMenu() {
        System.out.println("Возврат в главное меню из Task2...");

        // Закрываем это окно
        dispose();

        // Показываем главное меню
        if (mainMenuFrame != null) {
            System.out.println("Показываем главное меню");
            mainMenuFrame.setVisible(true);
            mainMenuFrame.toFront();
        } else {
            System.out.println("mainMenuFrame is null!");
        }
    }

    private void generateShapes(int count) {
        shapes.clear();
        for (int i = 0; i < count; i++) {
            // Получаем размеры панели для ограничения координат
            int width = drawingPanel.getWidth() > 0 ? drawingPanel.getWidth() : 700;
            int height = drawingPanel.getHeight() > 0 ? drawingPanel.getHeight() : 450;
            Shape shape = ShapeFactory.createRandomShape(width, height);
            if (shape != null) {
                shapes.add(shape);
            }
        }
        drawingPanel.repaint();
    }

    private void regenerateShapes() {
        generateShapes(20);

        // Визуальная обратная связь при нажатии
        generateButton.setText("🔄 Генерация...");
        generateButton.setBackground(new Color(100, 150, 200));

        Timer timer = new Timer(300, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateButton.setText("🔄 Перегенерировать фигуры");
                generateButton.setBackground(new Color(70, 130, 180));
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    private void generateInitialShapes() {
        // Ждем пока панель отрисуется, затем генерируем фигуры
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                generateShapes(20);
            }
        });
    }

    public static void task2(JFrame mainMenuFrame) {
        // Скрываем главное меню перед показом окна с фигурами
        if (mainMenuFrame != null) {
            mainMenuFrame.setVisible(false);
        }

        SwingUtilities.invokeLater(() -> {
            Task2 task2 = new Task2(mainMenuFrame);
            task2.setVisible(true);
        });
    }
}