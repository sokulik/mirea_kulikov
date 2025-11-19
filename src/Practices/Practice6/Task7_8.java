package Practices.Practice6;

import Practices.Practice6.Classes.T78.Book;
import Practices.Practice6.Classes.T78.Magazine;
import Practices.Practice6.Classes.T78.Printable;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class Task7_8 extends JFrame {
    private JFrame mainMenuFrame;
    private JTextArea resultArea;
    private List<Printable> printables;
    private JLabel statusLabel;

    public Task7_8(JFrame mainMenuFrame) {
        super("Practice 6.7-8: Книги и журналы (Printable)");
        this.mainMenuFrame = mainMenuFrame;
        this.printables = new ArrayList<>();

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(900, 700);
        setLocationRelativeTo(null);

        initializeDefaultData();
        initComponents();
        layoutComponents();
        addListeners();
        showWelcomeScreen();
    }

    private void initializeDefaultData() {
        // Добавляем данные по умолчанию как в вашем примере
        printables.add(new Book("Война и мир"));
        printables.add(new Magazine("National Geographic"));
        printables.add(new Book("Преступление и наказание"));
        printables.add(new Magazine("Forbes"));
        printables.add(new Book("Мастер и Маргарита"));
        printables.add(new Magazine("Time"));
        printables.add(new Book("1984"));
        printables.add(new Magazine("Vogue"));
        printables.add(new Book("Гарри Поттер"));
        printables.add(new Magazine("Scientific American"));
    }

    private void initComponents() {
        resultArea = new JTextArea(20, 60);
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Consolas", Font.PLAIN, 12));
        resultArea.setBackground(new Color(248, 249, 250));
        resultArea.setMargin(new Insets(10, 10, 10, 10));

        statusLabel = new JLabel("Готов к работе");
        statusLabel.setFont(new Font("Arial", Font.BOLD, 12));
        statusLabel.setForeground(new Color(70, 130, 180));
    }

    private void layoutComponents() {
        setLayout(new BorderLayout(10, 10));

        // Верхняя панель с управлением
        JPanel controlPanel = createControlPanel();
        add(controlPanel, BorderLayout.NORTH);

        // Центральная панель с результатами
        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Результаты"));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane, BorderLayout.CENTER);

        // Панель статуса
        JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        statusPanel.setBackground(new Color(240, 240, 240));
        statusPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        statusPanel.add(statusLabel);
        add(statusPanel, BorderLayout.SOUTH);
    }

    private JPanel createControlPanel() {
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        controlPanel.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));
        controlPanel.setBackground(new Color(240, 240, 240));

        // Основные операции
        JButton showBooksButton = createOperationButton("📚 Показать книги", new Color(40, 167, 69));
        showBooksButton.addActionListener(e -> showBooks());

        JButton showMagazinesButton = createOperationButton("📰 Показать журналы", new Color(70, 130, 180));
        showMagazinesButton.addActionListener(e -> showMagazines());

        JButton showAllButton = createOperationButton("📊 Показать всё", new Color(255, 193, 7));
        showAllButton.addActionListener(e -> showAll());

        JButton printAllButton = createOperationButton("🖨️ Печать всех", new Color(153, 102, 204));
        printAllButton.addActionListener(e -> printAll());

        // Управление коллекцией
        JButton addBookButton = createOperationButton("➕ Добавить книгу", new Color(108, 117, 125));
        addBookButton.addActionListener(e -> addBook());

        JButton addMagazineButton = createOperationButton("➕ Добавить журнал", new Color(108, 117, 125));
        addMagazineButton.addActionListener(e -> addMagazine());

        JButton clearButton = createOperationButton("🧹 Очистить", new Color(220, 53, 69));
        clearButton.addActionListener(e -> clearCollection());

        JButton statsButton = createOperationButton("📈 Статистика", new Color(255, 140, 0));
        statsButton.addActionListener(e -> showStatistics());

        controlPanel.add(showBooksButton);
        controlPanel.add(showMagazinesButton);
        controlPanel.add(showAllButton);
        controlPanel.add(printAllButton);
        controlPanel.add(addBookButton);
        controlPanel.add(addMagazineButton);
        controlPanel.add(clearButton);
        controlPanel.add(statsButton);

        return controlPanel;
    }

    private JButton createOperationButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        return button;
    }

    private void showBooks() {
        StringBuilder output = new StringBuilder();
        output.append("=== 📚 СПИСОК КНИГ ===\n\n");

        List<Book> books = getBooksFromPrintables();

        if (books.isEmpty()) {
            output.append("Книги не найдены\n");
        } else {
            output.append("Найдено книг: ").append(books.size()).append("\n");
            output.append("─".repeat(50)).append("\n\n");

            for (int i = 0; i < books.size(); i++) {
                Book book = books.get(i);
                output.append(String.format("%2d. 📖 %s\n", i + 1, book.getTitle()));
            }
        }

        output.append("\n💡 Использован статический метод: Book.printBooks()");

        resultArea.setText(output.toString());
        setStatus("✅ Показаны книги: " + books.size() + " шт.");
    }

    private void showMagazines() {
        StringBuilder output = new StringBuilder();
        output.append("=== 📰 СПИСОК ЖУРНАЛОВ ===\n\n");

        List<Magazine> magazines = getMagazinesFromPrintables();

        if (magazines.isEmpty()) {
            output.append("Журналы не найдены\n");
        } else {
            output.append("Найдено журналов: ").append(magazines.size()).append("\n");
            output.append("─".repeat(50)).append("\n\n");

            for (int i = 0; i < magazines.size(); i++) {
                Magazine magazine = magazines.get(i);
                output.append(String.format("%2d. 📰 %s\n", i + 1, magazine.getTitle()));
            }
        }

        output.append("\n💡 Использован статический метод: Magazine.printMagazines()");

        resultArea.setText(output.toString());
        setStatus("✅ Показаны журналы: " + magazines.size() + " шт.");
    }

    private void showAll() {
        StringBuilder output = new StringBuilder();
        output.append("=== 📊 ВСЯ КОЛЛЕКЦИЯ ===\n\n");

        output.append("Всего элементов: ").append(printables.size()).append("\n");
        output.append("─".repeat(60)).append("\n\n");

        if (printables.isEmpty()) {
            output.append("Коллекция пуста\n");
        } else {
            for (int i = 0; i < printables.size(); i++) {
                Printable item = printables.get(i);
                if (item instanceof Book) {
                    Book book = (Book) item;
                    output.append(String.format("%2d. 📖 КНИГА: %s\n", i + 1, book.getTitle()));
                } else if (item instanceof Magazine) {
                    Magazine magazine = (Magazine) item;
                    output.append(String.format("%2d. 📰 ЖУРНАЛ: %s\n", i + 1, magazine.getTitle()));
                }
            }
        }

        output.append("\n🎯 Состав коллекции:\n");
        output.append("─".repeat(30)).append("\n");
        output.append("• Книги: ").append(getBooksFromPrintables().size()).append(" шт.\n");
        output.append("• Журналы: ").append(getMagazinesFromPrintables().size()).append(" шт.");

        resultArea.setText(output.toString());
        setStatus("✅ Показана вся коллекция: " + printables.size() + " элементов");
    }

    private void printAll() {
        StringBuilder output = new StringBuilder();
        output.append("=== 🖨️ ПЕЧАТЬ ВСЕХ ЭЛЕМЕНТОВ ===\n\n");

        output.append("Запуск метода print() для всех элементов:\n");
        output.append("─".repeat(50)).append("\n\n");

        if (printables.isEmpty()) {
            output.append("Нет элементов для печати\n");
        } else {
            for (Printable item : printables) {
                if (item instanceof Book) {
                    Book book = (Book) item;
                    output.append("📖 Печатаем книгу: ").append(book.getTitle()).append("\n");
                } else if (item instanceof Magazine) {
                    Magazine magazine = (Magazine) item;
                    output.append("📰 Печатаем журнал: ").append(magazine.getTitle()).append("\n");
                }
            }
        }

        output.append("\n✅ Печать завершена!");

        resultArea.setText(output.toString());
        setStatus("🖨️ Выполнена печать всех элементов");
    }

    private void addBook() {
        String title = JOptionPane.showInputDialog(this,
                "Введите название книги:", "➕ Добавить книгу", JOptionPane.QUESTION_MESSAGE);

        if (title != null && !title.trim().isEmpty()) {
            Book book = new Book(title.trim());
            printables.add(book);
            updateDisplay();
            setStatus("✅ Добавлена книга: " + title);
        }
    }

    private void addMagazine() {
        String title = JOptionPane.showInputDialog(this,
                "Введите название журнала:", "➕ Добавить журнал", JOptionPane.QUESTION_MESSAGE);

        if (title != null && !title.trim().isEmpty()) {
            Magazine magazine = new Magazine(title.trim());
            printables.add(magazine);
            updateDisplay();
            setStatus("✅ Добавлен журнал: " + title);
        }
    }

    private void clearCollection() {
        if (printables.isEmpty()) {
            showError("❌ Коллекция уже пуста!");
            return;
        }

        int result = JOptionPane.showConfirmDialog(this,
                "Очистить всю коллекцию? (" + printables.size() + " элементов)",
                "🧹 Очистка коллекции",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (result == JOptionPane.YES_OPTION) {
            printables.clear();
            initializeDefaultData(); // Восстанавливаем данные по умолчанию
            updateDisplay();
            setStatus("🧹 Коллекция очищена и восстановлены данные по умолчанию");
        }
    }

    private void showStatistics() {
        StringBuilder output = new StringBuilder();
        output.append("=== 📈 СТАТИСТИКА КОЛЛЕКЦИИ ===\n\n");

        int totalItems = printables.size();
        int booksCount = getBooksFromPrintables().size();
        int magazinesCount = getMagazinesFromPrintables().size();

        output.append("📊 Общая статистика:\n");
        output.append("─".repeat(40)).append("\n");
        output.append("• Всего элементов: ").append(totalItems).append("\n");
        output.append("• Книг: ").append(booksCount).append(" (").append(calculatePercentage(booksCount, totalItems)).append("%)\n");
        output.append("• Журналов: ").append(magazinesCount).append(" (").append(calculatePercentage(magazinesCount, totalItems)).append("%)\n\n");

        output.append("📚 Список книг:\n");
        output.append("─".repeat(40)).append("\n");
        List<Book> books = getBooksFromPrintables();
        if (books.isEmpty()) {
            output.append("Книги отсутствуют\n");
        } else {
            for (Book book : books) {
                output.append("• 📖 ").append(book.getTitle()).append("\n");
            }
        }

        output.append("\n📰 Список журналов:\n");
        output.append("─".repeat(40)).append("\n");
        List<Magazine> magazines = getMagazinesFromPrintables();
        if (magazines.isEmpty()) {
            output.append("Журналы отсутствуют\n");
        } else {
            for (Magazine magazine : magazines) {
                output.append("• 📰 ").append(magazine.getTitle()).append("\n");
            }
        }

        output.append("\n💡 Архитектура приложения:\n");
        output.append("─".repeat(40)).append("\n");
        output.append("• Интерфейс: Printable с методом print()\n");
        output.append("• Классы: Book и Magazine реализуют Printable\n");
        output.append("• Статические методы для фильтрации\n");

        resultArea.setText(output.toString());
        setStatus("📈 Показана статистика коллекции");
    }

    private List<Book> getBooksFromPrintables() {
        List<Book> books = new ArrayList<>();
        for (Printable item : printables) {
            if (item instanceof Book) {
                books.add((Book) item);
            }
        }
        return books;
    }

    private List<Magazine> getMagazinesFromPrintables() {
        List<Magazine> magazines = new ArrayList<>();
        for (Printable item : printables) {
            if (item instanceof Magazine) {
                magazines.add((Magazine) item);
            }
        }
        return magazines;
    }

    private String calculatePercentage(int part, int total) {
        if (total == 0) return "0";
        return String.format("%.1f", (double) part / total * 100);
    }

    private void updateDisplay() {
        // Обновляем отображение, показывая текущее состояние
        showAll();
    }

    private void setStatus(String message) {
        statusLabel.setText(message);

        // Автоматическое очищение статуса через 3 секунды
        Timer timer = new Timer(3000, e -> statusLabel.setText("Готов к работе"));
        timer.setRepeats(false);
        timer.start();
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Ошибка", JOptionPane.ERROR_MESSAGE);
    }

    private void showWelcomeScreen() {
        StringBuilder welcome = new StringBuilder();
        welcome.append("🎯 КНИГИ И ЖУРНАЛЫ - ИНТЕРФЕЙС PRINTABLE\n");
        welcome.append("═".repeat(60)).append("\n\n");
        welcome.append("Добро пожаловать в систему управления печатными изданиями!\n\n");

        welcome.append("📚 Архитектура приложения:\n");
        welcome.append("─".repeat(40)).append("\n");
        welcome.append("• Интерфейс Printable с методом print()\n");
        welcome.append("• Класс Book - представляет книги\n");
        welcome.append("• Класс Magazine - представляет журналы\n");
        welcome.append("• Статические методы для фильтрации\n\n");

        welcome.append("🚀 Основные возможности:\n");
        welcome.append("─".repeat(30)).append("\n");
        welcome.append("• 📚 Просмотр списка книг\n");
        welcome.append("• 📰 Просмотр списка журналов\n");
        welcome.append("• 📊 Просмотр всей коллекции\n");
        welcome.append("• 🖨️ Имитация печати всех элементов\n");
        welcome.append("• ➕ Добавление новых элементов\n");
        welcome.append("• 📈 Статистика коллекции\n\n");

        welcome.append("💡 Уже загружено: ").append(printables.size()).append(" элементов\n");

        resultArea.setText(welcome.toString());
    }

    private JPanel createBottomPanel() {
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(new Color(240, 240, 240));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton backButton = createBackButton();
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftPanel.setBackground(new Color(240, 240, 240));
        leftPanel.add(backButton);

        JLabel infoLabel = new JLabel("Интерфейс: Printable | Классы: Book, Magazine | Статические методы");
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

    public static void task7_8(JFrame mainMenuFrame) {
        if (mainMenuFrame != null) {
            mainMenuFrame.setVisible(false);
        }

        SwingUtilities.invokeLater(() -> new Task7_8(mainMenuFrame).setVisible(true));
    }
}