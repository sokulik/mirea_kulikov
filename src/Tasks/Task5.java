package Tasks;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import static DOP.DOP_FUNC.showMessageAndReturn;

public class Task5 extends JFrame {
    private JTextArea outputArea;
    private JTextField inputField;
    private JButton testButton, backButton;

    public Task5(JFrame mainMenuFrame) {
        super("Исключения - Задание 5");
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

        testButton = new JButton("Протестировать getDetails");
        backButton = new JButton("В главное меню");

        backButton.setBackground(new Color(70, 130, 180));
        backButton.setForeground(Color.WHITE);
    }

    private void layoutComponents() {
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Введите ключ (null для теста):"));
        inputField.setText("test");
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
                testGetDetails();
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

    private void testGetDetails() {
        String input = inputField.getText().trim();
        if (input.equalsIgnoreCase("null")) {
            input = null;
        }

        outputArea.setText("Тестирование метода getDetails с ключом: " + input + "\n\n");

        outputArea.append("Шаг 1: Без обработки исключений\n");
        outputArea.append("------------------------------\n");

        try {
            ThrowsDemo demo = new ThrowsDemo();
            String result = demo.getDetails(input);
            outputArea.append("Результат: " + result + "\n");
        } catch (NullPointerException e) {
            outputArea.append("Поймано NullPointerException: " + e.getMessage() + "\n");
        }

        outputArea.append("\nШаг 2: С обработкой исключений внутри метода\n");
        outputArea.append("--------------------------------------------\n");

        ThrowsDemoWithTryCatch demo2 = new ThrowsDemoWithTryCatch();
        String result2 = demo2.getDetails(input);
        outputArea.append("Результат: " + result2 + "\n");

        outputArea.append("\nОбъяснение: Лучше обрабатывать исключения в методе, " +
                "где они возникают, а не пробрасывать их вызывающему коду\n");
    }

    class ThrowsDemo {
        public String getDetails(String key) {
            if (key == null) {
                throw new NullPointerException("null key in getDetails");
            }
            return "data for " + key;
        }
    }

    class ThrowsDemoWithTryCatch {
        public String getDetails(String key) {
            try {
                if (key == null) {
                    throw new NullPointerException("null key in getDetails");
                }
                return "data for " + key;
            } catch (NullPointerException e) {
                return "Ошибка: ключ не может быть null";
            }
        }
    }

    public static void task5(JFrame mainMenuFrame) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Task5(mainMenuFrame).setVisible(true);
            }
        });
    }
}