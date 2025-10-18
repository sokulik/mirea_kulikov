package Practice5_3_to_4.Classes;
import Practice5_3_to_4.Interfaces.*;

public class Car implements Nameable, Priceable {
    private String name;
    private double price;

    public Car(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String getName() {
        return "Автомобиль: " + name;
    }

    @Override
    public double getPrice() {
        return price;
    }
}
