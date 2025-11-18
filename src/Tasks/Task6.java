package Tasks;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import static DOP.DOP_FUNC.showMessageAndReturn;

public class Task6 extends JFrame {
    private JTextArea outputArea;
    private JTextField inputField;
    private JButton testButton, backButton;

    public Task6(JFrame mainMenuFrame) {
        super("Исключения - Задание 6");
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

        testButton = new JButton("Протестировать printMessage");
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
                testPrintMessage();
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

    private void testPrintMessage() {
        String input = inputField.getText().trim();
        if (input.equalsIgnoreCase("null")) {
            input = null;
        }

        outputArea.setText("Тестирование метода printMessage с ключом: " + input + "\n\n");

        outputArea.append("Без обработки исключений:\n");
        outputArea.append("-------------------------\n");

        try {
            ThrowsDemo demo = new ThrowsDemo();
            demo.printMessage(input);
        } catch (Exception e) {
            outputArea.append("Поймано исключение: " + e.toString() + "\n");
        }

        outputArea.append("\nС обработкой исключений:\n");
        outputArea.append("-----------------------\n");

        ThrowsDemoWithHandling demo2 = new ThrowsDemoWithHandling();
        demo2.printMessage(input);

        outputArea.append("\nПрограмма продолжает работу после обработки исключений\n");
    }

    class ThrowsDemo {
        public void printMessage(String key) {
            String message = getDetails(key);
            outputArea.append("Сообщение: " + message + "\n");
        }

        public String getDetails(String key) {
            if (key == null) {
                throw new NullPointerException("null key in getDetails");
            }
            return "data for " + key;
        }
    }

    class ThrowsDemoWithHandling {
        public void printMessage(String key) {
            try {
                String message = getDetails(key);
                outputArea.append("Сообщение: " + message + "\n");
            } catch (NullPointerException e) {
                outputArea.append("Ошибка в printMessage: ключ не может быть null\n");
            }
        }

        public String getDetails(String key) {
            if (key == null) {
                throw new NullPointerException("null key in getDetails");
            }
            return "data for " + key;
        }
    }

    public static void task6(JFrame mainMenuFrame) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Task6(mainMenuFrame).setVisible(true);
            }
        });
    }
}