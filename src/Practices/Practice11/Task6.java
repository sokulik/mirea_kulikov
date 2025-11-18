package Practices.Practice11;

import javax.swing.*;
import java.awt.*;
import static DOP.DOP_FUNC.showMessageAndReturn;

public class Task6 extends JFrame{
    private JTextField inputField;
    private JTextArea outputArea;
    private JButton generateButton;
    private JButton backButton;

    public Task6(JFrame mainMenuFrame){
        setTitle("Проверка на натуральность");
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
        topPanel.add(new JLabel("Введите N для проверки: "));
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
            long N = Long.parseLong(inputField.getText().trim());


            if (N <= 0) {
                showMessageAndReturn(this ,"Введите положительное число!");
                return;
            }

            boolean isPrime = isPrime(N);

            if(N == 228){
                outputArea.setText("Даня гей");
            }else {

            outputArea.setText("Является ли число простым: "+isPrime+"\n");}
        }catch (NumberFormatException ex) {
            showMessageAndReturn(this, "Введите корректное число!");
        }
    }

    private boolean isPrime(long n) {
        if (n <= 1) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false;

        for(long i = 3; i <= Math.sqrt(n); i += 2) {
            if(n % i == 0)
                return false;
        }
        return true;
    }


    public static void task6(JFrame mainMenuFrame) {
        SwingUtilities.invokeLater(() -> new Task6(mainMenuFrame).setVisible(true));
    }
}


