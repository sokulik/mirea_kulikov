package Practices.Practice5;

import Practices.Practice5.Classes.T10.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.LinkedHashMap;

public class Task10 extends JFrame {
    private JFrame mainMenuFrame;
    private JTextArea resultArea;
    private ShoppingCart cart;
    private Map<String, List<Product>> catalog;
    private BuildComputer buildComputer;
    private String currentUser;

    public Task10(JFrame mainMenuFrame) {
        super("Practice 5.10: Интернет-магазин");
        this.mainMenuFrame = mainMenuFrame;
        this.cart = new ShoppingCart();
        this.buildComputer = new BuildComputer(cart);
        this.currentUser = null;
        this.catalog = new LinkedHashMap<>();

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(900, 750);
        setLocationRelativeTo(null);

        initializeCatalog();
        initComponents();
        layoutComponents();
        addListeners();
        showAuthScreen();
    }

    private void initializeCatalog() {
        // Компьютеры и компоненты
        List<Product> computers = new ArrayList<>();
        computers.add(new SimpleProduct("Собрать компьютер", 0, "computers"));

        // Электроника
        List<Product> electronics = new ArrayList<>();
        electronics.add(new SimpleProduct("iPhone 17", "Apple", 150000, "electronics"));
        electronics.add(new SimpleProduct("iPhone 17 Pro Max", "Apple", 300000, "electronics"));
        electronics.add(new SimpleProduct("Samsung Galaxy S24", "Samsung", 80000, "electronics"));
        electronics.add(new SimpleProduct("MacBook Pro", "Apple", 250000, "electronics"));

        // Книги
        List<Product> books = new ArrayList<>();
        books.add(new SimpleProduct("Вокруг света за 80 дней", "Жюль Верн", 3000, "books"));
        books.add(new SimpleProduct("1984", "Джордж Оруэлл", 2800, "books"));
        books.add(new SimpleProduct("Мастер и Маргарита", "Михаил Булгаков", 3500, "books"));

        // Мужская одежда
        List<Product> clothesMan = new ArrayList<>();
        clothesMan.add(new SimpleProduct("Мужская футболка", Size.M, 4000, "clothes"));
        clothesMan.add(new SimpleProduct("Джинсы классические", Size.L, 7500, "clothes"));
        clothesMan.add(new SimpleProduct("Худи с капюшоном", Size.M, 8900, "clothes"));
        clothesMan.add(new SimpleProduct("Рубашка офисная", Size.XL, 6500, "clothes"));

        // Женская одежда
        List<Product> clothesWoman = new ArrayList<>();
        clothesWoman.add(new SimpleProduct("Платье-миди", Size.M, 12000, "clothes"));
        clothesWoman.add(new SimpleProduct("Укороченный топ", Size.S, 3500, "clothes"));
        clothesWoman.add(new SimpleProduct("Кардиган oversize", Size.L, 7800, "clothes"));
        clothesWoman.add(new SimpleProduct("Юбка кожаная", Size.S, 9500, "clothes"));

        // Свечи и ароматы
        List<Product> candles = new ArrayList<>();
        candles.add(new SimpleProduct("Свеча 'Хвойный лес'", 2000, "30x10 см", "candles"));
        candles.add(new SimpleProduct("Свеча 'Ванильная мечта'", 1500, "20x8 см", "candles"));
        candles.add(new SimpleProduct("Свеча 'Лавандовый рай'", 1800, "25x9 см", "candles"));
        candles.add(new SimpleProduct("Свеча 'Морская свежесть'", 2500, "35x12 см", "candles"));

        // Спорт и фитнес
        List<Product> sport = new ArrayList<>();
        sport.add(new SimpleProduct("Футбольный мяч", 4500, "Профессиональный", "sport"));
        sport.add(new SimpleProduct("Беговая дорожка", 45000, "Электрическая", "sport"));
        sport.add(new SimpleProduct("Гантели 5 кг", 3000, "Набор 2 шт", "sport"));
        sport.add(new SimpleProduct("Йога-мат", 2500, "ПВХ 6мм", "sport"));

        // Игрушки
        List<Product> toys = new ArrayList<>();
        toys.add(new SimpleProduct("Конструктор LEGO", 4500, "City 1500 деталей", "toys"));
        toys.add(new SimpleProduct("Кукла Barbie", 3200, "С аксессуарами", "toys"));
        toys.add(new SimpleProduct("Настольная игра Монополия", 2900, "Классическая", "toys"));
        toys.add(new SimpleProduct("Мягкий мишка", 1800, "30 см", "toys"));

        // Косметика
        List<Product> beauty = new ArrayList<>();
        beauty.add(new SimpleProduct("Тушь для ресниц", 2500, "Объемная", "beauty"));
        beauty.add(new SimpleProduct("Помада", 3200, "Матовая", "beauty"));
        beauty.add(new SimpleProduct("Тональный крем", 4100, "SPF 30", "beauty"));
        beauty.add(new SimpleProduct("Тени для век", 2900, "Палетка 12 цветов", "beauty"));

        catalog.put("💻 Компьютеры", computers);
        catalog.put("📱 Электроника", electronics);
        catalog.put("📚 Книги", books);
        catalog.put("👔 Мужская одежда", clothesMan);
        catalog.put("👗 Женская одежда", clothesWoman);
        catalog.put("🕯️ Свечи и ароматы", candles);
        catalog.put("🏃 Спорт и фитнес", sport);
        catalog.put("🧸 Игрушки", toys);
        catalog.put("💄 Косметика", beauty);
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

        // Верхняя панель с информацией о пользователе и корзине
        JPanel infoPanel = createInfoPanel();
        add(infoPanel, BorderLayout.NORTH);

        // Центральная панель с результатами
        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Интернет-магазин"));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane, BorderLayout.CENTER);

        // Панель снизу с кнопками
        JPanel bottomPanel = createBottomPanel();
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private JPanel createInfoPanel() {
        JPanel infoPanel = new JPanel(new BorderLayout());
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        infoPanel.setBackground(new Color(240, 240, 240));

        JLabel userLabel = new JLabel("👤 Гость");
        userLabel.setFont(new Font("Arial", Font.BOLD, 14));
        userLabel.setForeground(new Color(70, 130, 180));

        JLabel cartLabel = new JLabel("🛒 Корзина: 0 товаров | 0.00 руб.");
        cartLabel.setFont(new Font("Arial", Font.BOLD, 12));
        cartLabel.setForeground(new Color(40, 167, 69));

        // Обновляем метки при изменениях
        Timer timer = new Timer(1000, e -> {
            userLabel.setText("👤 " + (currentUser != null ? currentUser : "Гость"));
            cartLabel.setText("🛒 Корзина: " + cart.getItemCount() + " товаров | " +
                    String.format("%.2f", cart.getTotalPrice()) + " руб.");
        });
        timer.start();

        infoPanel.add(userLabel, BorderLayout.WEST);
        infoPanel.add(cartLabel, BorderLayout.EAST);

        return infoPanel;
    }

    private JPanel createBottomPanel() {
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(new Color(240, 240, 240));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton backButton = createBackButton();
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftPanel.setBackground(new Color(240, 240, 240));
        leftPanel.add(backButton);

        // Панель с основными кнопками
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        controlPanel.setBackground(new Color(240, 240, 240));

        JButton authButton = new JButton("🔐 Авторизация");
        authButton.setBackground(new Color(70, 130, 180));
        authButton.setForeground(Color.WHITE);
        authButton.addActionListener(e -> showAuthDialog());

        JButton catalogButton = new JButton("📂 Каталог");
        catalogButton.setBackground(new Color(40, 167, 69));
        catalogButton.setForeground(Color.WHITE);
        catalogButton.addActionListener(e -> showCatalog());

        JButton cartButton = new JButton("🛒 Корзина");
        cartButton.setBackground(new Color(255, 193, 7));
        cartButton.setForeground(Color.BLACK);
        cartButton.addActionListener(e -> showCart());

        JButton buildPcButton = new JButton("🔧 Сборка ПК");
        buildPcButton.setBackground(new Color(108, 117, 125));
        buildPcButton.setForeground(Color.WHITE);
        buildPcButton.addActionListener(e -> buildComputer());

        JButton checkoutButton = new JButton("💳 Оформить");
        checkoutButton.setBackground(new Color(220, 53, 69));
        checkoutButton.setForeground(Color.WHITE);
        checkoutButton.addActionListener(e -> checkout());

        controlPanel.add(authButton);
        controlPanel.add(catalogButton);
        controlPanel.add(cartButton);
        controlPanel.add(buildPcButton);
        controlPanel.add(checkoutButton);

        bottomPanel.add(leftPanel, BorderLayout.WEST);
        bottomPanel.add(controlPanel, BorderLayout.CENTER);

        return bottomPanel;
    }

    private void showAuthScreen() {
        StringBuilder output = new StringBuilder();
        output.append("🎉 Добро пожаловать в Интернет-магазин!\n");
        output.append("═".repeat(50)).append("\n\n");
        output.append("Для начала работы необходимо авторизоваться.\n\n");
        output.append("💡 Возможности магазина:\n");
        output.append("• 📂 Просмотр каталога товаров\n");
        output.append("• 🛒 Добавление товаров в корзину\n");
        output.append("• 🔧 Сборка компьютера на заказ\n");
        output.append("• 💳 Оформление заказа\n");
        output.append("• 👤 Система пользователей\n\n");
        output.append("Нажмите кнопку '🔐 Авторизация' для входа или регистрации.");

        resultArea.setText(output.toString());
    }

    private void showAuthDialog() {
        JDialog authDialog = new JDialog(this, "🔐 Авторизация", true);
        authDialog.setLayout(new GridLayout(4, 2, 10, 10));
        authDialog.setSize(350, 200);
        authDialog.setLocationRelativeTo(this);

        JComboBox<String> authType = new JComboBox<>(new String[]{"Вход", "Регистрация"});
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JLabel resultLabel = new JLabel("");

        authDialog.add(new JLabel("Тип:"));
        authDialog.add(authType);
        authDialog.add(new JLabel("Логин:"));
        authDialog.add(usernameField);
        authDialog.add(new JLabel("Пароль:"));
        authDialog.add(passwordField);
        authDialog.add(resultLabel);

        JButton okButton = new JButton("OK");
        okButton.setBackground(new Color(70, 130, 180));
        okButton.setForeground(Color.WHITE);
        okButton.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword());
            boolean isLogin = authType.getSelectedIndex() == 0;

            if (username.isEmpty() || password.isEmpty()) {
                resultLabel.setText("❌ Заполните все поля");
                resultLabel.setForeground(Color.RED);
                return;
            }

            if (isLogin) {
                if (UserManager.validateUser(username, password)) {
                    currentUser = username;
                    resultLabel.setText("✅ Вход выполнен!");
                    resultLabel.setForeground(Color.GREEN);
                    authDialog.dispose();
                    showMainScreen();
                } else {
                    resultLabel.setText("❌ Неверный логин/пароль");
                    resultLabel.setForeground(Color.RED);
                }
            } else {
                if (UserManager.userExists(username)) {
                    resultLabel.setText("❌ Пользователь существует");
                    resultLabel.setForeground(Color.RED);
                } else if (password.length() < 3) {
                    resultLabel.setText("❌ Пароль < 3 символов");
                    resultLabel.setForeground(Color.RED);
                } else {
                    User newUser = new User(username, password);
                    UserManager.saveUser(newUser);
                    currentUser = username;
                    resultLabel.setText("✅ Регистрация успешна!");
                    resultLabel.setForeground(Color.GREEN);
                    authDialog.dispose();
                    showMainScreen();
                }
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(okButton);
        authDialog.add(new JLabel(""));
        authDialog.add(buttonPanel);

        authDialog.setVisible(true);
    }

    private void showMainScreen() {
        StringBuilder output = new StringBuilder();
        output.append("🏠 Добро пожаловать, ").append(currentUser).append("!\n");
        output.append("═".repeat(50)).append("\n\n");
        output.append("📊 Статистика:\n");
        output.append("• 👤 Пользователей в системе: ").append(UserManager.getUserCount()).append("\n");
        output.append("• 🛒 Товаров в корзине: ").append(cart.getItemCount()).append("\n");
        output.append("• 💰 Сумма корзины: ").append(String.format("%.2f", cart.getTotalPrice())).append(" руб.\n\n");
        output.append("🎯 Доступные действия:\n");
        output.append("• 📂 Каталог товаров - просмотр и покупка\n");
        output.append("• 🔧 Сборка ПК - создание компьютера на заказ\n");
        output.append("• 🛒 Корзина - управление покупками\n");
        output.append("• 💳 Оформить - завершение покупки\n");

        resultArea.setText(output.toString());
    }

    private void showCatalog() {
        if (currentUser == null) {
            JOptionPane.showMessageDialog(this, "❌ Сначала авторизуйтесь!", "Ошибка", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JDialog catalogDialog = new JDialog(this, "📂 Каталог товаров", true);
        catalogDialog.setLayout(new BorderLayout());
        catalogDialog.setSize(600, 500);
        catalogDialog.setLocationRelativeTo(this);

        JTabbedPane tabbedPane = new JTabbedPane();

        for (Map.Entry<String, List<Product>> entry : catalog.entrySet()) {
            String category = entry.getKey();
            List<Product> products = entry.getValue();

            JPanel categoryPanel = new JPanel(new BorderLayout());
            DefaultListModel<String> listModel = new DefaultListModel<>();
            JList<String> productList = new JList<>(listModel);
            productList.setFont(new Font("Consolas", Font.PLAIN, 12));

            for (Product product : products) {
                listModel.addElement(product.display());
            }

            JScrollPane scrollPane = new JScrollPane(productList);
            categoryPanel.add(scrollPane, BorderLayout.CENTER);

            JButton addButton = new JButton("➕ Добавить в корзину");
            addButton.setBackground(new Color(40, 167, 69));
            addButton.setForeground(Color.WHITE);
            addButton.addActionListener(e -> {
                int selectedIndex = productList.getSelectedIndex();
                if (selectedIndex != -1) {
                    Product selectedProduct = products.get(selectedIndex);

                    if (selectedProduct.getName().equals("Собрать компьютер")) {
                        catalogDialog.dispose();
                        buildComputer();
                    } else {
                        cart.addItem(selectedProduct);
                        JOptionPane.showMessageDialog(catalogDialog,
                                "✅ Добавлено: " + selectedProduct.getShortInfo(),
                                "Успех", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            });

            categoryPanel.add(addButton, BorderLayout.SOUTH);
            tabbedPane.addTab(category, categoryPanel);
        }

        catalogDialog.add(tabbedPane, BorderLayout.CENTER);
        catalogDialog.setVisible(true);
    }

    private void showCart() {
        if (currentUser == null) {
            JOptionPane.showMessageDialog(this, "❌ Сначала авторизуйтесь!", "Ошибка", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (cart.isEmpty()) {
            JOptionPane.showMessageDialog(this, "🛒 Корзина пуста!", "Информация", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        StringBuilder output = new StringBuilder();
        output.append("🛒 Содержимое корзины\n");
        output.append("═".repeat(60)).append("\n\n");

        List<Product> items = cart.getItems();
        for (int i = 0; i < items.size(); i++) {
            output.append(String.format("%2d. %s\n", i + 1, items.get(i).getShortInfo()));
        }

        output.append("\n").append("═".repeat(60)).append("\n");
        output.append("💰 Общая сумма: ").append(String.format("%.2f", cart.getTotalPrice())).append(" руб.\n");
        output.append("📦 Количество товаров: ").append(cart.getItemCount()).append("\n\n");
        output.append("💡 Используйте кнопку '💳 Оформить' для завершения покупки.");

        resultArea.setText(output.toString());
    }

    private void buildComputer() {
        if (currentUser == null) {
            JOptionPane.showMessageDialog(this, "❌ Сначала авторизуйтесь!", "Ошибка", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Упрощенная версия сборки компьютера для GUI
        JDialog pcDialog = new JDialog(this, "🔧 Сборка компьютера", true);
        pcDialog.setLayout(new GridLayout(4, 2, 10, 10));
        pcDialog.setSize(400, 300);
        pcDialog.setLocationRelativeTo(this);

        // Монитор
        JComboBox<String> monitorCombo = new JComboBox<>(new String[]{
                "Samsung Odyssey 34\" 165Hz - 55000 руб.",
                "Asus ROG Swift 27\" 240Hz - 50000 руб.",
                "Acer Predator 34\" 180Hz - 60000 руб.",
                "HP Omen 27\" 165Hz - 38000 руб."
        });

        // Процессор
        JComboBox<String> cpuCombo = new JComboBox<>(new String[]{
                "Intel i9-13900K - 60000 руб.",
                "AMD Ryzen 9 7950X - 70000 руб.",
                "Apple M3 - 120000 руб.",
                "Intel i7-13700K - 40000 руб."
        });

        // SSD
        JComboBox<String> ssdCombo = new JComboBox<>(new String[]{
                "256GB SSD - 2500 руб.",
                "512GB SSD - 4000 руб.",
                "1TB SSD - 7000 руб."
        });

        JLabel totalLabel = new JLabel("Итого: 0 руб.");

        pcDialog.add(new JLabel("🖥️ Монитор:"));
        pcDialog.add(monitorCombo);
        pcDialog.add(new JLabel("⚡ Процессор:"));
        pcDialog.add(cpuCombo);
        pcDialog.add(new JLabel("💾 Накопитель:"));
        pcDialog.add(ssdCombo);
        pcDialog.add(totalLabel);

        // Обновление общей суммы
        Runnable updateTotal = () -> {
            int total = getPriceFromCombo(monitorCombo) +
                    getPriceFromCombo(cpuCombo) +
                    getPriceFromCombo(ssdCombo);
            totalLabel.setText("Итого: " + total + " руб.");
        };

        monitorCombo.addActionListener(e -> updateTotal.run());
        cpuCombo.addActionListener(e -> updateTotal.run());
        ssdCombo.addActionListener(e -> updateTotal.run());

        JButton buildButton = new JButton("🔨 Собрать");
        buildButton.setBackground(new Color(70, 130, 180));
        buildButton.setForeground(Color.WHITE);
        buildButton.addActionListener(e -> {
            int total = getPriceFromCombo(monitorCombo) +
                    getPriceFromCombo(cpuCombo) +
                    getPriceFromCombo(ssdCombo);

            // Создаем простой продукт для компьютера
            SimpleProduct computer = new SimpleProduct(
                    "Собранный компьютер", total, "computers"
            );

            cart.addItem(computer);
            JOptionPane.showMessageDialog(pcDialog,
                    "✅ Компьютер собран и добавлен в корзину!\n💰 Стоимость: " + total + " руб.",
                    "Успех", JOptionPane.INFORMATION_MESSAGE);
            pcDialog.dispose();
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(buildButton);
        pcDialog.add(new JLabel(""));
        pcDialog.add(buttonPanel);

        updateTotal.run();
        pcDialog.setVisible(true);
    }

    private int getPriceFromCombo(JComboBox<String> combo) {
        String text = (String) combo.getSelectedItem();
        if (text != null && text.contains("руб.")) {
            String[] parts = text.split(" - ");
            if (parts.length > 1) {
                try {
                    return Integer.parseInt(parts[1].replace(" руб.", "").trim());
                } catch (NumberFormatException e) {
                    return 0;
                }
            }
        }
        return 0;
    }

    private void checkout() {
        if (currentUser == null) {
            JOptionPane.showMessageDialog(this, "❌ Сначала авторизуйтесь!", "Ошибка", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (cart.isEmpty()) {
            JOptionPane.showMessageDialog(this, "❌ Корзина пуста!", "Ошибка", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int result = JOptionPane.showConfirmDialog(this,
                "💳 Подтвердить покупку на сумму " + String.format("%.2f", cart.getTotalPrice()) + " руб.?\n\n" +
                        "📦 Заказ будет доставлен в течение 3-5 дней.\n" +
                        "📧 Информация отправлена на вашу почту.",
                "Оформление заказа",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (result == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(this,
                    "🎉 Покупка оформлена! Спасибо за ваш заказ, " + currentUser + "!",
                    "Успех",
                    JOptionPane.INFORMATION_MESSAGE);
            cart.clear();
            showMainScreen();
        }
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

    public static void task10(JFrame mainMenuFrame) {
        if (mainMenuFrame != null) {
            mainMenuFrame.setVisible(false);
        }

        SwingUtilities.invokeLater(() -> new Task10(mainMenuFrame).setVisible(true));
    }
}