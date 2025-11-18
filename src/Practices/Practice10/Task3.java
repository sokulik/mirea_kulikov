package Practices.Practice10;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;
import static DOP.DOP_FUNC.showMessageAndReturn;

public class Task3 extends JFrame {
    private JTextArea textArea;
    private JButton clearButton, saveButton, timeButton, backButton;

    public Task3(JFrame mainMenuFrame) {
        super("Текстовый редактор - Задание 3");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 550);
        setLocationRelativeTo(null);

        initComponents();
        createMenuBar();
        layoutComponents();
        addListeners(mainMenuFrame);
    }

    private void initComponents() {
        textArea = new JTextArea(20, 40);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(new Font("Arial", Font.PLAIN, 14));

        clearButton = new JButton("Очистить");
        saveButton = new JButton("Сохранить");
        timeButton = new JButton("Время");
        backButton = new JButton("В главное меню");

        backButton.setBackground(new Color(70, 130, 180));
        backButton.setForeground(Color.WHITE);
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        // Меню "Файл"
        JMenu fileMenu = new JMenu("Файл");
        JMenuItem newItem = new JMenuItem("Новый");
        JMenuItem saveItem = new JMenuItem("Сохранить");
        JMenuItem exitItem = new JMenuItem("Выйти");

        fileMenu.add(newItem);
        fileMenu.add(saveItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        // Меню "Правка"
        JMenu editMenu = new JMenu("Правка");
        JMenuItem copyItem = new JMenuItem("Копировать");
        JMenuItem cutItem = new JMenuItem("Вырезать");
        JMenuItem pasteItem = new JMenuItem("Вставить");
        JMenuItem selectAllItem = new JMenuItem("Выделить все");

        editMenu.add(copyItem);
        editMenu.add(cutItem);
        editMenu.add(pasteItem);
        editMenu.addSeparator();
        editMenu.add(selectAllItem);

        // Меню "Справка"
        JMenu helpMenu = new JMenu("Справка");
        JMenuItem aboutItem = new JMenuItem("О программе");

        helpMenu.add(aboutItem);

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(helpMenu);

        setJMenuBar(menuBar);

        // Обработчики для пунктов меню
        newItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
            }
        });

        saveItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveText();
            }
        });

        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        copyItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textArea.copy();
            }
        });

        cutItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textArea.cut();
            }
        });

        pasteItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textArea.paste();
            }
        });

        selectAllItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textArea.selectAll();
            }
        });

        aboutItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(Task3.this,
                        "Текстовый редактор v1.0\n" +
                                "Практическая работа №10\n" +
                                "Разработано в учебных целях",
                        "О программе",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    private void layoutComponents() {
        setLayout(new BorderLayout());

        // Панель с кнопками
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(clearButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(timeButton);

        // Область текста с прокруткой
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Текстовая область"));

        // Панель для кнопки назад
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(buttonPanel, BorderLayout.CENTER);

        JPanel backPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        backPanel.add(backButton);
        bottomPanel.add(backPanel, BorderLayout.SOUTH);

        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        ((JComponent) getContentPane()).setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    private void addListeners(final JFrame mainMenuFrame) {
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
            }
        });

        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveText();
            }
        });

        timeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textArea.append("\nТекущее время: " + new Date());
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

    private void saveText() {
        if (textArea.getText().trim().isEmpty()) {
            showMessageAndReturn(this, "Текст для сохранения отсутствует!");
        } else {
            JOptionPane.showMessageDialog(this, "Текст успешно сохранен!", "Успех", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void task3(JFrame mainMenuFrame) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Task3(mainMenuFrame).setVisible(true);
            }
        });
    }
}