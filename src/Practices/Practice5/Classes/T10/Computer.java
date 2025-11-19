package Practices.Practice5.Classes.T10;

public class Computer implements Priceable {
    private Monitor monitor;
    private SSD ssd;
    private Processor processor;
    private double price;

    public Computer(Monitor monitor, Processor processor, SSD ssd) {
        this.monitor = monitor;
        this.ssd = ssd;
        this.processor = processor;
        this.price = calculateTotalPrice();
    }

    private double calculateTotalPrice() {
        return monitor.getPrice() + processor.getPrice() + ssd.getPrice();
    }

    // Геттеры
    public Monitor getMonitor() { return monitor; }
    public SSD getSsd() { return ssd; }
    public Processor getProcessor() { return processor; }

    @Override
    public double getPrice() { return price; } // реализуем метод интерфейса

    @Override
    public String toString() {
        return "💻 Собранный компьютер:\n" +
                "  🖥️  " + monitor + "\n" +
                "  ⚡ " + processor + "\n" +
                "  💾 " + ssd.getSSD() + "\n" +
                "  💰 Общая стоимость: " + getPriceFormatted();
    }
}