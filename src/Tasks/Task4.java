package Tasks;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import static DOP.DOP_FUNC.showMessageAndReturn;

public class Task4 extends JFrame {
    private JTextField display;
    private JButton backButton;
    private double firstNumber = 0;
    private String operator = "";
    private boolean startNewNumber = true;

    public Task4(JFrame mainMenuFrame) {
        super("Графический калькулятор - Задание 4");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 450);
        setLocationRelativeTo(null);
        setResizable(false);

        initComponents();
        layoutComponents();
        addListeners(mainMenuFrame);
    }

    private void initComponents() {
        display = new JTextField();
        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setFont(new Font("Arial", Font.BOLD, 20));
        display.setText("0");

        backButton = new JButton("В главное меню");
        backButton.setBackground(new Color(70, 130, 180));
        backButton.setForeground(Color.WHITE);
    }

    private void layoutComponents() {
        setLayout(new BorderLayout());

        // Панель дисплея
        JPanel displayPanel = new JPanel(new BorderLayout());
        displayPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        displayPanel.add(display, BorderLayout.CENTER);

        // Панель кнопок калькулятора
        JPanel buttonPanel = new JPanel(new GridLayout(5, 4, 5, 5));

        String[] buttons = {
                "C", "±", "%", "/",
                "7", "8", "9", "*",
                "4", "5", "6", "-",
                "1", "2", "3", "+",
                "", "0", ".", "="
        };

        for (String text : buttons) {
            if (text.isEmpty()) {
                buttonPanel.add(new JLabel(""));
            } else {
                JButton button = new JButton(text);
                button.setFont(new Font("Arial", Font.BOLD, 16));

                if (text.matches("[0-9.]")) {
                    button.setBackground(Color.WHITE);
                } else if (text.equals("=")) {
                    button.setBackground(new Color(255, 165, 0));
                    button.setForeground(Color.WHITE);
                } else {
                    button.setBackground(new Color(200, 200, 200));
                }

                button.setBorder(BorderFactory.createRaisedBevelBorder());
                buttonPanel.add(button);
            }
        }

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        bottomPanel.add(backButton);

        add(displayPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void addListeners(final JFrame mainMenuFrame) {
        Component[] components = ((JPanel) getContentPane().getComponent(1)).getComponents();

        for (Component comp : components) {
            if (comp instanceof JButton) {
                JButton button = (JButton) comp;
                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        handleButtonClick(button.getText());
                    }
                });
            }
        }

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

    private void handleButtonClick(String buttonText) {
        switch (buttonText) {
            case "0": case "1": case "2": case "3": case "4":
            case "5": case "6": case "7": case "8": case "9":
                if (startNewNumber || display.getText().equals("0")) {
                    display.setText(buttonText);
                    startNewNumber = false;
                } else {
                    display.setText(display.getText() + buttonText);
                }
                break;

            case ".":
                if (startNewNumber) {
                    display.setText("0.");
                    startNewNumber = false;
                } else if (!display.getText().contains(".")) {
                    display.setText(display.getText() + ".");
                }
                break;

            case "+": case "-": case "*": case "/":
                if (!operator.isEmpty() && !startNewNumber) {
                    calculate();
                }
                firstNumber = Double.parseDouble(display.getText());
                operator = buttonText;
                startNewNumber = true;
                break;

            case "=":
                if (!operator.isEmpty()) {
                    calculate();
                    operator = "";
                }
                break;

            case "C":
                display.setText("0");
                firstNumber = 0;
                operator = "";
                startNewNumber = true;
                break;

            case "±":
                double value = Double.parseDouble(display.getText());
                display.setText(String.valueOf(-value));
                break;

            case "%":
                value = Double.parseDouble(display.getText());
                display.setText(String.valueOf(value / 100));
                startNewNumber = true;
                break;
        }
    }

    private void calculate() {
        double secondNumber = Double.parseDouble(display.getText());
        double result = 0;

        switch (operator) {
            case "+":
                result = firstNumber + secondNumber;
                break;
            case "-":
                result = firstNumber - secondNumber;
                break;
            case "*":
                result = firstNumber * secondNumber;
                break;
            case "/":
                if (secondNumber != 0) {
                    result = firstNumber / secondNumber;
                } else {
                    showMessageAndReturn(this, "Деление на ноль невозможно!");
                    display.setText("Ошибка");
                    startNewNumber = true;
                    return;
                }
                break;
        }

        if (result == (long) result) {
            display.setText(String.format("%d", (long) result));
        } else {
            display.setText(String.format("%s", result));
        }

        startNewNumber = true;
    }

    public static void task4(JFrame mainMenuFrame) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Task4(mainMenuFrame).setVisible(true);
            }
        });
    }
}