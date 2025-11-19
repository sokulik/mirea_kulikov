package Practices.Practice5.Classes.T34;

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
