package Practice4_4.Classes;

import Practice4_4.Enums.*;

public class Memory {
    private MemoryBrand brand;
    private int sizeGB;
    private String type;

    public Memory(MemoryBrand brand, int sizeGB, String type) {
        this.brand = brand;
        this.sizeGB = sizeGB;
        this.type = type;
    }

    public String getInfo() {
        return String.format("Память: %s, Размер: %d GB, Тип: %s",
                brand, sizeGB, type);
    }
}