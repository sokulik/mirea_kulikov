package Practices.Practice5;

import DOP.BaseTaskFrame;
import Practices.Practice5.Classes.T34.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Task3_4 extends BaseTaskFrame {
    private JTextArea resultArea;

    public Task3_4(JFrame mainMenuFrame) {
        super(mainMenuFrame,"Practice 5.3-4: Интерфейсы Nameable и Priceable");

        initComponents();
        layoutComponents();
        addListeners();
        demonstrateInterfaces();
    }

    private void initComponents() {
        resultArea = new JTextArea(20, 60);
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Consolas", Font.PLAIN, 12));
        resultArea.setBackground(new Color(248, 249, 250));
    }

    private void layoutComponents() {
        setLayout(new BorderLayout(10, 10));

        // Верхняя панель с кнопками
        JPanel controlPanel = createControlPanel();
        add(controlPanel, BorderLayout.NORTH);

        // Центральная панель с результатами
        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Демонстрация интерфейсов"));
        add(scrollPane, BorderLayout.CENTER);

        // Панель снизу с кнопкой назад
        JPanel bottomPanel = createBottomPanel();
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private JPanel createControlPanel() {
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        controlPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        controlPanel.setBackground(new Color(240, 240, 240));

        JButton demoButton = new JButton("🎭 Демонстрация");
        demoButton.setBackground(new Color(70, 130, 180));
        demoButton.setForeground(Color.WHITE);
        demoButton.addActionListener(e -> demonstrateInterfaces());

        JButton nameableButton = new JButton("🏷️ Nameable");
        nameableButton.setBackground(new Color(40, 167, 69));
        nameableButton.setForeground(Color.WHITE);
        nameableButton.addActionListener(e -> demonstrateNameable());

        JButton priceableButton = new JButton("💰 Priceable");
        priceableButton.setBackground(new Color(255, 193, 7));
        priceableButton.setForeground(Color.BLACK);
        priceableButton.addActionListener(e -> demonstratePriceable());

        JButton customButton = new JButton("➕ Создать объекты");
        customButton.setBackground(new Color(108, 117, 125));
        customButton.setForeground(Color.WHITE);
        customButton.addActionListener(e -> createCustomObjects());

        controlPanel.add(demoButton);
        controlPanel.add(nameableButton);
        controlPanel.add(priceableButton);
        controlPanel.add(customButton);

        return controlPanel;
    }

    private void demonstrateInterfaces() {
        StringBuilder output = new StringBuilder();
        output.append("=== Демонстрация интерфейсов Nameable и Priceable ===\n\n");

        // Проверка Nameable
        output.append("🏷️ Интерфейс Nameable:\n");
        output.append("─".repeat(40)).append("\n");

        Nameable earth = new Planet("Земля");
        Nameable lion = new Animal("Лев");
        Nameable tesla = new Car("Tesla Model S", 75000.0);

        output.append(earth.getName()).append("\n");
        output.append(lion.getName()).append("\n");
        output.append(tesla.getName()).append("\n\n");

        // Проверка Priceable
        output.append("💰 Интерфейс Priceable:\n");
        output.append("─".repeat(40)).append("\n");

        Priceable car = new Car("Tesla Model S", 75000.0);
        Priceable laptop = new Product("Ноутбук", 1500.0);

        output.append("Цена автомобиля: $").append(car.getPrice()).append("\n");
        output.append("Цена товара: $").append(laptop.getPrice()).append("\n\n");

        // Дополнительная информация
        output.append("💡 Информация о классах:\n");
        output.append("─".repeat(40)).append("\n");
        output.append("• Planet - реализует Nameable\n");
        output.append("• Animal - реализует Nameable\n");
        output.append("• Car - реализует Nameable и Priceable\n");
        output.append("• Product - реализует Priceable\n");

        resultArea.setText(output.toString());
    }

    private void demonstrateNameable() {
        StringBuilder output = new StringBuilder();
        output.append("=== Интерфейс Nameable ===\n\n");
        output.append("Классы, реализующие Nameable:\n");
        output.append("─".repeat(40)).append("\n\n");

        Nameable[] nameables = {
                new Planet("Земля"),
                new Planet("Марс"),
                new Planet("Юпитер"),
                new Animal("Лев"),
                new Animal("Слон"),
                new Animal("Орел"),
                new Car("Tesla Model S", 75000.0),
                new Car("BMW X5", 60000.0),
                new Car("Toyota Camry", 30000.0)
        };

        for (Nameable nameable : nameables) {
            output.append("• ").append(nameable.getName()).append("\n");
        }

        output.append("\n💡 Все объекты имеют метод getName()\n");

        resultArea.setText(output.toString());
    }

    private void demonstratePriceable() {
        StringBuilder output = new StringBuilder();
        output.append("=== Интерфейс Priceable ===\n\n");
        output.append("Классы, реализующие Priceable:\n");
        output.append("─".repeat(40)).append("\n\n");

        Priceable[] priceables = {
                new Car("Tesla Model S", 75000.0),
                new Car("BMW X5", 60000.0),
                new Car("Toyota Camry", 30000.0),
                new Product("Ноутбук", 1500.0),
                new Product("Смартфон", 800.0),
                new Product("Наушники", 200.0)
        };

        double totalPrice = 0;
        for (Priceable priceable : priceables) {
            output.append("• ").append(getPriceableDescription(priceable))
                    .append(": $").append(priceable.getPrice()).append("\n");
            totalPrice += priceable.getPrice();
        }

        output.append("\n💰 Общая стоимость всех товаров: $").append(totalPrice).append("\n");
        output.append("💡 Все объекты имеют метод getPrice()\n");

        resultArea.setText(output.toString());
    }

    private String getPriceableDescription(Priceable priceable) {
        if (priceable instanceof Car) {
            return ((Car) priceable).getName().replace("Автомобиль: ", "");
        } else if (priceable instanceof Product) {
            // Для Product нужно получить название
            return "Товар"; // Можно добавить поле title в Product
        }
        return "Объект";
    }

    private void createCustomObjects() {
        JDialog customDialog = new JDialog(this, "Создание объектов", true);
        customDialog.setLayout(new GridLayout(5, 2, 10, 10));
        customDialog.setSize(400, 300);
        customDialog.setLocationRelativeTo(this);

        JComboBox<String> typeCombo = new JComboBox<>(new String[]{"Planet", "Animal", "Car", "Product"});
        JTextField nameField = new JTextField();
        JTextField priceField = new JTextField();
        JLabel priceLabel = new JLabel("Цена:");

        customDialog.add(new JLabel("Тип объекта:"));
        customDialog.add(typeCombo);
        customDialog.add(new JLabel("Название:"));
        customDialog.add(nameField);
        customDialog.add(new JLabel(""));
        customDialog.add(new JLabel(""));
        customDialog.add(priceLabel);
        customDialog.add(priceField);

        // Скрываем поле цены для объектов без Priceable
        typeCombo.addActionListener(e -> {
            String selectedType = (String) typeCombo.getSelectedItem();
            boolean showPrice = "Car".equals(selectedType) || "Product".equals(selectedType);
            priceLabel.setVisible(showPrice);
            priceField.setVisible(showPrice);
        });

        JButton createButton = new JButton("Создать");
        createButton.setBackground(new Color(70, 130, 180));
        createButton.setForeground(Color.WHITE);
        createButton.addActionListener(e -> {
            String type = (String) typeCombo.getSelectedItem();
            String name = nameField.getText().trim();

            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(customDialog, "Введите название", "Ошибка", JOptionPane.ERROR_MESSAGE);
                return;
            }

            StringBuilder output = new StringBuilder();
            output.append("=== Созданные объекты ===\n\n");

            switch (type) {
                case "Planet":
                    Planet planet = new Planet(name);
                    output.append("Создана планета:\n").append(planet.getName()).append("\n");
                    break;
                case "Animal":
                    Animal animal = new Animal(name);
                    output.append("Создано животное:\n").append(animal.getName()).append("\n");
                    break;
                case "Car":
                    try {
                        double price = Double.parseDouble(priceField.getText());
                        Car car = new Car(name, price);
                        output.append("Создан автомобиль:\n").append(car.getName()).append("\n");
                        output.append("Цена: $").append(car.getPrice()).append("\n");
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(customDialog, "Введите корректную цену", "Ошибка", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    break;
                case "Product":
                    try {
                        double price = Double.parseDouble(priceField.getText());
                        Product product = new Product(name, price);
                        output.append("Создан товар:\n").append(name).append("\n");
                        output.append("Цена: $").append(product.getPrice()).append("\n");
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(customDialog, "Введите корректную цену", "Ошибка", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    break;
            }

            resultArea.setText(output.toString());
            customDialog.dispose();
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(createButton);
        customDialog.add(new JLabel(""));
        customDialog.add(buttonPanel);

        // Инициализация видимости
        priceLabel.setVisible(false);
        priceField.setVisible(false);

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

        JLabel infoLabel = new JLabel("Интерфейсы: Nameable, Priceable | Классы: Planet, Animal, Car, Product");
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

    public static void task3_4(JFrame mainMenuFrame) {
        if (mainMenuFrame != null) {
            mainMenuFrame.setVisible(false);
        }

        SwingUtilities.invokeLater(() -> new Task3_4(mainMenuFrame).setVisible(true));
    }
}