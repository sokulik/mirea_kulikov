package Tasks;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;
import java.io.StringReader;
import static DOP.DOP_FUNC.showMessageAndReturn;

public class Task7 extends JFrame {
    private JTextArea outputArea;
    private JButton testButton, backButton;

    public Task7(JFrame mainMenuFrame) {
        super("Исключения - Задание 7");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);

        initComponents();
        layoutComponents();
        addListeners(mainMenuFrame);
    }

    private void initComponents() {
        outputArea = new JTextArea(20, 50);
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 12));

        testButton = new JButton("Протестировать цепочку вызовов");
        backButton = new JButton("В главное меню");

        backButton.setBackground(new Color(70, 130, 180));
        backButton.setForeground(Color.WHITE);
    }

    private void layoutComponents() {
        setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(testButton);

        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Вывод программы"));

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.add(backButton);

        add(buttonPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        ((JComponent) getContentPane()).setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    private void addListeners(final JFrame mainMenuFrame) {
        testButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                testExceptionChain();
            }
        });

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                mainMenuFrame.setVisible(true);
            }
        });

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                mainMenuFrame.setVisible(true);
            }
        });
    }

    private void testExceptionChain() {
        outputArea.setText("Тестирование цепочки вызовов исключений\n\n");

        outputArea.append("Проблема: Unreported exception Exception\n");
        outputArea.append("Метод getDetails() бросает Exception, но не объявляет его в сигнатуре\n\n");

        outputArea.append("Решение 1: Добавить throws в сигнатуры методов\n");
        outputArea.append("----------------------------------------------\n");

        try {
            ThrowsDemoWithThrows demo = new ThrowsDemoWithThrows();
            demo.getKey();
        } catch (Exception e) {
            outputArea.append("Поймано исключение в main: " + e.getMessage() + "\n");
        }

        outputArea.append("\nРешение 2: Обработать исключение в одном из методов\n");
        outputArea.append("---------------------------------------------------\n");

        ThrowsDemoWithHandling demo2 = new ThrowsDemoWithHandling();
        demo2.getKey();

        outputArea.append("\nОбъяснение: Исключения должны быть либо обработаны (try-catch), " +
                "либо объявлены в сигнатуре метода (throws)\n");
    }

    class ThrowsDemoWithThrows {
        public void getKey() throws Exception {
            Scanner myScanner = new Scanner(new StringReader("")); // пустая строка для теста
            String key = myScanner.next();
            printDetails(key);
        }

        public void printDetails(String key) throws Exception {
            String message = getDetails(key);
            outputArea.append("Сообщение: " + message + "\n");
        }

        public String getDetails(String key) throws Exception {
            if (key.equals("")) {
                throw new Exception("Key set to empty string");
            }
            return "data for " + key;
        }
    }

    class ThrowsDemoWithHandling {
        public void getKey() {
            try {
                Scanner myScanner = new Scanner(new StringReader("")); // пустая строка для теста
                String key = myScanner.next();
                printDetails(key);
            } catch (Exception e) {
                outputArea.append("Обработано в getKey(): " + e.getMessage() + "\n");
            }
        }

        public void printDetails(String key) {
            try {
                String message = getDetails(key);
                outputArea.append("Сообщение: " + message + "\n");
            } catch (Exception e) {
                outputArea.append("Обработано в printDetails(): " + e.getMessage() + "\n");
            }
        }

        public String getDetails(String key) throws Exception {
            if (key.equals("")) {
                throw new Exception("Key set to empty string");
            }
            return "data for " + key;
        }
    }

    public static void task7(JFrame mainMenuFrame) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Task7(mainMenuFrame).setVisible(true);
            }
        });
    }
}