package Tasks;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;
import java.io.StringReader;
import static DOP.DOP_FUNC.showMessageAndReturn;

public class Task8 extends JFrame {
    private JTextArea outputArea;
    private JTextField inputField;
    private JButton testButton, backButton;

    public Task8(JFrame mainMenuFrame) {
        super("Исключения - Задание 8");
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

        inputField = new JTextField(15);
        inputField.setText("test");

        testButton = new JButton("Протестировать с циклом ввода");
        backButton = new JButton("В главное меню");

        backButton.setBackground(new Color(70, 130, 180));
        backButton.setForeground(Color.WHITE);
    }

    private void layoutComponents() {
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Введите начальный ключ:"));
        inputPanel.add(inputField);
        inputPanel.add(testButton);

        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Вывод программы"));

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.add(backButton);

        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        ((JComponent) getContentPane()).setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    private void addListeners(final JFrame mainMenuFrame) {
        testButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                testWithLoop();
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

    private void testWithLoop() {
        String initialInput = inputField.getText().trim();
        outputArea.setText("Тестирование с циклом ввода\n\n");

        ImprovedThrowsDemo demo = new ImprovedThrowsDemo();
        demo.getKey(initialInput);
    }

    class ImprovedThrowsDemo {
        public void getKey(String initialInput) {
            boolean validInput = false;
            int attempts = 0;
            String currentInput = initialInput;

            String[] testInputs = {initialInput, "", "validKey", "anotherKey"};

            while (!validInput && attempts < 3) {
                outputArea.append("Попытка " + (attempts + 1) + ": ввод = '" + currentInput + "'\n");

                try {
                    printDetails(currentInput);
                    validInput = true;
                    outputArea.append("Успех! Ключ принят: " + currentInput + "\n");
                } catch (Exception e) {
                    outputArea.append("Ошибка: " + e.getMessage() + "\n");
                    outputArea.append("Пожалуйста, введите другой ключ\n\n");

                    attempts++;
                    if (attempts < testInputs.length) {
                        currentInput = testInputs[attempts];
                    }
                }
            }

            if (!validInput) {
                outputArea.append("\nПревышено максимальное количество попыток. Программа завершена.\n");
            }
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

    public static void task8(JFrame mainMenuFrame) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Task8(mainMenuFrame).setVisible(true);
            }
        });
    }
}