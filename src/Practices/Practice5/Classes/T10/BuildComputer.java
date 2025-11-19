package Practices.Practice5.Classes.T10;

import java.util.Scanner;
import java.util.function.Predicate;
import java.util.function.Function;
import java.util.List;
import java.util.ArrayList;

public class BuildComputer {
    private final Scanner scanner;
    private final ShoppingCart cart;

    public BuildComputer(ShoppingCart cart) {
        this.scanner = new Scanner(System.in);
        this.cart = cart;
    }

    public Computer buildComputer() {
        System.out.println("=== 🛠️ Сборка компьютера ===");

        Monitor monitor = selectMonitor();
        Processor processor = selectProcessor();
        SSD ssd = selectSSD();

        Computer computer = new Computer(monitor, processor, ssd);

        // Создаем продукт и добавляем в корзину
        Product computerProduct = new ComputerProduct(computer);
        cart.addItem(computerProduct);

        System.out.println("✅ Компьютер успешно собран и добавлен в корзину!");
        System.out.println("💰 Общая стоимость: " + computer.getPriceFormatted());

        return computer;
    }

    private Monitor selectMonitor() {
        System.out.println("\n--- 🖥️ Выбор монитора ---");

        M_Brand selectedBrand = selectEnum("бренд монитора", M_Brand.values());
        M_name selectedModel = selectFilteredEnum("модель", M_name.values(),
                model -> model.getBrand() == selectedBrand,
                model -> String.format("%s %s %s - %d руб.",
                        model.getName(), model.getSize(), model.getRefreshRate(), model.getPrice()));

        return new Monitor(selectedBrand, selectedModel);
    }

    private Processor selectProcessor() {
        System.out.println("\n--- ⚡ Выбор процессора ---");

        Brand selectedBrand = selectEnum("бренд процессора", Brand.values());
        Generation selectedGeneration = selectFilteredEnum("поколение", Generation.values(),
                gen -> gen.getBrand() == selectedBrand,
                Generation::toString);

        Model selectedModel = selectFilteredEnum("модель", Model.values(),
                model -> model.getGeneration() == selectedGeneration,
                model -> model.toString() + " - " + model.getPrice() + " руб.");

        return new Processor(selectedBrand, selectedGeneration, selectedModel);
    }

    private <T extends Enum<T>> T selectEnum(String typeName, T[] values) {
        System.out.println("Доступные " + typeName + ":");
        for (int i = 0; i < values.length; i++) {
            System.out.println((i + 1) + ". " + values[i]);
        }
        System.out.print("Выберите " + typeName + " (1-" + values.length + "): ");
        int choice = getValidInput(1, values.length);
        return values[choice - 1];
    }

    private <T> T selectFilteredEnum(String typeName, T[] values,
                                     Predicate<T> filter,
                                     Function<T, String> formatter) {
        System.out.println("\nДоступные " + typeName + ":");

        List<T> filteredList = new ArrayList<>();
        for (T item : values) {
            if (filter.test(item)) {
                filteredList.add(item);
            }
        }

        for (int i = 0; i < filteredList.size(); i++) {
            System.out.println((i + 1) + ". " + formatter.apply(filteredList.get(i)));
        }

        System.out.print("Выберите " + typeName + " (1-" + filteredList.size() + "): ");
        int choice = getValidInput(1, filteredList.size());
        return filteredList.get(choice - 1);
    }

    public SSD selectSSD() {
        System.out.println("\n--- 💾 Выбор SSD накопителя ---");
        SSD[] ssds = SSD.values();
        for (int i = 0; i < ssds.length; i++) {
            System.out.println((i + 1) + ". " + ssds[i].getSSD());
        }
        System.out.print("Выберите SSD (1-" + ssds.length + "): ");
        int ssdChoice = getValidInput(1, ssds.length);
        return ssds[ssdChoice - 1];
    }

    private int getValidInput(int min, int max) {
        int input;
        while (true) {
            try {
                input = Integer.parseInt(scanner.nextLine());
                if (input >= min && input <= max) {
                    break;
                } else {
                    System.out.print("Пожалуйста, введите число от " + min + " до " + max + ": ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Пожалуйста, введите корректное число: ");
            }
        }
        return input;
    }

    public void closeScanner() {
        if (scanner != null) {
            scanner.close();
        }
    }
}