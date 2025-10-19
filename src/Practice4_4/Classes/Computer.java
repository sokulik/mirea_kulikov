package Practice4_4.Classes;

import Practice4_4.Enums.*;

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
        System.out.println("\nМарка компьютера: " + brand);
        System.out.println(processor.getInfo());
        System.out.println(memory.getInfo());
        System.out.println(monitor.getInfo());
    }
}
