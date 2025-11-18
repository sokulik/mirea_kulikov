package Tasks;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Task5 extends JFrame {
    private JTextField filenameField;
    private JTextArea outputArea;
    private JButton readButton, backButton;

    public Task5(JFrame mainMenuFrame) {
        super("Чтение из файла - Задание 5");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 700);
        setLocationRelativeTo(null);

        initComponents();
        layoutComponents();
        addListeners(mainMenuFrame);
    }

    private void initComponents() {
        filenameField = new JTextField("src/DOP/test.txt", 30);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        outputArea.setBackground(new Color(240, 240, 240));

        readButton = new JButton("Прочитать файл");
        backButton = new JButton("В главное меню");

        readButton.setBackground(new Color(70, 130, 180));
        readButton.setForeground(Color.WHITE);
        backButton.setBackground(new Color(70, 130, 180));
        backButton.setForeground(Color.WHITE);
    }

    private void layoutComponents() {
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.setDividerLocation(100); // Верхняя панель маленькая, нижняя большая
        splitPane.setResizeWeight(0.1); // 10% для верхней, 90% для нижней

        JPanel topPanel = new JPanel(new BorderLayout());

        JPanel filenamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        filenamePanel.add(new JLabel("Имя файла:"));
        filenamePanel.add(filenameField);
        filenamePanel.add(readButton);
        topPanel.add(filenamePanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBorder(BorderFactory.createTitledBorder("Содержимое файла:"));
        bottomPanel.add(new JScrollPane(outputArea), BorderLayout.CENTER);

        JPanel backPanel = new JPanel();
        backPanel.add(backButton);
        bottomPanel.add(backPanel, BorderLayout.SOUTH);

        splitPane.setTopComponent(topPanel);
        splitPane.setBottomComponent(bottomPanel);

        add(splitPane, BorderLayout.CENTER);
    }

    private void addListeners(final JFrame mainMenuFrame) {
        readButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                readFromFile();
            }
        });

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                mainMenuFrame.setVisible(true);
            }
        });

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                mainMenuFrame.setVisible(true);
            }
        });
    }

    private void readFromFile() {
        String filename = filenameField.getText().trim();
        File file = new File(filename);

        outputArea.setText("");

        if (filename.isEmpty()) {
            outputArea.setText("Ошибка: Введите имя файла!");
            return;
        }

        if (!file.exists()) {
            outputArea.setText("ФАЙЛ НЕ НАЙДЕН: " + filename + "\n\n");
            outputArea.append("Файл не существует. Создайте его с помощью задания 4 (Запись в файл).");
            return;
        }

        try (FileReader reader = new FileReader(filename)) {
            int character;
            int charCount = 0;
            int lineCount = 1;

            outputArea.append("СОДЕРЖИМОЕ ФАЙЛА: " + filename + "\n");
            outputArea.append("=================" + "=".repeat(filename.length()) + "\n\n");

            outputArea.append(lineCount + ": ");
            while ((character = reader.read()) != -1) {
                char currentChar = (char) character;
                outputArea.append(String.valueOf(currentChar));

                if (currentChar == '\n') {
                    lineCount++;
                    outputArea.append(lineCount + ": ");
                }
                charCount++;
            }

            outputArea.append("\n\nСТАТИСТИКА:\n");
            outputArea.append("===========\n");
            outputArea.append("Прочитано символов: " + charCount + "\n");
            outputArea.append("Количество строк: " + lineCount + "\n");
            outputArea.append("Размер файла: " + file.length() + " байт\n");
            outputArea.append("Путь: " + file.getAbsolutePath() + "\n");

        } catch (IOException ex) {
            outputArea.setText("Ошибка чтения файла: " + ex.getMessage());
        }
    }

    public static void task5(JFrame mainMenuFrame) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Task5(mainMenuFrame).setVisible(true);
            }
        });
    }
}