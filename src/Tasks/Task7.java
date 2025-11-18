package Tasks;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Task7 extends JFrame {
    private JTextField filenameField;
    private JTextArea inputArea, outputArea;
    private JButton readButton, appendButton, backButton;
    private JCheckBox timestampCheckbox;

    public Task7(JFrame mainMenuFrame) {
        super("Добавление в конец файла - Задание 7");
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

        timestampCheckbox = new JCheckBox("Добавить временную метку", true);

        readButton = new JButton("Прочитать файл");
        appendButton = new JButton("Добавить в конец");
        backButton = new JButton("В главное меню");

        readButton.setBackground(new Color(70, 130, 180));
        readButton.setForeground(Color.WHITE);
        appendButton.setBackground(new Color(60, 180, 75));
        appendButton.setForeground(Color.WHITE);
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
        filenamePanel.add(readButton);
        controlPanel.add(filenamePanel, BorderLayout.NORTH);

        JPanel settingsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        settingsPanel.add(timestampCheckbox);
        settingsPanel.add(appendButton);
        controlPanel.add(settingsPanel, BorderLayout.CENTER);

        topPanel.add(controlPanel, BorderLayout.NORTH);

        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.setBorder(BorderFactory.createTitledBorder("Текст для добавления в конец файла:"));
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
                readFileContent();
            }
        });

        appendButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                appendToFile();
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

    private void readFileContent() {
        String filename = filenameField.getText().trim();
        File file = new File(filename);

        outputArea.setText("");

        if (filename.isEmpty()) {
            outputArea.setText("Ошибка: Введите имя файла!");
            return;
        }

        if (!file.exists()) {
            outputArea.setText("ФАЙЛ НЕ НАЙДЕН: " + filename + "\n\n");
            outputArea.append("Файл будет создан при добавлении текста.");
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

    private void appendToFile() {
        String filename = filenameField.getText().trim();
        String textToAppend = inputArea.getText().trim();

        if (filename.isEmpty()) {
            outputArea.setText("Ошибка: Введите имя файла!");
            return;
        }

        if (textToAppend.isEmpty()) {
            outputArea.setText("Ошибка: Введите текст для добавления!");
            return;
        }

        try {
            File file = new File(filename);
            File directory = file.getParentFile();
            if (directory != null && !directory.exists()) {
                directory.mkdirs();
            }

            FileWriter writer = new FileWriter(filename, true); // true = добавление в конец

            if (timestampCheckbox.isSelected()) {
                String timestamp = new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss] ").format(new Date());
                writer.write("\n" + timestamp);
            }

            writer.write(textToAppend + "\n");
            writer.close();

            outputArea.setText("УСПЕХ: Текст добавлен в конец файла '" + filename + "'!\n\n");

            outputArea.append("ДОБАВЛЕННЫЙ ТЕКСТ:\n");
            outputArea.append("==================\n");
            if (timestampCheckbox.isSelected()) {
                String timestamp = new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss] ").format(new Date());
                outputArea.append(timestamp);
            }
            outputArea.append(textToAppend);
            outputArea.append("\n\nСтатистика добавления:\n");
            outputArea.append("Добавлено символов: " + textToAppend.length() + "\n");
            outputArea.append("Режим: добавление в конец (append = true)\n");
            outputArea.append("Исходное содержимое файла сохранено\n");

        } catch (IOException ex) {
            outputArea.setText("Ошибка добавления в файл: " + ex.getMessage());
        }
    }

    public static void task7(JFrame mainMenuFrame) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Task7(mainMenuFrame).setVisible(true);
            }
        });
    }
}