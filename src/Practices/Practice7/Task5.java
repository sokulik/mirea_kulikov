package Practices.Practice7;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Task5 extends JFrame {
    private JFrame mainMenuFrame;
    private JTextArea resultArea;
    private static final int COUNT = 10000000;

    public Task5(JFrame mainMenuFrame) {
        super("Задание 5: Сравнение ArrayList vs LinkedList");
        this.mainMenuFrame = mainMenuFrame;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);

        initComponents();
        layoutComponents();
        addListeners();
    }

    private void initComponents() {
        resultArea = new JTextArea(15, 50);
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Consolas", Font.PLAIN, 12));
        resultArea.setBackground(new Color(248, 249, 250));
    }

    private void layoutComponents() {
        setLayout(new BorderLayout(10, 10));

        // Верхняя панель с кнопкой запуска
        JPanel controlPanel = createControlPanel();
        add(controlPanel, BorderLayout.NORTH);

        // Центральная панель с результатами
        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Результаты тестирования"));
        add(scrollPane, BorderLayout.CENTER);

        // Панель снизу с кнопкой назад
        JPanel bottomPanel = createBottomPanel();
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private JPanel createControlPanel() {
        JPanel controlPanel = new JPanel(new BorderLayout());
        controlPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        controlPanel.setBackground(new Color(240, 240, 240));

        JLabel titleLabel = new JLabel("Сравнение производительности ArrayList и LinkedList", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        titleLabel.setForeground(new Color(70, 130, 180));

        JButton runTestButton = new JButton("🚀 Запустить тест");
        runTestButton.setFont(new Font("Arial", Font.BOLD, 14));
        runTestButton.setBackground(new Color(40, 167, 69));
        runTestButton.setForeground(Color.WHITE);
        runTestButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(30, 130, 50), 2),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
        runTestButton.addActionListener(e -> runPerformanceTest());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(240, 240, 240));
        buttonPanel.add(runTestButton);

        controlPanel.add(titleLabel, BorderLayout.CENTER);
        controlPanel.add(buttonPanel, BorderLayout.SOUTH);

        return controlPanel;
    }

    private JPanel createBottomPanel() {
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(new Color(240, 240, 240));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton backButton = createBackButton();
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftPanel.setBackground(new Color(240, 240, 240));
        leftPanel.add(backButton);

        JLabel infoLabel = new JLabel("Количество элементов: " + String.format("%,d", COUNT));
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

    private void runPerformanceTest() {
        resultArea.setText("🔄 Выполнение теста...\n\n");

        // Запускаем в отдельном потоке чтобы не блокировать GUI
        SwingWorker<Void, String> worker = new SwingWorker<Void, String>() {
            @Override
            protected Void doInBackground() throws Exception {
                publish("Сравнение ArrayList vs LinkedList\n");
                publish("Количество элементов: " + String.format("%,d", COUNT) + "\n");
                publish("═".repeat(50) + "\n\n");

                // Тест ArrayList
                publish("📊 Тестирование ArrayList...\n");
                List<Integer> arrayList = new ArrayList<>();
                long start = System.currentTimeMillis();

                for (int i = 0; i < COUNT; i++) {
                    arrayList.add(i);
                }

                for (int i = 0; i < 100; i++) {
                    arrayList.add(0, i); // addFirst для ArrayList
                }

                for (int i = 0; i < 100; i++) {
                    arrayList.remove(0); // removeFirst для ArrayList
                }

                long arrayListTime = System.currentTimeMillis() - start;
                publish("✅ ArrayList завершен: " + arrayListTime + " мс\n\n");

                // Тест LinkedList
                publish("📊 Тестирование LinkedList...\n");
                List<Integer> linkedList = new LinkedList<>();
                start = System.currentTimeMillis();

                for (int i = 0; i < COUNT; i++) {
                    linkedList.add(i);
                }

                for (int i = 0; i < 100; i++) {
                    linkedList.add(0, i); // addFirst для LinkedList
                }

                for (int i = 0; i < 100; i++) {
                    linkedList.remove(0); // removeFirst для LinkedList
                }

                long linkedListTime = System.currentTimeMillis() - start;
                publish("✅ LinkedList завершен: " + linkedListTime + " мс\n\n");

                // Результаты
                publish("🎯 Результаты:\n");
                publish("═".repeat(30) + "\n");
                publish(String.format("ArrayList:  %,d мс\n", arrayListTime));
                publish(String.format("LinkedList: %,d мс\n", linkedListTime));
                publish("\n");

                if (arrayListTime < linkedListTime) {
                    double percentage = (linkedListTime - arrayListTime) * 100.0 / linkedListTime;
                    publish(String.format("🏆 ArrayList быстрее на %.2f%%\n", percentage));
                } else {
                    double percentage = (arrayListTime - linkedListTime) * 100.0 / arrayListTime;
                    publish(String.format("🏆 LinkedList быстрее на %.2f%%\n", percentage));
                }

                publish("\n💡 Вывод: " + (arrayListTime < linkedListTime ?
                        "ArrayList эффективнее для данной операции" :
                        "LinkedList эффективнее для данной операции"));

                return null;
            }

            @Override
            protected void process(java.util.List<String> chunks) {
                for (String chunk : chunks) {
                    resultArea.append(chunk);
                }
            }
        };

        worker.execute();
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

    public static void task5(JFrame mainMenuFrame) {
        if (mainMenuFrame != null) {
            mainMenuFrame.setVisible(false);
        }

        SwingUtilities.invokeLater(() -> new Task5(mainMenuFrame).setVisible(true));
    }
}