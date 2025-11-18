package Practices.Practice11;


import javax.swing.*;
import java.awt.*;

public class Task4 extends JFrame {
    private JTextField inputK;
    private JTextField inputS;
    private JTextArea outputArea;
    private JButton generateButton;
    private JButton backButton;

    public Task4(JFrame mainMenuFrame){
        setTitle("K&S");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);

        initComponents();
        layoutComponents();
        addListeners(mainMenuFrame);

        setIconImage(new ImageIcon("/src/resources/IconPR.jpg").getImage());
    }

    private void initComponents(){
        inputK = new JTextField(10);
        inputS = new JTextField(10);
        outputArea = new JTextArea(20, 30);
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        generateButton = new JButton("Генерировать");
        backButton = new JButton("В главное меню");
    }

    private void layoutComponents(){
        JPanel topPanel = new JPanel(new GridLayout(0,1,5,5));
        topPanel.add(new JLabel("Введите K (разрядность чисел): "));
        topPanel.add(inputK);
        topPanel.add(new JLabel("Введите S (необходимая сумма цифр):"));
        topPanel.add(inputS);
        topPanel.add(generateButton);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(backButton);

        JScrollPane scrollPane = new JScrollPane(outputArea);

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10,10, 10));
        scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 10,10));

    }

    private void addListeners(JFrame mainMenuFrame) {
        generateButton.addActionListener(e -> execution());
        inputK.addActionListener(e -> execution());
        inputS.addActionListener(e -> execution());
        backButton.addActionListener( e -> {
            dispose();
            mainMenuFrame.setVisible(true);
        });
    }

    private void execution(){
        try{

            int K = Integer.parseInt(inputK.getText().trim());
            int S = Integer.parseInt(inputS.getText().trim());

            if ( K < 1 || S < 1){
                JOptionPane.showMessageDialog(this,
                        "K и S не могут быть меньше единицы!",
                        "Ошибка",
                        JOptionPane.ERROR_MESSAGE);

            } else {
            int rangeMin = (int)Math.pow(10, K-1);
            int rangeMax = (int)Math.pow(10, K) - 1;
            int current = rangeMin;
            int count = 0;
            int[] found = new int[10000000];
            int foundIndex = 0;

            while (current <= rangeMax){
                int number = current;
                int sum = 0;

                while (number > 0){
                    int digit = number % 10;
                    sum += digit;
                    number /= 10;

                }
                if (sum == S){
                    found[foundIndex] = current;
                    foundIndex++;
                    count++;

                }

                current++;
            }
                outputArea.setText("Количество " + K + "-значных чисел с суммой цифр " + S + " составляет: " + count + "\n");
                outputArea.append("Найденные числа:\n");

                for (int i = 0; i < foundIndex; i++) {
                    outputArea.append(found[i] + " ");

                    if ((i + 1) % 10 == 0) {
                        outputArea.append("\n");
                    }
                }



        }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Введите корректное число!",
                    "Ошибка",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void task4(JFrame mainMenuFrame) {
        SwingUtilities.invokeLater(() -> new Task4(mainMenuFrame).setVisible(true));
    }
}

