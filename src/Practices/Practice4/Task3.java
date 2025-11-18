package Practices.Practice4;

import Practices.Practice4.Classes.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class Task3 extends JFrame {
    private JFrame mainMenuFrame;
    private JTextArea resultArea;
    private ShoppingCart cart;
    private Map<String, List<Product>> catalog;
    private String currentUser;

    public Task3(JFrame mainMenuFrame) {
        super("Practice 4.3: Интернет-магазин");
        this.mainMenuFrame = mainMenuFrame;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 700);
        setLocationRelativeTo(null);

        cart = new ShoppingCart();
        initializeCatalog();
        currentUser = "Гость";

        initComponents();
        layoutComponents();
        addListeners();
        showWelcomeMessage();
    }

    private void initComponents() {
        resultArea = new JTextArea(20, 60);
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Consolas", Font.PLAIN, 12));
        resultArea.setBackground(new Color(248, 249, 250));
    }

    private void layoutComponents() {
        setLayout(new BorderLayout(10, 10));

        // Верхняя панель с управлением
        JPanel controlPanel = createControlPanel();
        add(controlPanel, BorderLayout.NORTH);

        // Центральная панель с результатами
        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Интернет-магазин"));
        add(scrollPane, BorderLayout.CENTER);

        // Панель снизу с кнопкой назад
        JPanel bottomPanel = createBottomPanel();
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private JPanel createControlPanel() {
        JPanel controlPanel = new JPanel(new GridLayout(2, 4, 5, 5));
        controlPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        controlPanel.setBackground(new Color(240, 240, 240));

        JButton catalogButton = new JButton("📁 Каталог");
        catalogButton.setBackground(new Color(70, 130, 180));
        catalogButton.setForeground(Color.WHITE);
        catalogButton.addActionListener(e -> showCategories());

        JButton cartButton = new JButton("🛒 Корзина");
        cartButton.setBackground(new Color(255, 193, 7));
        cartButton.setForeground(Color.BLACK);
        cartButton.addActionListener(e -> showCart());

        JButton checkoutButton = new JButton("💳 Оформить заказ");
        checkoutButton.setBackground(new Color(40, 167, 69));
        checkoutButton.setForeground(Color.WHITE);
        checkoutButton.addActionListener(e -> checkout());

        JButton clearButton = new JButton("🗑️ Очистить корзину");
        clearButton.setBackground(new Color(220, 53, 69));
        clearButton.setForeground(Color.WHITE);
        clearButton.addActionListener(e -> clearCart());

        JButton demoButton = new JButton("🎭 Демо данные");
        demoButton.setBackground(new Color(108, 117, 125));
        demoButton.setForeground(Color.WHITE);
        demoButton.addActionListener(e -> showDemoData());

        controlPanel.add(catalogButton);
        controlPanel.add(cartButton);
        controlPanel.add(checkoutButton);
        controlPanel.add(clearButton);
        controlPanel.add(demoButton);

        return controlPanel;
    }

    private void initializeCatalog() {
        catalog = new HashMap<>();

        List<Product> electronics = new ArrayList<>();
        List<Product> books = new ArrayList<>();
        List<Product> clothesMan = new ArrayList<>();
        List<Product> clothesWoman = new ArrayList<>();
        List<Product> candles = new ArrayList<>();
        List<Product> sport = new ArrayList<>();
        List<Product> toys = new ArrayList<>();
        List<Product> beauty = new ArrayList<>();

        electronics.add(new Product("Iphone17", 150000, "electronix"));
        electronics.add(new Product("Iphone17 Pro Max", 300000, "electronix"));
        electronics.add(new Product("Iphone17 Air", 220000, "electronix"));
        electronics.add(new Product("Iphone16", 70000, "electronix"));
        electronics.add(new Product("Iphone16 Pro Max", 100000, "electronix"));
        electronics.add(new Product("Iphone16 Plus", 85000, "electronix"));
        electronics.add(new Product("Iphone15", 60000, "electronix"));
        electronics.add(new Product("Iphone15 Pro Max", 80000, "electronix"));
        electronics.add(new Product("Iphone15 Plus", 70000, "electronix"));
        electronics.add(new Product("Iphone13", 40000, "electronix"));
        electronics.add(new Product("Iphone13 Pro Max", 60000, "electronix"));
        electronics.add(new Product("Iphone13 Plus", 55000, "electronix"));
        electronics.add(new Product("Iphone12", 35000, "electronix"));
        electronics.add(new Product("Iphone12 Pro Max", 40000, "electronix"));
        electronics.add(new Product("Iphone12 Plus", 37000, "electronix"));
        electronics.add(new Product("Iphone11", 30000, "electronix"));
        electronics.add(new Product("Iphone11 Pro Max", 35000, "electronix"));
        electronics.add(new Product("Iphone11 Plus", 33000, "electronix"));
        electronics.add(new Product("Iphone SE", 35000, "electronix"));

        books.add(new Product("Вокруг света за 80 дней", "Жюль Верн", 3000, "books"));
        books.add(new Product("1984", "Джордж Оруэлл", 2800, "books"));
        books.add(new Product("Мастер и Маргарита", "Михаил Булгаков", 3500, "books"));
        books.add(new Product("Три товарища", "Эрих Мария Ремарк", 3200, "books"));
        books.add(new Product("Гарри Поттер и философский камень", "Джоан Роулинг", 4000, "books"));
        books.add(new Product("Преступление и наказание", "Федор Достоевский", 2900, "books"));
        books.add(new Product("Маленький принц", "Антуан де Сент-Экзюпери", 2500, "books"));
        books.add(new Product("Убить пересмешника", "Харпер Ли", 3100, "books"));
        books.add(new Product("Война и мир", "Лев Толстой", 4500, "books"));
        books.add(new Product("Алхимик", "Пауло Коэльо", 3300, "books"));

        clothesMan.add(new Product("Мужская футболка", Size.M, 4000, "clothesman"));
        clothesMan.add(new Product("Футболка поло", Size.M, 4500, "clothesman"));
        clothesMan.add(new Product("Футболка поло", Size.L, 4500, "clothesman"));
        clothesMan.add(new Product("Свитшот с капюшоном (Худи)", Size.S, 8900, "clothesman"));
        clothesMan.add(new Product("Свитшот с капюшоном (Худи)", Size.M, 8900, "clothesman"));
        clothesMan.add(new Product("Классические прямые джинсы", Size.L, 7500, "clothesman"));
        clothesMan.add(new Product("Классические прямые джинсы", Size.XS, 7500, "clothesman"));
        clothesMan.add(new Product("Олимпийка", Size.M, 6200, "clothesman"));
        clothesMan.add(new Product("Олимпийка", Size.S, 6200, "clothesman"));
        clothesMan.add(new Product("Хлопковые шорты", Size.L, 4800, "clothesman"));

        clothesWoman.add(new Product("Укороченный топ", Size.XS, 3500, "clotheswoman"));
        clothesWoman.add(new Product("Укороченный топ", Size.S, 3500, "clotheswoman"));
        clothesWoman.add(new Product("Платье-миди", Size.M, 12000, "clotheswoman"));
        clothesWoman.add(new Product("Платье-миди", Size.L, 12000, "clotheswoman"));
        clothesWoman.add(new Product("Кожаная юбка", Size.S, 9500, "clotheswoman"));
        clothesWoman.add(new Product("Кожаная юбка", Size.M, 9500, "clotheswoman"));
        clothesWoman.add(new Product("Кардиган oversize", Size.L, 7800, "clotheswoman"));
        clothesWoman.add(new Product("Кардиган oversize", Size.XS, 7800, "clotheswoman"));
        clothesWoman.add(new Product("Блузка с жабо", Size.M, 6500, "clotheswoman"));
        clothesWoman.add(new Product("Блузка с жабо", Size.S, 6500, "clotheswoman"));

        candles.add(new Product("Свеча 30x10", "Хвоя", 2000, "candles"));
        candles.add(new Product("Свеча 20x8", "Ваниль", 1500, "candles"));
        candles.add(new Product("Свеча 25x9", "Лаванда", 1800, "candles"));
        candles.add(new Product("Свеча 15x6", "Корица", 1200, "candles"));
        candles.add(new Product("Свеча 22x7", "Роза", 1600, "candles"));
        candles.add(new Product("Свеча 18x5", "Кокос", 1300, "candles"));
        candles.add(new Product("Свеча 28x11", "Сандал", 2200, "candles"));
        candles.add(new Product("Свеча 35x12", "Океан", 2500, "candles"));
        candles.add(new Product("Свеча 40x15", "Кофе", 3000, "candles"));
        candles.add(new Product("Свеча 25x8", "Мята", 1700, "candles"));
        candles.add(new Product("Свеча 30x10", "Хвоя", 2000, "candles"));

        sport.add(new Product("Футбольный мяч", 4500, "sport"));
        sport.add(new Product("Баскетбольный мяч", 5200, "sport"));
        sport.add(new Product("Теннисная ракетка", 8900, "sport"));
        sport.add(new Product("Беговая дорожка", 45000, "sport"));
        sport.add(new Product("Гантели 5 кг", 3000, "sport"));
        sport.add(new Product("Велосипед горный", 78000, "sport"));
        sport.add(new Product("Йога-мат", 2500, "sport"));
        sport.add(new Product("Скакалка", 800, "sport"));
        sport.add(new Product("Тренажер для пресса", 12000, "sport"));
        sport.add(new Product("Эспандер", 1500, "sport"));

        toys.add(new Product("Конструктор LEGO", 4500, "toys"));
        toys.add(new Product("Кукла Barbie", 3200, "toys"));
        toys.add(new Product("Мягкий мишка", 1800, "toys"));
        toys.add(new Product("Настольная игра Монополия", 2900, "toys"));
        toys.add(new Product("Детский набор доктора", 2100, "toys"));
        toys.add(new Product("Железная дорога", 6700, "toys"));
        toys.add(new Product("Набор для рисования", 1500, "toys"));
        toys.add(new Product("Интерактивный робот", 8900, "toys"));
        toys.add(new Product("Пазл 1000 элементов", 1200, "toys"));
        toys.add(new Product("Настольная игра Джанга", 2300, "toys"));

        beauty.add(new Product("Тушь", 2500, "beauty"));
        beauty.add(new Product("Помада", 3200, "beauty"));
        beauty.add(new Product("Блеск для губ", 1800, "beauty"));
        beauty.add(new Product("Тональный крем", 4100, "beauty"));
        beauty.add(new Product("Тени для век", 2900, "beauty"));
        beauty.add(new Product("Пудра", 2300, "beauty"));
        beauty.add(new Product("Гель для бровей", 1700, "beauty"));
        beauty.add(new Product("Румяна", 2200, "beauty"));
        beauty.add(new Product("Консилер", 1900, "beauty"));
        beauty.add(new Product("Хайлайтер", 2700, "beauty"));

        catalog.put("Электроника", electronics);
        catalog.put("Книги", books);
        catalog.put("Мужская одежда", clothesMan);
        catalog.put("Женская одежда", clothesWoman);
        catalog.put("Свечи", candles);
        catalog.put("Спорт", sport);
        catalog.put("Игрушки", toys);
        catalog.put("Косметика", beauty);
    }

    private void showCategories() {
        StringBuilder output = new StringBuilder();
        output.append("=== Каталог товаров ===\n\n");

        List<String> categories = new ArrayList<>(catalog.keySet());
        for (int i = 0; i < categories.size(); i++) {
            String category = categories.get(i);
            output.append((i + 1) + ". " + category + "\n");
            List<Product> products = catalog.get(category);
            for (Product product : products) {
                output.append("   └ " + product + "\n");
            }
            output.append("\n");
        }

        output.append("💡 Для добавления в корзину используйте кнопку 'Демо данные'\n");

        resultArea.setText(output.toString());
    }

    private void showCart() {
        StringBuilder output = new StringBuilder();
        output.append("=== Корзина ===\n\n");

        if (cart.getItems().isEmpty()) {
            output.append("Корзина пуста\n");
        } else {
            output.append(cart.toString()).append("\n");
        }

        resultArea.setText(output.toString());
    }

    private void checkout() {
        StringBuilder output = new StringBuilder();
        output.append("=== Оформление заказа ===\n\n");

        if (cart.getItems().isEmpty()) {
            output.append("Корзина пуста! Добавьте товары перед оформлением заказа.\n");
        } else {
            output.append(cart.toString()).append("\n\n");
            output.append("✅ Заказ успешно оформлен!\n");
            output.append("Спасибо за покупку, ").append(currentUser).append("!\n");
            cart.clear();
        }

        resultArea.setText(output.toString());
    }

    private void clearCart() {
        cart.clear();
        resultArea.setText("🛒 Корзина очищена!\n");
    }

    private void showDemoData() {
        // Добавляем демо-товары в корзину
        cart.addItem(new Product("Iphone17", 150000, "electronix"));
        cart.addItem(new Product("Мастер и Маргарита", "Михаил Булгаков", 3500, "books"));
        cart.addItem(new Product("Мужская футболка", Size.M, 4000, "clothesman"));
        cart.addItem(new Product("Свеча 30x10", "Хвоя", 2000, "candles"));
        cart.addItem(new Product("Футбольный мяч", 4500, "sport"));

        StringBuilder output = new StringBuilder();
        output.append("=== Демонстрационные данные ===\n\n");
        output.append("✅ Добавлены демо-товары в корзину:\n");
        output.append("• Iphone17 - 150000 руб.\n");
        output.append("• Мастер и Маргарита - 3500 руб.\n");
        output.append("• Мужская футболка - 4000 руб.\n");
        output.append("• Свеча Хвоя - 2000 руб.\n");
        output.append("• Футбольный мяч - 4500 руб.\n\n");
        output.append("🛒 Теперь в корзине ").append(cart.getItems().size()).append(" товаров\n");
        output.append("💰 Общая сумма: ").append(cart.getTotalPrice()).append(" руб.\n");

        resultArea.setText(output.toString());
    }

    private void showWelcomeMessage() {
        StringBuilder output = new StringBuilder();
        output.append("🛍️  Добро пожаловать в Интернет-магазин!\n\n");
        output.append("Текущий пользователь: ").append(currentUser).append("\n\n");
        output.append("Доступные функции:\n");
        output.append("• 📁 Каталог - просмотр товаров\n");
        output.append("• 🛒 Корзина - просмотр корзины\n");
        output.append("• 💳 Оформить заказ - завершение покупки\n");
        output.append("• 🗑️ Очистить корзину - удаление всех товаров\n");
        output.append("• 🎭 Демо данные - добавление тестовых товаров\n\n");
        output.append("💡 Нажмите 'Демо данные' для начала работы!\n");

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

        JLabel infoLabel = new JLabel("Классы: Product, ShoppingCart, User, UserManager | Перечисления: Size");
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

    public static void task3(JFrame mainMenuFrame) {
        if (mainMenuFrame != null) {
            mainMenuFrame.setVisible(false);
        }

        SwingUtilities.invokeLater(() -> new Task3(mainMenuFrame).setVisible(true));
    }
}