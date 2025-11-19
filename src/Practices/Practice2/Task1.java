package Practices.Practice2;

import DOP.BaseTaskFrame;
import Practices.Practice2.Classes.Author;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Task1 extends BaseTaskFrame {
    private JTextArea resultArea;
    private JTextField nameField, emailField, genderField, ageField;

    public Task1(JFrame mainMenuFrame) {
        super(mainMenuFrame, "Practice 2: Работа с авторами");

        initComponents();
        layoutComponents();
        addListeners();
        demonstrateAuthors();
    }

    private void initComponents() {
        resultArea = new JTextArea(15, 40);
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Consolas", Font.PLAIN, 12));
        resultArea.setBackground(new Color(248, 249, 250));

        nameField = new JTextField(15);
        emailField = new JTextField(15);
        genderField = new JTextField(2);
        ageField = new JTextField(5);

        nameField.setFont(new Font("Arial", Font.PLAIN, 12));
        emailField.setFont(new Font("Arial", Font.PLAIN, 12));
        genderField.setFont(new Font("Arial", Font.PLAIN, 12));
        ageField.setFont(new Font("Arial", Font.PLAIN, 12));
    }

    private void layoutComponents() {
        setLayout(new BorderLayout(10, 10));

        // Верхняя панель с созданием автора
        JPanel inputPanel = createInputPanel();
        add(inputPanel, BorderLayout.NORTH);

        // Центральная панель с результатами
        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Информация об авторах"));
        add(scrollPane, BorderLayout.CENTER);

        // Панель снизу с кнопкой назад
        JPanel bottomPanel = createBottomPanel();
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private JPanel createInputPanel() {
        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        inputPanel.setBackground(new Color(240, 240, 240));

        inputPanel.add(new JLabel("Имя автора:"));
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Email:"));
        inputPanel.add(emailField);

        inputPanel.add(new JLabel("Пол (м/ж):"));
        inputPanel.add(genderField);

        inputPanel.add(new JLabel("Возраст:"));
        inputPanel.add(ageField);

        JButton createButton = new JButton("Создать автора");
        createButton.setBackground(new Color(70, 130, 180));
        createButton.setForeground(Color.WHITE);
        createButton.addActionListener(e -> createAuthor());

        inputPanel.add(new JLabel(""));
        inputPanel.add(createButton);

        return inputPanel;
    }

    private void createAuthor() {
        try {
            String name = nameField.getText().trim();
            String email = emailField.getText().trim();
            String genderStr = genderField.getText().trim();
            int age = Integer.parseInt(ageField.getText().trim());

            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Введите имя автора", "Ошибка", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (genderStr.isEmpty() || (genderStr.charAt(0) != 'м' && genderStr.charAt(0) != 'ж')) {
                JOptionPane.showMessageDialog(this, "Пол должен быть 'м' или 'ж'", "Ошибка", JOptionPane.ERROR_MESSAGE);
                return;
            }

            char gender = genderStr.charAt(0);
            Author author = new Author(name, email, gender, age);

            resultArea.append("✅ Создан новый автор:\n");
            resultArea.append(author.toString() + "\n");
            resultArea.append("─".repeat(50) + "\n");

            // Очистка полей
            nameField.setText("");
            emailField.setText("");
            genderField.setText("");
            ageField.setText("");
            nameField.requestFocus();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Введите корректный возраст",
                    "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void demonstrateAuthors() {
        StringBuilder output = new StringBuilder();
        output.append("📚 Демонстрация работы с авторами\n");
        output.append("═".repeat(50) + "\n\n");

        // Создаем тестовых авторов как в оригинальном main
        Author author = new Author();
        output.append("Автор по умолчанию:\n");
        output.append(author.toString() + "\n\n");

        author.setName("Достоевский");
        author.setEmail("dostal_vstavil@mail.ru");
        author.setAge(40);
        author.setGender('м');
        output.append("Автор после установки значений:\n");
        output.append(author.toString() + "\n\n");

        Author author1 = new Author("Марк Твен", "s@mail.ru", 'м', 18);
        output.append("Автор созданный через конструктор:\n");
        output.append(author1.toString() + "\n\n");

        output.append("💡 Создайте своего автора используя форму выше!\n");

        resultArea.setText(output.toString());
    }

    private JPanel createBottomPanel() {
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(new Color(240, 240, 240));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton backButton = createBackButton();
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftPanel.setBackground(new Color(240, 240, 240));
        leftPanel.add(backButton);

        JLabel infoLabel = new JLabel("Класс Author: name, email, gender, age");
        infoLabel.setFont(new Font("Arial", Font.PLAIN, 11));
        infoLabel.setForeground(Color.DARK_GRAY);

        bottomPanel.add(leftPanel, BorderLayout.WEST);
        bottomPanel.add(infoLabel, BorderLayout.EAST);

        return bottomPanel;
    }

    private void addListeners() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                returnToMainMenu();
            }
        });
    }

    public static void task1(JFrame mainMenuFrame) {
        if (mainMenuFrame != null) {
            mainMenuFrame.setVisible(false);
        }

        SwingUtilities.invokeLater(() -> new Task1(mainMenuFrame).setVisible(true));
    }
}