package Tasks;

import javax.swing.*;
import java.awt.*;


public class Task5 extends JFrame{
    private JTextField inputField;
    private JTextArea outputArea;
    private JButton generateButton;
    private JButton backButton;

    public Task5(JFrame mainMenuFrame){
        setTitle("Сумма цифр N");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        initComponents();
        layoutComponents();
        addListeners(mainMenuFrame);

        setIconImage(new ImageIcon("/src/resources/IconPR.jpg").getImage());
    }

    private void initComponents(){
        inputField = new JTextField(10);
        outputArea = new JTextArea(15, 30);
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        generateButton = new JButton("Генерировать");
        backButton = new JButton("В главное меню");
    }

    private void layoutComponents(){
        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Введите N: "));
        topPanel.add(inputField);
        topPanel.add(generateButton);

        JScrollPane scrollPane = new JScrollPane(outputArea);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(backButton);

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10,10, 10));
        scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 10,10));

    }

    private void addListeners(JFrame mainMenuFrame) {
        generateButton.addActionListener(e -> execution());
        inputField.addActionListener(e -> execution());
        backButton.addActionListener( e -> {
            dispose();
            mainMenuFrame.setVisible(true);
        });
    }

    private void execution(){
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

    public static void task5(JFrame mainMenuFrame) {
        SwingUtilities.invokeLater(() -> new Task5(mainMenuFrame).setVisible(true));
    }
}


