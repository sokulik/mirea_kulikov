package Practice5_3_to_4;

import Practice5_3_to_4.Interfaces.*;
import Practice5_3_to_4.Classes.*;
public class Task3_4 {
    public static void task3_4() {
        // Проверка Nameable
        Nameable earth = new Planet("Земля");
        Nameable lion = new Animal("Лев");
        Nameable tesla = new Car("Tesla Model S", 75000.0);

        System.out.println(earth.getName());
        System.out.println(lion.getName());
        System.out.println(tesla.getName());

        // Проверка Priceable
        Priceable car = new Car("Tesla Model S", 75000.0);
        Priceable laptop = new Product("Ноутбук", 1500.0);

        System.out.println("Цена автомобиля: $" + car.getPrice());
        System.out.println("Цена товара: $" + laptop.getPrice());
    }
}
