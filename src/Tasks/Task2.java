package Tasks;

import javax.swing.*;
import java.awt.*;

public class Task2 extends JFrame{
    private JTextField inputField;
    private JTextArea outputArea;
    private JButton generateButton;
    private JButton backButton;

    public Task2(JFrame mainMenuFrame){
    setTitle("от 1 до n");
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
        topPanel.add(new JLabel("Введите n: "));
        topPanel.add(inputField);
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
        inputField.addActionListener(e -> execution());
        backButton.addActionListener( e -> {
            dispose();
            mainMenuFrame.setVisible(true);
        });

}

    private void execution(){
        try{
            int n = Integer.parseInt(inputField.getText().trim());

            if (n <= 0 ){
                JOptionPane.showMessageDialog(this,
                        "Введите положительное число!",
                        "Ошибка",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            StringBuilder row = new StringBuilder();

            for (int i = 1; i <= n; i++){
                row.append(i).append(" ");

            if (i % 10 == 0 && i < n) {
                row.append("\n");
            }
            }


            outputArea.setText("Числа от 1 до "+n+"\n");
            outputArea.append(row.toString());
        }catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Введите корректное число!",
                    "Ошибка",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void task2(JFrame mainMenuFrame) {
        SwingUtilities.invokeLater(() -> new Task2(mainMenuFrame).setVisible(true));
    }
}


