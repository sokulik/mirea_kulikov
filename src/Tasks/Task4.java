package Tasks;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Task4 extends JFrame {
    private JTextField filenameField;
    private JTextArea inputArea;
    private JTextArea outputArea;
    private JButton writeButton, clearButton, backButton;

    public Task4(JFrame mainMenuFrame) {
        super("Запись в файл - Задание 4");
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

        writeButton = new JButton("Записать в файл");
        clearButton = new JButton("Очистить");
        backButton = new JButton("В главное меню");

        writeButton.setBackground(new Color(70, 130, 180));
        writeButton.setForeground(Color.WHITE);
        backButton.setBackground(new Color(70, 130, 180));
        backButton.setForeground(Color.WHITE);
    }

    private void layoutComponents() {
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.setDividerLocation(350); // Разделитель посередине
        splitPane.setResizeWeight(0.5); // Обе панели одинакового размера

        JPanel topPanel = new JPanel(new BorderLayout());

        JPanel filenamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        filenamePanel.add(new JLabel("Имя файла:"));
        filenamePanel.add(filenameField);
        topPanel.add(filenamePanel, BorderLayout.NORTH);

        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.setBorder(BorderFactory.createTitledBorder("Введите текст для записи:"));
        inputPanel.add(new JScrollPane(inputArea), BorderLayout.CENTER);
        topPanel.add(inputPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(writeButton);
        buttonPanel.add(clearButton);
        topPanel.add(buttonPanel, BorderLayout.SOUTH);

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
        writeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                writeToFile();
            }
        });

        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                inputArea.setText("");
                outputArea.setText("");
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

    private void writeToFile() {
        String filename = filenameField.getText().trim();
        String text = inputArea.getText();

        if (filename.isEmpty()) {
            outputArea.setText("Ошибка: Введите имя файла!");
            return;
        }

        if (text.trim().isEmpty()) {
            outputArea.setText("Ошибка: Введите текст для записи!");
            return;
        }

        try {
            // Создаем директорию если она не существует
            File file = new File(filename);
            File directory = file.getParentFile();
            if (directory != null && !directory.exists()) {
                boolean created = directory.mkdirs();
                if (!created) {
                    outputArea.setText("Ошибка: Не удалось создать директорию " + directory.getPath());
                    return;
                }
            }

            FileWriter writer = new FileWriter(filename, false);
            writer.write(text);
            writer.close();

            outputArea.setText("УСПЕХ: Текст записан в файл '" + filename + "'\n\n");
            outputArea.append("Записанное содержимое:\n");
            outputArea.append("======================\n");
            outputArea.append(text);
            outputArea.append("\n\nРазмер файла: " + text.length() + " символов");

        } catch (IOException ex) {
            outputArea.setText("Ошибка записи в файл: " + ex.getMessage());
        }
    }

    public static void task4(JFrame mainMenuFrame) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Task4(mainMenuFrame).setVisible(true);
            }
        });
    }
}