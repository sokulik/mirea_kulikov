package Task3;

import javax.swing.*;
import java.awt.*;

    public class Task3 extends JFrame {
        private JTextField inputA;
        private JTextField inputB;
        private JTextArea outputArea;
        private JButton generateButton;

        public Task3(){
            setTitle("A и B сидели на трубе");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(600, 400);
            setLocationRelativeTo(null);

            initComponents();
            layoutComponents();
            addListeners();

            setIconImage(new ImageIcon("/src/resources/IconPR.jpg").getImage());
        }

        private void initComponents(){
            inputA = new JTextField(10);
            inputB = new JTextField(10);
            outputArea = new JTextArea(15, 30);
            outputArea.setEditable(false);
            outputArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
            generateButton = new JButton("Генерировать");
        }

        private void layoutComponents(){
            JPanel topPanel = new JPanel();
            topPanel.add(new JLabel("Введите A: "));
            topPanel.add(inputA);
            topPanel.add(new JLabel("Введите B:"));
            topPanel.add(inputB);
            topPanel.add(generateButton);

            JScrollPane scrollPane = new JScrollPane(outputArea);
            add(topPanel, BorderLayout.NORTH);
            add(scrollPane, BorderLayout.CENTER);

            topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10,10, 10));
            scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 10,10));

        }

        private void addListeners() {
            generateButton.addActionListener(e -> generateSequence());
            inputA.addActionListener(e -> generateSequence());
            inputB.addActionListener(e -> generateSequence());
        }

        private void generateSequence(){
            try{
                int A = Integer.parseInt(inputA.getText().trim());
                int B = Integer.parseInt(inputB.getText().trim());

                if (A < B){

                StringBuilder row = new StringBuilder();

                for (int i = A; i <= B; i++){
                    row.append(i).append(" ");

                    if ((i-A+1) % 10 == 0 && i < B) {
                        row.append("\n");
                    }
                }


                outputArea.setText("Числа от "+A+" до "+B+" в порядке возрастания: "+"\n");
                outputArea.append(row.toString());
            } else if (A>B){

                    StringBuilder row = new StringBuilder();
                    int count = 0;

                    for (int i = A; i >= B; i--){
                        row.append(i).append(" ");
                        count++;

                        if (count % 10 == 0 && i > B) {
                            row.append("\n");
                        }
                    }


                    outputArea.setText("Числа от "+A+" до "+B+" в порядке убывания: "+"\n");
                    outputArea.append(row.toString());
                } else {
                    outputArea.setText("Введеные числа равны!\n");
                }


            }catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                        "Введите корректное число!",
                        "Ошибка",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

        public static void task3() {
            SwingUtilities.invokeLater(() -> new Task3().setVisible(true));
        }
    }




