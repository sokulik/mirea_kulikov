package Practice4_4;

import Practice4_4.Enums.*;
import Practice4_4.Classes.*;

public class Task4 {
    public static void task4() {
        Processor processor = new Processor(ProcessorBrand.INTEL, 3.5, 8);
        Memory memory = new Memory(MemoryBrand.SAMSUNG, 16, "DDR4");
        Monitor monitor = new Monitor(MonitorBrand.DELL, 27.0, "1920x1080");
        Computer computer = new Computer(ComputerBrand.LENOVO, processor, memory, monitor);

        computer.displaySpecifications();
    }
}
