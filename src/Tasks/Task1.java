package Tasks;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Task1 extends JFrame {
    private JTextField inputField;
    private JTextArea outputArea;
    private JButton generateButton;
    private JButton backButton;

    public Task1(JFrame mainMenuFrame) {
        setTitle("Треугольная последовательность");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        initComponents();
        layoutComponents();
        addListeners(mainMenuFrame);

        setIconImage(new ImageIcon("src/resources/IconPR.jpg").getImage());
    }



    private void initComponents() {
        inputField = new JTextField(10);
        outputArea = new JTextArea(15, 30);
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        generateButton = new JButton("Сгенерировать");
        backButton = new JButton("В главное меню");
    }

    private void layoutComponents() {
        setLayout(new BorderLayout(10, 10));

        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Введите n:"));
        topPanel.add(inputField);
        topPanel.add(generateButton);

        JScrollPane scrollPane = new JScrollPane(outputArea);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(backButton);

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
    }

    private void addListeners(JFrame mainMenuFrame) {
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                execution();
            }
        });

        inputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                execution();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                mainMenuFrame.setVisible(true);
            }
        });
    }

    private void execution() {
        try {
            int n = Integer.parseInt(inputField.getText().trim());

            if (n <= 0) {
                JOptionPane.showMessageDialog(this,
                        "Введите положительное число!",
                        "Ошибка",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            StringBuilder sequence = new StringBuilder();
            int count = 0;

            for (int i = 1; count < n; i++) {
                for (int j = 1; j <= i && count < n; j++) {
                    sequence.append(i).append(" ");
                    count++;
                }
                if (count < n) {
                    sequence.append("\n");
                }
            }

            outputArea.setText("Последовательность для n = " + n + ":\n\n");
            outputArea.append(sequence.toString());

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Введите корректное число!",
                    "Ошибка",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void task1(JFrame mainMenuFrame) {
        SwingUtilities.invokeLater(() -> new Task1(mainMenuFrame).setVisible(true));
            }
    }
