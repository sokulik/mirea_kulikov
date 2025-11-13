package Tasks;

import javax.swing.*;
import java.awt.*;
import static DOP.DOP_FUNC.showMessageAndReturn;

public class Task7 extends JFrame{
    private JTextField inputField;
    private JTextArea outputArea;
    private JButton generateButton;
    private JButton backButton;

    public Task7(JFrame mainMenuFrame){
        setTitle("Разложение на простые множители");
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
        generateButton = new JButton("Разложить");
        backButton = new JButton("В главное меню");
    }

    private void layoutComponents(){
        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Введите число для разложения: "));
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
            long n = Long.parseLong(inputField.getText().trim());

            if (n <= 1) {
                showMessageAndReturn(this ,"Введите число больше 1!");
                return;
            }
            outputArea.setText("Разложение числа " + n + " на простые множители:\n\n");

            StringBuilder factors = new StringBuilder();

            while (n % 2 == 0) {
                factors.append("2");
                n /= 2;
                if (n != 1) factors.append(" × ");
            }

            for (int i = 3; i * i <= n; i += 2) {
                while (n % i == 0) {
                    if (factors.length() > 0 && !factors.toString().endsWith(" × ")) {
                        factors.append(" × ");
                    }
                    factors.append(i);
                    n /= i;
                }
            }

            if (n > 1) {
                if (factors.length() > 0 && !factors.toString().endsWith(" × ")) {
                    factors.append(" × ");
                }
                factors.append(n);
            }

            outputArea.append(factors.toString());

        }catch (NumberFormatException ex) {
            showMessageAndReturn(this, "Введите корректное число!");
        }
    }






    public static void task7(JFrame mainMenuFrame) {
        SwingUtilities.invokeLater(() -> new Task7(mainMenuFrame).setVisible(true));
    }
}


