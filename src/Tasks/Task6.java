package Tasks;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Task6 extends JFrame {
    private JTextField filenameField;
    private JTextArea inputArea, outputArea;
    private JButton readButton, replaceButton, backButton;

    public Task6(JFrame mainMenuFrame) {
        super("Замена информации в файле - Задание 6");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 700);
        setLocationRelativeTo(null);

        initComponents();
        layoutComponents();
        addListeners(mainMenuFrame);
    }

    private void initComponents() {
        filenameField = new JTextField("src/DOP/test.txt", 30);

        inputArea = new JTextArea();
        inputArea.setFont(new Font("Arial", Font.PLAIN, 12));
        inputArea.setLineWrap(true);
        inputArea.setWrapStyleWord(true);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        outputArea.setBackground(new Color(240, 240, 240));

        readButton = new JButton("Прочитать текущий файл");
        replaceButton = new JButton("Заменить содержимое");
        backButton = new JButton("В главное меню");

        readButton.setBackground(new Color(70, 130, 180));
        readButton.setForeground(Color.WHITE);
        replaceButton.setBackground(new Color(220, 80, 60));
        replaceButton.setForeground(Color.WHITE);
        backButton.setBackground(new Color(70, 130, 180));
        backButton.setForeground(Color.WHITE);
    }

    private void layoutComponents() {
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.setDividerLocation(350);
        splitPane.setResizeWeight(0.5);

        JPanel topPanel = new JPanel(new BorderLayout());

        JPanel controlPanel = new JPanel(new BorderLayout());

        JPanel filenamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        filenamePanel.add(new JLabel("Имя файла:"));
        filenamePanel.add(filenameField);
        controlPanel.add(filenamePanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(readButton);
        buttonPanel.add(replaceButton);
        controlPanel.add(buttonPanel, BorderLayout.CENTER);

        topPanel.add(controlPanel, BorderLayout.NORTH);

        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.setBorder(BorderFactory.createTitledBorder("Новое содержимое файла:"));
        inputPanel.add(new JScrollPane(inputArea), BorderLayout.CENTER);
        topPanel.add(inputPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBorder(BorderFactory.createTitledBorder("Результат операции:"));
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
                readCurrentFile();
            }
        });

        replaceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                replaceFileContent();
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

    private void readCurrentFile() {
        String filename = filenameField.getText().trim();
        File file = new File(filename);

        outputArea.setText("");

        if (filename.isEmpty()) {
            outputArea.setText("Ошибка: Введите имя файла!");
            return;
        }

        if (!file.exists()) {
            outputArea.setText("ФАЙЛ НЕ НАЙДЕН: " + filename + "\n\n");
            outputArea.append("Файл будет создан при замене содержимого.");
            return;
        }

        try (FileReader reader = new FileReader(filename)) {
            int character;
            int lineCount = 1;

            outputArea.append("ТЕКУЩЕЕ СОДЕРЖИМОЕ ФАЙЛА: " + filename + "\n");
            outputArea.append("==========================" + "=".repeat(filename.length()) + "\n\n");

            outputArea.append(lineCount + ": ");
            while ((character = reader.read()) != -1) {
                char currentChar = (char) character;
                outputArea.append(String.valueOf(currentChar));

                if (currentChar == '\n') {
                    lineCount++;
                    outputArea.append(lineCount + ": ");
                }
            }

            outputArea.append("\n\nРазмер файла: " + file.length() + " байт\n");

        } catch (IOException ex) {
            outputArea.setText("Ошибка чтения файла: " + ex.getMessage());
        }
    }

    private void replaceFileContent() {
        String filename = filenameField.getText().trim();
        String newText = inputArea.getText();

        if (filename.isEmpty()) {
            outputArea.setText("Ошибка: Введите имя файла!");
            return;
        }

        if (newText.trim().isEmpty()) {
            outputArea.setText("Ошибка: Введите новое содержимое файла!");
            return;
        }

        try {
            File file = new File(filename);
            File directory = file.getParentFile();
            if (directory != null && !directory.exists()) {
                directory.mkdirs();
            }

            FileWriter writer = new FileWriter(filename, false); // false = перезапись
            writer.write(newText);
            writer.close();

            outputArea.setText("УСПЕХ: Содержимое файла заменено!\n\n");
            outputArea.append("НОВОЕ СОДЕРЖИМОЕ ФАЙЛА:\n");
            outputArea.append("========================\n");
            outputArea.append(newText);
            outputArea.append("\n\nЗаписано символов: " + newText.length());

        } catch (IOException ex) {
            outputArea.setText("Ошибка замены файла: " + ex.getMessage());
        }
    }

    public static void task6(JFrame mainMenuFrame) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Task6(mainMenuFrame).setVisible(true);
            }
        });
    }
}