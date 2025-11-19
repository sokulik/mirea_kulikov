package Practices.Practice5;

import Practices.Practice5.Classes.T69.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Task6_9 extends JFrame {
    private JFrame mainMenuFrame;
    private JTextArea resultArea;
    private Printable[] printables;

    public Task6_9(JFrame mainMenuFrame) {
        super("Practice 5.6-9: Интерфейс Printable");
        this.mainMenuFrame = mainMenuFrame;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 700);
        setLocationRelativeTo(null);

        initPrintables();
        initComponents();
        layoutComponents();
        addListeners();
        demonstratePrintable();
    }

    private void initPrintables() {
        printables = new Printable[20];
        // Книги
        printables[0] = new Book("Преступление и наказание", "Фёдор Достоевский", "Роман", 3500);
        printables[1] = new Book("Мастер и Маргарита", "Михаил Булгаков", "Фантастика", 4200);
        printables[2] = new Book("1984", "Джордж Оруэлл", "Антиутопия", 2800);
        printables[3] = new Book("Гарри Поттер и философский камень", "Джоан Роулинг", "Фэнтези", 3200);
        printables[4] = new Book("Три товарища", "Эрих Мария Ремарк", "Роман", 3100);
        printables[5] = new Book("Маленький принц", "Антуан де Сент-Экзюпери", "Притча", 1900);
        printables[6] = new Book("Убить пересмешника", "Харпер Ли", "Роман", 2700);
        printables[7] = new Book("Властелин колец", "Джон Р.Р. Толкин", "Фэнтези", 5100);
        printables[8] = new Book("Анна Каренина", "Лев Толстой", "Классика", 3800);
        printables[9] = new Book("Сто лет одиночества", "Габриэль Гарсиа Маркес", "Магический реализм", 3600);

        // Журналы
        printables[10] = new Magazine("Forbes", "Бизнес", 800);
        printables[11] = new Magazine("National Geographic", "Наука", 750);
        printables[12] = new Magazine("Vogue", "Мода", 900);
        printables[13] = new Magazine("Time", "Новости", 600);
        printables[14] = new Magazine("Popular Mechanics", "Технологии", 550);
        printables[15] = new Magazine("Cosmopolitan", "Женский", 700);
        printables[16] = new Magazine("GQ", "Мужской", 850);
        printables[17] = new Magazine("The Economist", "Политика", 950);
        printables[18] = new Magazine("Sports Illustrated", "Спорт", 500);
        printables[19] = new Magazine("Scientific American", "Научно-популярный", 720);
    }

    private void initComponents() {
        resultArea = new JTextArea(25, 70);
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Consolas", Font.PLAIN, 12));
        resultArea.setBackground(new Color(248, 249, 250));
        resultArea.setMargin(new Insets(10, 10, 10, 10));
    }

    private void layoutComponents() {
        setLayout(new BorderLayout(10, 10));

        // Верхняя панель с кнопками
        JPanel controlPanel = createControlPanel();
        add(controlPanel, BorderLayout.NORTH);

        // Центральная панель с результатами
        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Демонстрация интерфейса Printable"));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane, BorderLayout.CENTER);

        // Панель снизу с кнопкой назад
        JPanel bottomPanel = createBottomPanel();
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private JPanel createControlPanel() {
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        controlPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        controlPanel.setBackground(new Color(240, 240, 240));

        JButton demoButton = new JButton("🎭 Полная демонстрация");
        demoButton.setBackground(new Color(70, 130, 180));
        demoButton.setForeground(Color.WHITE);
        demoButton.addActionListener(e -> demonstratePrintable());

        JButton booksButton = new JButton("📚 Только книги");
        booksButton.setBackground(new Color(40, 167, 69));
        booksButton.setForeground(Color.WHITE);
        booksButton.addActionListener(e -> demonstrateBooks());

        JButton magazinesButton = new JButton("📰 Только журналы");
        magazinesButton.setBackground(new Color(255, 193, 7));
        magazinesButton.setForeground(Color.BLACK);
        magazinesButton.addActionListener(e -> demonstrateMagazines());

        JButton statsButton = new JButton("📊 Статистика");
        statsButton.setBackground(new Color(220, 53, 69));
        statsButton.setForeground(Color.WHITE);
        statsButton.addActionListener(e -> showStatistics());

        JButton customButton = new JButton("➕ Создать объект");
        customButton.setBackground(new Color(108, 117, 125));
        customButton.setForeground(Color.WHITE);
        customButton.addActionListener(e -> createCustomObject());

        controlPanel.add(demoButton);
        controlPanel.add(booksButton);
        controlPanel.add(magazinesButton);
        controlPanel.add(statsButton);
        controlPanel.add(customButton);

        return controlPanel;
    }

    private void demonstratePrintable() {
        StringBuilder output = new StringBuilder();
        output.append("=== Демонстрация интерфейса Printable ===\n\n");
        output.append("Все объекты массива Printable:\n");
        output.append("═".repeat(80)).append("\n\n");

        for (int i = 0; i < printables.length; i++) {
            output.append(String.format("%2d. ", i + 1));
            if (printables[i] instanceof Book) {
                Book book = (Book) printables[i];
                output.append("📖 КНИГА: \"").append(book.getName())
                        .append("\" | Автор: ").append(book.getAuthor())
                        .append(" | Жанр: ").append(book.getType())
                        .append(" | Цена: ").append(book.getPrice()).append(" руб.\n");
            } else if (printables[i] instanceof Magazine) {
                Magazine magazine = (Magazine) printables[i];
                output.append("📰 ЖУРНАЛ: \"").append(magazine.getName())
                        .append("\" | Категория: ").append(magazine.getType())
                        .append(" | Цена: ").append(magazine.getPrice()).append(" руб.\n");
            }
        }

        output.append("\n").append("═".repeat(80)).append("\n");
        output.append("Всего объектов: ").append(printables.length).append(" (10 книг + 10 журналов)\n");

        resultArea.setText(output.toString());
    }

    private void demonstrateBooks() {
        StringBuilder output = new StringBuilder();
        output.append("=== Демонстрация книг ===\n\n");
        output.append("Список книг:\n");
        output.append("─".repeat(70)).append("\n\n");

        int bookCount = 0;
        double totalBookPrice = 0;

        for (Printable printable : printables) {
            if (printable instanceof Book) {
                bookCount++;
                Book book = (Book) printable;
                output.append(String.format("%2d. 📖 %s\n", bookCount, book.getName()));
                output.append("    Автор: ").append(book.getAuthor())
                        .append(" | Жанр: ").append(book.getType())
                        .append(" | Цена: ").append(book.getPrice()).append(" руб.\n\n");
                totalBookPrice += book.getPrice();
            }
        }

        output.append("─".repeat(70)).append("\n");
        output.append("Всего книг: ").append(bookCount).append("\n");
        output.append("Общая стоимость книг: ").append(totalBookPrice).append(" руб.\n");
        output.append("Средняя цена книги: ").append(String.format("%.2f", totalBookPrice / bookCount)).append(" руб.\n");

        resultArea.setText(output.toString());
    }

    private void demonstrateMagazines() {
        StringBuilder output = new StringBuilder();
        output.append("=== Демонстрация журналов ===\n\n");
        output.append("Список журналов:\n");
        output.append("─".repeat(70)).append("\n\n");

        int magazineCount = 0;
        double totalMagazinePrice = 0;

        for (Printable printable : printables) {
            if (printable instanceof Magazine) {
                magazineCount++;
                Magazine magazine = (Magazine) printable;
                output.append(String.format("%2d. 📰 %s\n", magazineCount, magazine.getName()));
                output.append("    Категория: ").append(magazine.getType())
                        .append(" | Цена: ").append(magazine.getPrice()).append(" руб.\n\n");
                totalMagazinePrice += magazine.getPrice();
            }
        }

        output.append("─".repeat(70)).append("\n");
        output.append("Всего журналов: ").append(magazineCount).append("\n");
        output.append("Общая стоимость журналов: ").append(totalMagazinePrice).append(" руб.\n");
        output.append("Средняя цена журнала: ").append(String.format("%.2f", totalMagazinePrice / magazineCount)).append(" руб.\n");

        resultArea.setText(output.toString());
    }

    private void showStatistics() {
        StringBuilder output = new StringBuilder();
        output.append("=== Статистика по массиву Printable ===\n\n");

        int bookCount = 0;
        int magazineCount = 0;
        double totalBookPrice = 0;
        double totalMagazinePrice = 0;
        double mostExpensivePrice = 0;
        Printable mostExpensive = null;
        double cheapestPrice = Double.MAX_VALUE;
        Printable cheapest = null;

        for (Printable printable : printables) {
            double price = 0;
            if (printable instanceof Book) {
                bookCount++;
                price = ((Book) printable).getPrice();
                totalBookPrice += price;
            } else if (printable instanceof Magazine) {
                magazineCount++;
                price = ((Magazine) printable).getPrice();
                totalMagazinePrice += price;
            }

            if (price > mostExpensivePrice) {
                mostExpensivePrice = price;
                mostExpensive = printable;
            }
            if (price < cheapestPrice) {
                cheapestPrice = price;
                cheapest = printable;
            }
        }

        output.append("📊 Общая статистика:\n");
        output.append("─".repeat(40)).append("\n");
        output.append("• Всего объектов: ").append(printables.length).append("\n");
        output.append("• Книг: ").append(bookCount).append("\n");
        output.append("• Журналов: ").append(magazineCount).append("\n\n");

        output.append("💰 Финансовая статистика:\n");
        output.append("─".repeat(40)).append("\n");
        output.append("• Общая стоимость всех объектов: ").append(totalBookPrice + totalMagazinePrice).append(" руб.\n");
        output.append("• Средняя цена книги: ").append(String.format("%.2f", totalBookPrice / bookCount)).append(" руб.\n");
        output.append("• Средняя цена журнала: ").append(String.format("%.2f", totalMagazinePrice / magazineCount)).append(" руб.\n\n");

        output.append("🏆 Рекорды:\n");
        output.append("─".repeat(40)).append("\n");
        if (mostExpensive != null) {
            output.append("• Самый дорогой: ");
            if (mostExpensive instanceof Book) {
                Book book = (Book) mostExpensive;
                output.append("📖 \"").append(book.getName()).append("\" - ").append(mostExpensivePrice).append(" руб.\n");
            } else {
                Magazine magazine = (Magazine) mostExpensive;
                output.append("📰 \"").append(magazine.getName()).append("\" - ").append(mostExpensivePrice).append(" руб.\n");
            }
        }
        if (cheapest != null) {
            output.append("• Самый дешёвый: ");
            if (cheapest instanceof Book) {
                Book book = (Book) cheapest;
                output.append("📖 \"").append(book.getName()).append("\" - ").append(cheapestPrice).append(" руб.\n");
            } else {
                Magazine magazine = (Magazine) cheapest;
                output.append("📰 \"").append(magazine.getName()).append("\" - ").append(cheapestPrice).append(" руб.\n");
            }
        }

        resultArea.setText(output.toString());
    }

    private void createCustomObject() {
        JDialog customDialog = new JDialog(this, "Создание объекта", true);
        customDialog.setLayout(new GridLayout(5, 2, 10, 10));
        customDialog.setSize(400, 300);
        customDialog.setLocationRelativeTo(this);

        JComboBox<String> typeCombo = new JComboBox<>(new String[]{"Book", "Magazine"});
        JTextField nameField = new JTextField();
        JTextField authorField = new JTextField();
        JTextField typeField = new JTextField();
        JTextField priceField = new JTextField();

        JLabel authorLabel = new JLabel("Автор:");
        JLabel typeLabel = new JLabel("Жанр/Категория:");

        customDialog.add(new JLabel("Тип объекта:"));
        customDialog.add(typeCombo);
        customDialog.add(new JLabel("Название:"));
        customDialog.add(nameField);
        customDialog.add(authorLabel);
        customDialog.add(authorField);
        customDialog.add(typeLabel);
        customDialog.add(typeField);
        customDialog.add(new JLabel("Цена:"));
        customDialog.add(priceField);

        // Обработчик изменения типа объекта
        typeCombo.addActionListener(e -> {
            String selectedType = (String) typeCombo.getSelectedItem();
            boolean isBook = "Book".equals(selectedType);
            authorLabel.setText(isBook ? "Автор:" : "Издатель:");
            typeLabel.setText(isBook ? "Жанр:" : "Категория:");
            authorField.setText("");
        });

        JButton createButton = new JButton("Создать");
        createButton.setBackground(new Color(70, 130, 180));
        createButton.setForeground(Color.WHITE);
        createButton.addActionListener(e -> {
            String type = (String) typeCombo.getSelectedItem();
            String name = nameField.getText().trim();
            String author = authorField.getText().trim();
            String objectType = typeField.getText().trim();
            String priceText = priceField.getText().trim();

            if (name.isEmpty() || author.isEmpty() || objectType.isEmpty() || priceText.isEmpty()) {
                JOptionPane.showMessageDialog(customDialog, "Заполните все поля", "Ошибка", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                double price = Double.parseDouble(priceText);
                StringBuilder output = new StringBuilder();
                output.append("=== Создан новый объект ===\n\n");

                if ("Book".equals(type)) {
                    Book book = new Book(name, author, objectType, price);
                    output.append("📖 Новая книга:\n");
                    output.append("• Название: ").append(book.getName()).append("\n");
                    output.append("• Автор: ").append(book.getAuthor()).append("\n");
                    output.append("• Жанр: ").append(book.getType()).append("\n");
                    output.append("• Цена: ").append(book.getPrice()).append(" руб.\n");
                } else {
                    Magazine magazine = new Magazine(name, objectType, price);
                    output.append("📰 Новый журнал:\n");
                    output.append("• Название: ").append(magazine.getName()).append("\n");
                    output.append("• Категория: ").append(magazine.getType()).append("\n");
                    output.append("• Цена: ").append(magazine.getPrice()).append(" руб.\n");
                }

                output.append("\n💡 Объект успешно создан и может быть добавлен в массив!\n");

                resultArea.setText(output.toString());
                customDialog.dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(customDialog, "Введите корректную цену", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(createButton);
        customDialog.add(new JLabel(""));
        customDialog.add(buttonPanel);

        customDialog.setVisible(true);
    }

    private JPanel createBottomPanel() {
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(new Color(240, 240, 240));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton backButton = createBackButton();
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftPanel.setBackground(new Color(240, 240, 240));
        leftPanel.add(backButton);

        JLabel infoLabel = new JLabel("Интерфейс: Printable | Классы: Book, Magazine");
        infoLabel.setFont(new Font("Arial", Font.PLAIN, 11));
        infoLabel.setForeground(Color.DARK_GRAY);

        bottomPanel.add(leftPanel, BorderLayout.WEST);
        bottomPanel.add(infoLabel, BorderLayout.EAST);

        return bottomPanel;
    }

    private JButton createBackButton() {
        JButton backButton = new JButton("← Назад к меню");
        backButton.setFont(new Font("Arial", Font.BOLD, 12));
        backButton.setBackground(new Color(108, 117, 125));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(80, 90, 100), 2),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));

        backButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                backButton.setBackground(new Color(130, 140, 150));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                backButton.setBackground(new Color(108, 117, 125));
            }
        });

        backButton.addActionListener(e -> returnToMainMenu());
        return backButton;
    }

    private void addListeners() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                returnToMainMenu();
            }
        });
    }

    private void returnToMainMenu() {
        dispose();
        if (mainMenuFrame != null) {
            mainMenuFrame.setVisible(true);
            mainMenuFrame.toFront();
        }
    }

    public static void task6_9(JFrame mainMenuFrame) {
        if (mainMenuFrame != null) {
            mainMenuFrame.setVisible(false);
        }

        SwingUtilities.invokeLater(() -> new Task6_9(mainMenuFrame).setVisible(true));
    }
}