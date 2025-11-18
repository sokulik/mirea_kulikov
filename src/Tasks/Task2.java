package Tasks;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;
import java.io.StringReader;
import static DOP.DOP_FUNC.showMessageAndReturn;

public class Task2 extends JFrame {
    private JTextArea outputArea;
    private JTextField inputField;
    private JButton testButton, backButton;

    public Task2(JFrame mainMenuFrame) {
        super("Исключения - Задание 2");
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

        inputField = new JTextField(20);
        inputField.setText("Qwerty");

        testButton = new JButton("Протестировать ввод");
        backButton = new JButton("В главное меню");

        backButton.setBackground(new Color(70, 130, 180));
        backButton.setForeground(Color.WHITE);
    }

    private void layoutComponents() {
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Введите данные для теста:"));
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
                testInput();
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

    private void testInput() {
        String input = inputField.getText().trim();
        outputArea.setText("Тестирование ввода: '" + input + "'\n\n");

        outputArea.append("Без обработки исключений:\n");
        outputArea.append("-------------------------\n");

        try {
            Scanner myScanner = new Scanner(new StringReader(input));
            String intString = myScanner.next();
            outputArea.append("Введенная строка: " + intString + "\n");

            int i = Integer.parseInt(intString);
            outputArea.append("Преобразование в int: успешно, значение = " + i + "\n");

            int result = 2 / i;
            outputArea.append("Результат 2 / " + i + " = " + result + "\n");

        } catch (Exception e) {
            outputArea.append("Возникло исключение: " + e.toString() + "\n");
            outputArea.append("Тип исключения: " + e.getClass().getSimpleName() + "\n");
        }

        outputArea.append("\nС обработкой исключений:\n");
        outputArea.append("------------------------\n");

        testWithExceptionHandling(input);
    }

    private void testWithExceptionHandling(String input) {
        try {
            Scanner myScanner = new Scanner(new StringReader(input));
            String intString = myScanner.next();
            outputArea.append("Введенная строка: " + intString + "\n");

            int i = Integer.parseInt(intString);
            outputArea.append("Преобразование в int: успешно, значение = " + i + "\n");

            int result = 2 / i;
            outputArea.append("Результат 2 / " + i + " = " + result + "\n");

        } catch (NumberFormatException e) {
            outputArea.append("Ошибка преобразования: '" + input + "' не является числом\n");
        } catch (ArithmeticException e) {
            outputArea.append("Ошибка деления: попытка деления на ноль\n");
        } catch (Exception e) {
            outputArea.append("Другая ошибка: " + e.toString() + "\n");
        }
    }

    public static void task2(JFrame mainMenuFrame) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Task2(mainMenuFrame).setVisible(true);
            }
        });
    }
}