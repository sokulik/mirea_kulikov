package Practice5_10;

import Practice5_10.Classes.*;
import Practice5_10.Enums.*;
import java.util.*;

public class Task10 {
    private final Scanner scanner;
    private final ShoppingCart cart;
    private final Map<String, List<Product>> catalog;
    private final BuildComputer buildComputer;
    private String currentUser;

    public Task10() {
        scanner = new Scanner(System.in);
        cart = new ShoppingCart();
        buildComputer = new BuildComputer(cart);
        catalog = new LinkedHashMap<>();
        currentUser = null;
        initializeCatalog();
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

        //  Свечи и ароматы
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

    public void run() {
        System.out.println("🎉 Добро пожаловать в Интернет-магазин!");
        System.out.println("=====================================");

        while (true) {
            if (currentUser == null) {
                showAuthMenu();
            } else {
                showMainMenu();
            }
        }
    }

    private void showAuthMenu() {
        System.out.println("\n=== 🔐 АВТОРИЗАЦИЯ ===");
        System.out.println("1. 🔑 Вход в аккаунт");
        System.out.println("2. 📝 Регистрация");
        System.out.println("3. 🚪 Выход из магазина");
        System.out.print("Выберите действие: ");

        int choice = getIntInput();
        switch (choice) {
            case 1:
                login();
                break;
            case 2:
                register();
                break;
            case 3:
                exitStore();
                break;
            default:
                System.out.println("❌ Неверный выбор! Попробуйте снова.");
        }
    }

    private void showMainMenu() {
        System.out.println("\n=== 🏠 ГЛАВНОЕ МЕНЮ ===");
        System.out.println("👤 Пользователь: " + currentUser);
        System.out.println("🛒 Товаров в корзине: " + cart.getItemCount());
        System.out.println("💰 Сумма корзины: " + String.format("%.2f", cart.getTotalPrice()) + " руб.");
        System.out.println("1. 📂 Каталог товаров");
        System.out.println("2. 🛒 Просмотр корзины");
        System.out.println("3. 🔧 Собрать компьютер");
        System.out.println("4. 💳 Оформить заказ");
        System.out.println("5. 🚪 Выход из аккаунта");
        System.out.print("Выберите действие: ");

        int choice = getIntInput();

        switch (choice) {
            case 1:
                showCategories();
                break;
            case 2:
                showCart();
                break;
            case 3:
                buildComputer.buildComputer();
                break;
            case 4:
                checkout();
                break;
            case 5:
                logout();
                break;
            default:
                System.out.println("❌ Неверный выбор!");
        }
    }

    private void login() {
        System.out.print("Введите логин: ");
        String username = scanner.nextLine();
        System.out.print("Введите пароль: ");
        String password = scanner.nextLine();

        if (UserManager.validateUser(username, password)) {
            currentUser = username;
            System.out.println("✅ Вход успешно выполнен! Добро пожаловать, " + username + "!");
        } else {
            System.out.println("❌ Неверный логин или пароль.");
        }
    }

    private void register() {
        System.out.print("Придумайте логин: ");
        String username = scanner.nextLine();

        if (UserManager.userExists(username)) {
            System.out.println("❌ Пользователь с таким именем уже существует.");
            return;
        }

        System.out.print("Введите пароль: ");
        String password = scanner.nextLine();

        if (password.length() < 3) {
            System.out.println("❌ Пароль должен содержать минимум 3 символа.");
            return;
        }

        User newUser = new User(username, password);
        UserManager.saveUser(newUser);
    }

    private void showCategories() {
        System.out.println("\n=== 📂 КАТАЛОГ ТОВАРОВ ===");
        List<String> categories = new ArrayList<>(catalog.keySet());

        for (int i = 0; i < categories.size(); i++) {
            System.out.println((i + 1) + ". " + categories.get(i));
        }

        System.out.println("0. ↩️ Назад в главное меню");
        System.out.print("Выберите категорию: ");

        int choice = getIntInput();
        if (choice == 0) return;

        if (choice > 0 && choice <= categories.size()) {
            String selectedCategory = categories.get(choice - 1);
            showProducts(selectedCategory);
        } else {
            System.out.println("❌ Неверный номер категории.");
        }
    }

    private void showProducts(String category) {
        while (true) {
            System.out.println("\n=== 📦 " + category.toUpperCase() + " ===");
            List<Product> products = catalog.get(category);

            for (int i = 0; i < products.size(); i++) {
                System.out.println((i + 1) + ". " + products.get(i).display());
            }

            System.out.println("\n0. ↩️ Назад к категориям");
            System.out.print("Выберите товар для добавления в корзину: ");

            int choice = getIntInput();
            if (choice == 0) break;

            if (choice > 0 && choice <= products.size()) {
                Product selectedProduct = products.get(choice - 1);

                if (selectedProduct.getName().equals("Собрать компьютер")) {
                    buildComputer.buildComputer();
                } else {
                    cart.addItem(selectedProduct);
                }
            } else {
                System.out.println("❌ Неверный номер товара.");
            }
        }
    }

    private void showCart() {
        if (cart.isEmpty()) {
            System.out.println("🛒 Корзина пуста.");
            return;
        }

        while (true) {
            System.out.println("\n" + cart.toString());
            System.out.println("\n1. 🗑️ Удалить товар");
            System.out.println("2. 💳 Оформить заказ");
            System.out.println("0. ↩️ Назад");
            System.out.print("Выберите действие: ");

            int choice = getIntInput();
            switch (choice) {
                case 1:
                    removeFromCart();
                    break;
                case 2:
                    checkout();
                    return;
                case 0:
                    return;
                default:
                    System.out.println("❌ Неверный выбор.");
            }
        }
    }

    private void removeFromCart() {
        System.out.print("Введите номер товара для удаления: ");
        int itemNumber = getIntInput();

        if (itemNumber > 0 && itemNumber <= cart.getItems().size()) {
            cart.removeItem(itemNumber - 1);
        } else {
            System.out.println("❌ Неверный номер товара.");
        }
    }

    private void checkout() {
        if (cart.isEmpty()) {
            System.out.println("❌ Корзина пуста! Добавьте товары перед оформлением заказа.");
            return;
        }

        System.out.println("\n=== 💳 ОФОРМЛЕНИЕ ЗАКАЗА ===");
        System.out.println(cart.toString());
        System.out.println("\n✅ Подтвердить покупку? (да/нет): ");

        String answer = scanner.nextLine();
        if (answer.equalsIgnoreCase("да")) {
            System.out.println("🎉 Покупка оформлена! Спасибо за ваш заказ!");
            System.out.println("📦 Заказ будет доставлен в течение 3-5 дней.");
            System.out.println("📧 Информация о заказе отправлена на вашу почту.");
            cart.clear();
        } else {
            System.out.println("❌ Покупка отменена.");
        }
    }

    private void logout() {
        System.out.println("👋 До свидания, " + currentUser + "! Ждем вас снова!");
        currentUser = null;
    }

    private void exitStore() {
        System.out.println("🛍️ Спасибо за посещение нашего магазина! До новых встреч!");
    }

    private int getIntInput() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("❌ Пожалуйста, введите число: ");
            }
        }
    }

    public static void task10() {
        Task10 store = new Task10();
        store.run();
    }
}