package Practice4_3;

import Practice4_3.Classes.*;
import Practice4_3.Enums.*;
import java.util.*;

public class Task3 {
    private Scanner scanner;
    private ShoppingCart cart;
    private Map<String, List<Product>> catalog;
    private String currentUser;

    public Task3() {
        scanner = new Scanner(System.in);
        cart = new ShoppingCart();
        initializeCatalog();
        currentUser = null;
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

    public void run() {
        System.out.println("=== Добро пожаловать в Интернет магазин! ===");

        while (true) {
            if (currentUser == null) {
                showAuthMenu();
            } else {
                showMainMenu();
            }
        }
    }

    private void showAuthMenu() {
        System.out.println("\n=== Авторизация пользователя ===");
        System.out.println("1. Вход");
        System.out.println("2. Регистрация");
        System.out.println("3. Выход");
        System.out.println("Выберите действие: ");

        int choice = getIntInput();
        switch (choice) {
            case 1:
                login();
                break;
            case 2:
                register();
                break;
            case 3:
                System.out.println("Заходите к нам еще!");
                System.exit(0);
                break;
            default:
                System.out.println("Неверный выбор!");
        }
    }

    private void showMainMenu() {
        System.out.println("\n=== Главное меню ===");
        System.out.println("Текущий пользователь: " + currentUser);
        System.out.println("1. Каталог товаров");
        System.out.println("2. Корзина");
        System.out.println("3. Оформить заказ");
        System.out.println("4. Выход из аккаунта");
        System.out.println("Выберите действие: ");

        int choice = getIntInput();

        switch (choice) {
            case 1:
                showCategories();
                break;
            case 2:
                showCart();
                break;
            case 3:
                checkout();
                break;
            case 4:
                currentUser = null;
                System.out.println("Вы вышли из акккаунта :(");
                break;
            default:
                System.out.println("Неверный выбор");
        }
    }

    private void login() {
        System.out.println("Введите логин: ");
        String username = scanner.nextLine();
        System.out.println("Введите пароль: ");
        String password = scanner.nextLine();

        if (UserManager.validateUser(username, password)) {
            currentUser = username;
            System.out.println("Вход успешно выполнен!");
        } else {
            System.out.println("Неверный логин или пароль. повторите попытку или зарегистрируйтесь!");
        }
    }

    private void register() {
        System.out.println("Придумайте логин: ");
        String username = scanner.nextLine();
        if (UserManager.userExist(username)) {
            System.out.println("Пользователь с таким именем уже существует.");
            return;
        }

        System.out.println("Введите пароль: ");
        String password = scanner.nextLine();

        User newUser = new User(username, password);
        UserManager.saveUser(newUser);

        System.out.println("Регистрация прошла успешно! Теперь войдите в свой аккаунт!");
    }

    private void showCategories() {
        System.out.println("\n=== Каталоги товаров ===");
        List<String> categories = new ArrayList<>(catalog.keySet());

        for (int i = 0; i < categories.size(); i++) {
            System.out.println((i + 1) + ". " + categories.get(i));
        }
        System.out.println("Выберите каталог или введите 0 для возврата: ");
        int choice = getIntInput();

        if (choice > 0 && choice <= categories.size()) {
            String selectedCategory = categories.get(choice - 1);
            showProducts(selectedCategory);
        }
    }

    private void showProducts(String category) {
      while (true) {
          System.out.println("===Товары в категории: " + category + " ===");
          List<Product> products = catalog.get(category);

          for (int i = 0; i < products.size(); i++) {
              System.out.println((i + 1) + ". " + products.get(i));
          }
          System.out.println("Выберите товар для добавления в корзину (0 - выход): ");
          int choice = getIntInput();
          if (choice == 0){
              break;
          }
          else if (choice > 0 && choice <= products.size()) {
              Product selectedProduct = products.get(choice - 1);
              cart.addItem(selectedProduct);
              System.out.println("Товар: '" + selectedProduct.getName() + "' добавлен в корзину");
          }
      }
    }

    private void showCart(){

          if (!cart.getItems().isEmpty()) {
            while (true) {
                System.out.println("\n" + cart.toString());
                System.out.println("Хотите удалить товар из корзины? (да/нет)");
                String answer = scanner.nextLine();

                if (answer.equalsIgnoreCase("да")) {
                    System.out.println("Введите номер товара для удаления: ");
                    int itemNumber = getIntInput();
                    if (itemNumber > 0 && itemNumber <= cart.getItems().size()) {
                        Product productToRemove = cart.getItems().get(itemNumber - 1);
                        cart.removeItem(productToRemove);
                        System.out.println("Товар: '" + productToRemove + "' удален из корзины!");

                    } else {
                        System.out.println("неверный номер товара!");
                    }
                } else {
                    break;
                }


            }
          }
    }

    private void checkout(){
        if (cart.getItems().isEmpty()){
            System.out.println("Корзина пуста!");
            return;
        }
        System.out.println("\n=== оформление покупаки ===");
        System.out.println(cart.toString());
        System.out.println("Подтвердить покупку? (да/нет)");

        String answer = scanner.nextLine();
        if (answer.equalsIgnoreCase("да")){
            System.out.println("Покупка оформлена! Спасибо за ваш заказ!");
            cart.clear();
        }
        else {
            System.out.println("Покупка отменена :(");
        }
    }

    private int getIntInput(){
        while (true){
            try {
                return Integer.parseInt(scanner.nextLine());
            }
            catch (NumberFormatException e ){
                System.out.println("Пожалуйства, введите число: ");
            }
        }
    }

    public static void task3() {
        Task3 store = new Task3();
        store.run();


    }




}
