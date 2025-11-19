package Practices.Practice5.Classes.T10;

public class Processor implements Priceable {
    private Brand brand;
    private Generation generation;
    private Model model;

    public Processor(Brand brand, Generation generation, Model model) {
        this.model = model;
        this.brand = brand;
        this.generation = generation;
    }

    // Геттеры
    public Brand getBrand() { return brand; }
    public Generation getGeneration() { return generation; }
    public Model getModel() { return model; }

    @Override
    public double getPrice() { // реализуем метод интерфейса
        return model.getPrice();
    }

    @Override
    public String toString() {
        return "Процессор " + brand + " " + generation + " " +
                model.getModel() + " - " + getPriceFormatted();
    }
}