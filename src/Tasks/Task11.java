package Tasks;

import javax.swing.*;
import java.awt.*;
import static DOP.DOP_FUNC.showMessageAndReturn;

public class Task11 extends JFrame{
    private JTextField inputField;
    private JTextArea outputArea;
    private JButton generateButton;
    private JButton backButton;

    public Task11(JFrame mainMenuFrame){
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
            String numberStr = inputField.getText().trim();

            if (!numberStr.matches("\\d+")) {
                throw new IllegalArgumentException("Введите корректное число (только цифры)!");
            }

            outputArea.setText("Исходное число: " + numberStr + "\n\n");

            int count = countOnesInSequence(numberStr, 0, false);
            outputArea.append("Количество единиц до двух нулей подряд: " + count);

        } catch (IllegalArgumentException ex) {
            showMessageAndReturn(this, ex.getMessage());
        }
    }

    private static int countOnesInSequence(String numberStr, int index, boolean prevZero) {
        if (index >= numberStr.length()) {
            return 0;
        }

        char currentChar = numberStr.charAt(index);
        int currentDigit = Character.getNumericValue(currentChar);

        if (prevZero && currentDigit == 0) {
            return 0;
        }

        if (currentDigit == 1) {
            return 1 + countOnesInSequence(numberStr, index + 1, false);
        } else if (currentDigit == 0) {
            return countOnesInSequence(numberStr, index + 1, true);
        } else {
            return countOnesInSequence(numberStr, index + 1, false);
        }
    }


    public static void task11(JFrame mainMenuFrame) {
        SwingUtilities.invokeLater(() -> new Task11(mainMenuFrame).setVisible(true));
    }
}


