package Tasks;

import javax.swing.*;
import java.awt.*;
import static DOP.DOP_FUNC.showMessageAndReturn;

public class Task10 extends JFrame{
    private JTextField inputField;
    private JTextArea outputArea;
    private JButton generateButton;
    private JButton backButton;

    public Task10(JFrame mainMenuFrame){
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
        generateButton = new JButton("Развернуть!");
        backButton = new JButton("В главное меню");
    }

    private void layoutComponents(){
        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Введите число для разворота: "));
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
            int n = Integer.parseInt(inputField.getText().trim());

            outputArea.setText("Разворот числа " + n + "\n\n");

            int result = reverseNumber(n);
            outputArea.append(String.valueOf(result));

        } catch (NumberFormatException ex) {
            showMessageAndReturn(this, "Введите корректное число!");
        } catch (IllegalArgumentException ex) {
            showMessageAndReturn(this, ex.getMessage());
        }
    }

    public static int reverseNumber(int n) {
        if (!hasNoZeros(n)) {
            throw new IllegalArgumentException("Число не должно содержать нули");
        }
        return reverseHelper(n, 0);
    }

    private static int reverseHelper(int n, int reversed) {
        if (n == 0) {
            return reversed;
        }
        return reverseHelper(n / 10, reversed * 10 + n % 10);
    }

    private static boolean hasNoZeros(int n) {
        if (n == 0) return true;
        if (n % 10 == 0) return false;
        return hasNoZeros(n / 10);
    }


    public static void task10(JFrame mainMenuFrame) {
        SwingUtilities.invokeLater(() -> new Task10(mainMenuFrame).setVisible(true));
    }
}


