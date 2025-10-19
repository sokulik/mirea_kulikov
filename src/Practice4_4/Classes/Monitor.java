package Practice4_4.Classes;

import Practice4_4.Enums.*;

public class Monitor {
    private MonitorBrand brand;
    private double sizeInches;
    private String resolution;

    public Monitor(MonitorBrand brand, double sizeInches, String resolution) {
        this.brand = brand;
        this.sizeInches = sizeInches;
        this.resolution = resolution;
    }

    public String getInfo() {
        return String.format("Монитор: %s, Диагональ: %.1f\", Разрешение: %s",
                brand, sizeInches, resolution);
    }
}