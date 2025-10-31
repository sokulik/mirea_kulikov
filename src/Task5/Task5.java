package Task5;

import javax.swing.*;
import java.awt.*;


public class Task5 extends JFrame{
    private JTextField inputField;
    private JTextArea outputArea;
    private JButton generateButton;

    public Task5(){
        setTitle("Сумма цифр N");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        initComponents();
        layoutComponents();
        addListeners();

        setIconImage(new ImageIcon("/src/resources/IconPR.jpg").getImage());
    }

    private void initComponents(){
        inputField = new JTextField(10);
        outputArea = new JTextArea(15, 30);
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        generateButton = new JButton("Генерировать");
    }

    private void layoutComponents(){
        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Введите N: "));
        topPanel.add(inputField);
        topPanel.add(generateButton);

        JScrollPane scrollPane = new JScrollPane(outputArea);
        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10,10, 10));
        scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 10,10));

    }

    private void addListeners() {
        generateButton.addActionListener(e -> generateSequence());
        inputField.addActionListener(e -> generateSequence());
    }

    private void generateSequence(){
        try{
            long N = Integer.parseInt(inputField.getText().trim());

            if (N <= 0 ){
                JOptionPane.showMessageDialog(this,
                        "Введите положительное число!",
                        "Ошибка",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

                long totalSum = calculateDigitSum(N);


            outputArea.setText("Сумма цифр числа N: "+totalSum+"\n");
        }catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Введите корректное число!",
                    "Ошибка",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    private long calculateDigitSum(long n) {
        n = Math.abs(n);
        if (n < 10) {
            return n;
        } else {
            return n % 10 + calculateDigitSum(n / 10);
        }
    }

    public static void task5() {
        SwingUtilities.invokeLater(() -> new Task5().setVisible(true));
    }
}


