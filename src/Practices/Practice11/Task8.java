package Practices.Practice11;

import javax.swing.*;
import java.awt.*;
import static DOP.DOP_FUNC.showMessageAndReturn;

public class Task8 extends JFrame{
    private JTextField inputField;
    private JTextArea outputArea;
    private JButton generateButton;
    private JButton backButton;

    public Task8(JFrame mainMenuFrame){
        setTitle("Палидром");
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
        generateButton = new JButton("Проверить");
        backButton = new JButton("В главное меню");
    }

    private void layoutComponents(){
        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Введите слово для проверки на палидром: "));
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

            String word = inputField.getText().trim();

            if (word.isEmpty()) {
                showMessageAndReturn(this ,"Введите слово!");
                return;
            }

            if (!word.matches("[a-z]+")){
            showMessageAndReturn(this ,"Только строчные латинские буквы!!");
            return;
            }
        outputArea.setText("Проверка слова: \"" + word + "\"\n\n");

        boolean isPalindrome = isPalindromeRecursive(word, 0, word.length() - 1);

        if (isPalindrome) {
            outputArea.append("Результат: YES \n\n");
            outputArea.append("Слово \"" + word + "\" является палиндромом!");
        } else {
            outputArea.append("Результат: NO \n\n");
            outputArea.append("Слово \"" + word + "\" не является палиндромом!");
        }
        }

    private boolean isPalindromeRecursive(String word, int left, int right) {
        if (left >= right) {
            return true;
        }

        if (word.charAt(left) != word.charAt(right)) {
            return false;
        }

        return isPalindromeRecursive(word, left + 1, right - 1);
    }

    public static void task8(JFrame mainMenuFrame) {
        SwingUtilities.invokeLater(() -> new Task8(mainMenuFrame).setVisible(true));
    }
}


