package Practices.Practice4.Classes;

public class Processor {
    private ProcessorBrand brand;
    private double speedGHz;
    private int cores;

    public Processor(ProcessorBrand brand, double speedGHz, int cores) {
        this.brand = brand;
        this.speedGHz = speedGHz;
        this.cores = cores;
    }

    public String getInfo() {
        return String.format("Процессор: %s, Тактовая частота: %.2f GHz, Ядра: %d",
                brand, speedGHz, cores);
    }
}