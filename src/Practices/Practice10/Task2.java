package Practices.Practice10;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import static DOP.DOP_FUNC.showMessageAndReturn;

public class Task2 extends JFrame {
    private JComboBox<String> countryComboBox;
    private JTextArea infoArea;
    private JButton backButton;

    public Task2(JFrame mainMenuFrame) {
        super("Информация о странах - Задание 2");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 450);
        setLocationRelativeTo(null);

        initComponents();
        layoutComponents();
        addListeners(mainMenuFrame);
    }

    private void initComponents() {
        String[] countries = {
                "Выберите страну",
                "Россия",
                "США",
                "Германия",
                "Франция",
                "Япония",
                "Китай",
                "Бразилия",
                "Индия"
        };

        countryComboBox = new JComboBox<>(countries);
        infoArea = new JTextArea(10, 30);
        infoArea.setEditable(false);
        infoArea.setLineWrap(true);
        infoArea.setWrapStyleWord(true);
        infoArea.setFont(new Font("Arial", Font.PLAIN, 14));

        backButton = new JButton("В главное меню");
        backButton.setBackground(new Color(70, 130, 180));
        backButton.setForeground(Color.WHITE);
    }

    private void layoutComponents() {
        setLayout(new BorderLayout(10, 10));

        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Выберите страну:"));
        topPanel.add(countryComboBox);

        JScrollPane scrollPane = new JScrollPane(infoArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Информация о стране"));

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.add(backButton);

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        ((JComponent) getContentPane()).setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    }

    private void addListeners(final JFrame mainMenuFrame) {
        countryComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedCountry = (String) countryComboBox.getSelectedItem();
                showCountryInfo(selectedCountry);
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

    private void showCountryInfo(String country) {
        String info = "";

        switch (country) {
            case "Россия":
                info = "Россия - крупнейшее государство в мире по площади.\n\n" +
                        "Столица: Москва\n" +
                        "Население: около 146 миллионов человек\n" +
                        "Официальный язык: русский\n" +
                        "Площадь: 17 098 246 км²\n" +
                        "Валюта: Российский рубль (RUB)";
                break;
            case "США":
                info = "США - федеративная республика в Северной Америке.\n\n" +
                        "Столица: Вашингтон\n" +
                        "Население: около 332 миллионов человек\n" +
                        "Официальный язык: английский\n" +
                        "Площадь: 9 833 520 км²\n" +
                        "Валюта: Доллар США (USD)";
                break;
            case "Германия":
                info = "Германия - государство в Центральной Европе.\n\n" +
                        "Столица: Берлин\n" +
                        "Население: около 83 миллионов человек\n" +
                        "Официальный язык: немецкий\n" +
                        "Площадь: 357 588 км²\n" +
                        "Валюта: Евро (EUR)";
                break;
            case "Франция":
                info = "Франция - государство в Западной Европе.\n\n" +
                        "Столица: Париж\n" +
                        "Население: около 68 миллионов человек\n" +
                        "Официальный язык: французский\n" +
                        "Площадь: 643 801 км²\n" +
                        "Валюта: Евро (EUR)";
                break;
            case "Япония":
                info = "Япония - островное государство в Восточной Азии.\n\n" +
                        "Столица: Токио\n" +
                        "Население: около 126 миллионов человек\n" +
                        "Официальный язык: японский\n" +
                        "Площадь: 377 975 км²\n" +
                        "Валюта: Японская иена (JPY)";
                break;
            case "Китай":
                info = "Китай - государство в Восточной Азии.\n\n" +
                        "Столица: Пекин\n" +
                        "Население: около 1,4 миллиарда человек\n" +
                        "Официальный язык: китайский\n" +
                        "Площадь: 9 596 961 км²\n" +
                        "Валюта: Китайский юань (CNY)";
                break;
            case "Бразилия":
                info = "Бразилия - крупнейшее государство в Южной Америке.\n\n" +
                        "Столица: Бразилиа\n" +
                        "Население: около 214 миллионов человек\n" +
                        "Официальный язык: португальский\n" +
                        "Площадь: 8 515 767 км²\n" +
                        "Валюта: Бразильский реал (BRL)";
                break;
            case "Индия":
                info = "Индия - государство в Южной Азии.\n\n" +
                        "Столица: Нью-Дели\n" +
                        "Население: около 1,38 миллиарда человек\n" +
                        "Официальный язык: хинди, английский\n" +
                        "Площадь: 3 287 263 км²\n" +
                        "Валюта: Индийская рупия (INR)";
                break;
            default:
                info = "Выберите страну из списка для получения информации.";
        }

        infoArea.setText(info);
    }

    public static void task2(JFrame mainMenuFrame) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Task2(mainMenuFrame).setVisible(true);
            }
        });
    }
}