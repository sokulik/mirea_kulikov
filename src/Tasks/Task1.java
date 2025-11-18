package Tasks;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import static DOP.DOP_FUNC.showMessageAndReturn;

public class Task1 extends JFrame {
    private JTextArea outputArea;
    private JButton step1Button, step2Button, step3Button, backButton;

    public Task1(JFrame mainMenuFrame) {
        super("Исключения - Задание 1");
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

        step1Button = new JButton("Шаг 1: Деление на ноль (int)");
        step2Button = new JButton("Шаг 2: Деление на ноль (double)");
        step3Button = new JButton("Шаг 3: Try-Catch блок");
        backButton = new JButton("В главное меню");

        backButton.setBackground(new Color(70, 130, 180));
        backButton.setForeground(Color.WHITE);
    }

    private void layoutComponents() {
        setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 5, 5));
        buttonPanel.add(step1Button);
        buttonPanel.add(step2Button);
        buttonPanel.add(step3Button);

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
        step1Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                step1Demo();
            }
        });

        step2Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                step2Demo();
            }
        });

        step3Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                step3Demo();
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

    private void step1Demo() {
        outputArea.setText("Шаг 1: Деление целых чисел 2 / 0\n");
        outputArea.append("Результат:\n");

        try {
            int result = 2 / 0;
            outputArea.append("Результат: " + result + "\n");
        } catch (ArithmeticException e) {
            outputArea.append("Поймано исключение: " + e.toString() + "\n");
            outputArea.append("Сообщение: " + e.getMessage() + "\n");
        }

        outputArea.append("\nОбъяснение: При делении целых чисел на ноль " +
                "возникает ArithmeticException: / by zero\n");
    }

    private void step2Demo() {
        outputArea.setText("Шаг 2: Деление чисел с плавающей точкой 2.0 / 0.0\n");
        outputArea.append("Результат:\n");

        try {
            double result = 2.0 / 0.0;
            outputArea.append("Результат: " + result + "\n");
            outputArea.append("Это Infinity (бесконечность)\n");
        } catch (Exception e) {
            outputArea.append("Поймано исключение: " + e.toString() + "\n");
        }

        outputArea.append("\nОбъяснение: Для чисел с плавающей точкой деление на ноль " +
                "дает Infinity, а не исключение\n");
    }

    private void step3Demo() {
        outputArea.setText("Шаг 3: Try-Catch блок для обработки исключения\n");
        outputArea.append("Результат:\n");

        try {
            outputArea.append("Пытаемся выполнить: 2 / 0\n");
            int result = 2 / 0;
            outputArea.append("Результат: " + result + "\n");
        } catch (ArithmeticException e) {
            outputArea.append("Поймано ArithmeticException!\n");
            outputArea.append("Сообщение: Attempted division by zero\n");
        }

        outputArea.append("\nОбъяснение: Блок try-catch перехватывает исключение " +
                "и позволяет программе продолжить работу\n");
    }

    public static void task1(JFrame mainMenuFrame) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Task1(mainMenuFrame).setVisible(true);
            }
        });
    }
}