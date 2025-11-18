package Practices.Practice4.Classes;

public class Computer {
    private ComputerBrand brand;
    private Processor processor;
    private Memory memory;
    private Monitor monitor;

    public Computer(ComputerBrand brand, Processor processor, Memory memory, Monitor monitor) {
        this.brand = brand;
        this.processor = processor;
        this.memory = memory;
        this.monitor = monitor;
    }

    public void displaySpecifications() {
        // Оригинальный метод для консоли
        System.out.println("\nМарка компьютера: " + brand);
        System.out.println(processor.getInfo());
        System.out.println(memory.getInfo());
        System.out.println(monitor.getInfo());
    }

    public String getSpecifications() {
        // Новый метод для GUI
        StringBuilder sb = new StringBuilder();
        sb.append("Марка компьютера: ").append(brand).append("\n");
        sb.append(processor.getInfo()).append("\n");
        sb.append(memory.getInfo()).append("\n");
        sb.append(monitor.getInfo());
        return sb.toString();
    }
}